package com.example.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.dao.DBConexion;
import com.example.models.Empleado;
import com.example.models.Genero;

public class EmpleadoServiceImpl implements EmpleadoService {

	private static final Logger LOG = Logger.getLogger("EmpleadoServiceInpl");
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

	@Override
	public List<Empleado> getEmpleados() {
		
		List<Empleado> empleados = new ArrayList<Empleado>();
		
		try(DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConnection()) {
			
			ResultSet rs = dbConexion.getEmpleados(connection);
			
			while (rs.next()) {
				
				empleados.add(
						Empleado.builder()
						.id(rs.getInt("id"))
						.nombre(rs.getString("nombre"))
						.PrimerApellido(rs.getString("primerApellido"))
						.SegundoApellido(rs.getString("segundoApellido"))
						.FechaAlta(rs.getDate("fechaAlta").toLocalDate())
						.Genero(Genero.valueOf(rs.getString("genero")))
						.Salario(new BigDecimal(rs.getDouble("salario")))
						.Departamentos_id(rs.getInt("departamentos_id"))
						.build()
						);
				
			}
			
		} catch (Exception e) {
			LOG.severe("Error al recuperar los empleados" + e.getMessage());
			e.printStackTrace();
		}
		
		return empleados;
	}

}
