package com.example.EmployeeRestProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeRestProject.entity.Employee;
import com.example.EmployeeRestProject.exception.EmployeeNotFoundException;
import com.example.EmployeeRestProject.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public List<Employee> getEmployees(){
		List<Employee> employees=service.getEmployees();
		return employees;
		
	}
	
	@PostMapping
	public Employee createEmployee(@RequestBody Employee emp) {
		Employee saveEmployee=service.saveEmployee(emp);
		return saveEmployee;
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int empId) throws Exception {
		try {
		Employee employee=service.getEmployeeById(empId);
		
		return ResponseEntity.ok(employee);
		
	}catch(EmployeeNotFoundException e) {
		return ResponseEntity.notFound().build();
	}
		
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int empId) {
        service.deleteEmployeeById(empId);
        return ResponseEntity.ok("Employee deleted successfully");
    }
	
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable("id") int empId,@RequestBody Employee emp) {
		Employee updateEmployee=service.updateEmployee(empId,emp);
		return updateEmployee;
		
	}
	

}
