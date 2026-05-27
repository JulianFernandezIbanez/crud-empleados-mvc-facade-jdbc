package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return(rs);
	}
}