package com.example.services;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Empleado;

public interface EmpleadoService {
	//Comprobacion de conexion a la BBDD
	public abstract boolean isConnectionOK() throws SQLException, Exception;
	public abstract List<Empleado> getEmpleados();
}
