package com.sisDis.iot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dweet {

//	private String myThing = "nuevoProyectoSSDD";
	private String myThing = "SSDDFISI";


public String obtenerValorTemp(){
     
    try {
        JSONObject json = 
            ReadJSON("http://dweet.io/get/dweets/for/" + myThing);

        JSONArray jsonArray_with = json.getJSONArray("with");
        
        for(int i=0; i<jsonArray_with.length(); i++){
            JSONObject jsonObject_with = (JSONObject) jsonArray_with.get(i);
            
            JSONObject jsonObject_with_content = 
                jsonObject_with.getJSONObject("content");
            
           
            String informacion = 
                    String.valueOf(jsonObject_with_content.getDouble("temperatura")) + " C";
            
            
            
            System.out.println(informacion);
            return informacion;
        }
        
    } catch (IOException | JSONException e){
        System.out.println(e.toString());
        return e.toString();
    }
    return null;
}

public String obtenerValorDist(){
    
//  
  
  try {
      JSONObject json = 
          ReadJSON("http://dweet.io/get/dweets/for/" + myThing);

      JSONArray jsonArray_with = json.getJSONArray("with");
      
      for(int i=0; i<jsonArray_with.length(); i++){
          JSONObject jsonObject_with = (JSONObject) jsonArray_with.get(i);
          
          JSONObject jsonObject_with_content = 
              jsonObject_with.getJSONObject("content");
          
         
          String informacion = 
                  String.valueOf(jsonObject_with_content.getDouble("distancia")) + " cm";
          
          
          
          System.out.println(informacion);
          return informacion;
      }
      
  } catch (IOException | JSONException e){
      System.out.println(e.toString());
      return e.toString();
  }
  return null;
}
public static JSONObject ReadJSON(String url) 
        throws IOException, JSONException {
    
    try (InputStream inputStream = new URL(url).openStream()) {
        InputStreamReader inputStreamReader = 
            new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = 
            new BufferedReader(inputStreamReader);

        StringBuilder jsonBody = new StringBuilder();
        int singleChar;
        while ((singleChar = bufferedReader.read()) != -1) {
            jsonBody.append((char)singleChar);
        }

        JSONObject json = new JSONObject(jsonBody.toString());
        return json;
    }
}
	
}
