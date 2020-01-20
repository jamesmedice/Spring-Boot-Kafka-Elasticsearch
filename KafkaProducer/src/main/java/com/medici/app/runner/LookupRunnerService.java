package com.medici.app.runner;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.medici.app.kafka.model.StackHolder;
import com.medici.app.kafka.producer.Sender;
import com.medici.app.model.KafkaProducerLog;
import com.medici.app.service.ElasticsearchBrokerService;

@Service
public class LookupRunnerService {

	@Autowired
	private Sender sender;

	@Autowired
	private ElasticsearchBrokerService service;

	@Async("threadPoolTaskExecutor")
	public CompletableFuture<StackHolder> send(StackHolder model) {
		try {

			sender.send(model);
			Thread.sleep(10L);
			send2Elasticsearch(model, model.toString(), java.util.logging.Level.INFO);

			return CompletableFuture.completedFuture(model);

		} catch (InterruptedException e) {

			send2Elasticsearch(model, model.toString(), java.util.logging.Level.WARNING);
			return null;

		}
	}

	private void send2Elasticsearch(StackHolder model, String message, Level level) {
		KafkaProducerLog logModel = new KafkaProducerLog(model.getId(), message, level.toString());
		service.save(logModel);
	}

}
