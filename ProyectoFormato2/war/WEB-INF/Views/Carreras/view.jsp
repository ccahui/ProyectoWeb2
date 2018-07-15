<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.entity.*;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar Carrera</title>
<meta name='viewport'
	content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/carrera/modificar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
	<%
		Carrera carrera = (Carrera) request.getAttribute("carrera");
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
			<li><a href="/carreras/add">INSERTAR CARRERA</a></li>
			<li><a href="/carreras">REGISTRO DE CARRERAS</a>
			<li class="active"><a
				href="/carreras/view?id=<%=carrera.getId()%>">MODIFICAR
					CARRERA</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR DATOS DE LA CARRERA</h4>

				<form id="form1" class="form-horizontal" action="/carreras/view"
					method="POST">
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id" value=<%=carrera.getId()%>>
							<input class="form-control" placeholder="Nombre de la Carrera"
								name="nombre" type="text" required
								value="<%=carrera.getNombre()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Descripcion</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Ingrese descripcion de la Carrera" autofocus
								name="descripcion" type="text" required
								value="<%=carrera.getDescripcion()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Duracion</label>
						<div class="col-sm-10">
							<select class="form-control" name="semestres">
								<%
									for (int i = 1; i <= 6; i++) {
										if (carrera.getSemestres() == i) {
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
						<label class="text-danger col-sm-2 control-label">Mensualidad</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Ingrese mensualidad a Pagar" name="mensualidad"
								type="number" required value="<%=carrera.getMensualidad()%>">
						</div>
					</div>

					<div class="text-right">
						<a href="/carreras" class="btn btn-danger">Cancelar</a>
						<button class="btn btn-success" id="enviar" type="submit">Guardar
							Cambios
						</button>
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