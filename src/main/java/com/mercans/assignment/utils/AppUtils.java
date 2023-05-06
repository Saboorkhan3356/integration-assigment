package com.mercans.assignment.utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercans.assignment.configuration.DataType;
import com.mercans.assignment.configuration.DynamicConfiguration;
import com.mercans.assignment.interfaces.IActionHandler;
import com.mercans.assignment.interfaces.impl.ChangeActionHandler;
import com.mercans.assignment.interfaces.impl.HireActionHandler;
import com.mercans.assignment.interfaces.impl.TerminateActionHandler;
import com.mercans.assignment.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppUtils {
	
	public DynamicConfiguration getConfigurations() {
		if (Constants.DYNAMIC_CONFIGURATION == null) 
			loadConfigurations();
		
		return Constants.DYNAMIC_CONFIGURATION;
	}
	
	public void loadConfigurations() {
		ObjectMapper mapper = new ObjectMapper();
		File resource;
		try {
			resource = new ClassPathResource("dynamic_config.json").getFile();
			Constants.DYNAMIC_CONFIGURATION = mapper.readValue(resource, DynamicConfiguration.class);
			log.info("Configurations Loaded SuccessFully");
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			log.error("Invalid configuration file");
		}
	}

	public static String convertDateFormat(String fromFormat, String toFormat, String input) {
		String result = null;
		SimpleDateFormat fromDateFormat = new SimpleDateFormat(fromFormat);
		SimpleDateFormat toDateFormat = new SimpleDateFormat(toFormat);
		try {
			result = toDateFormat.format(fromDateFormat.parse(input));
		} catch (ParseException e) {
			log.error(e.getLocalizedMessage());
		}
		return result;
	}

	public static Date getDateFromString(String input) {
		try {
			return new SimpleDateFormat(Constants.OUTPUT_DATE_FORMAT).parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateEmployeeCode(String firstDate) {
		String employeeCode = null;
		String sixDigits = convertDateFormat(Constants.INPUT_DATE_FORMAT,
				Constants.EMPLOYEE_CODE_DF, firstDate);
		if (sixDigits != null && !sixDigits.isEmpty())
			employeeCode = sixDigits.concat(Integer.toHexString(new Random().nextInt(254) + 1));
		return employeeCode;
	}
	
	public List<String[]> validateEmployeesData(List<String[]> list, Map<String, Integer> headersMap , List<String> errors) {
		List<String[]> listProcessed = new ArrayList<String[]>();
		
		list.stream().forEach(currentRow ->{
			String systemId = currentRow[0];
			Constants.DYNAMIC_CONFIGURATION.getFields().stream().forEach(fieldConfig ->{
				String value = currentRow[headersMap.get(fieldConfig.getSourceField())];
				
				if(isNullOrEmpty(value) && fieldConfig.getIsMandatory())
					errors.add("Row with system Id '"+systemId+"' has empty or null '"+fieldConfig.getSourceField()+"'");
				
				if (isNotNullOrEmpty(fieldConfig.getMappingKey())) {
					value = Constants.DYNAMIC_CONFIGURATION.getMappings().get(fieldConfig.getMappingKey()).get(value.toLowerCase());
				}
				
				if (fieldConfig.getValidationPattern() != null && !fieldConfig.getValidationPattern().isEmpty()) {
					if (!Pattern.matches(fieldConfig.getValidationPattern(), value)) {
						value = "";
						errors.add("Row with system Id '"+systemId+"' has invalid '"+fieldConfig.getSourceField()+"'");
					}
				}
				
				if (isNotNullOrEmpty(fieldConfig.getDateFormat())) {
						String newValue = AppUtils.convertDateFormat(fieldConfig.getDateFormat(),
								Constants.OUTPUT_DATE_FORMAT, value);
						if (isNotNullOrEmpty(newValue))
							value = newValue;
						else {
							value = "";
							errors.add("Row with system Id '"+systemId+"' has invalid date in '"+fieldConfig.getSourceField()+"'");
						}
					}	
				if (fieldConfig.getDataType().equals(DataType.Decimal)
						|| fieldConfig.getDataType().equals(DataType.Integer)) {
					if (!NumberUtils.isParsable(value)) {
						value = "";
						errors.add("Row with system Id '"+systemId+"' has invalid data type in '"+fieldConfig.getSourceField()+"'");
					}
				}
				
				
				currentRow[headersMap.get(fieldConfig.getSourceField())] = value;
				
			});
			listProcessed.add(currentRow);
		});
		return listProcessed;
	}
	
	
	public static boolean isNotNullOrEmpty(String val) {
		return val != null && !val.isEmpty();
	}
	
	public static boolean isNullOrEmpty(String val) {
		return val == null || val.isEmpty();
	}
	
	public List<Employee> getEmployeesList(List<String[]> listProcessed, Map<String, Integer> columnMap) {
		ArrayList<Employee> employeesList = new ArrayList<Employee>();
		listProcessed.stream().forEach(row->{
			Employee employee = new Employee(row, columnMap);
			if (isNullOrEmpty(employee.getAction()))
				log.error(employee + " -- Record is discarded due to missing action.");
			else {
				IActionHandler actionHandler = getActionHandler(employee.getAction());
				employee = actionHandler.handle(employee);
				if (employee != null)
					employeesList.add(employee);
				else
					log.error("Record is discarded as mandatory field is missing.");
			}
		});
		return employeesList;
	}
	
	public IActionHandler getActionHandler(String action) {
		if (action.equalsIgnoreCase(Constants.ACTION_HIRE))
			return new HireActionHandler();
		else if (action.equalsIgnoreCase(Constants.ACTION_CHANGE))
			return new ChangeActionHandler();
		else if (action.equalsIgnoreCase(Constants.ACTION_TERMINATE))
			return new TerminateActionHandler();
		return null;
	}
	
}
