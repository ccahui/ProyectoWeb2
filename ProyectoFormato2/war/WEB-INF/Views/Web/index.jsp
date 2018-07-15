<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.entity.*" %>
<%@ page import="java.util.List"%>
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
<script type="text/javascript" src="/js/usuario/insertar.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</head>
<body>
	<header>
		<!-- MENU DE NAVEGACION donde inverse da el background black-->
		<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
			<div class="container">
				<!-- Collapsed, solo se mostrara para dispositivos moviles, el menu trabaja con responsive-->
				<div class="navbar-header">
					<button class="navbar-toggle collapsed" data-toggle="collapse"
						data-target="#navegacion">
						<!-- Para dispositivos de lectura que no puedan reconocer el button -->
						<span class="sr-only">Desplegar/Ocultar</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a href="" class="navbar-brand" id="colegio">INSTITUTO SUPERIOR</a>
				</div>
				<!--Menu Inicia-->
				<div class="collapse navbar-collapse" id="navegacion">
					<ul class="nav navbar-nav">
						<li><a href="/alumno/miNota">Alumno</a></li>
						<li><a href="/profesor/misCursos">Docente </a></li>
						<li><a href="/usuario/inicio">Usuarios</a></li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
						
						<% 
						String estadoSession = (String)request.getAttribute("estado");
						String gmail = (String)request.getAttribute("gmail");
						if(estadoSession == null){
						%>
						<li class="dropdown">
							<!--Un submenu desplegable --> <a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Iniciar Session<span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/alumno/miNota">Alumno</a></li>
								<li><a href="/profesor/misCursos">Docente </a></li>
								<li><a href="/usuario/inicio">Usuarios</a></li>
							</ul>
						</li>
						<%} else {%>
						<li class="dropdown">
							<!--Un submenu desplegable --> <a class="dropdown-toggle"
							data-toggle="dropdown" href="#"><%=gmail %><span
								class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/cerrarSession">Cerrar Session</a></li>
							</ul>
						</li>
						<%} %>
						
					</ul>


				</div>

			</div>
		</nav>
		<!--Culmina el menu de Navegacion -->
	</header>
	<!--FRANJA de TITULO de la Pagina-->
	<div class="jumbotron">
		<div class="container">
			<h1>ICit</h1>
			<p>Mas de 20 años en la enseñanza</p>
		</div>
	</div>
	<!--SLIDER DE LA PAGINA, imagenes -->
	<div class="container" id="slider">
		<div id="myCarousel" class="carousel slider" data-ride="carousel">
			<!-- Indicators, pequenas animacione que nos proporciona JQeury con la clase .carousel-slider -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
			</ol>
			<!--Imagenes que ddisoinemos -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="/images/slider-img-4.jpg" alt="Los Angeles">
				</div>
				<div class="item">
					<img src="/images/computacion-e-informatica.jpg" alt="Chicago">
				</div>
				<div class="item">
					<img src="/images/contabilidad.jpg" alt="New York">
				</div>
				<div class="item">
					<img src="/images/secretariado-ejecutivo.jpg" alt="New York">
				</div>
			</div>

			<!--Controles que nos permite <- -> dezplazar las imagenes -->
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
				<span class="glyphicon glyphicon-chevron-left"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<br>
	<!--CONTENIDO DE LA PAGINA-->
	<div class="container ">
		<div class="col-md-9">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class='panel-title'>
						<strong>Vision</strong>
					</h3>
				</div>
				<p class="panel-body text-justify">SEl instituto ICIT tiene como
					vision formar alumnos innovadores, reflexivos, lideres y críticos
					con capacidad investigadora dentro de la práctica constante de los
					valores que nos permita desarrollar actitudes y aptitudes
					científico culturales.</p>
			</div>
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class='panel-title'>
						<strong>Mision</strong>
					</h3>
				</div>
				<p class="panel-body text-justify">El instituto superior
					tecnológico ICIT será en los próximos años una entidad que fomenta
					el fortalecimiento para formar estudiantes creativos , críticos con
					un proyecto de vida a través de los valores como responsabilidad y
					respeto mediante la ejecución de programas académicos.</p>

			</div>

		</div>
	</div>
	<!--PIE DE PAGINA-->
	<footer>
		<div class="container">
			<div class="text-center">
				<p class="text-info">
					Toda la informaci&oacute;n en este sitio Programacion Web2 <strong>&copy;2018
						.</strong>
				</p>
			</div>
		</div>
	</footer>
</body>
</html>