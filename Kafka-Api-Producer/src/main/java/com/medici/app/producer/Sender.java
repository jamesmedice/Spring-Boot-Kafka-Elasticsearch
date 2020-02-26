package com.medici.app.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.medici.app.model.Company;
import com.medici.app.model.KafkaPrimeModel;

@Service
public class Sender implements BaseSender<Company> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Value("${kafka.topic.producer}")
	private String jsonTopic;

	@Value("${kafka.topic.consumer}")
	private String jsonTopicReply;

	@Autowired
	private ReplyingKafkaTemplate<String, KafkaPrimeModel, KafkaPrimeModel> replyKafkaTemplate;

	@Override
	public Company send(Company message) {
		try {

			replyKafkaTemplate.start();

			LOGGER.info(SENT_OBJECT, message.getClass(), message.toString());
			ProducerRecord<String, KafkaPrimeModel> record = new ProducerRecord<String, KafkaPrimeModel>(jsonTopic, message);

			record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, jsonTopicReply.getBytes()));
			RequestReplyFuture<String, KafkaPrimeModel, KafkaPrimeModel> receivedModel = replyKafkaTemplate.sendAndReceive(record);

			SendResult<String, KafkaPrimeModel> result = receivedModel.getSendFuture().get(10, TimeUnit.SECONDS);
			LOGGER.info(EXPECTED_OBJECT, result.getProducerRecord().value().toString());

			ConsumerRecord<String, KafkaPrimeModel> consumerRecord = receivedModel.get(20, TimeUnit.SECONDS);
			LOGGER.info(RECEIVED_OBJECT, result.getProducerRecord().value().toString());
			return (Company) consumerRecord.value();

		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		} catch (ExecutionException e) {
			LOGGER.error(e.getMessage());
		} catch (TimeoutException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally

		{
			replyKafkaTemplate.stop();
		}

		return null;

	}

}
