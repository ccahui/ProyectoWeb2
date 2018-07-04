<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Carrera"%>
<%@page import="modelo.Factura"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de Facturas</title>
<meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/eliminar/eliminar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</head>
<body>
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
			<li><a href="/factura/insertar">INSERTAR FACTURA</a></li>
			<li class="active"><a href="">REGISTRO DE FACTURA</a>
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->
	<div class="container-fluid">
		<h4 class="titulo">REGISTRO DE FACTURAS</h4>
		<div class="table-responsive">
			<table class="table table-hover table-condensed">
				<tr>
					<th>Nombre</th>
					<th>Carrera</th>
					<th>Mes</th>
					<Th>Monto</th>
					<th>Fecha</th>

				</tr>
				<%
					List<Factura> array = (List<Factura>) request.getAttribute("array");
					List<Alumno>  arrayAlumno = (List<Alumno>) request.getAttribute("arrayAlumno");
					List<Carrera> arrayCarrera = (List<Carrera>) request.getAttribute("arrayCarrera");
					
					Alumno alumno;
					Carrera carrera;
					if (array.size() > 0) {
						int i = 0;
						for (Factura factura : array) {
							alumno = arrayAlumno.get(i);
							carrera = arrayCarrera.get(i);
				%>
				<tr>
					<td><%=alumno.getNombre()+" "+alumno.getApellido()%></td>
					<td><%=carrera.getNombre()%></td>
					<td><%=factura.mesDescripcion()%></td>
					<td><%=factura.getMonto() %></td>
					<td><%=factura.fechaDescripcion()%></td>

					<td class='btn btn-group'><a
						href="/factura/modificar?id=<%=factura.getId()%>"
						class="btn btn-primary btn-sm">Modificar</a> <a
						href="/factura/eliminar?id=<%=factura.getId()%>"
						class="btn btn-danger btn-sm">Eliminar</a></td>
				</tr>
				<%
					i++;}
					}
				%>

			</table>
			<div class="text-center">
				<a href="/factura" class="btn btn-primary">ACTUALIZAR</a> <br>
			</div>

		</div>

	</div>
</body>
</html>