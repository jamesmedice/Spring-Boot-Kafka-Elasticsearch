package com.medici.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medici.app.model.KafkaProducerLog;
import com.medici.app.repository.LoggerRepository;

@Service
public class ElasticsearchBrokerService {

	@Autowired
	private LoggerRepository loggerRepository;

	public void deleteAll() {
		loggerRepository.deleteAll();
	}

	public void deleteById(String id) {
		loggerRepository.deleteById(id);
	}

	public KafkaProducerLog save(KafkaProducerLog model) {
		return loggerRepository.save(model);
	}

	public Optional<KafkaProducerLog> findById(String modelId) {
		return loggerRepository.findById(modelId);
	}

	public Iterable<KafkaProducerLog> findAll() {
		return loggerRepository.findAll();
	}

	public Iterable<KafkaProducerLog> findAll(Sort sort) {
		return loggerRepository.findAll(sort);
	}

	public Iterable<KafkaProducerLog> findAll(Pageable page) {
		return loggerRepository.findAll(page);
	}

}
