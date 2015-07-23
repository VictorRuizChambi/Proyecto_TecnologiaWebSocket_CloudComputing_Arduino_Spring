package com.sisDis.webSocket;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sisDis.iot.Dweet;
import com.sisDis.model.InstEducativa;
import com.sisDis.service.InsEducService;

@ServerEndpoint(value="/ws")
@Singleton
@Controller
public class HomeController {


  @Autowired
  private InsEducService  instEducService;
	
private static final Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());  
private TaskScheduler scheduler = new ConcurrentTaskScheduler();
private static List<Mensaje> mensajesC = new ArrayList<Mensaje>(); 
private static List<Mensaje> mensajesS = new ArrayList<Mensaje>();
private static Cronometro miCronometro = new Cronometro(999999999);
static final int SERVIDOR = 999999999;
static final int CLIENTE = 30;


      
@OnOpen
public void onOpen(Session userSession)
{
    System.out.println("Usuario nuevo: " + userSession.getId());
    userSessions.add(userSession);
    
}    

@OnClose
public void onClose(Session userSession) {
    System.out.println("Connection closed. Id: " + userSession.getId());
    userSessions.remove(userSession);
}

@OnMessage
public void onMessage(String message, Session userSession) throws IOException {
    System.out.println("Recibiendo en onMessage: " + message);
    
		//guardo los mensajes  en una lista estatica	
    	String miTurno=turno(message);
		String mensaje=mensaje(message);
		System.out.println("Mensaje:"+mensaje);
		
		if(!miTurno.equals("tres")){
			Mensaje mensj = new Mensaje();
			mensj.setMensaje(mensaje);
			
			if(miTurno.equals("uno")){
				mensj.setTurno(1);
			}else{
				mensj.setTurno(0);
			}
			
			mensajesC.add(mensj);
			mensajesS.add(mensj);
		}
		
		
			if(miTurno.equals("uno")){
				updatePriceAndBroadcast(CLIENTE,message,userSession);
			    miCronometro.setTiempo(CLIENTE);
			}else{
				if(miTurno.equals("cero")){
				updatePriceAndBroadcast(SERVIDOR,message,userSession);
			    miCronometro.setTiempo(SERVIDOR);
				}else{
					
					updateInfoSensores(message,userSession);
				}
			}
			

}

public static String mensaje(String Mensaje) 
	{
		String mensaje=Mensaje.substring(12, Mensaje.length());
		int coma=mensaje.lastIndexOf(",");
		mensaje=mensaje.substring(0,coma-1);
		return mensaje;
	}

public static String turno(String Mensaje) 
	{
	int coma=Mensaje.lastIndexOf(",");
		String turno=Mensaje.substring(coma+10, Mensaje.length()-2);
		return turno;
	}

/**
 * Iterates stock list, update the price by randomly choosing a positive
 * or negative percentage, then broadcast it to all subscribing clients
 */
private void updatePriceAndBroadcast(int solicitante, String nuevoMensaje, Session userSession) {
	  
	  int msjC = 0;
	  int msjS = 0;
  
  for(Mensaje msj : mensajesC){
  	if(msj.getTurno()==1){ 		
  		msjC++;
  	}
  	if(msj.getTurno()==0){
  		msjS++;
  	}
  	
  }

  if((msjC==1 && msjS==0) || !miCronometro.isContinuar()){
  	miCronometro = new Cronometro(solicitante);
  	miCronometro.start();
  }
  
  if(msjC>0){
	  System.out.println("cantidad de usuario: " + userSessions.size());
  	for (Session session : userSessions) 
	        {
  		session.getAsyncRemote().sendText(nuevoMensaje);
  		System.out.println("Enviado : usuario" + session.getId());
  		System.out.println("envio nu nuevo mensaje");
	        }	
  	
  }else{
  	
  		mensajesC.clear();
  		mensajesS.clear();
  }
}

public void updateInfoSensores(String nuevoMensaje, Session userSession){

	for (Session session : userSessions) 
	        {
		session.getAsyncRemote().sendText(nuevoMensaje);
		System.out.println("Enviado : usuario" + session.getId());
		System.out.println("envio nu nuevo mensaje");
	        }	
	
}

/**
 * Invoked after bean creation is complete, this method will schedule 
 * updatePriceAndBroacast every 1 second
 */
@PostConstruct
private void broadcastTimePeriodically() {
  scheduler.scheduleAtFixedRate(new Runnable() {
    @Override public void run() {
//    	System.out.println("el tiempo es: "+ miCronometro.getTiempo());
      if(miCronometro.getTiempo()==1){
      	mensajeNoDisponibilidad();
      	
      }
      mostrarInformacion();
    }
  }, 1000);
}

