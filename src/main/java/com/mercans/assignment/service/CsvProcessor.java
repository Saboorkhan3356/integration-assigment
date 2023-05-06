package com.mercans.assignment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercans.assignment.configuration.ApplicationConfiguration;
import com.mercans.assignment.model.Employee;
import com.mercans.assignment.model.ProcessingResultPayload;
import com.mercans.assignment.utils.AppUtils;
import com.mercans.assignment.utils.Constants;
import com.mercans.assignment.utils.CsvUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CsvProcessor {
	
	@Autowired AppUtils appUtils;
	@Autowired CsvUtils csvUtils;
	@Autowired ApplicationConfiguration appConfigs;

	
	public ProcessingResultPayload processCsv() {
		List<String> errors = new ArrayList<String>();
		String filePath = appConfigs.getPath() + appConfigs.getFilename();
		List<String[]> recordList = csvUtils.readLinesFromFile(filePath);
		List<String[]> processedRecordsList;
		Map<String, Integer> headers;
		if (recordList != null) {
			headers = csvUtils.getHeadersMap(recordList.get(Constants.HEADER_INDEX)) ;
			recordList.remove(Constants.HEADER_INDEX.intValue());
			processedRecordsList = appUtils.validateEmployeesData(recordList, headers, errors);
			
			List<Employee> employees = appUtils.getEmployeesList(processedRecordsList, headers);
			ProcessingResultPayload request = new ProcessingResultPayload(UUID.randomUUID().toString(), appConfigs.getFilename(), errors, employees);
			ObjectMapper mapper = new ObjectMapper();
			try {
				log.info(mapper.writeValueAsString(request));
			}
			catch(Exception e) {
			log.error(e.getStackTrace().toString());
			}
			return request;
		}
		return null;
		
	}

}
