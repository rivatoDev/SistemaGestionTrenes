package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.WrongPasswordException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

public class SwitchMenuAdministrador {
    public SwitchMenuAdministrador() {
    }

    public static void usuarioAdministrador (int op, Usuario usuario, String archivo) {
        int subOp = 0;
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 0:
                break;
            case 1:
                    SwitchMenuTrenes();
                break;
            case 2:
                    System.out.println(Menu.menuVagones());
                break;
            case 3:
                do {
                    System.out.println(Menu.configuracion());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    sc.nextLine();
                    SwitchMenuPrincipal.configuracion(subOp, usuario, archivo);
                } while (subOp != 0);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public static boolean modificarVagon (int op, Vagon vagon, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        Vagon vagonModificado = vagon;
        Scanner sc = new Scanner(System.in);
        switch(op) {
            case 0:
                break;
            case 1:
                System.out.println("ID: ");
                vagonModificado.setIdVagon(sc.nextLine());
                break;
            case 2:
                System.out.println("Capacidad: ");
                vagonModificado.setCapacidad(sc.nextInt());
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }

        if(op >= 1 && op <= 2) {
            return GestionVagon.modificarRegistro(vagon, vagonModificado, tipoVagon, archivo);
        }
        return false;
    }

    public static void administrarVagones (int op, String archivo) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 0:
                break;
            case 1:
                try {
//                    if(GestionVagon.agregarRegistro(vagon, tipoVagon, archivo))
                        System.out.println("El Vagon se agrego exitosamente");
//                    else
                        System.out.println("Ocurrio un error");
                } catch (ElementAlreadyExistsException e) {
                    System.out.println("El Vagon ya existe");
                }
                break;
            case 2:
                do {
                    System.out.println(Menu.modificarVagon());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    try {
//                        modificarVagon(subOp, vagon, tipoVagon, archivo);
                        System.out.println("El Vagon se agrego exitosamente");
                    } catch (ElementAlreadyExistsException e) {
                        System.out.println("El Vagon ya existe");
                    }
                } while (subOp != 0);
                break;
            case 3:

                break;
        }
    }
}
