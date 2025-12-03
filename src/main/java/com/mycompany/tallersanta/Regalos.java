/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallersanta;

public class Regalos {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String marca;
    private int cantidad;
    
    public Regalos(String codigo, String nombre, String descripcion, String marca, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.cantidad = cantidad;
    }
    
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void informacion(){
        System.out.println("Codigo: " + codigo + "\nNombre: " + nombre + "\nDescripcion: " + descripcion + "\nMarca: " + marca + "\nCantidad: " + cantidad);
    }
}
