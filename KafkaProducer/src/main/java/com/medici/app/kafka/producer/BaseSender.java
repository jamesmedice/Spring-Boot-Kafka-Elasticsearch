package com.medici.app.kafka.producer;

public interface BaseSender<T> {

	public void send(T entity);

	public static final String SENT_OBJECT = "sending {}='{}'";

}
