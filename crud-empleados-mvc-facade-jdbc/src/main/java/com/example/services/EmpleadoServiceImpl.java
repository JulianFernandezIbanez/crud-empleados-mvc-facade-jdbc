package com.example.services;

import java.sql.Connection;

import com.example.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

	@Override
	public boolean isConnectionOK() throws Exception {
		// Conectar con DAO
		
		boolean connectionOk = false;
		
		try(DBConexion conexion = new DBConexion("root", "Temp2026");
				Connection conn = conexion.getConnection()) {
			if(conn != null) {
				connectionOk = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return connectionOk;
	}

}
