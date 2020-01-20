package com.medici.app.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface BaseReceiver<K, V> {

	public void receive(ConsumerRecord<K, V> recored);

	public static final String RECEIVED = "received {} : '{}'";

	public static final String OBJECT_NOT_FOUND = "object not found";
}
