package com.example.demo.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Employee;
import com.example.demo.service.dto.EmployeeResponseDto;

@Service
public interface EmployeeService {
	
	public List<Employee>getEmployeeFilterBySalary(Double from, Double to);
	
	public List<Employee>getEmployeeFilterBySalaryAndPosition(Double from, Double to,String position);
	
	public List<Employee>getAllEmployees();
	
	public List<EmployeeResponseDto> getEmployeesDto();
	
	void saveEmployee(Employee employee);
	
	public Map<String,Object> findAll(Integer numOfPage, Integer sizeOfPage,HttpServletRequest request) throws Exception;
}
