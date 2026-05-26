package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.example.services.EmpleadoServiceImpl;

/**
 * Servlet implementation class MainControler
 */
@WebServlet("/MainControler")
public class MainControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger("MainControler");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Hay que conectar con la capa de servicios, 
		// que a su vez conectara con la capa DAO (implementacion de JDBC)
		// para hacer las consultas SQL correspondientes
		// y finalmente el Servlet mostrara la respuesta renderizando una vista JSP
		
		//Comprobar conexion con la BBDD a traves de la capa servicios
		EmpleadoServiceImpl empleadoService = new EmpleadoServiceImpl();
		boolean connectionResult = false;
		
		try {
			connectionResult = empleadoService.isConnectionOK();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (connectionResult) {
			LOG.info("Conexion Exitosa");
		}else {
			LOG.info("Error de conexion");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
