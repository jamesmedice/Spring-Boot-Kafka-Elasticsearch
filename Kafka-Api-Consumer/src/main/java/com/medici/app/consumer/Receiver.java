package com.medici.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.medici.app.model.Company;

@Component
public class Receiver implements BaseReceiver<String, Company> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@KafkaListener(topics = "${kafka.topic.json.company}")
	@SendTo("companyreplytopic")
	@Override
	public Company receive(ConsumerRecord<String, Company> record, @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) {
		LOGGER.info(RECEIVED_KEY, record.key(), record.offset(), record.partition(), record.topic(), record.value().toString());
		record.headers().add(KafkaHeaders.CORRELATION_ID, correlation);
		return record.value();
	}

}
