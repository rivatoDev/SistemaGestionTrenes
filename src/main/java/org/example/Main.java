package org.example;


import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.Menus.Menu;
import org.example.Clases.Menus.SwitchMenuAdministrador;
import org.example.Clases.Menus.SwitchMenuCliente;
import org.example.Clases.Menus.SwitchMenuPrincipal;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Archivos
        final String almacenamientoVagones = "vagones.json";
        final String almacenamientoUsuarios = "usuarios.json";
        //Archivos

        //Utilidades
        Scanner sc = new Scanner(System.in);
        int op;
        int subOp;
        Usuario usuarioActivo;
        //Utilidades

        do {
            System.out.println(Menu.MenuPrincipal());
            System.out.println("Opcion");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 0:
                    System.out.println("--------------------------------------------------FIN--------------------------------------------------");
                    break;
                case 1:
                    usuarioActivo = iniciarSesion(almacenamientoUsuarios);
                    if(usuarioActivo != null) {
                        if (usuarioActivo.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                            System.out.println(Menu.menuCliente());
                            System.out.println("Opcion: ");
                            subOp = sc.nextInt();
                            sc.nextLine();
                            SwitchMenuAdministrador.usuarioAdministrador(subOp, usuarioActivo, almacenamientoUsuarios);
                        } else if (usuarioActivo.getTipoUsuario() == TipoUsuario.CLIENTE) {
                            do {
                                System.out.println(Menu.menuCliente());
                                System.out.println("Opcion: ");
                                subOp = sc.nextInt();
                                sc.nextLine();
                                SwitchMenuCliente.usuarioCliente(subOp, usuarioActivo, almacenamientoUsuarios);
                            } while (subOp != 0);
                        }
                    }
                    break;
                case 2:
                       if (crearUsuario(TipoUsuario.CLIENTE, almacenamientoUsuarios)) {
                           System.out.println("El usuario se ha creado con exito");
                       }
                    break;
            }
        } while(op != 0);

    }

    //Usuario
    public static boolean crearUsuario (TipoUsuario tipoUsuario, String archivo) {
        Scanner sc = new Scanner(System.in);
        Usuario usuario = new Usuario();
        boolean flag;

        System.out.println("--------------------------------------------------CREACION DE USUARIO--------------------------------------------------");
        System.out.println("DNI: ");
        usuario.setDni(sc.nextLine());
        System.out.println("Nombre: ");
        usuario.setNombre(sc.nextLine());
        System.out.println("Apellido: ");
        usuario.setApellido(sc.nextLine());

        do {
            try {
                System.out.println("Nombre De Usuario: ");
                usuario.setNombreUsuario(sc.nextLine());
                if (!Objects.requireNonNull(GestionUsuario.getJSONArray(new JSONArray(leerArchivo(archivo)))).getUsuarios().contains(usuario)) {
                    flag = true;
                } else {
                    System.out.println("El nombre de usuario ya esta ocupado");
                    flag = false;
                }
            } catch (FileDoesntExistException e) {
                flag = true;
            }
        } while (!flag);

        System.out.println("Clave: ");
        usuario.setContrasenia(sc.nextLine());
        usuario.setTipoUsuario(tipoUsuario);
        System.out.println("--------------------------------------------------CREACION DE USUARIO--------------------------------------------------");

        return GestionUsuario.agregarRegistro(usuario, archivo);
    }

    public static Usuario iniciarSesion(String archivo) {
        Scanner teclado = new Scanner(System.in);
        String nombreUsuario;
        String contrasenia;
        GestionUsuario gu = null;

        try {
            gu = GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));
        } catch (FileDoesntExistException e) {
            System.out.println("El archivo no existe.");
        }

        System.out.println("--------------------------------------------------INICIO DE SESION--------------------------------------------------");
        System.out.print("Nombre de Usuario: ");
        nombreUsuario = teclado.nextLine();
        System.out.println("Contraseña: ");
        contrasenia = teclado.nextLine();
        System.out.println("--------------------------------------------------INICIO DE SESION--------------------------------------------------");
        try {
            return Objects.requireNonNull(gu).verificarUsuario(nombreUsuario, contrasenia);
        } catch (NullPointerException e) {
            System.out.println("Nombre de usuario o clave incorrecta.");
        }
        return null;
    }
    //Usuario

    //Archivos
    /**
     * Crea un archivo de texto.
     * @param nombre Nombre del archivo.
     * @return true si se pudo crear el archivo sin problemas, sino false.
     */
    public static boolean crearArchivo (String nombre) {
        try {
            if (!new File(nombre).createNewFile()) {
                throw new FileAlreadyExistsException(nombre);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Lee un archivo de texto.
     * @param archivo Nombre del archivo.
     * @return Un JSONTokener con los datos del archivo.
     * @throws FileDoesntExistException Se lanza cuando el archivo a leer no existe.
     * Va a ser necesario para la carga de registros en el archivo.
     */
    public static JSONTokener leerArchivo (String archivo) throws FileDoesntExistException {
        JSONTokener json;
        try {
            json = new JSONTokener(new BufferedReader(new FileReader(archivo)));
        } catch (FileNotFoundException e) {
            throw new FileDoesntExistException();
        }
        return json;
    }
    //Archivos
}