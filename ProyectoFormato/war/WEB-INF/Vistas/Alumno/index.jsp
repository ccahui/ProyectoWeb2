<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.carrera.Carrera"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
		<a href="/" class="navbar-brand">Inicio</a>
	</div>
	<div class="collapse navbar-collapse" id="navegacion">
		<!-- Fijar el atributo role-->
		<ul class="nav navbar-nav">
			<li><a href="/alumno/insertar">INSERTAR ALUMNO</a></li>
			<li class="active"><a href="">REGISTRO DE ALUMNOS</a>
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->
	<div class="container">
		<h4 class="titulo">REGISTRO DE ALUMNOS</h4>

		<div class="row">
			<div class="col-md-5">
				<div class="list-group">

					<%
						List<Carrera> array = (List<Carrera>) request.getAttribute("array");
						if (array != null) {
							for (Carrera carrera : array) {
					%>
					<a href="/alumno/carrera?id=<%=carrera.getId()%>"
						class="list-group-item"><%=carrera.getNombre()%></a>
					<%
						}
						}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>