package org.example;

import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Clases.Menus.Almacenamiento;
import org.example.Clases.Menus.Menu;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.json.JSONArray;

import java.util.Scanner;

public class SMenuVagones {
    public SMenuVagones() {
    }

    //Alta
    
    //Alta

    //Baja
    /**
     * Elimina un vagon seleccionado.
     * @param almacenamiento clase que contiene los nombres de todos los archivos necesarios.
     * @return true si se pudo eliminar el vagon sin problemas.
     */
    public static boolean eliminarVagon (Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionVagon<Vagon> gv;

        try {
            Vagon vagon = seleccionarTipo();
            if(vagon instanceof VagonDeCarga) {
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getNombreArchivoVagonesDeCarga())), VagonDeCarga::getJSONObject);
                System.out.println(gv);
                System.out.println("ID: ");
                return GestionVagon.eliminarRegistro(gv.verificarVagon(sc.nextLine()), VagonDeCarga::getJSONObject, almacenamiento.getNombreArchivoVagonesDeCarga());
            } else {
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getNombreArchivoVagonesComerciales())), VagonComercial::getJSONObject);
                System.out.println(gv);
                System.out.println("ID: ");
                return GestionVagon.eliminarRegistro(gv.verificarVagon(sc.nextLine()), VagonComercial::getJSONObject, almacenamiento.getNombreArchivoVagonesComerciales());
            }
        } catch (NullPointerException e) {
            System.out.println("ID invalido");
        } catch (IllegalArgumentException e) {
            System.out.println("Opcion incorrecta.");
        }
        return false;
    }
    //Baja

    //Modificacion
    /**
     * Selecciona el tipo de vagon.
     * @return Un vagon instanciado con el tipo seleccionado.
     * @throws IllegalArgumentException si la opcion ingresada no es correcta.
     */
    public static Vagon seleccionarTipo () {
        Scanner sc = new Scanner(System.in);
        Vagon vagon;

        System.out.println(Menu.menuTipoVagon());
        int op = sc.nextInt();
        switch (op) {
            case 1:
                vagon = new VagonDeCarga();
                break;
            case 2:
                vagon = new VagonComercial();
                break;
            default:
                throw new IllegalArgumentException("Opcion no valida");
        }
        return vagon;
    }

    /**
     * Modifica la capacidad del vagon, el id es inmutable.
     * @param op el numero del menu a acceder.
     * @param archivo el archivo donde se va a guardar
     * @return true si se pudo modificar el archivo sin problema.
     * @throws IllegalArgumentException si la opcion ingresada no es valida.
     */
    public static boolean modificarVagon (int op, Almacenamiento almacenamiento) {
        boolean flag;
        Scanner sc = new Scanner(System.in);
        GestionVagon<Vagon> gv;
        Vagon vagonModificado;

        do {
            try {
                vagonModificado = seleccionarTipo();
                flag = true;
            } catch (IllegalArgumentException) {
                System.out.println("Opcion invalida");
                flag = false;
            }
        } while (!flag);

        switch(op) {
            case 0:
                break;
            case 1:
                System.out.println("Capacidad: ");
                vagonModificado.setCapacidad(sc.nextInt());
                break;
            default:
                throw new IllegalArgumentException("Opcion no valida.");
        }

        try {
            if(vagonModificado instanceof VagonDeCarga) {
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getNombreArchivoVagonesDeCarga())), VagonDeCarga::getJSONObject);
                System.out.println(gv);
                System.out.println("ID: ");
                return GestionVagon.modificarRegistro(gv.verificarVagon(sc.nextLine()), vagonModificado, VagonDeCarga::getJSONObject, almacenamiento.getNombreArchivoVagonesDeCarga());
            } else {
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getNombreArchivoVagonesComerciales())), VagonComercial::getJSONObject);
                System.out.println(gv);
                System.out.println("ID: ");
                return GestionVagon.modificarRegistro(gv.verificarVagon(sc.nextLine()), vagonModificado, VagonComercial::getJSONObject, almacenamiento.getNombreArchivoVagonesComerciales());
            }
        } catch (NullPointerException e) {
            System.out.println("ID invalido");
        }
        return false;
    }
    //Modificacion

    /**
     * Carga, elimina y modifica los vagones.
     * @param op opcion a acceder.
     * @param almacenamiento Clase que contiene los nombres de todos los archivos.
     */
    public static void administrarVagones (int op, Almacenamiento almacenamiento) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if(GestionVagon.agregarRegistro(vagon, tipoVagon, archivo))
                        System.out.println("El Vagon se agrego exitosamente");
                    else
                        System.out.println("Ocurrio un error");
                } catch (ElementAlreadyExistsException e) {
                    System.out.println("El Vagon ya existe");
                }
                break;
            case 2:
                do {
                    System.out.println(Menu.modificarVagon());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    try {
                        if(modificarVagon(subOp, almacenamiento)) {
                            System.out.println("El Vagon se modifico exitosamente");
                        }
                        else {
                            System.out.println("Ocurrio un error");
                        }
                    } catch (ElementAlreadyExistsException e) {
                        System.out.println("El Vagon ya existe");
                    }
                } while (subOp != 0);
                break;
            case 3:
                if (eliminarVagon(almacenamiento)) {
                    System.out.println("El usuario se elimino con exito");
                }
                break;
        }
    }
}