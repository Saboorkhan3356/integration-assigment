package com.mercans.assignment.configuration;

public enum DataType {

	Text("Text"), Integer("Integer"), Decimal("Decimal"), Bool("Bool"), Date("Date");

	private String type;

	DataType(String type) {
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
