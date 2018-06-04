<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.nota.Nota"%>
<%@page import="modelo.curso.Curso"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/profesor/misCursosNota.js"></script>
<style>
/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/
/* 
    Created on : 06/01/2018, 02:47:48 PM
    Author     : Christian
*/
.btn-sm {
	height: 25px;
	padding-top: 2px
}

input {
	width: 50px;
	height: 30px;
}

.rpta {
	border-top: white solid 2px
}

@media screen and (max-width: 768px ) {
	.btn-group {
		width: 200px;
	}
}
</style>
</head>
<body>
	<% 
		Curso curso = (Curso)request.getAttribute("curso");
		
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
		<a href="/" class="navbar-brand">Inicio</a>
	</div>
	<div class="collapse navbar-collapse" id="navegacion">
		<!-- Fijar el atributo role-->
		<ul class="nav navbar-nav">
			<li><a href="/profesor/misCursos">CURSO</a></li>
			<li class="active"><a
				href="/profesor/misCursos/nota?id=<%= curso.getId()%>">NOTA</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<% 
							List<Nota> array = (List<Nota>)request.getAttribute("array");//Codigo de las carrera idCarrera
							List<String> arrayAlumno = (List<String>)request.getAttribute("arrayAlumno");//Nombre de la Carrera
							%>
		<div class='panel panel-default'>
			<div class='panel-heading'>
				<h4 class='panel-title'><%=curso.getNombre()%></h4>
			</div>
			<DIV class='panel-body'>
				<div class="table-responsive  ">
					<table class="table table-hover table-condensed">
						<tr>
							<th>Codigo</th>
							<th>Nombre Alumno</th>
							<Th>Nota1</Th>
							<Th>Nota2</Th>
							<Th>Nota3</Th>
							<th></th>
						</tr>

						<%
							int length = array.size();
							if(array!=null && length > 0) {
								Nota nota;
								for(int  i = 0; i < length;i++){
									nota = array.get(i);
						%>


						<tr>
							<td><%= nota.getId() %></td>
							<td><%= arrayAlumno.get(i)%></td>
							<td><%=nota.getNota1() == null ? "":nota.getNota1() %></td>
							<td><%=nota.getNota2()== null ? "":nota.getNota1() %></td>
							<td><%=nota.getNota3() == null ? "":nota.getNota1()%></td>

							<td class='btn-group'>
								<button class='insertar btn btn-primary btn-sm'>
									<Strong>Insertar</strong>
								</button>
								<button class='cancelar btn btn-danger btn-sm'>
									<Strong>Cancelar</strong>
								</button>
							</td>
						</tr>
						<tr class='rpta' hidden>
							<td></td>
							<td></td>
							<td><input class='valor' type='number'></td>
							<td><input class='valor' type='number'></td>
							<td><input class='valor' type='number'></td>
							<td>
								<button class='actualizar btn btn-success btn-sm'>
									<strong>Actualizar</strong>
								</button>
							</td>
						</tr>

						<%}}
                            %>
					</table>
				</div>
			</DIV>
		</div>
	</div>


</body>
</html>