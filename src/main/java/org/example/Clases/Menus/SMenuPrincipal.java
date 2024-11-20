package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.WrongPasswordException;
import org.example.Main;
import org.json.JSONArray;

import java.util.Objects;
import java.util.Scanner;

public class SMenuPrincipal {
    public SMenuPrincipal() {
    }

    public static boolean modificarDatos(int op, Usuario usuario, String archivo) {
        Usuario usuarioModificado = usuario;
        Scanner sc = new Scanner(System.in);
        switch(op) {
            case 0:
                break;
            case 1:
                System.out.println("DNI: ");
                usuarioModificado.setDni(sc.nextLine());
                break;
            case 2:
                System.out.println("Nombre: ");
                usuarioModificado.setNombre(sc.nextLine());
                break;
            case 3:
                System.out.println("Apellido: ");
                usuarioModificado.setApellido(sc.nextLine());
                break;
            case 4:
                System.out.println("Nombre De Usuario: ");
                usuarioModificado.setNombreUsuario(sc.nextLine());
                if(Objects.requireNonNull(GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))).getUsuarios().contains(usuarioModificado)) {
                   throw new ElementAlreadyExistsException("El nombre de usuario no esta disponible, elige otro.");
                }
                break;
            case 5:
                System.out.println("Clave Actual: ");
                if(!Objects.equals(usuario.getContrasenia(), sc.nextLine())) {
                    throw new WrongPasswordException("La contraseña no es la correcta.");
                }
                System.out.println("Nueva Clave: ");
                usuarioModificado.setContrasenia(sc.nextLine());
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }

        if(op >= 1 && op <= 5) {
           return GestionUsuario.modificarRegistro(usuario, usuarioModificado, archivo);
        }
        return false;
    }

    public static void configuracion (int op, Usuario usuario, String archivo) {
        int subOp = 0;
        Scanner sc = new Scanner(System.in);
        switch(op) {
            case 0:
                break;
            case 1:
                do {
                    System.out.println(usuario);
                    System.out.println(Menu.modificarDatos());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    sc.nextLine();
                    try {
                        modificarDatos(subOp, usuario, archivo);
                    } catch (ElementAlreadyExistsException | WrongPasswordException e) {
                        System.out.println(e.getMessage());
                    }
                } while (subOp != 0);
                break;
            case 2:
                GestionUsuario.eliminarRegistro(usuario, archivo);
                break;
        }
    }
}