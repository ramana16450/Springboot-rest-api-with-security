package com.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restapi.entity.Employee;
import com.restapi.exception.EmployeeNotFoundException;
import com.restapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public ResponseEntity<String> saveEmp(Employee employee) {
		
		employeeRepository.save(employee);
		return new ResponseEntity<>("Employee has been saved successfully", HttpStatus.ACCEPTED);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmpById(int id) {
		return employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
	}

	@Override
	public ResponseEntity<String> editEmp(int id, Employee updatedEmployee) {
	
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent()) {
			Employee employee1=employee.get();
			employee1.setName(updatedEmployee.getName());
			employee1.setSalary(updatedEmployee.getSalary());
			employee1.setState(updatedEmployee.getState());
			employeeRepository.save(employee1);
			return new ResponseEntity<>("Employee has been updated successfully", HttpStatus.OK);
		
		}
		else {
			
			throw new EmployeeNotFoundException(id);
			
		}
			

		
	}

	
	public ResponseEntity<String> deleteEmp(int id) {
		Optional<Employee> employee=employeeRepository.findById(id);
		if(employee.isPresent()) {
			employeeRepository.deleteById(id);
			return new ResponseEntity<>("Employee has been deleted successfully", HttpStatus.ACCEPTED);
		}
		else {
			throw new EmployeeNotFoundException(id);
		}
		
	}
	
	
	

}
