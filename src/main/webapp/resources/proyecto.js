
  var host = window.location.host;
  var path = window.location.pathname;
  var webCtx = path.substring(0, path.indexOf('/', 1));
  var endPointURL = "ws://" + host + webCtx + ":8000/ws";
  var Proyecto = null;
  var randomData;
	

  
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
      		
      			if(turno === "tres"){
	      			if(mens.charAt(stringLength - 1) ==='m'){
	      				document.getElementById("graficoDistancia").innerHTML=mens;
//	      				actualizarGrafDistancia(mens);
	      			}else{
	      				document.getElementById("graficoTemperatura").innerHTML=mens;
	      				
	      				mens=mens.substr(0,stringLength - 3);
	      				
	      		         
	      				
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
  
  

  var opts = {
		  lines: 12,
		  angle: 0.5,
		  lineWidth: 0.1,
		  limitMax: 'false', 
		  percentColors: [[0.0, "#cccccc" ], [0.50, "#ffff00"], [1.0, "#ff0000"]], // !!!!
		  strokeColor: '#E0E0E0',
		  generateGradient: true,
		    pointer: {
		    length: 0.9, // The radius of the inner circle
		    strokeWidth: 0.035, // The rotation offset
		    color: '#000000' // Fill color
		  },
		  colorStart: '#cdcdcd',   // Colors
		  colorStop: '#ff0000',    // just experiment with them
		  strokeColor: '#000000',   // to see which ones work best for you
		  generateGradient: true
		};
		var target = document.getElementById('foo'); // your canvas element
		var gauge = new Donut(target).setOptions(opts); // create sexy gauge!
		gauge.maxValue = 360; // set max gauge value
		gauge.animationSpeed = 32; // set animation speed (32 is default value)
		gauge.set(270); // set actual value



	
	


