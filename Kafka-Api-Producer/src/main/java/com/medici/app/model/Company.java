package com.medici.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company implements KafkaPrimeModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1442425481935659318L;

	private String id;

	private String serialnumber;

	private String description;

	private int branches;

	private int countries;

	private double marketvalue;
}