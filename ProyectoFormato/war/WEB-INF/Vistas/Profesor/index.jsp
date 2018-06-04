<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.profesor.Profesor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="/CSS/bootstrap.min.css">
<link rel='stylesheet' href="/CSS/cargando.css">
<script type="text/javascript" src="/js/jQuery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

<script>
            $(document).ready(function(){
                $('.btn-danger').click(function(){
                    var a = confirm("Estas Seguro de Eliminar");
                    if(!a){
                        return false;
                    }
                });
                
            });
            
        </script>
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
			<li class="active"><a href="">REGISTRO DE PROFESORES</a>
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->
	<div class="container-fluid">
		<h4 class="titulo">REGISTRO DE PROFESORES</h4>
		<div class="table-responsive">
			<table class="table table-hover table-condensed">
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>DNI</th>
					<th>Gmail</th>
					<Th>Rol</th>
					<th>Estado</th>

				</tr>
				<% 
					List<Profesor> array = (List<Profesor>)request.getAttribute("array");
				
					if(array.size() > 0) {
						
						for(Profesor prof:array){
				%>
				<tr>
					<td><%= prof.getNombre() %></td>
					<td><%= prof.getApellido() %></td>
					<td><%= prof.getDni() %></td>
					<td><%= prof.getGmail() %></td>
					<td><%= prof.getIdRol() %></td>
					<td><%= prof.isEstado() %></td>

					<td class='btn btn-group'><a
						href="/profesor/modificar?id=<%= prof.getId() %>"
						class="btn btn-primary btn-sm">Modificar</a> <a
						href="profesor/eliminar?id=<%= prof.getId() %>"
						class="btn btn-danger btn-sm">Eliminar</a></td>
				</tr>
				<% }
				} %>

			</table>
			<div class="text-center">
				<a href="/profesor" class="btn btn-primary">ACTUALIZAR</a> <br>
			</div>

		</div>

	</div>
</body>
</html>