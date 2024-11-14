package org.example;


import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Clases.Menus.Almacenamiento;
import org.example.Clases.Menus.Menu;
import org.example.Clases.Menus.SMenuAdministrador;
import org.example.Clases.Menus.SwitchMenuCliente;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.FileDoesntExistException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Almacenamiento almacenamiento = new Almacenamiento("trenesDeCarga.json", "trenesComerciales.json",
                "vagonesDeCarga.json", "vagonesComerciales.json",
                "rutas.json", "maquinistas.json", "usuarios.json");

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
                    usuarioActivo = iniciarSesion(almacenamiento.getUsuarios());
                    if(usuarioActivo != null) {
                        if (usuarioActivo.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                            do {
                                System.out.println(Menu.menuAdministrador());
                                System.out.println("Opcion: ");
                                subOp = sc.nextInt();
                                sc.nextLine();
                                SMenuAdministrador.usuarioAdministrador(subOp, usuarioActivo, almacenamiento);
                            } while (subOp != 0);
                        } else if (usuarioActivo.getTipoUsuario() == TipoUsuario.CLIENTE) {
                            do {
                                System.out.println(Menu.menuCliente());
                                System.out.println("Opcion: ");
                                subOp = sc.nextInt();
                                sc.nextLine();
                                SwitchMenuCliente.usuarioCliente(subOp, usuarioActivo, almacenamiento.getUsuarios());
                            } while (subOp != 0);
                        }
                    }
                    break;
                case 2:
                       if (crearUsuario(TipoUsuario.CLIENTE, almacenamiento.getUsuarios())) {
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

    //Vagon
//    public static Vagon ingresarVagon () {
////        System.out.printl;2222
//    }

    //Vagon

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

    public static boolean crearTren(String archivo) {
        Scanner sc = new Scanner(System.in);
        Tren tren;

        System.out.println("--------------------------------------------------CREACION DE TREN--------------------------------------------------");
        System.out.println("¿El tren es comercial o de carga? (Escriba 'comercial' o 'carga'):");
        String tipoTren = sc.nextLine().toLowerCase();

        if (tipoTren.equals("comercial")) {
            tren = new TrenComercial();
        } else if (tipoTren.equals("carga")) {
            tren = new TrenDeCarga();
        } else {
            System.out.println("Tipo de tren no válido.");
            return false;
        }

        // Asignación de atributos
        System.out.println("Modelo: ");
        tren.setModelo(new String(sc.nextLine()));
        System.out.println("Patente: ");
        tren.setPatente(new String(sc.nextLine()));
        System.out.println("Ubicación: ");
        tren.setUbicacion(new String(sc.nextLine()));

        System.out.println("Capacidad (Número): ");
        tren.setCapacidad(sc.nextDouble());
        sc.nextLine();

        tren.setEstado(true);
        tren.setEstadoViaje(false);

        System.out.println("--------------------------------------------------CREACION DE TREN--------------------------------------------------");

        JSONObject a = new JSONObject();
        a = tren.convertirAJSONObject();

        return GestionTren.agregarRegistro(tren, TrenDeCarga::getJSONObject, archivo);
    }
}

