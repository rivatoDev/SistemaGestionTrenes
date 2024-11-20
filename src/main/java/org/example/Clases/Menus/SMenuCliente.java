package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.WrongUserException;

import java.util.Scanner;

public class SMenuCliente {
    public SMenuCliente() {
    }

    public static void usuarioCliente (int op, Usuario usuario, String archivo) throws WrongUserException {
        int subOp = 0;
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
                    e.toString();
                } catch (WrongUserException e) {
                    e.toString();
                } catch (Exception e) {
                    e.printStackTrace();
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
