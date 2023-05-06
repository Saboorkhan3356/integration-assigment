package com.mercans.assignment.interfaces.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.mercans.assignment.interfaces.IActionHandler;
import com.mercans.assignment.model.Employee;
import com.mercans.assignment.utils.AppUtils;
import com.mercans.assignment.utils.Constants;

@Component
public class TerminateActionHandler implements IActionHandler {

	@Override
	public Employee handle(Employee employee) {
		if (AppUtils.isNotNullOrEmpty(employee.getEmployeeCode())) {
			if ((!employee.getData().containsKey("person.termination_date")
					|| AppUtils.isNullOrEmpty(employee.getData().get("person.termination_date"))))
				employee.getData().put("person.termination_date",
						new SimpleDateFormat(Constants.INPUT_DATE_FORMAT).format(new Date()));
			return employee;

		}
		return null;

	}

}
