package com.example.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.example.models.Departamento;
import com.example.models.Empleado;
import com.example.models.Genero;
import com.example.services.DepartamentoService;
import com.example.services.DepartamentoServiceImpl;
import com.example.services.EmpleadoService;
import com.example.services.EmpleadoServiceImpl;

/**
 * Servlet implementation class AltaController
 */
@WebServlet("/AltaController")
public class AltaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger("AltaController");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DepartamentoService departamentoService = new DepartamentoServiceImpl();
		List<Departamento> departamentos = departamentoService.getDepartamentos();
		
		request.setAttribute("departamentos", departamentos);
		request.getRequestDispatcher("views/formularioAltaModificacion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recibir datos del formulario
		//La informacion viene en formato String
		String nombre = request.getParameter("nombre");
		String primerApellido = request.getParameter("primerApellido");
		String segundoApellido = request.getParameter("segundoApellido") == null ?
				"" : request.getParameter("segundoApellido");
		LocalDate FechaAlta = LocalDate.parse(request.getParameter("fechaAlta"));
		Genero genero = Genero.valueOf(request.getParameter("genero"));
		BigDecimal Salario = BigDecimal.valueOf(Double.valueOf(request.getParameter("salario")));
		int departamento_id = Integer.parseInt(request.getParameter("departamento"));
		List<String> direccionesCorreo = null;
		List<String> numerosTelefono = null;
		
		if(request.getParameter("correos") != null) {
			String correos = request.getParameter("correos");
			String[] direccionesDeCorreoRecibidos = correos.split(";");
			
			direccionesCorreo = Arrays.asList(direccionesDeCorreoRecibidos);
		}
		
		if(request.getParameter("telefonos") != null) {
			String telefonos = request.getParameter("telefonos");
			String[] numerosDeTelefonoRecibidos = telefonos.split(";");
			
			numerosTelefono = Arrays.asList(numerosDeTelefonoRecibidos);
		}
		
		
		Empleado empleado = Empleado.builder()
				.nombre(nombre)
				.PrimerApellido(primerApellido)
				.SegundoApellido(segundoApellido)
				.FechaAlta(FechaAlta)
				.Genero(genero)
				.Salario(Salario)
				.Departamentos_id(departamento_id)
				.build();

		//Comprobacion de la informacion obtenida
		/*LOG.info("Nombre recibido: "+nombre);
		LOG.info("Genero recibido: "+genero);
		*/
		
		EmpleadoService empleadoService = new EmpleadoServiceImpl();
		try {
			empleadoService.altaEmpleado(empleado, direccionesCorreo, numerosTelefono);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Empleado> empleados = empleadoService.getEmpleados();
		
		request.setAttribute("empleados", empleados);
		request.getRequestDispatcher("views/listadoEmpleados.jsp").forward(request, response);
	}

}
