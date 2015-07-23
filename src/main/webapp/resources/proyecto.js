
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
	      				actualizarGrafDistancia(mens);
	      			}else{
	      				document.getElementById("graficoTemperatura").innerHTML=mens;
	      				
	      				mens=mens.substr(0,stringLength - 3);
	      				
	      			    var point = [ (new Date()).getTime(), parseFloat(mens) ];

	      		         actualizarGrafTemp(point);
	      				
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
  
  
  
function actualizarGrafDistancia(dist){  
  
  $(function () {
	    $('#ContDist').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: 0,
	            plotShadow: false
	        },
	        title: {
	            text: 'Sensor<br>Distancia<br>SSDD 2015-I',
	            align: 'center',
	            verticalAlign: 'middle',
	            y: 40
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}cm</b>'
	        },
	        plotOptions: {
	            pie: {
	                dataLabels: {
	                    enabled: true,
	                    distance: -50,
	                    style: {
	                        fontWeight: 'bold',
	                        color: 'white',
	                        textShadow: '0px 1px 2px black'
	                    }
	                },
	                startAngle: -90,
	                endAngle: 90,
	                center: ['50%', '75%']
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'Distancia',
	            innerSize: '50%',
	            data: [
	                ['Sensor HC-SR04',   dist]
	            ]
	        }]
	    });
	});
}



function actualizarGrafTemp(point){
	
	var shift = randomData.data.length > 60;
	randomData.addPoint(point, true, shift);
	
$('#GraficTemperatura').highcharts({
chart : {
type : 'line',
events : {
  load : function() {
    randomData = this.series[0];
  }
}
},
title : {
text : false
},
xAxis : {
type : 'datetime',
minRange : 60 * 1000
},
yAxis : {
title : {
  text : false
},
max: 100
},
legend : {
enabled : false
},
plotOptions : {
series : {
  threshold : 0,
  marker : {
    enabled : false
  }
}
},
series : [ {
name : 'Data',
  data : [ ]
} ]
});

}