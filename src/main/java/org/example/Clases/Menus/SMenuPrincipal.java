package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.WrongPasswordException;
import org.example.Main;
import org.json.JSONArray;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Clase que contiene el menu para poder gestionar los datos de los usuarios.
 */
public class SMenuPrincipal {
    public SMenuPrincipal() {
    }

    /**
     * Menu principal para poder gestionar los datos de los usuarios.
     * @param op Opcion a usar en el switch
     * @param usuario El usuario con el que se inicio sesion.
     * @param archivo Nombre del archivo a usar.
     * @return true si se pudo modificar los datos sin problemas, sino false.
     */
    public static boolean modificarDatos(int op, Usuario usuario, String archivo) {
        Usuario usuarioModificado = usuario;
        Scanner sc = new Scanner(System.in);

        try {
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
        } catch (InputMismatchException e) {
            System.out.println("La opcion debe ser un numero entero.");
            sc.nextLine();
        }

        if(op >= 1 && op <= 5) {
           return GestionUsuario.modificarRegistro(usuario, usuarioModificado, archivo);
        }
        return false;
    }

    /**
     * Menu para seleccionar que datos del usuario se va a modificar.
     * @param op La opcion a usar en el switch.
     * @param usuario El usuario con el que se inicio sesion.
     * @param archivo Nombre del archivo a usar.
     */
    public static void configuracion (int op, Usuario usuario, String archivo) {
        int subOp;
        Scanner sc = new Scanner(System.in);

        try {
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
                default:
                    System.out.println("Opcion incorrecta.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("La opcion debe ser un numero entero.");
            sc.nextLine();
        }
    }
}