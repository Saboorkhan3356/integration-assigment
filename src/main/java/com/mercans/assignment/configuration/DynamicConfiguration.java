package com.mercans.assignment.configuration;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicConfiguration {

	private String fileNamePattern;
	private Map<String, Map<String, String>> mappings;
	private List<FieldConfiguration> fields;

}
