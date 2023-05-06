package com.mercans.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercans.assignment.model.ProcessingResultPayload;
import com.mercans.assignment.service.CsvProcessor;

@RestController
public class CsvController {

	@Autowired
	CsvProcessor csvProcessor;

	@PostMapping(produces = "application/json", path = "csv/processCsv")
	public ResponseEntity<ProcessingResultPayload> processCsv() {

		return ResponseEntity.ok(csvProcessor.processCsv());
	}

}
