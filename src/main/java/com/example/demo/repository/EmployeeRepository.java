package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>{

	@Query(value = "{'salary' : {$gte: ?0, $lte: ?1}}")
	public List<Employee> getEmployeesFilterBySalary(Double from,Double to);
	

	/*Mayor o igual (grater than o equal - gte) Menor que(lower than) */
	@Query(value="{ $or: [ {salary: {$gte:?0, $lt:?1}}, {position: ?2}]}")
	public List<Employee> getEmployeesFilterBySalaryAndPosition(Double from,Double to,String position);
	
	/*Crear metodo y Query para devolver una lista de Dto's de EmpleadoResponseDto */
	
	
	public Page<Employee> findAll(Pageable pageable);
}
