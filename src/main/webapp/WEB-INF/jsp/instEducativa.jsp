<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Gestor de Institucion Educativa</title>
  <link rel="stylesheet" href="<c:url value='/resources/IE.css'/>">
  
</head>
<body>
	
		<header>
		<div class="logotipo">
<!-- 			<img src="Imagenes/wolf.jpg" width="100" alt=""> -->
		</div>
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
	<section class="main">
		<section class="articles">
			<article>
			<form:form method="post" action="instEduc.do" modelAttribute="colegio">
		<table>
			<tr>
				<td><SELECT NAME="accion" onchange="this.form['Busqueda'].value=this.value" SIZE="1">
						<OPTION VALUE="1">Búsqueda por Nombre</OPTION>
						<OPTION VALUE="2">Búsqueda por Ugel</OPTION>
						<OPTION VALUE="3">Búsqueda por Distrito</OPTION>
						<OPTION VALUE="4">Búsqueda por Id</OPTION>
				</SELECT></td>
				<td></td>

			</tr>
			
			<tr>
				<td><form:input path="institucion" /></td>
				<td><input type="submit" name="acto"  value="Realizar Busqueda"/></td>
			</tr>
		</table>

	</form:form>




	<h3>Colegios</h3>

	<c:if test="${!empty listacoles}">
		<table class="data" border="1">
			<tr>
				<th>identificador</th>
				<th>sector</th>
				<th>ugel</th>
				<th>institucion</th>
				<th>direccion</th>
				<th>distrito</th>
			</tr>
			<c:forEach items="${listacoles}" var="cole">
				<tr>
					<td>${cole.identificador}</td>
					<td>${cole.sector}</td>
					<td>${cole.ugel}</td>
					<td>${cole.institucion}</td>
					<td>${cole.direccion}</td>
					<td>${cole.distrito}</td>
					<%-- 		<td><a href="delete/${contact.id}">delete</a></td> --%>


				</tr>
			</c:forEach>

		</table>
	</c:if>
	<c:if test="${mensaje == 'ColegioNoregistrado'}">
		<c:out value="Colegio No Registrado, o No se ha ingresado un dato correcto"></c:out>
	</c:if>
	<c:if test="${mensaje == 'Noesentero'}">
		<c:out value="No se ha ingresado un valor entero"></c:out>
	</c:if>
				</article>

		</section>
	
		<aside>
			<h3>Consulta de Colegios</h3>
				<p>En la presente aplicación web usted podrá
				realizar la búsqueda de algunos centros educativos
				por "Nombre de Institución", por "Numero de Ugel"
				a la que pertenece la institución, por "Distrito"
				asi como por "Id" que presente cada institución,
				si el colegio no se encuentra registrado, o ingresa
				un valor que no es correcto de acuerdo al tipo de busqueda
				se mostrará un mensaje indicando la posible falla
				al momento de realizar la búsqueda, esperamos que sea de
				su agrado el uso de está sencilla pero funcional aplicación </p>
		</aside>
	</section>

	<footer>
		<p>
			Sistemas Distribuidos 2015-I
			 
		</p>
	</footer>
	


</body>
</html>