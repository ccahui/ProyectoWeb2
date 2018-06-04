<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.alumno.Alumno"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/nota/insertar.js"></script>
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
		<a href="/" class="navbar-brand">Inicio </a>
	</div>
	<div class="collapse navbar-collapse" id="navegacion">
		<!-- Fijar el atributo role-->
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">INSERTAR NOTA</a></li>
			<li><a href="/nota">REGISTRO DE NOTAS</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">DATOS DE LA NOTA</h4>
				<form id="form1" class="form-horizontal" action="/nota/insertar"
					method="POST">
					<%
					List<Alumno> array = (List<Alumno>)request.getAttribute("array");
					%>
					<div class="form-group">
						<label class="text-info col-sm-2 control-label">Alumno</label>
						<div class="col-sm-10">
							<%
							if(array != null && array.size() > 0){
							%>
							<select class="form-control" name="idAlumno">
								<% for(Alumno alumno : array) {%>
								<option value= <%=alumno.getId() %>><%=alumno.getNombre()%></option>
								<%} %>
							</select>
							<% }%>
						</div>
					</div>
					<div class="text-right">
						<button class="btn btn-success" id="enviar" type="submit">Guardar
							Datos</button>
					</div>
				</form>
				<div class="text-center">
					<!--Simbolo de Loading -->
					<div id="cargando" style="display: inline-block"></div>
					<!--Resultado del Ajax -->
					<div id="resultado"></div>
				</div>
			</div>
		</div>
	</div>
	<br>
</html>