<%@page import="com.example.models.Detalle"%>
<%@page import="com.example.models.Empleado"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalles</title>
	</head>
	<body>
		<%
			Empleado empleado = (Empleado) request.getAttribute("empleado");
			Detalle detalles = (Detalle) request.getAttribute("detalles");
		%>
		<h1>Detalles del empleado</h1>
		<div>
			<p>Nombre: <%= empleado.nombre()%></p>
			<p>Apellidos: <%= empleado.PrimerApellido()%> <%= empleado.SegundoApellido()%></p>
			<p>Fecha de Alta en la empresa: <%= empleado.FechaAlta()%></p>
			<p>Genero: <%= empleado.Genero()%></p>
			<p>Salario: <%= empleado.Salario()%></p>
			<%
			// Evitar NullPointerException: verificar que "detalles" no sea null antes de usarlo
			if (detalles == null) {
			%>
				<p>No se encontraron detalles adicionales para este empleado.</p>
			<%
			} else {
			%>
			<p>Facultad : <%= detalles.nombreDpto()%></p>
			<p>Tiene los siguientes correos y telefonos</p>
			<ul>
				<li>Correos</li>
				<%
				for (String email : detalles.correos()) {
					%>
					<li><%=email %></li>
					<%
				}
				%>
			</ul>
			<ul>
				<li>Telefonos</li>
				<%
				for (String numero : detalles.numTlfo()) {
					%>
					<li><%=numero %></li>
					<%
				}
				%>
			</ul>
			<%
			}
			%>
		</div>
	</body>
</html>