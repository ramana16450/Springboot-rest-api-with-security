package com.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entity.Employee;
import com.restapi.service.EmployeeService;

@RestController
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/hello")
	public String hello() {
		return "hello good morning";
	}
	
	@PostMapping("/saveEmp")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> saveEMp(@RequestBody Employee employee) {
		return employeeService.saveEmp(employee);
		
	}
	
	
	@GetMapping("/allEmp")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Employee> getAllEmployees(){
		
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/getEmpById/{id}")
	@PreAuthorize("hasRole('USER')")
	public Employee getEmpById(@PathVariable int id) {
		
		return employeeService.getEmpById(id);
	}
	
	@PutMapping("editEmpById/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> editEmp(@PathVariable int id, @RequestBody Employee updatedEmployee){
		return employeeService.editEmp(id, updatedEmployee);
		
	}
	
	@DeleteMapping("/deleteEmpById/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteEmp(@PathVariable int id){
		
		return employeeService.deleteEmp(id);
		
	}

}
