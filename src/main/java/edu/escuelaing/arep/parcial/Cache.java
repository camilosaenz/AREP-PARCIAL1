package edu.escuelaing.arep.parcial;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Camilo
 *
 */
public class Cache  implements Delayed {
	
	public  String clave;
	public String informacion;
	public long tiempo;
	
	public Cache(String clave, String informacion, long tiempo){
		
		this.clave = clave;
		this.informacion = informacion;
		this.tiempo = tiempo;
		
	}

	
	
	public String getClave() {
		return clave;
	}



	public void setClave(String clave) {
		this.clave = clave;
	}



	public String getInformacion() {
		return informacion;
	}



	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}



	public long getTiempo() {
		return tiempo;
	}



	public void setTiempo(long tiempo) {
		this.tiempo = tiempo;
	}



	@Override
	public int compareTo(Delayed cache) {
		return Long.compare(tiempo, ((Cache) cache).tiempo);
	}

	@Override
	public long getDelay(TimeUnit time) {
		return time.convert(tiempo - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
}
