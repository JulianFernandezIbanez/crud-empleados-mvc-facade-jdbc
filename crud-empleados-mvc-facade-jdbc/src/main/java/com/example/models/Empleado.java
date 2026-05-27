package com.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record Empleado(int id, String nombre, 
		String PrimerApellido, String SegundoApellido, 
		LocalDate FechaAlta, Genero Genero, 
		BigDecimal Salario, int Departamentos_id) {
	
	
}
