package com.example.services;

import java.sql.Connection;

import com.example.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

	@Override
	public boolean isConnectionOK() {
		// Conectar con DAO
		DBConexion conexion = new DBConexion("root", "Temp2026");
		
		Connection conn = null;
		try {
			conn = conexion.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn == null ? false:true;
	}

}
