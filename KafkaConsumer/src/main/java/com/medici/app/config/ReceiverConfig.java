package com.medici.app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.medici.app.kafka.model.KafkaPrimeModel;

@Configuration
@EnableKafka
public class ReceiverConfig {

	private static final String READ_COMMITTED = "read_committed";

	private static final String LATEST = "latest";

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${kafka.group}")
	private String camelGroup;

	@Bean("consumerConfigs")
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, LATEST);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, READ_COMMITTED);
	    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);

		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 6000);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, camelGroup);

		return props;
	}

	@Bean
	public ConsumerFactory<String, KafkaPrimeModel> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(KafkaPrimeModel.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaPrimeModel> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaPrimeModel> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
		factory.getContainerProperties().setSyncCommits(true);
		return factory;
	}

}
