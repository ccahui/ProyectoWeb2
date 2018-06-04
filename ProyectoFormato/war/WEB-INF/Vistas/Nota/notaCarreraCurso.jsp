<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.nota.Nota"%>
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
		<li><a href="/nota/insertar">INSERTAR NOTA</a></li>
			<li ><a href="/nota">REGISTRO DE NOTAS</a></li>
			<li ><a href="/nota/carrera?id=<%= request.getAttribute("idCarrera")%>">CARRERA</a></li>
			<li class="active"><a href="/nota/carrera/curso?id=<%= request.getParameter("id") %>">CURSO</a>
			
		</ul>
	</div>
	</nav> </header>
	<!--TABLA -->
	<div class="container-fluid">
		<h4 class="titulo">Notas: <%= request.getAttribute("nombreCurso") %> </h4>
		<div class="table-responsive">
			<table class="table table-hover table-condensed">
				<tr>
					<th>Alumno</th>
					<th>Curso</th>
					<th>Nota1</th>
					<th>Nota2</th>
					<th>Nota3</th>
				</tr>
				
				<% 
					List<Nota> array = (List<Nota>)request.getAttribute("array");//Codigo de las carrera idCarrera
					List<String> arrayAlumno = (List<String>)request.getAttribute("arrayAlumno");//Nombre de la Carrera
					List<String> arrayCurso = (List<String>)request.getAttribute("arrayCurso");//Nombre
					int length = array.size();
					if(array!=null && length > 0) {
						Nota nota;
						for(int  i = 0; i < length;i++){
							nota = array.get(i);
				%>
				<tr>
					<td><%= arrayAlumno.get(i) %></td>
					<td><%= arrayCurso.get(i) %></td>
					<td><%=nota.getNota1() == null ? "":nota.getNota1() %></td>
					<td><%=nota.getNota2()== null ? "":nota.getNota1() %></td>
					<td><%=nota.getNota3() == null ? "":nota.getNota1()%></td>		
					<td class='btn btn-group'><a
						href="/nota/modificar?id=<%= nota.getId() %>"
						class="btn btn-primary btn-sm">Modificar</a> <a
						href="/nota/eliminar?id=<%= nota.getId() %>"
						class="btn btn-danger btn-sm">Eliminar</a></td>
				</tr>
				<% }
				} %>

			</table>
			<div class="text-center">
				<a href="/nota" class="btn btn-primary">ACTUALIZAR</a> <br>
			</div>

		</div>

	</div>
</body>
</html>