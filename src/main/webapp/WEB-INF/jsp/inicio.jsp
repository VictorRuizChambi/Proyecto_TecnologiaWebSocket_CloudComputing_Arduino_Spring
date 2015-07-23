<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Consulta de Colegios</title>
  <link rel="stylesheet" href="<c:url value='/resources/inicio.css'/>">
 
  
</head>
<body>

	<header>

		<nav>
			<ul>
				<li><a href="inicio.html">Inicio</a></li>
				<li><a href="instEduc.do">Consultas</a></li>
				<li><a href="servicio.html">Servicios</a></li>
				<li><a href="cliente.html">Atención al cliente</a></li>
				<li><a href="proyecto.html">Proyectos</a></li>
				
			</ul>

		</nav>
	</header>

			<table width="100%" height="80%"> 
				<tr> 
					<td width="100%" height="80%" align="center" valign="middle"> 

					<img src="<c:url value="/resources/pasacalle.jpg" />" >

					</td> 
				</tr> 
			</table>
				

				<h2 align="center">
				Instituciones educativas de Secundaria por sector y UGEL
				Instituciones educativas <br>de Educación Básica Regular de Nivel 
				Secundaria por sector y UGEL</h2>


	<footer>
		<p>
			Sistemas Distribuidos 2015-I
		</p>
	</footer>

</body>
</html>