public void mensajeNoDisponibilidad(){
	
	String mensajeN = "SERVIDOR: Disculpenos por la demora; ahora no tenemos solucion a su consulta"+
						" pero cuando la tengamos responderemos inmediatamente. Muchas Gracias\\n\\n";

	
	String nuevoMensaje = "{\"mensaje\":\""+mensajeN+"\",\""+ "turno\":\"cero\"}";
	
	Session userSession = null;
	try {
		onMessage(nuevoMensaje, userSession);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public void mostrarInformacion(){
	Dweet dweetio =  new Dweet();
	String infoDist = dweetio.obtenerValorDist();
	String infoTemp = dweetio.obtenerValorTemp();
	String nuevoMensajetemp = "{\"mensaje\":\""+infoTemp+"\",\""+ "turno\":\"tres\"}";
	String nuevoMensajedist = "{\"mensaje\":\""+infoDist+"\",\""+ "turno\":\"tres\"}";
	
	Session userSession = null;
	try {
		onMessage(nuevoMensajetemp, userSession);
		onMessage(nuevoMensajedist, userSession);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

   
  /**
   * Serve the main page
   */
  
	@RequestMapping(value="/instEduc.do", method=RequestMethod.GET)
	public String setupForm(Model modelo){
		
		modelo.addAttribute("listacoles", instEducService.obtenerlistaInst());
		modelo.addAttribute("colegio", new InstEducativa());
		return "instEducativa";
	}
	
	@RequestMapping(value="/instEduc.do", method=RequestMethod.POST)
	public String doACtions(@ModelAttribute("colegio") InstEducativa colegio, 
			BindingResult resultado, @RequestParam String accion, Model modelo){ /**/
		
		InstEducativa coleResultado = new InstEducativa();
		List<InstEducativa> institucionBuscada = new ArrayList<InstEducativa>();
		switch(Integer.parseInt(accion)){
			case 1:		
		        	coleResultado=colegio;
		    		institucionBuscada =instEducService.obtenerlistaInstN(colegio.getInstitucion());
		    		if(institucionBuscada.size()<1){
		    			institucionBuscada.add(new InstEducativa());
		    			modelo.addAttribute("mensaje", "ColegioNoregistrado");
		    		}
		    		break;
				
			case 2:
	        	coleResultado=colegio;
	        	if(esNumero(colegio.getInstitucion()))
	        	institucionBuscada= instEducService.obtenerlistaInstU(Integer.parseInt(colegio.getInstitucion()));
	        	else{
	        		institucionBuscada.add(new InstEducativa());
	        		modelo.addAttribute("mensaje", "Noesentero");}
				break;
				
			case 3:
	        	coleResultado=colegio;
	    		institucionBuscada= instEducService.obtenerlistaInstD(colegio.getInstitucion());
	    		if(institucionBuscada.size()<1){
	    			institucionBuscada.add(new InstEducativa());
	    			modelo.addAttribute("mensaje", "ColegioNoregistrado");
	    		}
				break;
				
			case 4:
	        	coleResultado=colegio;
	        	if(esNumero(colegio.getInstitucion()))
	    		institucionBuscada.add(instEducService.obtenerInst(Integer.parseInt(colegio.getInstitucion())));
	        	else{
	        		institucionBuscada.add(new InstEducativa());
	        		modelo.addAttribute("mensaje", "Noesentero");}
	    		break;
		}

		modelo.addAttribute("colegios", coleResultado);
		modelo.addAttribute("listacoles", institucionBuscada);
	return "instEducativa";
	}
	
	public boolean esNumero(String cadena){
		boolean esN;
		try{ 
		     int numero = Integer.parseInt(cadena); 
		     esN= true;
		}catch(NumberFormatException e){ 
			esN=false;
		}
		return esN;
	}  
  

  @RequestMapping(value = "/")
  public String principal() {
    return "inicio";
  } 
  @RequestMapping(value = "/inicio.html")
  public String retorno() {
    return "inicio";
  }
  @RequestMapping(value = "/servicio.html")
  public String servicio() {
    return "servicio";
  } 
  
  @RequestMapping(value = "/cliente.html")
  public String cliente() {
    return "cliente";
  } 
  
  @RequestMapping(value = "/servidor.html")
  public String servidor() {
    return "servidor";
  } 
 
  @RequestMapping(value = "/proyecto.html")
  public String proyecto() {
    return "Proyectos";
  }

}