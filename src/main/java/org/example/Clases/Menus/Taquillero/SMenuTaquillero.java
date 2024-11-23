package org.example.Clases.Menus.Taquillero;

import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.Menus.Almacenamiento;
import org.example.Clases.Menus.Menu;
import org.example.Clases.Menus.SMenuPrincipal;

import java.util.Scanner;

public class SMenuTaquillero {
    public SMenuTaquillero() {
    }

    /**
     * Menu principal de los taquilleros.
     * @param op La opcion a la que se va a entrar en el switch.
     * @param usuario El usuario con el que se inicio sesion.
     * @param almacenamiento Objeto que contiene los nombres de todos los archivos del sistema.
     */
    public static void usuarioTaquillero(int op, Usuario usuario, Almacenamiento almacenamiento) {
        int subOp = 0;
        Scanner sc = new Scanner(System.in);

        switch (op) {
            case 0:
                break;
            case 1:
                do {
                    System.out.println(Menu.menuRutas());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    SMenuRutas.administrarRutas(subOp, almacenamiento);
                } while (subOp != 0);
                break;
            case 2:
                System.out.println(Menu.menuMaquinistas());
                System.out.println("Opcion: ");
                subOp = sc.nextInt();
                SMenuMaquinistas.administrarMaquinistas(subOp, almacenamiento.getMaquinistas());
                break;
            case 3:
                do {
                    System.out.println(Menu.configuracion());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    sc.nextLine();
                    SMenuPrincipal.configuracion(subOp, usuario, almacenamiento.getUsuarios());
                } while (subOp != 0);
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }
}