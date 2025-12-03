
package com.mycompany.tallersanta;

import java.util.*;

public class GestionarKids {
    private ArrayList<Kids> KidsArray;
    private ArrayList<Regalos> RegalosArray;
    private ArrayList<Asignacion> AsignacionesArray;
    private Scanner scanner;
    
    public void inicializar(ArrayList<Kids> KidsArray, ArrayList<Regalos> RegalosArray, ArrayList<Asignacion> AsignacionesArray ){
        this.KidsArray = KidsArray;
        this.RegalosArray = RegalosArray;
        this.AsignacionesArray = AsignacionesArray;
        this.scanner = new Scanner(System.in);
    }
    
    public void menuKids(ArrayList<Regalos> RegalosArray, ArrayList<Asignacion> AsignacionesArray, ArrayList<Kids> KidsArray){
        this.KidsArray = KidsArray;
        this.RegalosArray = RegalosArray;
        this.AsignacionesArray = AsignacionesArray;
        this.scanner = new Scanner(System.in);
        menuKids();
    }
    
    public void menuKids(){
        boolean ejecutar = true;
        
        while (ejecutar) {            
           System.out.println("+++++++++++++++Gestion de niños+++++++++++++++ \n1. Ingresar niño \n2. Modificar datos de niño \n3. Eliminar registro de niño \n4. Consultar registro de niño \n5. Volver al menu principal");
           System.out.println("Ingrese una opcion");
           String opcion = scanner.nextLine();
           
            switch (opcion) {
                case "1":                    
                    try {
                        System.out.println("Ingrese la identificacion del niño");
                        String identificacion = scanner.nextLine();
                        System.out.println("Ingrese el nombre del niño");
                        String nombre = scanner.nextLine();                    
                        System.out.println("Ingrese el apellido del niño");
                        String apellido = scanner.nextLine();       
                        System.out.println("Ingrese la edad del niño");
                        int edad = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Ingrese la ciudad del niño");
                        String ciudad = scanner.nextLine();
                        System.out.println("Ingrese la Direccion del niño");
                        String direccion = scanner.nextLine();
                        if (identificacion.isBlank() || nombre.isBlank() || apellido.isBlank() || ciudad.isBlank() || direccion.isBlank()) {
                            System.out.println("Error: tienes que ingresar toda la informacion del niño");
                        }
                        if (direccion.equals(ciudad) || ciudad.equals(direccion)) {
                            System.out.println("Error: no puedes poner la misma informacion en la ciudad y direccion");
                            break;
                        }
                        if (edad < 0 ) {
                            System.out.println("Error: la edad no puede ser menor que 0");
                            break;
                        }
                        boolean encontrado = false;
                        for (Kids i : KidsArray) {
                            if (i.getId().equalsIgnoreCase(identificacion)) {
                                System.out.println("Error: no se puede registrar un niño con un id existente ");
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Kids nuevoKid = new Kids(identificacion, nombre, apellido, edad, ciudad, direccion);
                            KidsArray.add(nuevoKid);
                            GuardarKidsJSON.guardarKids(KidsArray);
                            System.out.println("Niño registrado correctamente");
                            break;
                        }
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Error: solo pon numeros enteros en edad");
                        scanner.nextLine();
                    }
                    
                    break;
                 
                case "2":
                    System.out.println("Ingreasa el id del niño que quieres modificar");
                    String buscar = scanner.nextLine();
                    Kids kidEncontrado = null;
                    
                    for (Kids i : KidsArray) {
                        if (i.getId().equals(buscar)) {
                            kidEncontrado = i;
                            break;
                        }
                    }
                    if (kidEncontrado == null) {
                        System.out.println("Error: no se encontro un niño con ese ID");
                        break;
                    }
                    System.out.println("Se encontro al niño: " + kidEncontrado.getNombre() + " " +kidEncontrado.getApellido());
                    
                    boolean ejecutarModificar = true;
                    
                    while (ejecutarModificar) {                        
                        System.out.println("+++++++++++++++Modificar registro de niños+++++++++++++++ \n1. Editar nombre \n2. Editar edad \n3. Editar ciudad \n4. Editar direccion detallada \n5. Volver al menu principal");
                        String opcionModificar = scanner.nextLine();
                        
                        switch (opcionModificar) {
                            case "1":
                                System.out.println("Ingrese nuevo nombre:");
                                String nombreEditar = scanner.nextLine();
                                System.out.println("Ingrese nuevo apellido(s)");
                                String apellidoEditar = scanner.nextLine();
                                
                                if (nombreEditar.isEmpty() || nombreEditar.isBlank() || apellidoEditar.isEmpty() || apellidoEditar.isBlank()) {
                                    System.out.println("Error: No se puede dejar en blanco el nombre del niño");
                                }
                                else{
                                    kidEncontrado.setNombre(nombreEditar);
                                    kidEncontrado.setApellido(apellidoEditar);
                                    GuardarKidsJSON.guardarKids(KidsArray);
                                    System.out.println("Nombre y apellido editado exitosamente");
                                }
                                break;
                                
                            case "2":
                                try {
                                    System.out.println("Ingrese la nueva edad");
                                    int edadNueva = scanner.nextInt();
                                    scanner.nextLine();
                                    
                                    if (edadNueva < 0) {
                                        System.out.println("Error: La edad no puede ser menor que 0");
                                        break;
                                    }
                                    else{
                                        kidEncontrado.setEdad(edadNueva);
                                        GuardarKidsJSON.guardarKids(KidsArray);
                                        System.out.println("Edad editada exitosamente");
                                    }
                                    
                                    
                                } catch (InputMismatchException e) {
                                    
                                    System.out.println("Error: solo pon numeros enteros en edad");
                                    scanner.nextLine();
                                }
                                break;
                            case "3":
                                System.out.println("Ingrese la nueva ciudad");
                                String nuevaCiudad = scanner.nextLine();
                                
                                if (nuevaCiudad.isBlank() || nuevaCiudad.isEmpty()) {
                                    System.out.println("No puedes dejar la ciudad en blanco");
                                    break;
                                }
                                if (nuevaCiudad.equalsIgnoreCase(kidEncontrado.getCiudad())) {
                                    System.out.println("Error: Pusiste la misma ciudad");
                                }
                                if (nuevaCiudad.equalsIgnoreCase(kidEncontrado.getDireccion())) {
                                    System.out.println("Error: Pusiste la misma direccion en ciudad");
                                }                                
                                else{
                                    kidEncontrado.setCiudad(nuevaCiudad);
                                    GuardarKidsJSON.guardarKids(KidsArray);
                                    System.out.println("Ciudad editada exitosamente");
                                }
                                break;
                            case "4":
                                System.out.println("Ingrese nueva Direccion detallada");
                                String nuevaDireccion = scanner.nextLine();
                                
                                if (nuevaDireccion.isBlank() || nuevaDireccion.isEmpty()) {
                                    System.out.println("No puedes dejar la direccion en blanco");
                                    break;
                                }
                                
                                if (nuevaDireccion.equalsIgnoreCase(kidEncontrado.getDireccion())) {
                                    System.out.println("Error: Pusiste la msima direccion ");
                                    break;
                                }
                                if (nuevaDireccion.equalsIgnoreCase(kidEncontrado.getCiudad())) {
                                    System.out.println("Error: Pusiste la msima ciudad en direccion");
                                    break;                                
                                }
                                else{
                                    kidEncontrado.setDireccion(nuevaDireccion);
                                    GuardarKidsJSON.guardarKids(KidsArray);
                                    System.out.println("Direccion editada exitosamente");
                                }
                                break;
                            case "5":
                                System.out.println("Saliendo de editar registros de niño");
                                ejecutarModificar = false;
                                break;
                                
                            default:
                                System.out.println("Error: Ingrese una opcion valida");
                        }
                    }
                    break;
                    
                case "3":
                    System.out.println("Ingrese el id del registro del niño que quiere eliminar");
                    String buscarId = scanner.nextLine();
                    Kids buscarEliminar = null;
                    
                    if (buscarId.isBlank() || buscarId.isEmpty()) {
                        System.out.println("No se puede buscar una ID vacia");
                        break;
                    }
                    boolean encontrar = false;
                    for (Asignacion i : AsignacionesArray) {
                        if (i.getIdKid().equalsIgnoreCase(buscarId)) {
                            encontrar = true;
                            break;
                        }
                    }
                    if (encontrar) {
                        System.out.println("No se puede eliminar los registros de este niño porque tiene un regalo asignado");
                        break;
                    }
                    
                    else{
                        for (Kids i : KidsArray) {
                            if (i.getId().equals(buscarId)) {
                                buscarEliminar = i;
                                break;
                            }
                        }
                    }
                    if (buscarEliminar == null) {
                        System.out.println("No se encontro un niño con ese id");
                    }
                    else{
                        KidsArray.remove(buscarEliminar);
                        GuardarKidsJSON.guardarKids(KidsArray);
                        System.out.println("Registro de niño eliminado exitosamente");
                    }
                    break;
                    
                case "4":
                    System.out.println("Ingrese el Id del niño que quiere consultar");
                    String idConsulta = scanner.nextLine();
                    Kids consulta = null; 
                    
                    if (idConsulta.isEmpty() || idConsulta.isBlank()) {
                        System.out.println("Error: tienes que ingresar un ID");
                        break;
                    }
                    
                    for (Kids i : KidsArray) {
                        if (i.getId().equals(idConsulta)) {
                            consulta = i;
                            break;
                        }
                    }
                    if (consulta == null) {
                        System.out.println("No se encontro un niño con ese id");
                        break;
                    }
                    else{
                        System.out.println("Registro encontrado");
                        consulta.informacion();
                    }
                    break;
                case "5":
                    ejecutar = false;
                    System.out.println("Saliendo al menu principal");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
            }
        }
    }
    
    
}
