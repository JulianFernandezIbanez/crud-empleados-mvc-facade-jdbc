<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<!-- El patron de diseño MVC (Model-View-Controller) es una arquitectura 
		de software que separa una aplicación en tres componentes principales: 
		el Modelo, la Vista y el Controlador. Esta separación permite una 
		mejor organización del código, facilita el mantenimiento y 
		mejora la escalabilidad de la aplicación.

		Concretamente:

		El Modelo: Comprende todo lo que tiene con los datos
		La Vista serian los archivos JSP
		Y el Controlador son los Servlets -->
		
		<!-- Conjuntamente con el patron MVC se utiliza el patron de diseño facade, que
		es un patron que divide al proyecto en capas, que pueden facilmente sustituibles,
		y que cada capa oculta a la anterior las complejidades de dicha implementacion

		Por ejemplo, es comun que una aplicacion/proyecto tengamos las capas siguientes

		Cada capa es un paquete, y cada paquete tiene una interfaz, 
		y una implementacion, de tal forma que si queremos cambiar 
		la implementacion de una capa, 
		no afectaria a las otras capas, 
		ya que cada capa solo conoce la interfaz de la capa anterior, 
		y no su implementacion.
		
		1- Capa DAO (Data Access Objectos), que seria el modelo, y donde se implementa 
		el acceso a las bases de datos con JDBC (Java Database Connectivity
		2- Capa de Servicios
		3- Capa de los controladores, que pueden haber varios
		4- Capas de implementaciones
		5- Capa model
		6- etc -->
	    <h1>Bienvenido a la aplicacion de gestion de Empleados</h1>
	</body>
</html>