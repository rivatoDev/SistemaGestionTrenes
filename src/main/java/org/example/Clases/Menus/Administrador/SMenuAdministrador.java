package org.example.Clases.Menus.Administrador;

import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.Menus.*;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase que contiene el menu para poder gestionar a los usuarios administradores
 */
public class SMenuAdministrador {
    public SMenuAdministrador() {
    }

    /**
     * Menu principal del administrador.
     * @param op la opcion seleccionada para el switch.
     * @param usuario El usuario con el que se inicio sesion.
     * @param almacenamiento la clase que contiene los nombres de todos los archivos del sistema.
     */
    public static void usuarioAdministrador (int op, Usuario usuario, Almacenamiento almacenamiento) {
        int subOp = 0;
        Scanner sc = new Scanner(System.in);
        GestionUsuario gu;

        try {
            gu = GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getUsuarios())));
            switch (op) {
                case 0:
                    break;
                case 1:
                    do {
                        System.out.println(Menu.menuTrenes());
                        System.out.println("Opcion: ");
                        subOp = sc.nextInt();
                        SMenuTrenes.administrarTrenes(subOp, almacenamiento);
                    } while(subOp != 0);
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
                    if (Main.crearUsuario(TipoUsuario.TAQUILLERO, almacenamiento.getUsuarios())) {
                        System.out.println("El usuario se ha creado con exito");
                    }
                    break;
                case 4:
                    if (Main.crearUsuario(TipoUsuario.ADMINISTRADOR, almacenamiento.getUsuarios())) {
                        System.out.println("El usuario se ha creado con exito");
                    }
                    break;
                case 5:
                    System.out.println(gu);
                    System.out.println("Nombre de Usuario");
                    try {
                        if(GestionUsuario.eliminarRegistro(gu.verificarUsuario(sc.nextLine()), almacenamiento.getUsuarios())) {
                            System.out.println("Se elimino el usuario con exito");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
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
        } catch (FileDoesntExistException e) {
            System.out.println("El archivo no existe");
            op = 0;
        } catch (InputMismatchException e) {
            System.out.println("La opcion debe ser un numero entero.");
            sc.nextLine();
        }
    }
}