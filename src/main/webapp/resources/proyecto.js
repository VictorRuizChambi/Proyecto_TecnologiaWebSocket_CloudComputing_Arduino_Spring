
  var host = window.location.host;
  var path = window.location.pathname;
  var webCtx = path.substring(0, path.indexOf('/', 1));
  var endPointURL = "ws://" + host + webCtx + ":8000/ws";
  var Proyecto = null;

  
  function connect () {
  	
  	console.log("Conectando a ruta: " + endPointURL);
  	
  	Proyecto = new WebSocket(endPointURL);
      console.log(endPointURL);
      
      Proyecto.onopen=function (event)
      {
          console.log("Se abrio una conexion");
      };
      
      Proyecto.onerror=function (event){
      	console.log("Hubo un error en la conexion");
      };

      
      
      Proyecto.onmessage = function (frame) 
      {
          console.log("debug onmessage");    
             
              var texto = JSON.parse(frame.data);
              
              
              
              var mens = "";
           
      		mens = texto.mensaje;
      		turno = texto.turno;
            
      		var stringLength = mens.length;
      		
      			if(turno = "tres"){
	      			if(mens.charAt(stringLength - 1) ==='m'){
	      				document.getElementById("graficoDistancia").innerHTML=mens;
	      			}else{
	      				document.getElementById("graficoTemperatura").innerHTML=mens;
	      				
	      			}	
      					
      			}
      			

      };

  }


  function onError(){
  	console.log("Error en la conexion");
  }

  function disconnect () {
      sendMessage();
      Proyecto.close();
      
  }


  function sendMessage() 
  {
      console.log("debug sendmessage");

      
  }