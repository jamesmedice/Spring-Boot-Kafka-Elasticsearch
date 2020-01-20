package com.medici.app.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.medici.app.kafka.model.Director;

@Component
public class Receiver implements BaseReceiver<String, Director> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	@KafkaListener(topics = "${kafka.topic.json.director}")
	@Override
	public void receive(ConsumerRecord<String, Director> record, Consumer<String, Director> consumer) {

		LOGGER.info(RECEIVED_KEY, record.key(), record.offset(), record.partition(), record.topic(), record.value().toString());
		commitSyncOffset(record, consumer);
	}

	public void commitSyncOffset(ConsumerRecord<String, Director> record, Consumer<String, Director> consumer) {
		Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
		offsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1));

		consumer.commitSync(offsets);
		
		// ONCE YOU WANNA USE ASYNC
//		consumer.commitAsync(offsets, new OffsetCommitCallback() {
//			@Override
//			public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
//				if (exception != null) {
//					LOGGER.error("kafka offset commit fail: {}", exception);
//				}
//			}
//		});
	}

}
