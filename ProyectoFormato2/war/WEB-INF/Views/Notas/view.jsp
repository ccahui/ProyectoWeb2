<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar Nota</title>
   <meta name='viewport' content='width=device-width, user-scalable=no, initial-scale=1.0, maximun-scale=1.0,minimun-scale=1.0'>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/nota/modificar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
	<%
		Nota nota = (Nota) request.getAttribute("nota");
		Alumno alumno = (Alumno) request.getAttribute("alumno");
		Curso curso = (Curso) request.getAttribute("curso");
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
			<li><a href="/notas/add">INSERTAR NOTA</a></li>
			<li><a href="/notas">REGISTRO DE NOTAS</a>
			<li><a href="/notas/carrera?id=<%=alumno.getIdCarrera()%>">CARRERA
			</a>
			<li><a href="/notas/carrera/curso?id=<%=curso.getId()%>">CURSO</a>
			<li class="active"><a
				href="/notas/view?id=<%=request.getParameter("id")%>">MODIFICAR
					NOTA</a>
		</ul>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row">
			<div class="col-sm-10 col-md-6">
				<!-- Crear Alumno -->
				<h4 class="titulo">MODIFICAR NOTA</h4>
				<form id="form1" class="form-horizontal" action="/notas/view"
					method="POST">

					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Alumno</label>
						<div class="col-sm-10">
							<input type="text" hidden name="id" value=<%=nota.getId()%>>
							<input class="form-control" 
								name="nombre" type="text" required
								value="<%=alumno.getNombre()%>" disabled="true">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Curso</label>
						<div class="col-sm-10">
							<input class="form-control"  name="curso"
								type="text" required value="<%=curso.getNombre()%>"
								disabled="true">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nota1</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="Nota1" name="nota1"
								type="number" min="0" max="20" value="<%=nota.getNota1() == null ? "":nota.getNota1() %>" min="0" max="20">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nota2</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="Nota2" name="nota2"
								type="number" min="0" max="20" value="<%=nota.getNota2() == null ? "":nota.getNota2() %>">
						</div>
					</div>
					<div class="form-group">
						<label class="text-danger col-sm-2 control-label">Nota3</label>
						<div class="col-sm-10">
							<input class="form-control" placeholder="Nota3" name="nota3"
								type="number" min="0" max="20" value="<%=nota.getNota3() == null ? "":nota.getNota3() %>">
						</div>
					</div>
					<div class="text-right">
						<a href="/notas/carrera/curso?id=<%=curso.getId()%>" class="btn btn-danger">Cancelar</a>
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