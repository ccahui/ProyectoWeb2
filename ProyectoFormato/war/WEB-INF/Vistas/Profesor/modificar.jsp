<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.profesor.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/profesor/modificar.js"></script>
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
			<li><a href="/profesor/insertar">INSERTAR PROFESOR</a></li>
			<li><a href="/profesor">REGISTRO DE PROFESORES</a>
			<li class="active"><a href="#">MODIFICAR PROFESOR</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR DATOS DEL PROFESOR</h4>
				<%
					Profesor prof = (Profesor) request.getAttribute("profesor");
				%>
				<form id="form1" class="form-horizontal"
					action="/profesor/modificar" method="POST">
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id"
								value=<%=prof.getId()%>> <input
								class="form-control" placeholder="Nombre Profesor"
								name="nombre" type="text" required=""
								value="<%=prof.getNombre()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Apellido:</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Apellido del Profesor" name="apellido"
								required type="text" value="<%=prof.getApellido()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">DNI:</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Profesion Profesor" name="dni"
								type="text" value=<%=prof.getDni()%>>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2 control-label text-danger">Email:</label>
						<div class="col-sm-10">
							<div class="input-group ">
								<div class="input-group-addon">@</div>
								<input class="form-control"
									placeholder="Gmail Profesor" name="gmail"
									type="email" value=<%=prof.getGmail()%>>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Rol</label>
						<div class="col-sm-10">
							<select class="form-control" name="idRol">
								<option value="1">Rol 1</option>
								<option value="2">Rol 2</option>
								<option value="3">Rol 3</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Estado</label>
						<div class="col-sm-10">
													<select class="form-control" name="estado">
								<option value="true">ACTIVO</option>
								<% if (!prof.isEstado()) {%>
								<option <%="selected"%> value="false">INACTIVO</option>
								<% } else {%>
								<option value="false">INACTIVO</option>
								<% }%>
							</select>
						</div>
					</div>
					
					<div class="text-right">
						<a href="/profesor" class="btn btn-danger">Cancelar</a>
						<button class="btn btn-success" id="enviar" type="submit">Guardar Cambios</button>
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