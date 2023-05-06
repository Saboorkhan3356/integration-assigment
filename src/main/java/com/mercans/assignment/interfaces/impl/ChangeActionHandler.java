package com.mercans.assignment.interfaces.impl;

import org.springframework.stereotype.Component;

import com.mercans.assignment.interfaces.IActionHandler;
import com.mercans.assignment.model.Employee;
import com.mercans.assignment.utils.AppUtils;
@Component
public class ChangeActionHandler implements IActionHandler{

	@Override
	public Employee handle(Employee employee) {
		
		if (AppUtils.isNullOrEmpty(employee.getEmployeeCode()))
			return null;
		return employee;
	}


}
