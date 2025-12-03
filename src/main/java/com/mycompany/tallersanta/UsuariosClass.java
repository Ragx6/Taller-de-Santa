
package com.mycompany.tallersanta;


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
    
    public String informacion(){
        return "Usuario{nombre=" + nombreUsuario + ", password=" + password + "}";
    }
}

