package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Usuario;

import java.util.Scanner;

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
                break;
            case 2:
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
}