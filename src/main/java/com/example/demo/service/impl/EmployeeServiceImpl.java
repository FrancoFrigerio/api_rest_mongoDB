package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.dto.EmployeeResponseDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	ModelMapper mapper;
	
	
	
	@Override
	public List<Employee> getEmployeeFilterBySalary(Double from, Double to) {
		
		return employeeRepository.getEmployeesFilterBySalary(from, to);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}


	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getEmployeeFilterBySalaryAndPosition(Double from, Double to, String position) {
		return employeeRepository.getEmployeesFilterBySalaryAndPosition(from, to, position);
	}
	
	
private TypeMap<Employee, EmployeeResponseDto> propertyMapper = null;
	
	public TypeMap<Employee, EmployeeResponseDto>createPropertyMapper(){
		if(this.propertyMapper == null) {
			return this.mapper.createTypeMap(Employee.class, EmployeeResponseDto.class);
		}else {
			return this.propertyMapper;
		}
		
	}
	

	@Override
	public List<EmployeeResponseDto> getEmployeesDto() {
		List<EmployeeResponseDto>resp = new ArrayList<>();
		propertyMapper = this.createPropertyMapper();
		try {
			propertyMapper.addMappings(
				//mapper -> mapper.map(employee -> employee.getName()+ "--", EmployeeResponseDto::setDatosPersonales)
				mapper -> {
					mapper.map(employee -> employee.personalData(),EmployeeResponseDto::setDatosPersonales);
					mapper.map(employee -> employee.getAdress().getCity() ,EmployeeResponseDto::setCity);
				}
			);
		}catch (Exception e) {
			System.out.println("exception".concat(e.getMessage()));
		}
		
		employeeRepository.findAll().forEach(employee ->{
			resp.add(this.mapper.map(employee, EmployeeResponseDto.class));
		});
		return resp;
	}


	@Override
	public Map<String,Object> findAll(Integer numOfPage, Integer sizeOfPage, HttpServletRequest request) throws Exception {
		Map<String, Object> response = new HashMap<>();
		Pageable pagination = PageRequest.of(numOfPage,sizeOfPage);
		log.debug("--->>",employeeRepository.findAll());
		Page<Employee> pageEmployee = employeeRepository.findAll(pagination);
		
		if(numOfPage+1 > pageEmployee.getTotalPages()) {
			response.put("message error", "The number of page is bigger to the total of page of this collection maybe try width page: 0");
		}else {
		response.put("employees", pageEmployee.getContent());
		response.put("total elements", pageEmployee.getNumberOfElements());
		response.put("next", pageEmployee.isLast()?"It is the last page":urlPage(request, pageEmployee.getNumber() +1,sizeOfPage));
		response.put("previus", pageEmployee.isFirst()?"It is the first page":urlPage(request, pageEmployee.getNumber() - 1,sizeOfPage));
		//response.put("all", pageEmployee);
		}
		return response;
	}
	
	public String urlPage(HttpServletRequest request, int page,int size) {
		return String.format("%s?numOfPage=%d", request.getRequestURI(), page).concat("&sizeOfPage="+size);
	}
}
