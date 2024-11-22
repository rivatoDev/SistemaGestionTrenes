package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Usuario;

public class Menu {
    public Menu() {
    }

    //Usuario
    public static String MenuPrincipal() {
        return
                "\n--------------------------------------------------INICIO--------------------------------------------------\n" +
                        "1. Iniciar Sesion" + '\n' +
                        "2. Crear Usuario" + '\n' +
                        "0. Salir" + '\n' +
                        "--------------------------------------------------INICIO--------------------------------------------------";
    }

    public static String configuracion() {
        return "--------------------------------------------------CONFIGURACION--------------------------------------------------\n" +
                "1. Modificar Datos" + '\n' +
                "2. Eliminar Usuario." + '\n' +
                "0. Volver." + '\n' +
                "--------------------------------------------------CONFIGURACION--------------------------------------------------";
    }

    public static String modificarDatos() {
        return "--------------------------------------------------DATOS PERSONALES--------------------------------------------------\n" +
                "1. DNI." + '\n' +
                "2. Nombre." + '\n' +
                "3. Apellido." + '\n' +
                "4. Nombre De Usuario." + '\n' +
                "5. Clave." + '\n' +
                "0. Volver." + '\n' +
                "--------------------------------------------------DATOS PERSONALES--------------------------------------------------";
    }
    //Usuario

    //Administrador
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

    //Trenes
    public static String menuTrenes(){
        return "---------------------------------------------MENU TRENES----------------------------------------------\n"+
                "1. Añadir tren." + "\n" +
                "2. Reactivar tren." + "\n" +
                "3. Modificar tren."+ "\n" +
                "4. Eliminar tren." + "\n" +
                "5. Ver trenes" + "\n" +
                "0. Volver" + "\n" +
                "--------------------------------------------MENU TRENES-----------------------------------------------";
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
                "5. Quitar Vagon" + "\n" +
                "0. Volver." + '\n' +
                "--------------------------------------------------MODIFICAR--------------------------------------------------";
    }
    //Trenes

    //Vagones
    public static String menuVagones () {
        return "--------------------------------------------------VAGONES--------------------------------------------------\n" +
                "1. Agregar Vagon." + '\n' +
                "2. Reactivar Vagon." + '\n' +
                "3. Modificar Vagon." + '\n' +
                "4. Eliminar Vagon." + '\n' +
                "5. Mostrar Vagones." + '\n' +
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
    //Vagones
    //Administrador

    //Taquillero
    public static String menuTaquillero () {
        return "--------------------------------------------------TAQUILLERO--------------------------------------------------\n" +
                "1. Rutas." + '\n' +
                "2. Maquinistas." + '\n' +
                "0. Volver." + '\n' +
               "--------------------------------------------------TAQUILLERO--------------------------------------------------";
    }

    //Rutas
    public static String menuRutas(){
        return "--------------------------------------------------RUTAS---------------------------------------------------\n" +
                "1. Añadir ruta." + "\n" +
                "2. Modificar ruta."+ "\n" +
                "3. Eliminar ruta." + "\n" +
                "4. Ver rutas" + "\n" +
                "0. Volver" + "\n" +
               "--------------------------------------------------RUTAS---------------------------------------------------";
    }

    public static String modificarRuta () {
        return "--------------------------------------------------MODIFICAR--------------------------------------------------\n" +
                "1. Salida" + '\n' +
                "2. Llegada" + "\n" +
                "3. Fecha" + "\n" +
                "4. Cambiar Tren" + "\n" +
                "5. Maquinista" + "\n" +
                "0. Volver." + '\n' +
               "--------------------------------------------------MODIFICAR--------------------------------------------------";
    }
    //Rutas

    //Maquinista
    public static String menuMaquinistas(){
        return "--------------------------------------------------RUTAS---------------------------------------------------\n" +
                "1. Añadir maquinista." + "\n" +
                "2. Modificar maquinista."+ "\n" +
                "3. Eliminar maquinista." + "\n" +
                "4. Ver maquinistas" + "\n" +
                "0. Volver" + "\n" +
                "--------------------------------------------------RUTAS---------------------------------------------------";
    }

    public static String modificarMaquinista () {
        return "--------------------------------------------------MODIFICAR--------------------------------------------------\n" +
                "1. DNI." + '\n' +
                "2. Nombre." + '\n' +
                "3. Apellido." + '\n' +
               "--------------------------------------------------MODIFICAR--------------------------------------------------";
    }
    //Maquinista
    //Taquillero

    //Cliente
    public static String menuCliente () {
        return
                "--------------------------------------------------CLIENTE--------------------------------------------------\n" +
                        "1. Sacar Entrada" + '\n' +
                        "2. Configuracion" + '\n' +
                        "0. Cerrar Sesion" + '\n' +
                        "--------------------------------------------------CLIENTE--------------------------------------------------";
    }
    //Cliente
}