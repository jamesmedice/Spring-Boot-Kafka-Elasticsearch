package com.medici.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "stack", type = "logging")
public class KafkaProducerLog {

	@Id
	private String id;
	private String message;
	private String level;
}
