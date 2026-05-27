package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.example.models.Empleado;
import com.example.services.EmpleadoService;
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
		// Conectar con la capa de servicios
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		List<Empleado> empleados = empleadoService.getEmpleados();
		
		//Renderizar vista de empleados
		request.setAttribute("empleados", empleados);
		
		request.getRequestDispatcher("views/listadoEmpleados.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
