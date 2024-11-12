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

    public static String menuAdministrador () {
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

    public static String menuVagones () {
        return "--------------------------------------------------VAGONES--------------------------------------------------\n" +
               "1. Agregar Vagon." + '\n' +
               "2. Modificar Vagon." + '\n' +
               "3. Eliminar Vagon." + '\n' +
               "0. Salir." + '\n' +
               "--------------------------------------------------VAGONES--------------------------------------------------\n";
    }

    public static String modificarVagon () {
        return "--------------------------------------------------MODIFICAR--------------------------------------------------\n" +
               "1. ID." +
               "2. Capacidad." +
               "--------------------------------------------------MODIFICAR--------------------------------------------------\n";
    }
}