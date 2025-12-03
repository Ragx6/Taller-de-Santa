
package com.mycompany.tallersanta;

public class Asignacion {
    private String idKid;
    private String codigoRegalo;
    
    public Asignacion(String idKid, String codigoRegalo){
        this.idKid = idKid;
        this.codigoRegalo = codigoRegalo;
    }
    
    public void setIdKid(String idKid){
        this.idKid = idKid;
    }
    
    public String getIdKid(){
        return idKid;
    }
    
    public void setCodigoRegalo(String codigoRegalo){
        this.codigoRegalo = codigoRegalo;
    }
    
    public String getCodigoRegalo(){
        return codigoRegalo;
    }
    public void informacion(){
        System.out.println("ID: " + idKid + "\nCodigo de Regalo: " + codigoRegalo);
    }
}
