<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.Curso"%>
<%@ page import="java.util.List"%>
<%@ page import="modelo.Carrera"%>
<%@ page import="modelo.Profesor"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar Curso</title>
   <meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/curso/modificar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
	<%
					Curso curso = (Curso) request.getAttribute("curso");
					List<Carrera> arrayCarrera =(List<Carrera>)request.getAttribute("arrayCarrera");
					List<Profesor> arrayProfesor = (List<Profesor>)request.getAttribute("arrayProfesor");
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
			<li><a href="/curso/insertar">INSERTAR CURSO</a></li>
			<li><a href="/curso">REGISTRO DE CURSOS</a>
			<li><a href="/curso/carrera?id=<%=curso.getIdCarrera() %>">CARRERA</a>
			<li class="active"><a
				href="/curso/modificar?id=<%=request.getParameter("id")%>">MODIFICAR
					NOTA</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR DATOS DEL CURSO</h4>
				<form id="form1" class="form-horizontal" action="/curso/modificar"
					method="POST">
					<%
						/*
					%>
					<div class="form-group">

						<!-- ESTO ESTA OCULTO -->

						<label class="text-danger col-sm-2 control-label">Carrera</label>
						<div class="col-sm-10">

							<input type="text" hidden name="id" value=<%=curso.getId()%>>

							if(array != null && array.size() > 0){ %> <select
								class="form-control" name="idCarrera">
								<%
									for(Carrera carrera : array) {
																																	if(carrera.getId().equals(curso.getIdCarrera())){
								%>

								<option value="<%=carrera.getId()%>" selected><%=carrera.getNombre()%></option>
								<%
									}else {
								%>
								<option value="<%=carrera.getId()%>"><%=carrera.getNombre()%></option>
								<%
									}
																																	}
								%>
							</select>
							<%
								}
							%>

							<!-- ESTA COMENTADO HASTA AQUI -->
						</div>
					</div>
					<%
						*/
					%>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id" value=<%=curso.getId()%>>
							<input class="form-control" placeholder="Nombre del Curso"
								name="nombre" type="text" required
								value="<%=curso.getNombre()%>">
						</div>
					</div>

					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Semestre</label>
						<div class="col-sm-10">
							<select class="form-control" name="semestre">
								<%
									for (int i = 1; i <= 6; i++) {
										if (curso.getSemestre() == i) {
								%>
								<option value=<%=i%> selected><%=i%></option>
								<%
									} else {
								%>
								<option value=<%=i%>><%=i%></option>

								<%
									}
								%>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<div class="form-group">

						<label class="text-danger col-sm-2 control-label">Asignar
							Profesor</label>
						<div class="col-sm-10">

							<select class="form-control" name="idProfesor">
								<option value="0" selected>Sin Definir</option>
								<%
									if (arrayProfesor != null && arrayProfesor.size() > 0) {
								%>



								<%
									for (Profesor profesor : arrayProfesor) {
											if (profesor.getId().equals(curso.getIdProfesor())) {
								%>

								<option value="<%=profesor.getId()%>" selected><%=profesor.getNombre()%></option>
								<%
									} else {
								%>
								<option value="<%=profesor.getId()%>"><%=profesor.getNombre()%></option>
								<%
									}
										}
								%>
								<%
									}
								%>
							</select>

							<!-- ESTA COMENTADO HASTA AQUI -->
						</div>
					</div>


					<div class="text-right">
						<a href="/curso/carrera?id=<%=curso.getIdCarrera()%>" class="btn btn-danger">Cancelar</a>
						<button class="btn btn-success" id="enviar" type="submit">Guardar
							Cambios</button>
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
</body>
</html>