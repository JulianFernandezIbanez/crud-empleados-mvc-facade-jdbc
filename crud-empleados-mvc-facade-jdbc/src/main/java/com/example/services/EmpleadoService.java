package com.example.services;

import java.sql.SQLException;

public interface EmpleadoService {
	//Comprobacion de conexion a la BBDD
	public abstract boolean isConnectionOK() throws SQLException;
}
