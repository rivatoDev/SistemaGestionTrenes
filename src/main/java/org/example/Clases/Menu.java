package org.example.Clases;

import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private GestionUsuario gu;
    private Usuario usuarioActual;
    private Scanner teclado;

    public Menu() {
        this.gu = new GestionUsuario();
        this.teclado = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Ingresar como Usuario");
            System.out.println("2. Ingresar como Administrador");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    iniciarSesionUsuario();
                    break;
                case 2:
                    iniciarSesionAdministrador();
                    break;
                case 3:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            System.out.println();
        } while (opcion != 3);
    }

    private void iniciarSesionUsuario() throws NoSuchElementException{
        Usuario usuario = new Usuario();
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = teclado.nextLine();
        System.out.println("Ingrese su contraseña");
        String contrasenia = teclado.nextLine();
        if(usuario == gu.verificarUsuario(nombreUsuario, contrasenia)){
            usuario = gu.verificarUsuario(nombreUsuario, contrasenia);
            mostrarMenuUsuario(usuario);
        }
        else{
            throw new NoSuchElementException();
        }
    }

    private void iniciarSesionAdministrador() {
        System.out.print("Ingrese el nombre de administrador: ");
        String nombreAdmin = teclado.nextLine();
        System.out.print("Ingrese la clave de administrador: ");
        String claveAdmin = teclado.nextLine();

        if(!(verificarAdministrador)){

        }
    }

    private void mostrarMenuUsuario(Usuario usuario) {
        int opcion;
        do {
            System.out.println("\n----- Menú Usuario -----");
            System.out.println("1. ");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    System.out.println("Saliendo del menú de usuario");
                    break;
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
            System.out.println("5. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

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
                    System.out.println("Saliendo del menú de administrador");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
            System.out.println();
        } while (opcion != 5);
    }
}
