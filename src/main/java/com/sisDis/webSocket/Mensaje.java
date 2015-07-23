package com.sisDis.webSocket;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Mensaje implements Serializable {

  private static final long serialVersionUID = 1L;
  private String mensaje = "";
  private int turno = 0; //0=servidor ; 1 = cliente

  


public Mensaje() {
    
  }
  
  public Mensaje(String mensaje, int turno) {
    this.mensaje = mensaje;
    this.turno = turno;
  }

  public String getMensaje() {
    return mensaje;
  }
  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
  public double getTurno() {
    return turno;
  }
  public void setTurno(int turno) {
    this.turno = turno;
  }
  


  @Override
  public String toString() {
    return "Stock [mensaje=" + mensaje + ", turno=" + turno + "]";
  }
  
  
}
