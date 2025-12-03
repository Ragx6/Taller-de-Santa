
package com.mycompany.tallersanta;
import java.util.*;
import java.io.*;


public class TallerSanta {

    public static void main(String[] args) {
        
        
        
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<UsuariosClass> Usuarios = GuardarUsuariosJSON.leerUsuarios();
        
        ArrayList<Regalos> RegalosArray = GuardarRegalosJSON.leerRegalos();
        
        ArrayList<Kids> KidsArray = GuardarKidsJSON.leerKids();
        
        ArrayList<Asignacion> AsignacionesArray = GuardarAsignacionesJSON.leerAsignaciones();

        
        boolean ejecutar = true;
        
        while (ejecutar) {            
            System.out.println("Bienvenido al sistema del taller de santa, para ingresar crea un usuario o logueate!");
            System.out.println("+++++++++++++++Menu de usuarios+++++++++++++++");
            System.out.println("1. Ingresar \n2. Crear Usuario \n3. Salir");
            System.out.println("Ingrese una opcion");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println("Ingrese su usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.println("Ingrese su contraseña");
                    String password = scanner.nextLine();
                    if (usuario.isBlank() || usuario.isEmpty()) {
                        System.out.println("Error: tienes que ingresar un usuario valido");
                        break;
                    }
                    else if (password.isBlank() || password.isEmpty()) {
                        System.out.println("Error: tienes que ingresar una contraseña");
                        break;
                    }
                    else{
                        boolean encontrado = false;
                        for (UsuariosClass i : Usuarios) {
                            if (i.getNombreUsuario().equalsIgnoreCase(usuario) && i.getPassword().equals(password)) {
                                encontrado = true;
                                break;
                            }
                        }
                        if (encontrado) {
                            System.out.println("Inicio de sesion exitoso, " + usuario);
                            boolean ejecutarMenu = true;
                            while (ejecutarMenu) {                                
                                System.out.println("+++++++++++++++Menu principal+++++++++++++++ \n1. Gestionar regalos \n2. Gestionar niños \n3. Asignar regalos \n4. Buscar asignaciones \n5. Reportes \n6. Salir");
                                System.out.println("Ingrese una opcion: ");
                                String opcionMenu = scanner.nextLine();
                                switch (opcionMenu) {
                                    case "1":
                                        GestionarRegalos gestionarRegalos = new GestionarRegalos();
                                        gestionarRegalos.inicializar(RegalosArray, AsignacionesArray);
                                        gestionarRegalos.menuRegalos();
                                        break;
                                    case "2":
                                        GestionarKids gestionarKids = new GestionarKids();
                                        gestionarKids.inicializar(KidsArray, RegalosArray, AsignacionesArray);
                                        gestionarKids.menuKids();
                                        break;
                                    case "3":
                                        System.out.println("Ingrese Id del niño");
                                        String idKid = scanner.nextLine();
                                        System.out.println("Ingrese el codigo del regalo");
                                        String codigoRegalo = scanner.nextLine();
                                        Kids kidEncontrado = null;
                                        
                                        for (Kids i : KidsArray) {
                                            if (i.getId().equalsIgnoreCase(idKid)) {
                                                kidEncontrado = i;
                                                break;
                                            }
                                        }
                                        if(kidEncontrado == null){
                                            System.out.println("No se encontro un niño con ese id");
                                            break;
                                        }
                                        
                                        Regalos regaloEncontrado = null;
                                        
                                        for (Regalos k : RegalosArray) {
                                            if (k.getCodigo().equalsIgnoreCase(codigoRegalo)) {
                                                regaloEncontrado = k;
                                                break;
                                            }
                                        }
                                        
                                        if (regaloEncontrado == null) {
                                            System.out.println("No se encontro un regalo con ese codigo");
                                            break;
                                        }
                                        
                                        if (regaloEncontrado.getCantidad() <= 0) {
                                            System.out.println("No hay inventario disponible para ese regalo, " + regaloEncontrado.getNombre());
                                            break;
                                        }
                                        
                                        boolean yaAsignado = false;
                                        for (Asignacion a : AsignacionesArray) {
                                            if(a.getIdKid().equalsIgnoreCase(idKid)){
                                                yaAsignado = true;
                                                break;
                                            }      
                                        }
                                        if (yaAsignado) {
                                            System.out.println("El niño ya tiene un regalo asignado");
                                            break;
                                        }
                                        
                                        Asignacion asignacioNueva = new Asignacion(idKid, codigoRegalo);
                                        AsignacionesArray.add(asignacioNueva);
                                        regaloEncontrado.setCantidad(regaloEncontrado.getCantidad() - 1);
                                        GuardarAsignacionesJSON.GuardarAsignaciones(AsignacionesArray);
                                        GuardarRegalosJSON.GuardarRegalos(RegalosArray);
                                        System.out.println("Regalo Asignado exitosamente a " + kidEncontrado.getNombre());
                                        break;
                                    case "4":
                                        System.out.println("Ingrese el ID del niño");
                                        String buscarAsig = scanner.nextLine();
                                        Asignacion buscar = null;
                                        
                                        if (buscarAsig.isBlank() || buscarAsig.isEmpty()) {
                                            System.out.println("Error: El id estaba vacio");
                                            break;
                                        }
                                        
                                        for (Asignacion i : AsignacionesArray) {
                                            if (i.getIdKid().equalsIgnoreCase(buscarAsig)) {
                                                buscar = i;
                                                break;
                                            }
                                            
                                        }
                                        
                                        if (buscar == null) {
                                            System.out.println("No se encontro un niño asignado con un regalo con ese id");
                                        }
                                        else{
                                            System.out.println("Asignacion encontrada: ");
                                            Kids kid = null;
                                            
                                            for (Kids r : KidsArray) {
                                                if (r.getId().equalsIgnoreCase(buscar.getIdKid())) {
                                                    kid = r;
                                                    break;
                                                }
                                            }
                                            
                                            Regalos regalo = null;
                                            for (Regalos c : RegalosArray) {
                                                if (c.getCodigo().equalsIgnoreCase(buscar.getCodigoRegalo())) {
                                                    regalo = c;
                                                    break;
                                                }
                                            }
                                            if (kid != null && regalo != null) {
                                                System.out.println("Niño: " + kid.getNombre() + " " + kid.getApellido());
                                                System.out.println("Regalo: " + regalo.getNombre());
                                            }
                                        }
                                        break;
                                        
                                    case "5":

                                        
                                        boolean ejecutarReps = true;
                                        
                                        while (ejecutarReps) {
                                            System.out.println("+++++++++++++++Menu Reportes+++++++++++++++");
                                            System.out.println("1. Inventario actual de regalos \n2. listado completo de niños registrados \n3. Detalle de regalos asignados a cada niño \n4. Listado de niños registraos sin regalo asignado \n5. Mostrar Regalos segun marca \n6. Salir" );
                                            String opcionReps = scanner.nextLine();
                                            
                                            switch (opcionReps) {
                                                case "1":
                                                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("ReporteInventario.txt"))) {
                                                        for (Regalos r : RegalosArray) {
                                                            String linea = "Codigo: " + r.getCodigo() + " Nombre: " + r.getNombre() + " Marca: " + r.getMarca() + " Cantidad: " + r.getCantidad();
                                                            System.out.println(linea);
                                                            writer.write(linea);
                                                            writer.newLine();
                                                        }
                                                        System.out.println("Reporte de inventario generado en ReporteInventario.txt");
                                                    } catch (IOException e) {
                                                        System.out.println("Error al general el archivo: " + e.getMessage());
                                                    }
                                                    break;
                                                    
                                                case "2":
                                                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("ReporteKids.txt"))) {
                                                        for (Kids k : KidsArray) {
                                                            String linea = "Id: " + k.getId() + " Nombre: " + k.getNombre() + " " + k.getApellido() + " Edad: " + k.getEdad() + " Ciudad " + k.getCiudad() + " Direccion: " + k.getDireccion();
                                                            System.out.println(linea);
                                                            writer.write(linea);
                                                            writer.newLine();
                                                        }
                                                    } catch (IOException e) {
                                                        System.out.println("Error al general archivo: " + e.getMessage());
                                                    }
                                                    break;
                                                case "3":
                                                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("ReporteAsignaciones.txt"))) {
                                                        for (Asignacion a : AsignacionesArray) {
                                                            Kids kid = null;
                                                            Regalos regalo = null;
                                                            
                                                            for (Kids k : KidsArray) {
                                                                if (k.getId().equalsIgnoreCase(a.getIdKid())) {
                                                                    kid = k;
                                                                    break;
                                                                }
                                                            }
                                                            for (Regalos r : RegalosArray) {
                                                                if (r.getCodigo().equalsIgnoreCase(a.getCodigoRegalo())) {
                                                                    regalo = r;
                                                                }
                                                            }
                                                            
                                                            if (kid != null && regalo != null) {
                                                                String linea = "Niño: " + kid.getNombre() + " " + kid.getApellido() + "Id: " + kid.getId() + "| Regalo: " + regalo.getNombre() + "Regalo codigo: " + regalo.getCodigo();
                                                                System.out.println(linea);
                                                                writer.write(linea);
                                                                writer.newLine();
                                                            }
                                                            System.out.println("Reporte generado en ReportesAsignaciones.txt");
                                                        }
                                                    } catch (IOException e) {
                                                        System.out.println("Error al generar el archivo: " + e.getMessage());
                                                    }
                                                    break;
                                                case "4":
                                                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("ReporteKidsSinRegalo.txt"))) {
                                                        for (Kids k : KidsArray) {
                                                            boolean asignado = false;
                                                            
                                                            for (Asignacion a : AsignacionesArray) {
                                                                if (a.getIdKid().equalsIgnoreCase(k.getId())) {
                                                                    asignado = true;
                                                                    break;
                                                                }
                                                            }
                                                            
                                                            if (!asignado) {
                                                                String linea = "Niño: " + k.getNombre() + " " + k.getApellido() + " ID: " + k.getId() + " Edad: " + k.getEdad() + " Ciudad: " + k.getCiudad();
                                                                System.out.println(linea);
                                                                writer.write(linea);
                                                                writer.newLine();
                                                            }
                                                            System.out.println("Reporte de niños sin regalo generado en ReporteKidsSinRegalo.txt");
                                                        }
                                                    } catch (IOException e) {
                                                        System.out.println("Error al generar el archivo: " + e.getMessage());
                                                    }
                                                    break;
                                                case "5":
                                                    System.out.println("Ingrese la marca");
                                                    String marca = scanner.nextLine();
                                                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("ReporteMarca_" + marca + ".txt"))) {
                                                        for (Regalos r : RegalosArray) {
                                                            if (r.getMarca().equalsIgnoreCase(marca)) {
                                                                String linea = "Codigo: " + r.getCodigo() + " Nombre: " + r.getNombre() + " Descripcion: " + r.getDescripcion() + " Cantidad: " + r.getCantidad();
                                                                writer.write(linea);
                                                                writer.newLine();
                                                            }
                                                        }
                                                        System.out.println("Reporte generado en archivo ReporteMarca_" + marca + ".txt");
                                                        
                                                    } catch (IOException e) {
                                                        System.out.println("Error al generar el archivo: " + e.getMessage());
                                                    }
                                                    break;
                                                case "6":
                                                    System.out.println("Saliendo");
                                                    ejecutarReps = false;
                                                    break;
                                                default:
                                                    System.out.print("Elije una opcion valida");
                                                    break;
                                            }
                                            
                                        }
                                        
                                        break;
                                    case "6":
                                        System.out.println("Saliendo de menu principal");
                                        ejecutarMenu = false;
                                        break;
                                    default:
                                        System.out.println("Opcion invalida!");
                                }
                                
                            }
                            
                            
                            
                        }
                        else{
                            System.out.println("Usuario o contraseña incorrectos");
                        }
                    }
                    break;
                case "2":
                    System.out.println("Ingrese el nombre del usuario que quieres crear:");
                    String nuevoUsuario = scanner.nextLine();
                    System.out.println("Ingrese la contraseña que quieres ponerle al usuario:");
                    String nuevoPassword = scanner.nextLine();
                    System.out.println("Ingresa la contraseña otra vez para verificar que este bien:");
                    String checkPassword = scanner.nextLine();
                                        
                    if (nuevoUsuario.isBlank() || nuevoUsuario.isEmpty()) {
                        System.out.println("Error: tienes que ingresar un usuario valido");
                        break;
                    }
                    else if (nuevoPassword.isBlank() || nuevoPassword.isEmpty()) {
                        System.out.println("Error: tienes que ingresar una contraseña");    
                        break;
                    }
                    
                    if (!checkPassword.equals(nuevoPassword)) {
                        System.out.println("Error: Las contraseñas ingresadas no son las mismas");
                        break;
                    }
                    boolean existe = false;
                    for (UsuariosClass i : Usuarios){
                        if(i.getNombreUsuario().equalsIgnoreCase(nuevoUsuario)){
                            existe = true;
                            break;
                        }
                    }
                    
                    if (existe) {
                        System.out.println("Error: Ya hay un usuario con el usuario que usaste. ");
                    }
                    else{
                        UsuariosClass addUsuario = new UsuariosClass(nuevoUsuario, nuevoPassword);
                        Usuarios.add(addUsuario);
                        GuardarUsuariosJSON.guardarUsuarios(Usuarios);
                        System.out.println("Usuario creado exitosamente!");
                    }
                    break;
                case "3":
                    ejecutar = false;
                    System.out.println("Saliendo del sistema, adios!");
                    break;
                default:
                    System.out.println("Opcion no valida!");
            }
            
        }
        
        
    }
}
