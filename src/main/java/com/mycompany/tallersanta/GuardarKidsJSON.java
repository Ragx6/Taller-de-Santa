
package com.mycompany.tallersanta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GuardarKidsJSON {
    public static final String ARCHIVO_JSON_KIDS = "kids.json";
    
    public static void guardarKids(ArrayList<Kids>KidsArray){
        Gson gson = new Gson();
        
        try(FileWriter writer = new FileWriter(ARCHIVO_JSON_KIDS)){
            gson.toJson(KidsArray, writer);
            System.out.println("Niño guardado en: " + ARCHIVO_JSON_KIDS);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + ARCHIVO_JSON_KIDS);
        }
    }
    
    public static ArrayList<Kids> leerKids(){
        Gson gson = new Gson();
        ArrayList<Kids> KidsArray = new ArrayList<>();
        try(FileReader reader = new FileReader(ARCHIVO_JSON_KIDS)){
            Type tipoLista = new TypeToken<ArrayList<Kids>>(){}.getType();
            KidsArray = gson.fromJson(reader, tipoLista);
            System.out.println("Usuarios cargados de: " + ARCHIVO_JSON_KIDS);
            if (KidsArray == null) {
                KidsArray = new ArrayList<>();
            }
            
        } catch (IOException e) {
            System.out.println("Error leyendo " + e.getMessage());
        }
        return KidsArray;
    }
}
