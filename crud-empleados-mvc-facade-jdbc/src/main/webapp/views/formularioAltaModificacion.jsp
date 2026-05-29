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
		%> 
		<h1>Formulario de Alta/Modificacion de Empleado</h1>
		
		<fieldset>
			<legend>Formulario de Gestion de Empleado</legend>
			<form action="#" method="post">
				<div>
					<label for="nombre">Nombre: </label>
					<input type="text" id="nombre" name="nombre" required placeholder="Introduzca su nombre">
				</div>
				<div>
					<label for="primerApellido">Primer Apellido: </label>
					<input type="text" id="primerApellido" name="primerApellido" required placeholder="Introduzca su primer apellido">
				</div>
				<div>
					<label for="segundoApellido">Segundo Apellido: </label>
					<input type="text" id="segundoApellido" name="segundoApellido" placeholder="Introduzca su segundo apellido">
				</div>
				<div>
					<label for="fechaAlta">Fecha de Alta: </label>
					<input type="date" name="fechaAlta" id="fechaAlta" required>
				</div>
				<div>
					<fieldset>
						<legend>Genero: </legend>
						<label for="hombre">Hombre</label>
						<input type="radio" id="hombre" name="genero" value="HOMBRE" required>
						<br>
						<label for="mujer">Mujer</label>
						<input type="radio" id="mujer" name="genero" value="MUJER" required>
						<br>
						<label for="otro">Otro</label>
						<input type="radio" id="otro" name="genero" value="OTRO" required>
					</fieldset>
				</div>
				<div>
					<label for="salario">Salario: </label>
					<input type="text" id="salario" name="salario" required placeholder="Introduzca su salario">
				</div>
				<div>
					<label for="departamento">Seleccione su departamento</label>
					<select id="departamento" name="departamento" required>
						<option></option>
						<% for (Departamento departamento : departamentos) {%>
							<option value=<%= departamento.id() %>><%= departamento.nombre() %></option>
						<%
							}	
						%>
					</select>
				</div>
				<div>
					<label for="correos">Correos: </label>
					<input type="text" id="correos" name="correos" placeholder="Añada uno o varios separados por ;">
				</div>
				<div>
					<label for="telefonos">Telefonos: </label>
					<input type="text" id="telefonos" name="telefonos" placeholder="Añada uno o varios separados por ;">
				</div>
				<br>
				<br>
				<input type="submit" value="Enviar">
			</form>
		</fieldset>
	</body>
</html>