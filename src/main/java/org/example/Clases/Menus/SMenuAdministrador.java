package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Usuario;

import java.util.Scanner;


public class SMenuAdministrador {
    public SMenuAdministrador() {
    }

    public static void usuarioAdministrador (int op, Usuario usuario, Almacenamiento almacenamiento) {
        int subOp = 0;
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 0:
                break;
            case 1:
                System.out.println(Menu.menuTrenes());
                System.out.println("Opcion: ");
                subOp = sc.nextInt();
                SwitchMenuTrenes.administrarTrenes(subOp, almacenamiento);
                break;
            case 2:
                do {
                    System.out.println(Menu.menuVagones());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    SMenuVagones.administrarVagones(subOp, almacenamiento);
                } while (subOp != 0);
                break;
            case 3:
                do {
                    System.out.println(Menu.configuracion());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    sc.nextLine();
                    SwitchMenuPrincipal.configuracion(subOp, usuario, almacenamiento.getUsuarios());
                } while (subOp != 0);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
}
