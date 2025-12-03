
package com.mycompany.tallersanta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class GuardarAsignacionesJSON {
    private static final String ARCHIVO_JSON_ASIGNACIONES = "asignaciones.json";
    
    public static void GuardarAsignaciones(ArrayList<Asignacion> AsignacionesArray){
        Gson gson = new Gson();
        
        try(FileWriter writer = new FileWriter(ARCHIVO_JSON_ASIGNACIONES)) {
            gson.toJson(AsignacionesArray, writer);
            System.out.println("Asignaciones guardados en: " + ARCHIVO_JSON_ASIGNACIONES);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + ARCHIVO_JSON_ASIGNACIONES);
        }
    }
    
    public static ArrayList<Asignacion> leerAsignaciones(){
        Gson gson = new Gson();
        ArrayList<Asignacion> AsignacionesArray = new ArrayList<>();
        try(FileReader reader = new FileReader(ARCHIVO_JSON_ASIGNACIONES) ) {
            Type tipoLista = new TypeToken<ArrayList<Asignacion>>(){}.getType();
            AsignacionesArray = gson.fromJson(reader, tipoLista);
            System.out.println("Asignaciones cargados de: " + ARCHIVO_JSON_ASIGNACIONES);
            if (AsignacionesArray == null) {
                AsignacionesArray = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error leyendo " + e.getMessage());
        }
        return AsignacionesArray;
    }
}
