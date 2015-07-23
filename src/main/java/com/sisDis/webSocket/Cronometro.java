package com.sisDis.webSocket;

public class Cronometro extends Thread{

	   private boolean continuar = true;
	   private int tiempo;
	   
	   public Cronometro (int tiempo){
		   this.tiempo = tiempo;
	   }
	   
	   
	   public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	   public boolean isContinuar() {
		return continuar;
	}


	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}

	   public void run()
	   {
	      
	      while (continuar)
	      {
	          try {
				Thread.sleep(1000);
				tiempo--;
				if(tiempo == 0){
					continuar=false;
				}
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
	      } 
	   }
}
