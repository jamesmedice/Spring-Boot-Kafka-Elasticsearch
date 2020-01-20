package com.medici.app.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.medici.app.kafka.model.KafkaPrimeModel;
import com.medici.app.kafka.model.StackHolder;

@Service
public class Sender implements BaseSender<StackHolder> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Value("${kafka.topic.json.stackHolder}")
	private String jsonTopic;

	@Autowired
	private KafkaTemplate<String, KafkaPrimeModel> kafkaTemplate;

	@Override
	public void send(StackHolder message) {
		LOGGER.info(SENT_OBJECT, message.getClass(), message.toString());
		kafkaTemplate.send(jsonTopic, message);
	}

}
