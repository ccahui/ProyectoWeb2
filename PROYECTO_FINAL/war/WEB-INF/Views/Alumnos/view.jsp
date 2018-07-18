<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.entity.*"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar Nota</title>
   <meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/alumno/modificar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
	<%
		Alumno alumno = (Alumno) request.getAttribute("alumno");
		List<Carrera> array = (List<Carrera>) request.getAttribute("array");
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
			<li><a href="/alumnos/carrera?id=<%=alumno.getIdCarrera()%>">CARRERA</a>
			<li class="active"><a
				href="/alumnos/view?id=<%=alumno.getId()%>">MODIFICAR ALUMNO</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR DATOS DEL ALUMNO</h4>

				<form id="form1" class="form-horizontal" action="/alumnos/view"
					method="POST">
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id" value=<%=alumno.getId()%>>
							<input class="form-control" placeholder="Nombre Alumno"
								name="nombre" type="text" required
								value="<%=alumno.getNombre()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Apellido:</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="Apellido del Alumno"
								name="apellido" required type="text"
								value="<%=alumno.getApellido()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Carrera</label>
						<div class="col-sm-10">
							<%
								if (array != null && array.size() > 0) {
							%>
							<select disabled class="form-control" name="idCarrera">
								<%
									for (Carrera carrera : array) {
											if (carrera.getId().equals(alumno.getIdCarrera())) {
								%>

								<option value="<%=carrera.getId()%>" selected><%=carrera.getNombre()%></option>
								<%
									} else {
								%>
								<option value="<%=carrera.getId()%> "><%=carrera.getNombre()%></option>
								<%
									}
										}
								%>
							</select>
							<%
								}
							%>
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Semestre</label>
						<div class="col-sm-10">
							<select class="form-control" name="semestre">
								<%
									for (int i = 1; i <= 6; i++) {
										if (alumno.getSemestre() == i) {
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
						<label class="col-sm-2 control-label text-danger">Email</label>
						<div class="col-sm-10">
							<div class="input-group ">
								<div class="input-group-addon">@</div>
								<input class="form-control" placeholder="Gmail del Alumno"
									name="gmail" type="email" value="<%=alumno.getGmail()%>">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">DNI</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="DNI del Alumno"
								name="dni" type="text" pattern = "[0-9]{8}"value=<%=alumno.getDni()%>>
						</div>
					</div>

					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Estado</label>
						<div class="col-sm-10">
							<select class="form-control" name="estado">
								<option value="true">ACTIVO</option>
								<%
									if (!alumno.isEstado()) {
								%>
								<option <%="selected"%> value="false">INACTIVO</option>
								<%
									} else {
								%>
								<option value="false">INACTIVO</option>
								<%
									}
								%>
							</select>
						</div>
					</div>

					<div class="text-right">
						<a href="/alumnos/carrera?id=<%=alumno.getIdCarrera() %>" class="btn btn-danger">Cancelar</a>
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