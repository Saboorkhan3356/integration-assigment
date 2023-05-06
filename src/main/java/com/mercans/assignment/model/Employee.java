package com.mercans.assignment.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercans.assignment.configuration.FieldConfiguration;
import com.mercans.assignment.configuration.FieldType;
import com.mercans.assignment.utils.AppUtils;
import com.mercans.assignment.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@JsonIgnore
	private int id;
	private String employeeCode;
	private String action;
	Map<String, String> data;
	@JsonProperty("payComponents")
	List<SalaryComponent> salaryComponents;

	public Employee(String[] values, Map<String, Integer> headersMap) {
		this.employeeCode = values[headersMap.get("contract_workerId")];
		this.action = values[headersMap.get("ACTION")];
		this.data = new HashMap<String, String>();
		for (FieldConfiguration dynamicConfigurationField : Constants.DYNAMIC_CONFIGURATION.getFields()) {
			String tempValue = values[headersMap.get(dynamicConfigurationField.getSourceField())];
			if (dynamicConfigurationField.getFieldType().equals(FieldType.Regular)
					&& dynamicConfigurationField.getTargetEntity().equalsIgnoreCase("Person") && tempValue != null
					&& !tempValue.isEmpty())
				this.data.put(
						dynamicConfigurationField.getTargetEntity() + "." + dynamicConfigurationField.getTargetField(),
						tempValue);
		}
		ArrayList<SalaryComponent> salaryComponents = new ArrayList<SalaryComponent>();
		if (AppUtils.isNotNullOrEmpty(values[headersMap.get("pay_amount")])&& AppUtils.isNotNullOrEmpty(values[headersMap.get("pay_currency")]) 
				&& AppUtils.isNotNullOrEmpty(values[headersMap.get("pay_effectiveFrom")])  && AppUtils.isNotNullOrEmpty(values[headersMap.get("pay_effectiveTo")])){
			SalaryComponent salaryComponent = new SalaryComponent(values[headersMap.get("pay_amount")],
					values[headersMap.get("pay_currency")], values[headersMap.get("pay_effectiveFrom")],
					values[headersMap.get("pay_effectiveTo")]);
			salaryComponents.add(salaryComponent);
		}
		if (AppUtils.isNotNullOrEmpty(values[headersMap.get("compensation_amount")])
				&& AppUtils.isNotNullOrEmpty(values[headersMap.get("compensation_currency")])
				&& AppUtils.isNotNullOrEmpty(values[headersMap.get("compensation_effectiveFrom")])
				&& AppUtils.isNotNullOrEmpty(values[headersMap.get("compensation_effectiveTo")])) {
			SalaryComponent salaryComponent = new SalaryComponent(values[headersMap.get("compensation_amount")],
					values[headersMap.get("compensation_currency")],
					values[headersMap.get("compensation_effectiveFrom")],
					values[headersMap.get("compensation_effectiveTo")]);
			salaryComponents.add(salaryComponent);
		}
		this.salaryComponents = salaryComponents;
	}

}
