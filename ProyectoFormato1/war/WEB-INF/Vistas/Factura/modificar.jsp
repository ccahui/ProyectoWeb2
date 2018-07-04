<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="modelo.Alumno"%>
<%@page import="modelo.Carrera"%>
<%@page import="modelo.Factura"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar Factura</title>
   <meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/factura/modificar.js"></script>
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
			<li><a href="/factura/insertar">INSERTAR FACTURA</a></li>
			<li><a href="/factura">REGISTRO DE FACTURAS</a>
			<li class="active"><a href="#">MODIFICAR FACTURA</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR DATOS DE LA FACTURA</h4>
				<%
				Factura factura = (Factura) request.getAttribute("factura");
				Alumno  alumno = (Alumno) request.getAttribute("alumno");
				Carrera carrera = (Carrera) request.getAttribute("carrera");
				%>
				<form id="form1" class="form-horizontal"
					action="/factura/modificar" method="POST">
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id" value=<%=factura.getId()%>>
							<input class="form-control" 
								name="nombre" type="text" required=""
								value="<%=alumno.getNombre()+" "+alumno.getApellido()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-danger">Carrera</label>
						<div class="col-sm-10">
								<input class="form-control" 
									name="idCarrera" type="email" value="<%=carrera.getNombre()%>" disabled>
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Monto</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="Costo de la Mensualidad"
								name="monto" required type="text"
								value="<%=factura.getMonto()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Mes</label>
						<div class="col-sm-10">
							<select class="form-control" name="mes">
								<% 
									int mesesA = 12;
									for(int i = 1;i<= mesesA;i++){
									if(i == factura.getMes())
									 {%>
								<option value="<%=i%>" selected><%=factura.mesDescripcion()%></option>
								<%}
									else {
									%>
									<option value="<%=i%>"><%= Factura.mesDescripcion(i)%></option>
								<%}} %>
							</select>
						</div>
					</div>
					<div class="text-right">
						<a href="/factura" class="btn btn-danger">Cancelar</a>
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