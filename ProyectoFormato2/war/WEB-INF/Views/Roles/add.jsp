<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name='viewport'
	content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>

<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/role/insertar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<style>
.titulo {
	/*color:inherit;*/
	color: inherit;
	margin-bottom: 20px;
}

.alert {
	padding: 10px;
}
</style>
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
			<li class="active"><a href="#">INSERTAR ROL</a></li>
			<li><a href="/roles">REGISTRO DE ROLES</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h3 class="titulo text-center">INSERTAR ROLE </h3>
				<form id="form1" class="form-horizontal" action="/roles/add"
					method="POST">
					<div class="form-group">
						<label class="text-info col-sm-3 control-label">Nombre ROLE</label>
						<div class="col-sm-9">
							<input class="form-control"
								placeholder="Ingrese Nombre del Role " autofocus name="nombre"
								type="text" required="">
						</div>
					</div>
					
					<div class="text-right">
						<button class="btn btn-success" id="enviar" type="submit">Insertar Role</button>
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