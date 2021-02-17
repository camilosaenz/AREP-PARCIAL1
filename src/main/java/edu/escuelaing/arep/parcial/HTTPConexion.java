package edu.escuelaing.arep.parcial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Camilo
 *
 */
public class HTTPConexion implements Recursos {

    public String url;
    public String clave;

    public HTTPConexion() {
    	
        url = "http://api.openweathermap.org/data/2.5";
        clave = "3918e2a8de7820f67a0e512c0eab71df";
        
    }

    public String getCity(String city) throws Exception{
        	
    	// Estructura de la url.
        URL pagina = new URL(url + "/weather?q=" + city+"&appid=" + clave);
        HttpURLConnection conexion = (HttpURLConnection) pagina.openConnection();
        conexion.setRequestMethod("GET");
        StringBuffer put = null;
        int responder = conexion.getResponseCode();
        
        if (responder == HttpURLConnection.HTTP_OK) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(
            		conexion.getInputStream()));
            String line;
            put = new StringBuffer();
            while ((line = buffer.readLine()) != null) {
            	put.append(line);
            }
            buffer.close();
        }
        
        return String.valueOf(put);
            
    }
}