<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrera: Registro de Cursos</title>
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
			<li><a href="/cursos/add">INSERTAR CURSO</a></li>
			<li class=""><a href="/cursos">REGISTRO DE CURSOS</a>
			<li class="active"><a
				href="/cursos/carrera?id=<%=request.getParameter("id")%>">CARRERA</a>
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->
	<div class="container">
		<h4 class="titulo">
			Cursos de
			<%=request.getAttribute("nombreCarrera")%></h4>
		<div class="table-responsive">
			<table class="table table-hover table-condensed">
				<tr>
					<th>Curso</th>
					<th>Semestre</th>
					<th>Profesor que lo Dicta</th>
					<!--Semestre en el Que se Dicta -->
				</tr>

				<%
					List<Curso> array = (List<Curso>) request.getAttribute("array");//Codigo de las carrera idCarrera
					List<String> arrayProfesor = (List<String>) request.getAttribute("arrayProfesor");//Nombre de la Carrera

					int length = array.size();
					if (array != null && length > 0) {
						Curso curso;
						for (int i = 0; i < length; i++) {
							curso = array.get(i);
				%>
				<tr>
					<td><%=curso.getNombre()%></td>
					<td><%=curso.getSemestre()%></td>
					<td><%=arrayProfesor.get(i)%></td>
					<td class='btn btn-group'><a
						href="/cursos/view?id=<%=curso.getId()%>"
						class="btn btn-primary btn-sm">Modificar</a> <a
						href="/cursos/delete?id=<%=curso.getId()%>"
						class="btn btn-danger btn-sm">Eliminar</a></td>
				</tr>
				<%
					}
				}
				%>
			</table>
			<div class="text-center">
				<a href="/cursos/carrera?id=<%=request.getParameter("id")%>" class="btn btn-primary">ACTUALIZAR</a> <br>
			</div>

		</div>

	</div>
</body>
</html>