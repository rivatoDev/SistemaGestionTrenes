package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Usuario;

import java.util.Scanner;

public class SMenuTaquillero {
    public SMenuTaquillero() {
    }

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
                    SMenu
                }
                break;
        }
    }
}
