<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Consulta de Colegios</title>
  <link rel="stylesheet" href="<c:url value='/resources/cliente.css'/>">
   <script src="http://bernii.github.io/gauge.js/dist/gauge.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
  <script src="<c:url value="/resources/proyecto.js" />"></script>
  
</head>
<body >

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



  <h1 class="int">Medición de Temperatura y Distancia</h1>


			
<script type="text/javascript">
function startTime(){
today=new Date();
h=today.getHours();
m=today.getMinutes();
s=today.getSeconds();
m=checkTime(m);
s=checkTime(s);
per=checkHour(h); 
h=acomodarHora(h);
document.getElementById('reloj').innerHTML=h+":"+m+":"+s+" " + per;
t=setTimeout('startTime()',500);}

	function checkTime(i)
	{
		if (i<10) {
			i="0" + i;
		}
		
		return i;
	}

	function checkHour(j)
	{
		var tp = "pm";
		if (j<12) {
			tp = "am";
		}
		
		return tp;
	}

	function acomodarHora(g){
			if(g>12){
				g=g-12;}
			return g;
		}


window.onload=function(){startTime();connect();}
</script>



	<div   id="reloj" style="font-size:20px;"></div>
    
    <p id="graficoTemperatura" style="font-size:20px;"></p>
    <p id="GraficTemperatura" style="font-size:20px;"></p>
    <p id="graficoDistancia" style="font-size:20px;"></p>
    <canvas id="foo"></canvas>

	<div id="ContDist" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>


	<footer>
		<p>
			Sistemas Distribuidos 2015-I
		</p>
	</footer>

</body>
</html>