package edu.escuelaing.arep.parcial;

import com.google.gson.Gson;

import static spark.Spark.*;

/**
 * 
 * @author Camilo
 *
 */
public class Spark {

    private static HTTPConexion http;

    private static Weather weather;

    public static void main(String[] args) {
    	
        Gson gson = new Gson();
        port(getPort());
        http = new HTTPConexion();
        weather = new Weather();
        
        // Etiqueta en la url "/clima"
        get("/clima", (req, res) -> {
        	
            String ciudad = req.queryParams("lugar");
            String json = "";
            
            if(ciudad.length()==0){
                return "Ingrese la cuidad que desea buscar: /clima?lugar=Bogota";
            }
            
            json = getCity(ciudad);
            return gson.toJson(json);
          
        });
    }

    private static String getCity(String ciudad) throws Exception{
    	
        String ans = null;
        
        if (weather.retornar(ciudad) == null) {
        	
            ans = http.getCity(ciudad);
            weather.agregar(ciudad,http.getCity(ciudad),300000);
            
        }
        
        ans = weather.retornar(ciudad);
        return ans;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
        	
            return Integer.parseInt(System.getenv("PORT"));
        }
        
        return 4567;
    }
}