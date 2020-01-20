package com.medici.app.kafka.consumer;

import java.util.Calendar;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.medici.app.kafka.model.Director;
import com.medici.app.kafka.model.StackHolder;
import com.medici.app.kafka.producer.Sender;

@Component
public class Receiver implements BaseReceiver<String, StackHolder> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@Autowired
	private Sender sender;

	@KafkaListener(topics = { "${kafka.topic.json.stackHolder}" })
	@Override
	public void receive(ConsumerRecord<String, StackHolder> record) {
		StackHolder entity = record.value();
		LOGGER.info(RECEIVED, entity.getClass(), entity.toString());

		Director message = new Director(entity.getId(), entity.getAge(), entity.getLevel(), entity.getExperience(), entity.getDomain(), Calendar.getInstance().getTime());
		sender.send(message);
	}
}
