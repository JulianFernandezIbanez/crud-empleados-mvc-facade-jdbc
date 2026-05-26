package com.example.services;

import java.sql.Connection;
import java.sql.SQLException;

import com.example.dao.DBConexion;

public class EmpleadoServiceImpl implements EmpleadoService {

	@Override
	public boolean isConnectionOK() throws SQLException {
		// Conectar con DAO
		DBConexion conexion = new DBConexion("root", "Temp2026");
		
		Connection conn = null;
		boolean connectionOk = false;
		
		try {
			conn = conexion.getConnection();
			if(conn != null) {
				connectionOk = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		
		return connectionOk;
	}

}
