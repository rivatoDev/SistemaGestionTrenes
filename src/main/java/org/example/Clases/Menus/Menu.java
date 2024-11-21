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
                "--------------------------------------------------CLIENTE--------------------------------------------------";
    }

    public static String menuAdministrador () {
        return
                "--------------------------------------------------ADMINISTRADOR--------------------------------------------------\n" +
                "1. Gestionar Trenes" + '\n' +
                "2. Gestionar Vagones" + '\n' +
                "3. Gestionar Rutas" + "\n" +
                "4. Configuracion" + '\n' +
                "0. Cerrar Sesion" + '\n' +
                "--------------------------------------------------ADMINISTRADOR--------------------------------------------------";
    }

    public static String configuracion () {
        return "--------------------------------------------------CONFIGURACION--------------------------------------------------\n" +
               "1. Modificar Datos" + '\n' +
               "2. Eliminar Usuario." + '\n' +
               "0. Volver." + '\n' +
               "--------------------------------------------------CONFIGURACION--------------------------------------------------";
    }

    public static String modificarDatos () {
        return "--------------------------------------------------DATOS PERSONALES--------------------------------------------------\n" +
               "1. DNI." + '\n' +
               "2. Nombre." + '\n' +
               "3. Apellido." + '\n' +
               "4. Nombre De Usuario." + '\n' +
               "5. Clave." + '\n' +
               "0. Volver." + '\n' +
               "--------------------------------------------------DATOS PERSONALES--------------------------------------------------";
    }

    public static String menuTrenes(){
        return "---------------------------------------------MENU TRENES ADMINISTRADOR----------------------------------------------\n"+
                "1. Añadir tren." + "\n" +
                "2. Modificar tren."+ "\n" +
                "3. Eliminar tren." + "\n" +
                "4. Ver trenes" + "\n" +
                "0. Volver" + "\n" +
                "--------------------------------------------MENU TRENES ADMNISTRADOR-----------------------------------------------";
    }

    public static String menuTipoTren () {
        return "--------------------------------------------------VAGONES--------------------------------------------------\n" +
                "1. De Carga." + '\n' +
                "2. Comercial." + '\n' +
                "--------------------------------------------------VAGONES--------------------------------------------------";
    }

    public static String modificarTren () {
        return "--------------------------------------------------MODIFICAR--------------------------------------------------\n" +
                "1. Modelo." + '\n' +
                "2. Ubicacion" + "\n" +
                "3. Capacidad" + "\n" +
                "4. Agregar Vagon" + "\n" +
                "0. Volver." + '\n' +
                "--------------------------------------------------MODIFICAR--------------------------------------------------";
    }

    public static String menuVagones () {
        return "--------------------------------------------------VAGONES--------------------------------------------------\n" +
               "1. Agregar Vagon." + '\n' +
               "2. Modificar Vagon." + '\n' +
               "3. Eliminar Vagon." + '\n' +
               "4. Mostrar Vagones." + '\n' +
               "0. Volver." + '\n' +
               "--------------------------------------------------VAGONES--------------------------------------------------";
    }

    public static String menuTipoVagon () {
        return "--------------------------------------------------VAGONES--------------------------------------------------\n" +
                "1. De Carga." + '\n' +
                "2. Comercial." + '\n' +
                "--------------------------------------------------VAGONES--------------------------------------------------";
    }

    public static String modificarVagon () {
        return "--------------------------------------------------MODIFICAR--------------------------------------------------\n" +
               "1. Capacidad." + '\n' +
               "0. Volver." + '\n' +
               "--------------------------------------------------MODIFICAR--------------------------------------------------";
    }

    public static String menuRutas(){
        return "---------------------------------------------MENU TRENES ADMINISTRADOR----------------------------------------------\n"+
                "1. Añadir ruta." + "\n" +
                "2. Modificar ruta."+ "\n" +
                "3. Eliminar ruta." + "\n" +
                "4. Ver rutas" + "\n" +
                "0. Volver" + "\n" +
                "--------------------------------------------MENU TRENES ADMNISTRADOR-----------------------------------------------";
    }

    public static String modificarRuta () {
        return "--------------------------------------------------MODIFICAR--------------------------------------------------\n" +
                "1. Salida" + '\n' +
                "2. Llegada" + "\n" +
                "3. Fecha" + "\n" +
                "0. Volver." + '\n' +
                "--------------------------------------------------MODIFICAR--------------------------------------------------";
    }
}