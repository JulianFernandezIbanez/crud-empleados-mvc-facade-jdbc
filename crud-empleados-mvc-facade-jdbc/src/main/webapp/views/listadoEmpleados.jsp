<%@page import="java.math.RoundingMode"%>
<%@page import="com.example.models.Empleado"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Empleados</title>
		<style>
			table, th, td {
				border: 1px solid black;
				border-collapse: collapse;
			}
			th, td {
				padding: 10px;
			}
	</style>
	</head>
	<body>
		<%
			List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
		%>
		<h1>Listado de empleados</h1>
		<div>
			<a title="Muestra el formulario de alta/modificacion de empleado" href="AltaController">Alta de Empleado</a>
		</div>
		<table>
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Primer Apellido</th>
					<th>Segundo Apellido</th>
					<th>Fecha de Alta</th>
					<th>Genero</th>
					<th>Salario</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Empleado empleado : empleados) {
				%>
					<tr>
						<td> <%= empleado.nombre() %></td>
						<td> <%= empleado.PrimerApellido() %></td>
						<td> <%= empleado.SegundoApellido() %></td>
						<td> <%= empleado.FechaAlta() %></td>
						<td> <%= empleado.Genero() %></td>
						<td> <%= empleado.Salario().setScale(2, RoundingMode.HALF_UP) %></td>
						<td><a href="DetallesController?idEmpleado=<%= empleado.id() %>">Detalles</a></td>
						<td><a href="UpdateController?idEmpleado=<%= empleado.id() %>">Modificar</a></td>
					</tr>	
				<%		
					}
				%>
			</tbody>
		</table>
	</body>
</html>