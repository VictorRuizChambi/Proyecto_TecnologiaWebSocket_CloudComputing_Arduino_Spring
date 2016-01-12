

var dweetClient = require("node-dweetio");
var dweetio = new dweetClient();


var serialport = require('serialport'),// include the library
   SerialPort = serialport.SerialPort, // make a local instance of it
   // get port name from the command line:
   
   //portName = process.argv[2];




portName = "COM12"


var myPort = new SerialPort(portName, {
   baudRate: 9600,
   // look for return and newline at the end of each data packet:
   parser: serialport.parsers.readline("\r\n")
 });

myPort.on('open', showPortOpen);
myPort.on('data', saveLatestData);
myPort.on('close', showPortClose);
myPort.on('error', showError);

function showPortOpen() {
   console.log('port open. Data rate: ' + myPort.options.baudRate);
}
 
function saveLatestData(data) {

	console.log(data);
	//console.log(data.length);
	var temp="";
	var dist="";
	var dim;

	for (var i = 0; i < data.length; i++) {
		if(data.charAt(i) == '/'){
			//console.log("Encontro "+ i);
			dim=i;
		}   		
	}

	for(var j=0; j< dim; j++){
		temp= temp+ data.charAt(j);
	}

	//console.log(temp);

	for(var k=dim+1; k< data.length; k++){
		dist= dist + data.charAt(k);
	}

	//console.log(dist);
   
   
   dweetio.dweet_for("SSDDFISI", {temperatura: temp, distancia: dist}, function(err, dweet){

    

	});   
}
 
function showPortClose() {
   console.log('port closed.');
}
 
function showError(error) {
   console.log('Serial port error: ' + error);
}

//var temp = ''+ myPort.options.baudRate;

