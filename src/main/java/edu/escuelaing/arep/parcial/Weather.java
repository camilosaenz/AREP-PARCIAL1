package edu.escuelaing.arep.parcial;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * 
 * @author Camilo
 *
 */
public class Weather {
	
    public final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
    public final DelayQueue<Cache> cola = new DelayQueue<>();

    public Weather() {
        Thread cleanerThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Cache cache = cola.take();
                    map.remove(cache.getClave(), cache.getInformacion());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    private void elimina(String city) {
    	map.remove(city);
    }

    public String retornar(String city) {
        return map.get(city);
    }

    private void limpia() {
    	map.clear();
    }

    public long size() {
        return map.size();
    }
    
    public void agregar(String city, String weather, long periodInMillis){
        if (weather == null) {
        	map.remove(city);
        } else {
            long expiryTime = System.currentTimeMillis() + periodInMillis;
            map.put(city, weather);
            cola.put(new Cache(city, weather, expiryTime));
        }
    }

}