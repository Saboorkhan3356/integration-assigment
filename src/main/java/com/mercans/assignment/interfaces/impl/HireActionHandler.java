package com.mercans.assignment.interfaces.impl;

import org.springframework.stereotype.Component;

import com.mercans.assignment.interfaces.IActionHandler;
import com.mercans.assignment.model.Employee;
import com.mercans.assignment.utils.AppUtils;

@Component
public class HireActionHandler implements IActionHandler{

	@Override
	public Employee handle(Employee employee) {

		if(employee.getData().containsKey("person.hire_date") && AppUtils.isNotNullOrEmpty(employee.getData().get("person.hire_date")))
		{
			if(AppUtils.isNullOrEmpty(employee.getEmployeeCode()) )
				employee.setEmployeeCode( AppUtils.generateEmployeeCode(employee.getData().get("person.hire_date")));
		return employee;
		}else
			return null;
	}
	

}
