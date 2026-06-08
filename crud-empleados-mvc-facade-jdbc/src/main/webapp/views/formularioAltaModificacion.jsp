<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Collector"%>
<%@page import="com.example.models.Genero"%>
<%@page import="com.example.models.EmpleadoUpdate"%>
<%@page import="com.example.models.Departamento"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Formulario</title>
	</head>
	
	<body>
		<%
			List<Departamento> departamentos = (List<Departamento>) request.getAttribute("departamentos");
			EmpleadoUpdate empleadoUpdate = (EmpleadoUpdate) request.getAttribute("empleadoUpdate");
		%> 
		<h1>Formulario de Alta/Modificacion de Empleado</h1>
		
		<fieldset>
			<legend>Formulario de Gestion de Empleado</legend>
			<form action="AltaController" method="post">
				<input type="hidden" id="idEmpleado" name="idEmpleado" value="<%= empleadoUpdate == null ? 0 : empleadoUpdate.id()%>">
				<div>
					<label for="nombre">Nombre: </label>
					<input type="text" id="nombre" name="nombre" value="<%= empleadoUpdate != null ? empleadoUpdate.nombreEmpleado() : ' ' %>" required placeholder="Introduzca su nombre">
				</div>
				<div>
					<label for="primerApellido">Primer Apellido: </label>
					<input type="text" id="primerApellido" name="primerApellido" value="<%= empleadoUpdate != null ? empleadoUpdate.PrimerApellido() : ' ' %>" required placeholder="Introduzca su primer apellido">
				</div>
				<div>
					<label for="segundoApellido">Segundo Apellido: </label>
					<input type="text" id="segundoApellido" name="segundoApellido" value="<%= empleadoUpdate != null ? empleadoUpdate.SegundoApellido() : ' ' %>" placeholder="Introduzca su segundo apellido">
				</div>
				<div>
					<label for="fechaAlta">Fecha de Alta: </label>
					<input type="date" name="fechaAlta" id="fechaAlta" value="<%= empleadoUpdate != null ? empleadoUpdate.FechaAlta() : ' ' %>" required>
				</div>
				<div>
					<fieldset>
						<legend>Genero: </legend>
						<label for="hombre">Hombre</label>
						<input type="radio" id="hombre" name="genero" value="HOMBRE" <%= empleadoUpdate != null && empleadoUpdate.Genero().equals(Genero.HOMBRE) ? "checked" : ' '%>  required>
						<br>
						<label for="mujer">Mujer</label>
						<input type="radio" id="mujer" name="genero" value="MUJER" <%= empleadoUpdate != null && empleadoUpdate.Genero().equals(Genero.MUJER) ? "checked" : ' '%> required>
						<br>
						<label for="otro">Otro</label>
						<input type="radio" id="otro" name="genero" value="OTRO" <%= empleadoUpdate != null && empleadoUpdate.Genero().equals(Genero.OTRO) ? "checked" : ' '%> required>
					</fieldset>
				</div>
				<div>
					<label for="salario">Salario: </label>
					<input type="text" id="salario" name="salario" value="<%= empleadoUpdate != null ? empleadoUpdate.Salario() : ' ' %>" required placeholder="Introduzca su salario">
				</div>
				<div>
					<label for="departamento">Seleccione su departamento</label>
					<select id="departamento" name="departamento" required>
						<option></option>
						<% for (Departamento departamento : departamentos) {%>
							<option value=<%= departamento.id() %> <%= empleadoUpdate != null && empleadoUpdate.idDpto() ==  departamento.id() ? "selected" : ' ' %>><%= departamento.nombre() %></option>
						<%
							}	
						%>
					</select>
				</div>
				<div>
					<label for="correos">Correos: </label>
					<input type="text" id="correos" name="correos" value="<%= empleadoUpdate != null && !empleadoUpdate.emails().contains(null) ? empleadoUpdate.emails().stream().collect(Collectors.joining(";")) : ' ' %>" placeholder="Añada uno o varios separados por ;">
				</div>
				<div>
					<label for="telefonos">Telefonos: </label>
					<input type="text" id="telefonos" name="telefonos" value="<%= empleadoUpdate != null && !empleadoUpdate.emails().contains(null) && empleadoUpdate.numTlf().size() > 0 ? empleadoUpdate.numTlf().stream().collect(Collectors.joining(";")) : ' ' %>" placeholder="Añada uno o varios separados por ;">
				</div>
				<br>
				<br>
				<input type="submit" value="Enviar">
			</form>
		</fieldset>
	</body>
</html>