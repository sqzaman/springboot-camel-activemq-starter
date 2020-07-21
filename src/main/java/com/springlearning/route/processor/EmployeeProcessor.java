package com.springlearning.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.springlearning.model.Department;
import com.springlearning.model.Employee;

@Component
public class EmployeeProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {

		Employee emp = exchange.getIn().getBody(Employee.class);
		emp.setDepartment(new Department("001", "Customer Care"));
		
		exchange.getIn().setBody(emp);
		
	}



}
