<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alumno Nota</title>
<meta name='viewport'
	content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
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
		<a href="/inicio" class="navbar-brand">Inicio</a>
	</div>
	<div class="collapse navbar-collapse" id="navegacion">
		<!-- Fijar el atributo role-->
		<ul class="nav navbar-nav">
			<li class="active"><a href="/alumno/miNota">ALUMNO NOTA</a>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/cerrarSession"> CERRAR SESSION </a></li>
		</ul>
	</div>
	</nav> </header>
	<%
		Alumno alumno = (Alumno) request.getAttribute("alumno");
		Carrera carrera = (Carrera) request.getAttribute("carrera");
		List<String> arrayCurso = (List<String>) request.getAttribute("arrayCurso");
		List<Nota> arrayNota = (List<Nota>) request.getAttribute("arrayNota");
	%>
	<!--TABLA -->
	<div class="container">
		<div>
			<h4 class="titulo">
				Carrera Profesional:
				<%=carrera.getNombre()%></h4>
			<h5 class="titulo">
				Alumno:
				<%=alumno.getNombre() + " " + alumno.getApellido()%></h5>
			<h5 class="titulo">
				Semestre:
				<%=alumno.getSemestre()%></h5>

		</div>

		<div class="table-responsive">
			<table class="table table-hover table-condensed">
				<tr>
					<th>Curso</th>
					<Th>Nota1</th>
					<th>Nota2</th>
					<th>Nota3</th>
					<th>Promedio Final</th>
				</tr>
				<%
					int length;
					if (arrayNota != null && (length = arrayNota.size()) > 0) { //Encuentras el Error ? que tonto
						Nota nota;
						for (int i = 0; i < length; i++) {
							nota = arrayNota.get(i);
				%>
				<tr>
					<td><%=arrayCurso.get(i)%></td>
					<td><%=nota.getNota1() == null ? "" : nota.getNota1()%></td>
					<td><%=nota.getNota2() == null ? "" : nota.getNota2()%></td>
					<td><%=nota.getNota3() == null ? "" : nota.getNota3()%></td>
					<td><b class="text-info"><%=nota.notaFinal() == null ? "" : nota.notaFinal()%></b></td>
				<tr>
					<%
						}
						}
					%>
				
			</table>

		</div>

	</div>
</body>
</html>