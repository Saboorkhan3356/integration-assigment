package com.mercans.assignment.configuration;

public enum FieldType {

	Regular("Regular"), ActionCode("ActionCode"), EmployeeCode("EmployeeCode");

	private final String value;

	FieldType(final String fieldType) {
		value = fieldType;
	}

	public String getValue() {
		return value;
	}
}
