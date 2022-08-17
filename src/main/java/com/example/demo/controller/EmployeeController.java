package com.example.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Employee;
import com.example.demo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	private ResponseEntity<?>getAllEmployees(){
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	
	@GetMapping("/salary")
	private ResponseEntity<?>getBySalary(@RequestParam(name = "from",required = true) Double from,
			@RequestParam(name = "to",required = true) Double to){
		return ResponseEntity.ok(employeeService.getEmployeeFilterBySalary(from,to));
	}
	
	@GetMapping("/employeeDto")
	private ResponseEntity<?>getDtos(){
		return ResponseEntity.ok(employeeService.getEmployeesDto());
	}
	
	
	@GetMapping("/salaryPosition")
	private ResponseEntity<?>getBySalaryAndPosition(@RequestParam(name = "from",required = true) Double from,
			@RequestParam(name = "to",required = true) Double to,
			@RequestParam(name = "position",required = true) String position){
		return ResponseEntity.ok(employeeService.getEmployeeFilterBySalaryAndPosition(from,to,position));
	}
	
	
	@PostMapping("/")
	private ResponseEntity<?>saveEmployee(@RequestBody Employee employee){
		System.out.println(employee.getFechaIngreso());
		employeeService.saveEmployee(employee);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@GetMapping("/pagination")
	private ResponseEntity<?>getPageEmployee(@RequestParam(name = "numOfPage",required = true, defaultValue = "0") int numOfPage,
			@RequestParam(name = "sizeOfPage",required = true, defaultValue = "3") int sizeOfPage,
			HttpServletRequest request)throws Exception{
		try {
			return ResponseEntity.ok(employeeService.findAll(numOfPage,sizeOfPage,request));
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
