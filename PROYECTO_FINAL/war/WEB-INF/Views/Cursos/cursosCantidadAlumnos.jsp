<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.*"%>
<%@page import="controller.CursoAux"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrera: Cursos</title>
   <meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
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
			<li class="active"><a href="/cursos/cantidadAlumnos">REPORTE DE CURSOS ALUMNO </a>
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->

	<div class="container">
		<h4 class="titulo">REGISTRO DE MATRICULAS DE CURSOS</h4>
		<div class="table-responsive col-md-7">
			<table class="table table-hover table-condensed ">
				<tr>
					<th>Codigo</th>
					<th>Curso</th>
					<th>Alumnos Matriculados</th>
					<!--Semestre en el Que se Dicta -->
				</tr>

				<%
					List<CursoAux> array = (List<CursoAux>) request.getAttribute("array");
					if(array!=null){
						for(CursoAux curso:array){
				%>
				<tr>
					<td><%=curso.getId()%></td>
					<td><%=curso.getNombre()%></td>
					<td><%=curso.getCantidadAlumnos()%></td>
				</tr>
				<%
					}
				}
				%>
			</table>

		</div>

	</div>
	
</body>
</html>