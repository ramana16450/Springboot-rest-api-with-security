package com.restapi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.restapi.entity.Employee;

public interface EmployeeService {

	ResponseEntity<String> saveEmp(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmpById(int id);

	ResponseEntity<String> editEmp(int id, Employee updatedEmployee);

	ResponseEntity<String> deleteEmp(int id);

}
