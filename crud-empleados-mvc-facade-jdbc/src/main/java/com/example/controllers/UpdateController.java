package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.example.models.Departamento;
import com.example.models.EmpleadoUpdate;
import com.example.services.DepartamentoService;
import com.example.services.DepartamentoServiceImpl;
import com.example.services.EmpleadoService;
import com.example.services.EmpleadoServiceImpl;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger("UpdateController");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
		
		//Conseguir toda la informacion del empleado seleccionado por el id
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		DepartamentoService departamentoService = new DepartamentoServiceImpl();
		
		EmpleadoUpdate empleadoUpdate = empleadoService.getEmpleadoById(idEmpleado);
		List<Departamento> departamentos = departamentoService.getDepartamentos();
		
		request.setAttribute("empleadoUpdate", empleadoUpdate);
		request.setAttribute("departamentos", departamentos);
		request.getRequestDispatcher("views/formularioAltaModificacion.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
