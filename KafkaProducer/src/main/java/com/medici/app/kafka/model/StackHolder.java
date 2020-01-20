package com.medici.app.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StackHolder implements KafkaPrimeModel {

	private static final long serialVersionUID = 3783059116442485779L;

	private String id;
	private int age;
	private String level;
	private String experience;
	private String domain;

}
