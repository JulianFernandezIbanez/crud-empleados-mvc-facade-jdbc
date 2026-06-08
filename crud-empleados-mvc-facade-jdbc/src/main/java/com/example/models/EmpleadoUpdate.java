package com.example.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record EmpleadoUpdate(
		int id, String nombreEmpleado, 
		String PrimerApellido, String SegundoApellido, 
		LocalDate FechaAlta, Genero Genero, 
		BigDecimal Salario, int idDpto,
		String nombreDpto,
		Set<String> numTlf, Set<String> emails) {

}
