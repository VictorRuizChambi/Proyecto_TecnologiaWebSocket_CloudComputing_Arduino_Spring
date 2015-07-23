
  var host = window.location.host;
  var path = window.location.pathname;
  var webCtx = path.substring(0, path.indexOf('/', 1));
  var endPointURL = "ws://" + host + webCtx + ":8000/ws";
  var MENSAJES = null;


  /*Este es el código de la parte del servidor*/

  function connect() {
  	
  	console.log("Conectando a ruta: " + endPointURL);
  	
      MENSAJES = new WebSocket(endPointURL);
      console.log(endPointURL);
      
      MENSAJES.onopen=function (event)
      {
          console.log("Se abrio una conexion");
      };
      
      MENSAJES.onerror=function (event){
      	console.log("Hubo un error en la conexion");
      };

      MENSAJES.onmessage = function (frame) 
      {
          console.log("debug onmessage");    
          
//              var jsonObj = JSON.parse(event.data);     
              var texto = JSON.parse(frame.data);
              
              
              
              var mens = "";
            
      		mens = texto.mensaje;
      		turno = texto.turno;
            
      		if(mens!=="SERVIDOR: Disculpenos por la demora; ahora no tenemos solucion a su consulta"+
					" pero cuando la tengamos responderemos inmediatamente. Muchas Gracias\n\n" && turno!=="tres" ){
            
      			document.getElementById("conversa").value= document.getElementById("conversa").value +  mens;
//       			var obj = document.getElementById("conversa");
//       			obj.scrollTop(obj[0].scrollHeight); 
      			document.getElementById("conversa").scrollTop = document.getElementById("conversa").scrollHeight;  
      		}
      };

  }


  function onError(){
  	console.log("Error en la conexion");
  }

  function disconnect () {
      sendMessage();
      MENSAJES.close();
      
  }


  function sendMessage() 
  {
      console.log("debug sendmessage");
      
      var mensaje = document.getElementById("areaconsulta").value;
      mensaje = "SERVIDOR: " + mensaje;
      var jsonstr = JSON.stringify({ 'mensaje': mensaje + "\n\n", 'turno': "cero"});
      MENSAJES.send(jsonstr);
      document.getElementById("areaconsulta").value="";
      
  }
    

