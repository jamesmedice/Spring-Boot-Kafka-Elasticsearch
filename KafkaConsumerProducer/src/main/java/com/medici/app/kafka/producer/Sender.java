package com.medici.app.kafka.producer;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.medici.app.kafka.model.Director;
import com.medici.app.kafka.model.KafkaPrimeModel;

@Service
public class Sender implements BaseSender<Director> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Value("${kafka.topic.json.director}")
	private String jsonTopic;

	@Autowired
	private KafkaTemplate<String, KafkaPrimeModel> kafkaTemplate;

	@Override
	public void send(Director message) {
		LOGGER.info(SENT_OBJECT, message.getClass(), message.toString());
		kafkaTemplate.send(jsonTopic, String.valueOf(Calendar.getInstance().getTimeInMillis()), message);
	}

}
