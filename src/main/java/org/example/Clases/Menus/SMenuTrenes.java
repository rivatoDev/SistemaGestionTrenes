package org.example.Clases.Menus;

import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.Scanner;
import java.util.function.Function;

public class SMenuTrenes<T extends Tren> {
    public SMenuTrenes() {
    }

    public static Tren seleccionarTipo() {
        Scanner sc = new Scanner(System.in);
        Tren tren = null;

        while (tren == null) {
            System.out.println(Menu.menuTipoTren());
            System.out.println("Opcion: ");
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    tren = new TrenDeCarga();
                    break;
                case "2":
                    tren = new TrenComercial();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        return tren;
    }

    public static GestionTren<Tren> leerTrenes (Function<JSONObject, Tren> tipoTren, String archivo) {
        String nombreArchivo;
        GestionTren<Tren> gt = null;
        try {
            if(new File(archivo).length() > 0) {
                gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoTren);
            }
        } catch (FileDoesntExistException e) {
            return null;
        }
        return gt;
    }

    public static Tren ingresarTren() {
        Tren tren = seleccionarTipo();
        Scanner sc = new Scanner(System.in);

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Modelo: ");
        tren.setModelo(sc.nextLine());
        System.out.println("Patente: ");
        tren.setPatente(sc.nextLine());
        System.out.println("Ubicacion: ");
        tren.setUbicacion(sc.nextLine());
        while (true) {
            System.out.println("Capacidad:");
            String input = sc.nextLine();
            try {
                double capacidad = Double.parseDouble(input);
                if (capacidad <= 0) {
                    System.out.println("La capacidad debe ser mayor a 0. Intente de nuevo.");
                } else {
                    tren.setCapacidad(capacidad);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número válido.");
            }
        }
        tren.setEstado(true);
        tren.setEstadoViaje(false);
        System.out.println("----------------------------------------------------------------------------------------------------");
        return tren;
    }

    /**
     * Agrega un vagon al sistema.
     *
     * @param almacenamiento clase en la que se encuentran los nombres de todos los archivos.
     * @return true si se pudo agregar el vagon sin problemas, sino false.
     */
    public static boolean agregarTren(Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionTren<Tren> gt;
        Tren tren = ingresarTren();
        try {
            if (tren instanceof TrenDeCarga) {
                return GestionTren.agregarRegistro(tren, TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
            } else {
                return GestionTren.agregarRegistro(tren, TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
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

    public static boolean eliminarTren (Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionTren<Tren> gt;

        try {
            Tren tren = seleccionarTipo();
            if(tren instanceof TrenDeCarga) {
                gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesDeCarga())), TrenDeCarga::getJSONObject);
                System.out.println(gt);
                System.out.println("Patente: ");
                return GestionTren.eliminarRegistro(gt.verificarTren(sc.nextLine()), TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
            } else {
                gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject);
                System.out.println(gt);
                System.out.println(gt.verificarTren(sc.nextLine()));
                System.out.println("Patente: ");
                return GestionTren.eliminarRegistro(gt.verificarTren(sc.nextLine()), TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
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
     * @return true si se pudo modificar el archivo sin problema.
     * @throws IllegalArgumentException si la opcion ingresada no es valida.
     */
    public static boolean modificarTren (int op, Tren tren, Function<JSONObject, Tren> tipoTren, String archivo) {
        Scanner sc = new Scanner(System.in);
        GestionTren<Tren> gt = leerTrenes(tipoTren, archivo);

        System.out.println(gt.verificarTren(tren.getPatente()));

        if(new File(archivo).length() > 0) {
            switch(op) {
                case 0:
                    break;
                case 1:
                    System.out.println("Nuevo Modelo: ");
                    tren.setModelo(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Ubicacion: ");
                    tren.setUbicacion(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Capacidad: ");
                    tren.setCapacidad(sc.nextInt());
                    break;
                case 4:
                    System.out.println("En proceso");
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no valida.");
            }
            return GestionTren.modificarRegistro(gt.verificarTren(tren.getPatente()), tren, tipoTren, archivo);
        }
        return false;
    }

    public static void administrarTrenes (int op, Almacenamiento almacenamiento) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        Function<JSONObject, Tren> tipoTren;
        String nombreArchivo;
        Tren tren;

        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if(agregarTren(almacenamiento)) {
                        System.out.println("El tren se agrego exitosamente");
                    }
                } catch (ElementAlreadyExistsException e) {
                    System.out.println("El tren ya existe");
                }
                break;
            case 2:
                tren = seleccionarTipo();
                if(tren instanceof TrenDeCarga) {
                    tipoTren = TrenDeCarga::getJSONObject;
                    nombreArchivo = almacenamiento.getTrenesDeCarga();
                } else {
                    tipoTren = TrenComercial::getJSONObject;
                    nombreArchivo = almacenamiento.getTrenesComerciales();
                }
                GestionTren<Tren> gt = leerTrenes(tipoTren, nombreArchivo);
                if(gt == null) {
                    System.out.println("No existe el archivo");
                } else {
                    System.out.println(gt);
                    System.out.println("Patente: ");
                    tren.setPatente(sc.nextLine());

                    if(!gt.getTrenes().contains(tren)) {
                        System.out.println("ID invalido.");
                    } else {
                        tren = gt.verificarTren(tren.getPatente());
                        do {
                            System.out.println(Menu.modificarTren());
                            System.out.println("Opcion: ");
                            subOp = sc.nextInt();
                            try {
                                if(subOp != 0) {
                                    if(modificarTren(subOp, tren, tipoTren, nombreArchivo)) {
                                        System.out.println("El tren se modifico exitosamente");
                                    }
                                    else {
                                        System.out.println("Ocurrio un error");
                                    }
                                }
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } while (subOp != 0);
                    }
                }
                break;
            case 3:
                if (eliminarTren(almacenamiento)) {
                    System.out.println("El tren se elimino con exito");
                }else{
                    System.out.println("No se encontró");
                }
                break;
            case 4:
                tren = seleccionarTipo();
                if(tren instanceof TrenDeCarga) {
                    tipoTren = TrenDeCarga::getJSONObject;
                    nombreArchivo = almacenamiento.getTrenesDeCarga();
                } else {
                    tipoTren = TrenComercial::getJSONObject;
                    nombreArchivo = almacenamiento.getTrenesComerciales();
                }
                System.out.println(leerTrenes(tipoTren, nombreArchivo));
                break;
        }
    }
}
