<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/administrador/insertar.js"></script>
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
			<li class="active"><a href="#">INSERTAR ADMINISTRADOR</a></li>
			<li><a href="/administrador">REGISTRO DE ADMINISTRADORES</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">DATOS DEL ADMINISTRADOR</h4>
				<form id="form1" class="form-horizontal" action="/administrador/insertar"
					method="POST">
					<div class="form-group">
						<label class="text-info col-sm-2 control-label">Nombre</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Ingrese Nombre del Profesor" autofocus
								name="nombre" type="text" required="">
						</div>
					</div>
					<div class="form-group">
						<label class="text-info col-sm-2 control-label">Apellido</label>
						<div class="col-sm-10">
							<input class="form-control"
								placeholder="Ingrese Apellido del Profesor" name="apellido"
								required type="text">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label text-info">Email</label>
						<div class="col-sm-10">
							<div class="input-group ">
								<div class="input-group-addon">@</div>
								<input class="form-control"
									placeholder="Ingrese gmail del Profesor" name="gmail"
									type="email">
							</div>
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