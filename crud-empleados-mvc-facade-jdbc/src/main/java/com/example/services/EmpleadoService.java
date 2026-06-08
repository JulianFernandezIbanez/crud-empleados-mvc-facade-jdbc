package com.example.services;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Detalle;
import com.example.models.Empleado;
import com.example.models.EmpleadoUpdate;

public interface EmpleadoService {
	//Comprobacion de conexion a la BBDD
	public abstract boolean isConnectionOK() throws SQLException, Exception;
	public abstract List<Empleado> getEmpleados();
	public abstract void altaEmpleado(Empleado empleado, List<String> emails, List<String> telefonos) throws SQLException;
	public abstract Detalle getDetalles(int idEmpleado);
	public abstract EmpleadoUpdate getEmpleadoById(int idEmpleado);
	public abstract void updateEmpleado(Empleado empleado, List<String> emails, List<String> numTlf);
}
