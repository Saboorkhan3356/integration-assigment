package com.mercans.assignment.model;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsvRecord {
	@CsvBindByName(column = "SystemId")
	private String systemId;
	
	@CsvBindByName(column = "ACTION")
	private String action;
	
	@CsvBindByName(column = "worker_name")
	private String workerName;
	
	@CsvBindByName(column = "worker_personalCode")
	private String workerPersonalCode;
	
	@CsvBindByName(column = "worker_gender")
	private String workerGender;
	
	
	@Builder.Default
	@CsvBindByName(column = "worker_numberOfKids")
	private Integer workerNumberOfKids = 0;
	
	@CsvBindByName(column = "worker_motherMaidenName")
	private String workerMotherMaidenName;
	
	@CsvBindByName(column = "worker_grandmotherMaidenName")
	private String workerGrandMotherMaidenName;
	
	@CsvBindByName(column = "contract_signatureDate")
	private String contractSignatureDate;
	
	@CsvBindByName(column = "contract_workStartDate")
	private String contractWorkStartDate;
	
	@CsvBindByName(column = "contract_type")
	private String contractType;
	
	@CsvBindByName(column = "contract_endDate")
	private String contractEndDate;
	
	@CsvBindByName(column = "contract_workerId")
	private String contractWorkerId;
	
	@CsvBindByName(column = "pay_amount")
	private String payAmount;
	
	@CsvBindByName(column = "pay_currency")
	private String payCurrency;
	
	@CsvBindByName(column = "pay_effectiveFrom")
	private String payEffectiveFrom;
	
	@CsvBindByName(column = "pay_effectiveTo")
	private String payEffectiveTo;
	
	@CsvBindByName(column = "compensation_amount")
	private String compensationAmount;
	
	@CsvBindByName(column = "compensation_type")
	private String compensationType;
	
	@CsvBindByName(column = "compensation_currency")
	private String compensationCurrency;
	
	@CsvBindByName(column = "compensation_effectiveFrom")
	private String compensationEffectiveFrom;
	
	@CsvBindByName(column = "compensation_effectiveTo")
	private String compensationEffectiveTo;

}
