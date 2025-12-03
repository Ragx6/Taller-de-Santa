
package com.mycompany.tallersanta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GuardarRegalosJSON {
    private static final String ARCHIVO_JSON_REGALOS = "regalos.json";
    
    public static void GuardarRegalos(ArrayList<Regalos> RegalosArray){
        Gson gson = new Gson();
        
        try(FileWriter writer = new FileWriter(ARCHIVO_JSON_REGALOS)) {
            gson.toJson(RegalosArray, writer);
            System.out.println("Regalos guardados en: " + ARCHIVO_JSON_REGALOS);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + ARCHIVO_JSON_REGALOS);
        }
    }
    
    public static ArrayList<Regalos> leerRegalos(){
        Gson gson = new Gson();
        ArrayList<Regalos> RegalosArray = new ArrayList<>();
        try(FileReader reader = new FileReader(ARCHIVO_JSON_REGALOS) ) {
            Type tipoLista = new TypeToken<ArrayList<Regalos>>(){}.getType();
            RegalosArray = gson.fromJson(reader, tipoLista);
            System.out.println("Regalos cargados de: " + ARCHIVO_JSON_REGALOS);
            if (RegalosArray == null) {
                RegalosArray = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error leyendo " + e.getMessage());
        }
        return RegalosArray;
    }
}
