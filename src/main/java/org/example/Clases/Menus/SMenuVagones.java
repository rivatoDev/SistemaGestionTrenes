package org.example.Clases.Menus;

import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;

import java.io.File;
import java.util.Scanner;

public class SMenuVagones {
    public SMenuVagones() {}

    /**
     * Selecciona el tipo de vagon.
     * @return Un vagon instanciado con el tipo seleccionado.
     * @throws IllegalArgumentException si la opcion ingresada no es correcta.
     */
    public static Vagon seleccionarTipo () {
        Scanner sc = new Scanner(System.in);
        Vagon vagon = null;
        boolean flag;

        do {
            flag = true;
            System.out.println(Menu.menuTipoVagon());
            System.out.println("Opcion: ");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    vagon = new VagonDeCarga();
                    break;
                case 2:
                    vagon = new VagonComercial();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    flag = false;
            }
        } while (!flag);
        return vagon;
    }

    //Mostrar
    public static String mostrarVagones (Almacenamiento almacenamiento) {
        if(seleccionarTipo() instanceof VagonDeCarga) {
            try {
                if(new File(almacenamiento.getVagonesDeCarga()).length() > 0) {
                    return GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), VagonDeCarga::getJSONObject).toString();
                }
            } catch (FileDoesntExistException e) {
                return "El archivo no existe";
            }
        } else {
            try {
                if(new File(almacenamiento.getVagonesComerciales()).length() > 0) {
                    return GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), VagonComercial::getJSONObject).toString();
                }
            } catch (FileDoesntExistException e) {
                return "El archivo no existe";
            }
        }
        return null;
    }
    //Mostrar

    //Alta
    /**
     * Ingresa un vagon por consola.
     * @return el vagon ingresado.
     */
    public static Vagon ingresarVagon () {
        Vagon v = seleccionarTipo();
        Scanner sc = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("ID: ");
        v.setIdVagon(sc.nextLine());
        System.out.println("Capacidad: ");
        v.setCapacidad(sc.nextDouble());
        System.out.println("----------------------------------------------------------------------------------------------------");
        return v;
    }

    /**
     * Agrega un vagon al sistema.
     * @param almacenamiento clase en la que se encuentran los nombres de todos los archivos.
     * @return true si se pudo agregar el vagon sin problemas, sino false.
     */
    public static boolean agregarVagon (Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionVagon<Vagon> gv;
        Vagon v = ingresarVagon();
        try {
            if(v instanceof VagonDeCarga) {
                return GestionVagon.agregarRegistro(v, VagonDeCarga::getJSONObject, almacenamiento.getVagonesDeCarga());
            } else {
                return GestionVagon.agregarRegistro(v, VagonComercial::getJSONObject, almacenamiento.getVagonesComerciales());
            }
        } catch (ElementAlreadyExistsException e) {
            System.out.println("El vagon ya existe");
        } catch (NullPointerException e) {
            System.out.println("ID invalido");
        } catch (IllegalArgumentException e) {
            System.out.println("Opcion incorrecta.");
        }
        return false;
        }
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
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), VagonDeCarga::getJSONObject);
                System.out.println(gv);
                System.out.println("ID: ");
                return GestionVagon.eliminarRegistro(gv.verificarVagon(sc.nextLine()), VagonDeCarga::getJSONObject, almacenamiento.getVagonesDeCarga());
            } else {
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesComerciales())), VagonComercial::getJSONObject);
                System.out.println(gv);
                System.out.println(gv.verificarVagon(sc.nextLine()));
                System.out.println("ID: ");
                return GestionVagon.eliminarRegistro(gv.verificarVagon(sc.nextLine()), VagonComercial::getJSONObject, almacenamiento.getVagonesComerciales());
            }
        } catch (FileDoesntExistException e) {
            System.out.println("El archivo no existe.");
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
     * Modifica la capacidad del vagon, el id es inmutable.
     * @param op el numero del menu a acceder.
     * @param almacenamiento clase que contiene todos los archivos.
     * @return true si se pudo modificar el archivo sin problema.
     * @throws IllegalArgumentException si la opcion ingresada no es valida.
     */
    public static boolean modificarVagon (int op, Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionVagon<Vagon> gv;
        Vagon vagonModificado = seleccionarTipo();

        switch(op) {
            case 0:
                break;
            case 1:
                try {
                    if(vagonModificado instanceof VagonDeCarga) {
                        if (new File(almacenamiento.getVagonesDeCarga()).length() > 0) {
                            gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), VagonDeCarga::getJSONObject);
                            System.out.println(gv);
                            System.out.println("ID: ");
                            vagonModificado.setIdVagon(sc.nextLine());
                            System.out.println("Nueva Capacidad: ");
                            vagonModificado.setCapacidad(sc.nextDouble());
                            sc.nextLine();
                            System.out.println(vagonModificado.equals(gv.verificarVagon(vagonModificado.getIdVagon())));
                            return GestionVagon.modificarRegistro(gv.verificarVagon(vagonModificado.getIdVagon()), vagonModificado, VagonDeCarga::getJSONObject, almacenamiento.getVagonesDeCarga());
                        }
                    } else {
                        if (new File(almacenamiento.getVagonesComerciales()).length() > 0) {
                            gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesComerciales())), VagonComercial::getJSONObject);
                            System.out.println(gv);
                            System.out.println("ID: ");
                            vagonModificado.setIdVagon(sc.nextLine());
                            System.out.println("Nueva Capacidad: ");
                            vagonModificado.setCapacidad(sc.nextDouble());
                            return GestionVagon.modificarRegistro(gv.verificarVagon(vagonModificado.getIdVagon()), vagonModificado, VagonComercial::getJSONObject, almacenamiento.getVagonesDeCarga());
                        }
                    }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
                break;
            default:
                throw new IllegalArgumentException("Opcion no valida.");
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
                    if(agregarVagon(almacenamiento)) {
                        System.out.println("El vagon se agrego exitosamente");
                    }
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
                    System.out.println("El vagon se elimino con exito");
                }
                break;
            case 4:
                System.out.println(mostrarVagones(almacenamiento));
                break;
        }
    }
}