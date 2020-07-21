package com.springlearning.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private String employeeId;
	private String employeeName;
	private LocalDate dateOfBirth;
	
	private Department department;

}
