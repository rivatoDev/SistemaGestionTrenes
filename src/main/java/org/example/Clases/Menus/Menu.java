package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Usuario;

public class Menu {
    public Menu() {}

    public static String MenuPrincipal() {
        return
                "\n--------------------------------------------------INICIO--------------------------------------------------\n" +
                "1. Iniciar Sesion" + '\n' +
                "2. Crear Usuario" + '\n' +
                "0. Salir" + '\n' +
                "--------------------------------------------------INICIO--------------------------------------------------";
    }

    public static String menuCliente () {
        return
                "--------------------------------------------------CLIENTE--------------------------------------------------\n" +
                "1. Sacar Entrada" + '\n' +
                "2. Configuracion" + '\n' +
                "0. Cerrar Sesion" + '\n' +
                "--------------------------------------------------CLIENTE--------------------------------------------------\n";
    }

    public static String administrador () {
        return
                "--------------------------------------------------ADMINISTRADOR--------------------------------------------------\n" +
                "1. Gestionar Trenes" + '\n' +
                "2. Gestionar Vagones" + '\n' +
                "3. Configuracion" + '\n' +
                "0. Cerrar Sesion" + '\n' +
                "--------------------------------------------------ADMINISTRADOR--------------------------------------------------\n";
    }

    public static String configuracion () {
        return "--------------------------------------------------CONFIGURACION--------------------------------------------------\n" +
               "1. Modificar Datos" + '\n' +
               "2. Eliminar Usuario." + '\n' +
               "0. Volver." + '\n' +
               "--------------------------------------------------CONFIGURACION--------------------------------------------------\n";
    }

    public static String modificarDatos () {
        return "--------------------------------------------------DATOS PERSONALES--------------------------------------------------\n" +
               "1. DNI." + '\n' +
               "2. Nombre." + '\n' +
               "3. Apellido." + '\n' +
               "4. Nombre De Usuario." + '\n' +
               "5. Clave." + '\n' +
               "0. Volver." + '\n' +
               "--------------------------------------------------DATOS PERSONALES--------------------------------------------------\n";
    }
    


    /* faltaria ver tema administradores*/
    /*
    private void iniciarSesionAdministrador() {
        System.out.print("Ingrese el nombre de administrador: ");
        String nombreAdmin = teclado.nextLine();
        System.out.print("Ingrese la clave de administrador: ");
        String claveAdmin = teclado.nextLine();

    }

    private void mostrarMenuUsuario(Usuario usuario) {
        int opcion;
        do {
            System.out.println("\n----- Menú Usuario -----");
            System.out.println("1. Ver mis datos");
            System.out.println("2. Modificar mis datos");
            System.out.println("3. ");
            System.out.println("4. Salir del programa");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(usuario.toString());
                    break;
                case 2:
                    //gu.modificarUsuario();
                case 3:
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                default:
                    System.out.println("Opcion no valida");
            }
            System.out.println();
        } while (opcion != 2);
    }

    private void mostrarMenuAdministrador() {
        int opcion;
        do {
            System.out.println("\n----- Menú Administrador -----");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Eliminar Usuario");
            System.out.println("3. Modificar Usuario");
            System.out.println("4. Listar Usuarios");
            System.out.println("5. Modificar administrador");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Saliendo del menu de administrador");
                default:
                    System.out.println("Opcion no valida");
            }
            System.out.println();
        } while (opcion != 6);
    }*/
}