package com.example.demo.service.dto;

import com.example.demo.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDto {
	private String id;
	private String datosPersonales; /*Este dto devolverá -Nombre, -Apellido, -Cuil del empleado*/
	private String city;
	private Double salary;
}
