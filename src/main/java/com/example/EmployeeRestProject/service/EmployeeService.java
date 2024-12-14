package com.example.EmployeeRestProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EmployeeRestProject.entity.Employee;
import com.example.EmployeeRestProject.exception.EmployeeNotFoundException;
import com.example.EmployeeRestProject.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;
	
	public List<Employee> getEmployees(){
		List<Employee> findAll=repo.findAll();
		return findAll;
	}

	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		Employee save=repo.save(emp);
		return save;
		
	}

	public Employee getEmployeeById(int empId) throws Exception {
		// TODO Auto-generated method stub
		
		return repo.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee not found with ID: " + empId));
		
	}

	

	 public void deleteEmployeeById(int empId) {
	        if (!repo.existsById(empId)) {
	            throw new EmployeeNotFoundException("Employee not found with ID: " + empId);
	        }
	        repo.deleteById(empId);
	    }

	public Employee updateEmployee(int empId, Employee emp) {
		Employee updateEmployee=repo.findById(empId).orElseThrow(()->new EmployeeNotFoundException("Employee not found with ID: " + empId));
		updateEmployee.setEmpName(emp.getEmpName());
		updateEmployee.setEmpSalary(emp.getEmpSalary());
		
		return repo.save(updateEmployee);
		// TODO Auto-generated method stub
		
	}

	

}
