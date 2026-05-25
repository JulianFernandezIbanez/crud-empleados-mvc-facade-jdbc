package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.services.EmpleadoServiceImpl;

/**
 * Servlet implementation class MainControler
 */
@WebServlet("/MainControler")
public class MainControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		empleadoService.isConnectionOK();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
