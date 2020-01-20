package com.medici.app.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface BaseReceiver<K, V> {

	public void receive(ConsumerRecord<K, V> recored, Consumer<K, V> consumer);

	public static final String RECEIVED_KEY = "received key {}, offset {}, partition {}, topic {} , object {}";
	public static final String OBJECT_NOT_FOUND = "object not found";
}
