package com.example.demo.domain;



import java.util.Date;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;


@Document(collection = "employees")
@Data
@AllArgsConstructor
public class Employee {
	
	@Id
	private String id;
	
	private String cuil;
	
	private String name;
	
	private String surname;
	
	private Double salary;
	
	private Date fechaIngreso;
	
	private String position;
	
	private Adress adress;
	


	public String personalData() {
		return "Cuil: " + cuil + ", Name: " + name + ", Surname: " + surname;
	}
}
