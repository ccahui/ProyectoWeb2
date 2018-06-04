<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="modelo.administrador.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/administrador/modificar.js"></script>
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
			<li><a href="/administrador/insertar">INSERTAR ADMINISTRADOR</a></li>
			<li><a href="/administrador">REGISTRO DE ADMINISTRADORES</a>
			<li class="active"><a href="#">MODIFICAR ADMINISTRADOR</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR DATOS DEL PROFESOR</h4>
				<%
					Administrador admin = (Administrador) request.getAttribute("administrador");
				%>
				<form id="form1" class="form-horizontal"
					action="/administrador/modificar" method="POST">
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id"
								value=<%=admin.getId()%>> <input
								class="form-control" placeholder="Nombre Profesor"
								name="nombre" type="text" required=""
								value="<%=admin.getNombre()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Apellido:</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Apellido del Profesor" name="apellido"
								required type="text" value="<%=admin.getApellido()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-danger">Email:</label>
						<div class="col-sm-10">
							<div class="input-group ">
								<div class="input-group-addon">@</div>
								<input class="form-control"
									placeholder="Gmail Profesor" name="gmail"
									type="email" value="<%=admin.getGmail()%>">
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Estado</label>
						<div class="col-sm-10">
													<select class="form-control" name="estado">
								<option value="true">ACTIVO</option>
								<% if (!admin.isEstado()) {%>
								<option <%="selected"%> value="false">INACTIVO</option>
								<% } else {%>
								<option value="false">INACTIVO</option>
								<% }%>
							</select>
						</div>
					</div>
					<div class="text-right">
						<a href="/administrador" class="btn btn-danger">Cancelar</a>
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