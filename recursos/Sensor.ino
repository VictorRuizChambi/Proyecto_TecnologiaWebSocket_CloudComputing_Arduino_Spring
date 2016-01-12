#include <DallasTemperature.h>
#include <OneWire.h>
 
#define Pin 2 //Se declara el pin donde se conectará la DATA
 
OneWire ourWire(Pin); //Se establece el pin declarado como bus para la comunicación OneWire
 
DallasTemperature sensors(&ourWire); //Se instancia la librería DallasTemperature

long distancia;
long tiempo;

void setup() {
delay(1000);
Serial.begin(9600);
pinMode(9, OUTPUT); /*activación del pin 9 como salida: para el pulso ultrasónico*/
pinMode(8, INPUT); /*activación del pin 8 como entrada: tiempo del rebote del ultrasonido*/
sensors.begin(); //Se inician los sensores
}
 
void loop() {
sensors.requestTemperatures(); //Prepara el sensor para la lectura
 
digitalWrite(9,LOW); /* Por cuestión de estabilización del sensor*/
delayMicroseconds(5);
digitalWrite(9, HIGH); /* envío del pulso ultrasónico*/
delayMicroseconds(10);
tiempo=pulseIn(8, HIGH); /* Función para medir la longitud del pulso entrante. Mide el tiempo que transcurrido entre el envío
del pulso ultrasónico y cuando el sensor recibe el rebote, es decir: desde que el pin 12 empieza a recibir el rebote, HIGH, hasta que
deja de hacerlo, LOW, la longitud del pulso entrante*/
distancia= int(0.017*tiempo); /*fórmula para calcular la distancia obteniendo un valor entero*/
/*Monitorización en centímetros por el monitor serial*/
Serial.print(sensors.getTempCByIndex(0)); //Se lee e imprime la temperatura en grados Celsius
Serial.print("/");
Serial.println(distancia);

delay(1000); //Se provoca un lapso de 1 segundo antes de la próxima lectura
 
}
