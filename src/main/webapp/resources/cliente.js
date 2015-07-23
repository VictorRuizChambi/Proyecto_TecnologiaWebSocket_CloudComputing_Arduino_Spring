
  var host = window.location.host;
  var path = window.location.pathname;
  var webCtx = path.substring(0, path.indexOf('/', 1));
  var endPointURL = "ws://" + host + webCtx + ":8000/ws";
  var Cliente = null;


  /*Este es el código de la parte del servidor*/

  function connect () {
  	
  	console.log("Conectando a ruta: " + endPointURL);
  	
  	Cliente = new WebSocket(endPointURL);
      console.log(endPointURL);
      
      Cliente.onopen=function (event)
      {
          console.log("Se abrio una conexion");
      };
      
      Cliente.onerror=function (event){
      	console.log("Hubo un error en la conexion");
      };

      Cliente.onmessage = function (frame) 
      {
          console.log("debug onmessage");    
          
//              var jsonObj = JSON.parse(event.data);     
              var texto = JSON.parse(frame.data);
              
              
              
              var mens = "";
           
      		mens = texto.mensaje;
      		turno = texto.turno;
      		console.log(turno);
      		if( turno !=="tres"){
            console.log("entro envio a cliente");
            document.getElementById("conversa").value= document.getElementById("conversa").value +  mens;
      	
  			document.getElementById("conversa").scrollTop = document.getElementById("conversa").scrollHeight;
      		}

      };

  }


  function onError(){
  	console.log("Error en la conexion");
  }

  function disconnect () {
      sendMessage();
      Cliente.close();
      
  }


  function sendMessage() 
  {
      console.log("debug sendmessage");

      
      

      var mensaje = document.getElementById("areaconsulta").value;
      	mensaje = "CLIENTE: " + mensaje;
      var jsonstr = JSON.stringify({ 'mensaje': mensaje + "\n\n", 'turno': "uno"});
      Cliente.send(jsonstr);
      document.getElementById("areaconsulta").value="";
      
  }