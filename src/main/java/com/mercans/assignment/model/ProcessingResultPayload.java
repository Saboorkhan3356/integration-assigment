package com.mercans.assignment.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessingResultPayload {

	private String uuid;
	private String fname;
	List<String> erros;
	@JsonProperty("payLoad")
	private List<Employee> employees;

}
