package com.example.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.example.dao.DBConexion;
import com.example.models.Detalle;
import com.example.models.Empleado;
import com.example.models.Genero;

public class EmpleadoServiceImpl implements EmpleadoService {

	private static final Logger LOG = Logger.getLogger("EmpleadoServiceImpl");
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
			LOG.severe("Error al recuperar los empleados " + e.getMessage());
			e.printStackTrace();
		}
		
		return empleados;
	}

	@Override
	public void altaEmpleado(Empleado empleado, List<String> emails, List<String> telefonos) throws SQLException {
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConnection()) {
			
			dbConexion.altaEmpleado(empleado, emails, telefonos, connection);
			
		} catch (Exception e) {
			LOG.severe("Error en la insercion "+e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public Detalle getDetalles(int idEmpleado) {

		Detalle detalles = null;
		
		try (DBConexion dbConexion = new DBConexion("root", "Temp2026");
				Connection connection = dbConexion.getConnection()) {
			
			ResultSet rs =  dbConexion.getDetalles(idEmpleado, connection);
			
			//Recuperacion de los nombres del Dpto
			String nombreDpto = null;
			
			if (rs.next()) {
				nombreDpto = rs.getString("nombre");
			}
			
			//Recuperacion de la lista de correos y telefonos
			//Utilizacion Set para que no adimita duplicados
			Set<String> listaCorreos = new HashSet<String>();
			Set<String> numerosTlf = new HashSet<String>();
			
			while(rs.next()) {
				listaCorreos.add(rs.getString("email"));
				numerosTlf.add(rs.getString("numero"));
			}
				
			detalles = new Detalle(nombreDpto, listaCorreos, numerosTlf);
			
			LOG.info("Detalles "+ detalles);
			
		} catch (Exception e) {
			LOG.severe("Error a la hora de recuperar los detalles "+e.getMessage());
			e.printStackTrace();	
		}
		
		return detalles;
	}



}
