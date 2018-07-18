<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de Alumnos</title>
   <meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/eliminar/eliminar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
<% 
	Carrera carrera = (Carrera)request.getAttribute("carrera");
%>
	<header> <nav class="navbar navbar-default ">
	<div class="navbar-header">
		<!-- Id navegacion  debe coincidir con el nombre id de abajo-->
		<button class="navbar-toggle" data-toggle="collapse"
			data-target="#navegacion">
			<!-- Mostrar en dispositivos de lectura que no reconocen los iconens-->
			<span class="sr-only">Mostrar Navegacion</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a href="/usuario/inicio" class="navbar-brand">Inicio</a>
	</div>
	<div class="collapse navbar-collapse" id="navegacion">
		<!-- Fijar el atributo role-->
		<ul class="nav navbar-nav">
			<li><a href="/alumnos/add">INSERTAR ALUMNO</a></li>
			<li><a href="/alumnos">REGISTRO DE ALUMNOS</a>
			<li class="active"><a href="/alumnos/carrera?id=<%=carrera.getId() %>">CARRERA</a>
			
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->
	<div class="container-fluid">
		<h4 class="titulo">ALUMNOS DE : <%=carrera.getNombre() %></h4>
		<div class="table-responsive">
			<table class="table table-hover table-condensed">
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Semestre</th>
					<th>Dni</th>
					<Th>Gmail</th>
					<th>Estado</th>

				</tr>
				<% 
					List<Alumno> array = (List<Alumno>)request.getAttribute("array");
					
					int length;
					if(array!=null && (length = array.size() )> 0) {
						Alumno alumno;
						for(int  i = 0; i < length;i++){
							alumno = array.get(i);
				%>
				<tr>
					<td><%= alumno.getNombre() %></td>
					<td><%= alumno.getApellido() %></td>
					<td><%= alumno.getSemestre() %></td>
					<td><%= alumno.getDni() %></td>
					<td><%= alumno.getGmail() %></td>
					<td><%= alumno.estadoDescripcion()%></td>

					<td class='btn btn-group'><a
						href="/alumnos/view?id=<%= alumno.getId() %>"
						class="btn btn-primary btn-sm">Modificar</a> <a
						href="/alumnos/delete?id=<%= alumno.getId() %>"
						class="btn btn-danger btn-sm">Eliminar</a></td>
				</tr>
				<% }
				} %>

			</table>
			<div class="text-center">
				<a href="/alumnos/carrera?id=<%=carrera.getId() %>" class="btn btn-primary">ACTUALIZAR</a> <br>
			</div>

		</div>

	</div>
</body>
</html>