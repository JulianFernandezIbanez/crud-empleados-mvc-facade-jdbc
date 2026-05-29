package com.example.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.dao.DBConexion;
import com.example.models.Departamento;

public class DepartamentoServiceImpl implements DepartamentoService {
	
	private static final Logger LOG = Logger.getLogger("DepartamentoServiceInpl");
	
	@Override
	public List<Departamento> getDepartamentos() {
		
		List<Departamento> departamentos = new ArrayList<Departamento>();
		
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConnection();) {
			
			ResultSet rs = dbConexion.getDepartamentos(connection);
			
			while(rs.next()) {
				departamentos.add(
					Departamento.builder()
					.id(rs.getInt("id"))
					.nombre(rs.getString("nombre"))
					.build());
			}
			
		} catch (Exception e) {
			LOG.severe("Error al recuperar los departamentos" + e.getMessage());
			e.printStackTrace();
		}
		
		return departamentos;
	}
	
	
}
