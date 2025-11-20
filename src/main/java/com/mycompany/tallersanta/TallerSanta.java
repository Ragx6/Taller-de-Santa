
package com.mycompany.tallersanta;
import java.util.*;

public class TallerSanta {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<UsuariosClass> Usuarios = new ArrayList<>();
        
        String nombre = scanner.nextLine();
        String password = scanner.nextLine();
        
        UsuariosClass nuevoUsuario = new UsuariosClass(nombre,password);
        
        Usuarios.add(nuevoUsuario);
        
        System.out.print(Usuarios.size());
        
        //intentando subir update
    }
}
