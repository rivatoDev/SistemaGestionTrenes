package org.example.Clases.Menus.Cliente;

import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.Menus.Menu;
import org.example.Clases.Menus.SMenuPrincipal;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.WrongUserException;

import java.util.Scanner;

/**
 * Clase que contiene el menu para gestionar a los clientes.
 */
public class SMenuCliente {
    public SMenuCliente() {
    }

    /**
     * Menu principal de los clientes.
     * @param op opcion a usar en el switch.
     * @param usuario el usuario con el que se inicio sesion
     * @param archivo nombre del archivo a usar.
     */
    public static void usuarioCliente (int op, Usuario usuario, String archivo) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if (usuario.getTipoUsuario() == TipoUsuario.CLIENTE) {
                        VagonComercial vagon = new VagonComercial();
                        System.out.println("Ingrese el ID del ticket:");
                        String idTicket = sc.nextLine();

                        boolean agregado = vagon.cargarPasajero(usuario, idTicket);
                        if (agregado) {
                            System.out.println("Usuario agregado al vagon");
                        }
                    } else {
                        throw new WrongUserException();
                    }
                } catch (JSONObjectEliminatedException e) {
                    System.out.println("El pasajero no existe");
                } catch (WrongUserException e) {
                    System.out.println("El usuario no es el correcto");
                } catch (Exception e) {
                    System.out.println("Ocurrio un error");
                }
                break;
            case 2:
                do {
                    System.out.println(Menu.configuracion());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    sc.nextLine();
                    SMenuPrincipal.configuracion(subOp, usuario, archivo);
                } while (subOp != 0);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
}