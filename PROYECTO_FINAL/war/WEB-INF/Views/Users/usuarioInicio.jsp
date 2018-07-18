<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name='viewport'
	content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/profesor/eliminar.js"></script>

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
		<a href="/inicio" class="navbar-brand">Inicio</a>
	</div>

	<div class="collapse navbar-collapse" id="navegacion">
		<!-- Fijar el atributo role-->
		<ul class="nav navbar-nav">
			<li class="active"><a href="/profesor/misCursos">Cursos</a>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/cerrarSession"> CERRAR SESSION </a></li>
		</ul>
	</div>
	</nav> </header>

	<!--TABLA -->
	<div class="container">
		<h4>
			<b>Lista de Accesos</b>
		</h4>
		<div class="row">
			<div class="col-md-5">
				<div class="list-group">
					<a href="/carreras" class="list-group-item">Carrera</a> <a
						href="/cursos" class="list-group-item">Curso</a> <a
						href="/profesores" class="list-group-item">Profesor</a> <a
						href="/alumnos" class="list-group-item">Alumno</a> <a
						href="/notas" class="list-group-item">Nota</a> <a href="/users"
						class="list-group-item">Usuario</a> <a href="/facturas"
						class="list-group-item">Factura</a> <a href="/roles"
						class="list-group-item">Role</a> <a href="/access"
						class="list-group-item">Acceso</a> <a href="/resources"
						class="list-group-item">Recurso</a><a href="/cursos/cantidadAlumnos"
						class="list-group-item">REPORTE DE CURSO CON MAYOR ALUMNADO</a>

				</div>
			</div>
		</div>
	</div>

</body>
</html>