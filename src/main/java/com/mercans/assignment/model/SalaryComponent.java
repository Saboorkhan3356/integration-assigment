package com.mercans.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryComponent {

	private String startDate;
	private String endDate;
	private double amount;
	private String currency;

	public SalaryComponent(String amount, String currency, String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.currency = currency;
		this.amount = Double.parseDouble(amount);
	}
}