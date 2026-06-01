package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.example.models.Empleado;

public class DBConexion implements AutoCloseable {
    
    private String username;
    private String password;
    private Connection connection;
    
    private static final Logger LOG = Logger.getLogger("DBConexion");
    
    public DBConexion(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws ClassNotFoundException {
        String urlConnection = "jdbc:mysql://localhost:3306/empresa-crud-empleados?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        Properties info = new Properties();
        
        // The MySQL JDBC driver expects the property key "user" (not "username").
        // Using the wrong key can lead to the driver attempting a connection without
        // the intended credentials (which may result in an "access denied" for the OS user).
        info.put("user", this.username);
        info.put("password", this.password);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(urlConnection, info);
            LOG.info("Conexion Establecida exitosamente");
        } catch (SQLException e) {
            LOG.severe("Error de conexion: " + e.getMessage());
            e.printStackTrace();
        }
        
        return this.connection;
    }

	@Override
	public void close() throws Exception {
		this.connection.close();
	}
	
	//Recuperacion de los registros de la tabla empleado
	public ResultSet getEmpleados(Connection connection) {
		
		ResultSet rs = null;
		String query = "SELECT * FROM `empresa-crud-empleados`.empleados";
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			LOG.severe("Error al recuperar los empleados. Causa "+e.getMessage());
			e.printStackTrace();
		}
		
		return(rs);
	}
	
	//Recuperacion de los registros de la tabla departamento
	public ResultSet getDepartamentos(Connection connection) {
		
		ResultSet rs = null;
		String query = "SELECT * FROM `empresa-crud-empleados`.departamentos";
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			LOG.severe("Error al recuperar los departamentos. Causa "+e.getMessage());
			e.printStackTrace();
		}
		
		return(rs);
	}
	
	//inserccion de Empleados en el marco de una transaccion
	public void altaEmpleado(Empleado empleado,
			List<String> dirCorreos, 
			List<String>numTlf, 
			Connection connection) throws SQLException {
		
		//Inserccion del empleado y devolver el last inserted id de la tabla de empleados
		String query1 = "INSERT INTO `empleados` (`nombre`, "
				+ "`primerApellido`, `segundoApellido`, "
				+ "`fechaAlta`, `genero`, `salario`, "
				+ "`departamentos_id`) VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?)";
		
		
		
		//Insertar correos y telefonos del id del empleado recogido
		String query2 = "INSERT INTO `correos` (`email`, `empleados_id`) "
				+ "VALUES (?, ?)";
		
		String query3 = "INSERT INTO `telefonos` (`numero`, `empleados_id`) "
				+ "VALUES (?, ?)";
		
		//Iniciamos la transaccion
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement stmt1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
			stmt1.setString(1, empleado.nombre());
			stmt1.setString(2, empleado.PrimerApellido());
			stmt1.setString(3, empleado.SegundoApellido());
			stmt1.setDate(4, Date.valueOf(empleado.FechaAlta()));
			stmt1.setString(5, empleado.Genero().name());
			stmt1.setDouble(6, empleado.Salario().doubleValue());
			stmt1.setInt(7, empleado.Departamentos_id());
			
			int totalFilas = stmt1.executeUpdate();
			
			if (totalFilas != 0) {
				//Recuperar el id del ultimo empleado isertado para su uso
				long lastInsertedId = 0L;
				
				ResultSet rs = stmt1.getGeneratedKeys();
				
				if (rs.next()) {
					lastInsertedId = rs.getLong(1);
				}
				
				//insercion de correos si han sido proporcionados
				if (dirCorreos != null && dirCorreos.size() > 0) {
					PreparedStatement stmt2 = connection.prepareStatement(query2);
					
					stmt2.setInt(2, Math.toIntExact(lastInsertedId));
					
					//El siguiente codigo no es optimo 
					/*for (String email : dirCorreos) {
						stmt2.setString(1, email);
						stmt2.executeUpdate();
					}*/
					for (String email : dirCorreos) {
						stmt2.setString(1, email);
						stmt2.addBatch();
					}
					
					stmt2.executeBatch();
				}
				
				//inserccion de telefonos si han sido proporcionados
				if (numTlf != null && numTlf.size() > 0) {
					PreparedStatement stmt3 = connection.prepareStatement(query3);
					
					stmt3.setInt(2, Math.toIntExact(lastInsertedId));
					
					for (String tlf : numTlf) {
						stmt3.setString(1, tlf);
						stmt3.addBatch();
					}
					
					stmt3.executeBatch();
				}
			}
			
			connection.commit();
		} catch (Exception e) {
			LOG.severe("Error en la insercion. Causa: "+e.getMessage());
			e.printStackTrace();
			connection.rollback();
			LOG.info("Transaccion revertida, insercion cancelada");
		} finally {
				connection.setAutoCommit(true);
		}

	}
}