package org.example.Clases.Menus.Taquillero;

import org.example.Clases.FamiliaPersona.GestionMaquinista;
import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.Menus.Menu;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;

import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SMenuMaquinistas {
    public SMenuMaquinistas() {
    }

    //Mostrar
    /**
     * Lee los maquinistas de un archivo.
     * @param archivo El archivo a leer.
     * @return Un gestor que contiene todas los maquinistas del archivo.
     */
    public static GestionMaquinista leerMaquinistas (String archivo) {
        GestionMaquinista gm;
        try {
            if(new File(archivo).length() > 0) {
                gm = GestionMaquinista.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));
            } else {
                gm = new GestionMaquinista();
            }
        } catch (FileDoesntExistException e) {
            return null;
        }
        return gm;
    }
    //Mostrar

    //Alta
    /**
     * Carga un maquinista
     * @return el maquinista cargado.
     */
    public static Maquinista ingresarMaquinista () {
        Scanner sc = new Scanner(System.in);
        Maquinista m = new Maquinista();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("ID: ");
        m.setIdMaquinista(sc.nextLine());
        System.out.println("Nombre: ");
        m.setNombre(sc.nextLine());
        System.out.println("Apellido");
        m.setApellido(sc.nextLine());
        System.out.println("DNI: ");
        m.setDni(sc.nextLine());
        System.out.println("----------------------------------------------------------------------------------------------------");
        return m;
    }
    //Alta

    //Modificacion
    /**
     * Modifica a un maquinista
     * @param op Opcion a la que se va aentrar en el switch.
     * @param maquinista El maquinista a modificar.
     * @param archivo El nombre del archivo a utilizar.
     * @return true si se pudo modificar el maquinista sin problemas.
     */
    public static boolean modificarMaquinista(int op, Maquinista maquinista, String archivo) {
        Scanner sc = new Scanner(System.in);
        GestionMaquinista gm = leerMaquinistas(archivo);
        System.out.println(gm.verificarMaquinista(maquinista.getIdMaquinista()));

        if(new File(archivo).length() > 0) {
            try {
                switch (op) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("DNI: ");
                        maquinista.setDni(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("Nombre: ");
                        maquinista.setNombre(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Apellido");
                        maquinista.setApellido(sc.nextLine());
                        break;
                    default:
                        throw new IllegalArgumentException("Opcion no valida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("La opcion debe ser un numero entero.");
                sc.nextLine();
            }

            return GestionMaquinista.modificarRegistro(gm.verificarMaquinista(maquinista.getIdMaquinista()), maquinista, archivo);
        }
        return false;
    }
    //Modificacion

    /**
     * Menu para administrar a los Maquinistas
     * @param op Opcion a la que va a entrar al switch.
     * @param archivo El nombre del archivo a utilizar.
     */
    public static void administrarMaquinistas(int op, String archivo) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        Maquinista m;
        GestionMaquinista gm = leerMaquinistas(archivo);

        if (op > 1 && op < 4) {
            System.out.println("ID: ");
            try {
                m = gm.verificarMaquinista(sc.nextLine());

                switch (op) {
                    case 0:
                        break;
                    case 1:
                        try {
                            if (GestionMaquinista.agregarRegistro(ingresarMaquinista(), archivo)) {
                                System.out.println("Se agrego el maquinista exitosamente");
                            }
                        } catch (ElementAlreadyExistsException e) {
                            System.out.println("El maquinista ya existe.");
                        }
                        break;
                    case 2:
                        do {
                            System.out.println(Menu.modificarMaquinista());
                            System.out.println("Opcion: ");
                            subOp = sc.nextInt();
                            if(modificarMaquinista(subOp, m, archivo)) {
                                System.out.println("El maquinista se modifico exitosamente");
                            }
                            else {
                                System.out.println("Ocurrio un error");
                            }
                        } while (subOp != 0);
                        break;
                    case 3:
                        if(GestionMaquinista.eliminarRegistro(m, archivo)) {
                            System.out.println("Se elimino el maquinista con exito");
                        }
                        break;
                    case 4:
                        System.out.println(gm);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("La opcion debe ser un numero entero.");
                sc.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}