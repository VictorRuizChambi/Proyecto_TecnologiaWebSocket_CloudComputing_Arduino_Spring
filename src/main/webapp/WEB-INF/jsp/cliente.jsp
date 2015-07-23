<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Consulta de Colegios</title>
  <link rel="stylesheet" href="<c:url value='/resources/cliente.css'/>">
  <script src="<c:url value="/resources/cliente.js" />"></script>
  <script src="https://code.jquery.com/jquery-1.11.0.min.js"></script>
 
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



  <h1 class="int">Consultas Online Grupo FISITO</h1>

			<table width="100%" height="50%"> 
				<tr> 
					<td width="10%" height="10%" align="center" valign="middle"> 
					<img src="<c:url value="/resources/GrupoFisito.png" />" >

					</td> 
				</tr> 
			</table>
			
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

    <p class="new">
        Consulta: <input id="areaconsulta" type="text" size="50" maxlength="150" class="mensj"/>

        <button class="enviar">Enviar al Servidor</button>
      </p>
      
      <div id="central">
      	 <textarea  readonly id="conversa" cols="40" rows="10" style="resize: none"></textarea>
      
      </div>
      
      
      <script>

    

        $(document).ready(function() {
            $('.enviar').click(function(e){
              e.preventDefault();
              
        sendMessage();
        return false;
      });

            
    });

  
        $('.new .mensj').keypress(function(e) {
            if(e.which == 13) {
            	sendMessage();
                return false;
            }
        });

        
        </script>

	<footer>
		<p>
			Sistemas Distribuidos 2015-I
		</p>
	</footer>

</body>
</html>