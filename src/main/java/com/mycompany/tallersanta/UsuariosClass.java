/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallersanta;

/**
 *
 * @author User
 */
public class UsuariosClass {
    private String nombreUsuario;
    private String password;
    
    
    public UsuariosClass(String nombreUsuario, String password){
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    
    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getNombreUsuario(){
        return nombreUsuario;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
}
