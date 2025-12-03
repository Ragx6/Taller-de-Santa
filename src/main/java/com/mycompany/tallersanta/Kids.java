
package com.mycompany.tallersanta;

public class Kids {
    private String id;
    private String nombre;
    private String apellido;
    private int edad;
    private String ciudad;
    private String direccion;
    
    public Kids(String id, String nombre, String apellido, int edad, String ciudad, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.direccion = direccion;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return id;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    
    public String getApellido(){
        return apellido;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setCiudad(String ciudad){
        this.ciudad = ciudad;
    }
    
    public String getCiudad(){
        return ciudad;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void informacion(){
        System.out.println("Id: " + id +"\nNombre completo: " + nombre + " " + apellido + "\nEdad: " + edad +"\nCiudad: " + ciudad + "\nDireccion: " + direccion);
    }
}
