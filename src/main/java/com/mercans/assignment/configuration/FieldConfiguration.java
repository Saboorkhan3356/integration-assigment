package com.mercans.assignment.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldConfiguration {

	private FieldType fieldType;
	private String sourceField;
	private DataType dataType;
	private Boolean isMandatory;
	private String mappingKey;
	private String targetEntity;
	private String targetField;
	private String dateFormat;
	private String validationPattern;
	private Integer regexCaptureGroupNr;

}
