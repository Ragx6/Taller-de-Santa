
package com.mycompany.tallersanta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class GuardarUsuariosJSON {
    private static final String ARCHIVO_JSON_USUARIOS = "usuarios.json";

    public static void guardarUsuarios(ArrayList<UsuariosClass> Usuarios){
        Gson gson = new Gson();
        
        try(FileWriter writer = new FileWriter(ARCHIVO_JSON_USUARIOS)){
            gson.toJson(Usuarios, writer);
            System.out.println("Usuarios guardados en archivo: " + ARCHIVO_JSON_USUARIOS);
        }catch(IOException e){
            System.out.println("Error guardando: " + ARCHIVO_JSON_USUARIOS);
        }
    }
    
    public static ArrayList<UsuariosClass> leerUsuarios(){
        Gson gson = new Gson();
        ArrayList<UsuariosClass> Usuarios = new ArrayList<>();
        try(FileReader reader = new FileReader(ARCHIVO_JSON_USUARIOS)){
            Type tipoLista = new TypeToken<ArrayList<UsuariosClass>>(){}.getType();
            Usuarios = gson.fromJson(reader, tipoLista);
            System.out.println("Usuarios cargados de: " + ARCHIVO_JSON_USUARIOS);
            if(Usuarios == null){
                Usuarios = new ArrayList<>();
            }
        }catch(IOException e){
            System.out.println("Error leyendo " + e.getMessage());
        }
        return Usuarios;
            
    }
    
}
