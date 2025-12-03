
package com.mycompany.tallersanta;
import java.util.*;


public class GestionarRegalos {
    private ArrayList<Regalos> RegalosArray;
    private ArrayList<Asignacion> AsignacionesArray;
    private Scanner scanner;
    
    public void inicializar(ArrayList<Regalos> RegalosArray, ArrayList<Asignacion> AsignacionesArray){
        this.RegalosArray = RegalosArray;
        this.AsignacionesArray = AsignacionesArray;
        this.scanner = new Scanner(System.in);
    }
    
    public void menuRegalos(ArrayList<Regalos> RegalosArray, ArrayList<Asignacion> AsignacionesArray){
        this.RegalosArray = RegalosArray;
        this.AsignacionesArray = AsignacionesArray;
        this.scanner = new Scanner(System.in);
        menuRegalos();
    }
    
    public void menuRegalos(){
        boolean ejecutar = true;
        
        while (ejecutar) {            
            System.out.println("+++++++++++++++Gestion de regalos+++++++++++++++ \n1. Ingresar regalo \n2. Modificar regalo \n3. Eliminar regalo \n4. Consultar regalo \n5. Reabastecer inventario \n6. Volver al menu principal");
            System.out.println("Ingrese una opcion");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    try {
                        System.out.println("Ingrese el codigo del regalo");
                        String codigo = scanner.nextLine();
                        System.out.println("Ingrese el nombre del regalo");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese la descripcion del regalo");
                        String descripcion = scanner.nextLine();
                        System.out.println("Ingrese la marca del regalo");
                        String marca = scanner.nextLine();
                        System.out.println("Ingrese la canitdad del regalo");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();
                        
                        if (codigo.isBlank() || nombre.isBlank() || descripcion.isBlank() || marca.isBlank()) {
                            System.out.println("Error: no puedes dejar ningun dato vacio");
                            break;
                        }
                        
                        if (cantidad <= 0 ) {
                            System.out.println("Error: La cantidad no puede ser menor que 0");
                            break;
                        }
                        boolean encontrado = false;
                        for (Regalos i : RegalosArray) {
                            if (i.getCodigo().equals(codigo) || i.getNombre().equals(nombre)) {
                                System.out.println("Error: no se puede crear un regalo con un nombre o codigo ya existente!");
                                encontrado = true;
                                break;
                            }
                        }
                        if (!encontrado) {
                            Regalos nuevoRegalo = new Regalos(codigo, nombre, descripcion, marca, cantidad);
                            RegalosArray.add(nuevoRegalo);
                            System.out.println("Regalo agregado exitosamente");
                            GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                        }
                        
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debes ingresar un numero valido en cantidad");
                        scanner.nextLine();
                    }
                    
                    break;
                case "2":
                    
                    System.out.println("Ingrese el codigo del regalo que quieres modificar:");
                    String buscarRegalo = scanner.nextLine();
                    Regalos regaloEncontrado = null;
                    
                    for (Regalos i : RegalosArray) {
                        if (i.getCodigo().equals(buscarRegalo)) {
                            regaloEncontrado = i;
                            break;
                        }
                    }
                    if (regaloEncontrado == null) {
                        System.out.println("No se encontro regalo");
                        break;
                    }
                    
                    System.out.println("Regalo encontrado: " + regaloEncontrado.getNombre());
                    
                    
                    
                    boolean ejecutarModificar = true;
                    
                    while (ejecutarModificar) {                        
                        System.out.println("+++++++++++++++Modificar regalos+++++++++++++++ \n1. Modificar nombre \n2. Modificar descripcion \n3. Modificar marca \n4. Modificar cantidad disponible  \n5. Volver al menu principal");
                        System.out.println("Ingrese una opcion");
                        String opcionModificar = scanner.nextLine();
                        
                        switch (opcionModificar) {
                            case "1":

                                System.out.println("Ingrese el nuevo nombre:");
                                String nuevoNombre = scanner.nextLine();
                                
                                if (nuevoNombre.isBlank() || nuevoNombre.isEmpty()) {
                                    System.out.println("Error: el nombre no puede estar vacio");
                                    break;
                                }
                                boolean encontrar = false;
                                for (Regalos i : RegalosArray) {
                                    if (i.getNombre().equalsIgnoreCase(nuevoNombre)) {
                                        encontrar = true;
                                    }
                                }
                                
                                if (!encontrar) {
                                    regaloEncontrado.setNombre(nuevoNombre);
                                    GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                                    System.out.println("Nombre actualizado");
     
                                }
                                else{
                                    System.out.println("Error: Ya hay un regalo con el nombre que ingresaste");
                                }
                                break;
                            case "2":
                                System.out.println("Ingresar nueva descripcion:");
                                String nuevaDescripcion = scanner.nextLine();
                                
                                if (nuevaDescripcion.isBlank() || nuevaDescripcion.isEmpty()) {
                                    System.out.println("Error: no puedes dejar la descripcion vacia");
                                }
                                else{
                                    regaloEncontrado.setDescripcion(nuevaDescripcion);
                                    GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                                    System.out.println("Descripcion modificada exitosamente");
                                }
                                break;
                                
                            case "3":
                                System.out.println("Ingrese nueva marca");
                                String nuevaMarca = scanner.nextLine();
                                
                                if (nuevaMarca.isBlank() || nuevaMarca.isEmpty()) {
                                    System.out.println("Error: no puedes dejar la marca vacia");
                                }
                                else{
                                    regaloEncontrado.setMarca(nuevaMarca);
                                    GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                                    System.out.println("Marca modificada exitosamente");
                                }
                                break;
                                
                            case "4":
                                System.out.println("Ingrese nueva cantidad: ");
                                
                                try {
                                    int nuevaCantidad = scanner.nextInt();
                                    scanner.nextLine();
                                    if (nuevaCantidad < 0) {
                                        
                                        System.out.println("Error: no se puede ingresar una cantidad menor que 0");
                                        }
                                    else{
                                        regaloEncontrado.setCantidad(nuevaCantidad);
                                        GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                                        System.out.println("Cantidad actualizada");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Error: Debes ingresar un numero valido");
                                    scanner.nextLine();
                                }
                            
                                break;

                            case "5":
                                System.out.println("Saliendo del menu de Regalos");
                                ejecutarModificar = false;
                                break;

                            default:
                                System.out.println("Error: Debes elejir una opcion valida");
                        }
                    }
                    
                    break;
                case "3":
                    System.out.println("Ingrese el codigo del regalo que quiere eliminar");
                    String codigoEliminar = scanner.nextLine();
                    Regalos regaloEliminar = null;
                    
                    for (Regalos i : RegalosArray) {
                        if (i.getCodigo().equals(codigoEliminar)) {
                            regaloEliminar = i;
                            break;
                        }
                    }
                    
                    if (regaloEliminar == null) {
                        System.out.println("No se encontro un regalo con ese codigo");
                        break;
                    }
                    
                    boolean asignado = false;
                    for (Asignacion a : AsignacionesArray) {
                        if (a.getCodigoRegalo().equals(regaloEliminar.getCodigo())) {
                            asignado = true;
                            break;
                        }
                    }
                    if (asignado) {
                        System.out.println("No se puede eliminar porque el regalo esta asignado a un niño");
                    }
                    else{
                        RegalosArray.remove(regaloEliminar);
                        GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                        System.out.println("Regalo eliminado exitosamente");
                    }
                    break;
                case "5":
                    
                    System.out.println("Ingrese el codigo del regalo que quieres reabastecer:");
                    String buscarRegaloRE = scanner.nextLine();
                    Regalos regaloEncontradoRe = null;
                    
                    for (Regalos i : RegalosArray) {
                        if (i.getCodigo().equals(buscarRegaloRE)) {
                            regaloEncontradoRe = i;
                            break;
                        }
                    }
                    if (regaloEncontradoRe == null) {
                        System.out.println("No se encontro regalo");
                        break;
                    }
                    
                    System.out.println("Regalo encontrado: " + regaloEncontradoRe.getNombre());
                    System.out.println("Ingresar cantidad a reabastecer");
                                
                    try {
                        int cantidadNueva = scanner.nextInt();
                        scanner.nextLine();
                        if (cantidadNueva <= 0 ) {
                            System.out.println("Error: la cantidad tiene que ser mayor a 0");
                            }
                        else {
                            regaloEncontradoRe.setCantidad(regaloEncontradoRe.getCantidad() + cantidadNueva);
                            System.out.println("Inventario reabastecido exitosamente");
                            GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                            }
                        }catch (InputMismatchException e) {
                            
                            System.out.println("Error: Debes ingresar un numero valido");
                            scanner.nextLine();
                                }
                    break;
                case "4":
                    System.out.println("Ingrese el codigo del regalo");
                    String consultar = scanner.nextLine();
                    Regalos regaloConsulta = null;
                    
                    for (Regalos i : RegalosArray) {
                        if (i.getCodigo().equals(consultar)) {
                            regaloConsulta = i;
                            break;
                        }
                    }
                    
                    if (regaloConsulta == null) {
                        System.out.println("No se encontro un regalo con ese codigo");
                    }
                    else{
                        System.out.println("Regalo encontrado:");
                        regaloConsulta.informacion();
                    }
                    
                    
                    break;
                case "6":
                    ejecutar = false;
                    System.out.println("Saliendo de gestion de regalos");
                    break;
                    
                default:
                    System.out.println("Opcion invalida");
            }
        }
        
        
        
    }
}
