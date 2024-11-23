package org.example.Clases.Menus.Administrador;

import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Clases.Menus.Almacenamiento;
import org.example.Clases.Menus.Menu;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Excepciones.LowCapacityException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Clase que contiene el menu para gestionar los trenes.
 */
public class SMenuTrenes {
    public SMenuTrenes() {
    }

    /**
     * Selecciona el tipo de tren.
     * @return Un tren ya instanciado del tipo de tren especificado.
     */
    public static Tren seleccionarTipo() {
        Scanner sc = new Scanner(System.in);
        Tren tren = null;

        while (tren == null) {
            System.out.println(Menu.menuTipoTren());
            System.out.println("Opcion: ");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    tren = new TrenDeCarga();
                    break;
                case 2:
                    tren = new TrenComercial();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        return tren;
    }

    /**
     * Lee todos los trenes que se encuentran en un archivo.
     * @param tipoTren Function con el metodo de getJSONObject del tipo de tren.
     * @param archivo El nombre del archivo a utilizar.
     * @return una clase gestora con todos los trenes del archivo.
     */
    public static GestionTren<Tren> leerTrenes (Function<JSONObject, Tren> tipoTren, String archivo) {
        GestionTren<Tren> gt;
        try {
            if(new File(archivo).length() > 0) {
                gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoTren);
            } else {
                gt = new GestionTren<>();
            }
        } catch (FileDoesntExistException e) {
            return null;
        }
        return gt;
    }

    //Alta
    /**
     * Carga un treen.
     * @param tren Tren cuya unica funcion es poder saber que tipo de tren se va a cargar.
     * @return el tren con los datos cargados.
     */
    public static Tren ingresarTren(Tren tren) {
        Scanner sc = new Scanner(System.in);
        Number capacidad;
        Tren t = tren;
        if(t != null) {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("Modelo: ");
            t.setModelo(sc.nextLine());
            System.out.println("Patente: ");
            t.setPatente(sc.nextLine());
            System.out.println("Ubicacion: ");
            t.setUbicacion(sc.nextLine());
            System.out.println("Capacidad: ");
            try {
                if(t instanceof TrenDeCarga) {
                    capacidad = sc.nextDouble();
                } else {
                    capacidad = sc.nextInt();
                }
                sc.nextLine();
                t.setCapacidad(capacidad);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número válido.");
            } catch (LowCapacityException e) {
                System.out.println(e.getMessage());
            }
            t.setEstado(true);
            t.setEstadoViaje(false);
            System.out.println("----------------------------------------------------------------------------------------------------");
        }
        return t;
    }
    //Alta

    //Modificacion
    /**
     * Modifica la capacidad del vagon, el id es inmutable.
     * @param op el numero del menu a acceder.
     * @return true si se pudo modificar el archivo sin problema.
     * @throws IllegalArgumentException si la opcion ingresada no es valida.
     */
    public static boolean modificarTren (int op, Tren tren, Function<JSONObject, Tren> tipoTren, String trenes, String vagones) {
        Scanner sc = new Scanner(System.in);
        Number capacidad;
        Function<Vagon, Boolean> tipoAcoplarVagon;
        Function<Vagon, Boolean> tipoQuitarVagon;
        Function<JSONObject, Vagon> tipogetJSONObject;
        GestionTren<Tren> gt = leerTrenes(tipoTren, trenes);
        GestionVagon<Vagon> gv;
        Vagon v;

        if(tren instanceof TrenDeCarga) {
            tipoAcoplarVagon = vagon -> ((TrenDeCarga) tren).acoplarVagon((VagonDeCarga) vagon);
            tipoQuitarVagon = vagon -> ((TrenDeCarga) tren).quitarVagon((VagonDeCarga) vagon);
            tipogetJSONObject = VagonDeCarga::getJSONObject;
        } else {
            tipoAcoplarVagon = vagon -> ((TrenComercial) tren).acoplarVagon((VagonComercial) vagon);
            tipoQuitarVagon = vagon -> ((TrenComercial) tren).quitarVagon((VagonComercial) vagon);
            tipogetJSONObject = VagonComercial::getJSONObject;
        }
        gv = SMenuVagones.leerVagones(tipogetJSONObject, vagones);
        System.out.println(gt.verificarTren(tren.getPatente()));

        if(new File(trenes).length() > 0) {
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
                    if (tren instanceof TrenDeCarga) {
                        capacidad = sc.nextDouble();
                    } else {
                        capacidad = sc.nextInt();
                    }
                    try {
                        tren.setCapacidad(capacidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida. Por favor ingrese un número válido.");
                    } catch (LowCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println(gv);
                    System.out.println("ID: ");
                    v = gv.verificarVagon(sc.nextLine());
                    tipoAcoplarVagon.apply(v);
                    GestionVagon.eliminarRegistro(v, tipogetJSONObject, vagones);
                    break;
                case 5:
                    System.out.println("ID: ");
                    v = gv.verificarVagon(sc.nextLine());
                    tipoQuitarVagon.apply(v);
                    GestionVagon.reactivarRegistro(v.getIdVagon(), tipogetJSONObject, vagones);
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no valida.");
            }
            return GestionTren.modificarRegistro(gt.verificarTren(tren.getPatente()), tren, tipoTren, trenes);
        }
        return false;
    }

    /**
     * Switch para gestionar los trenes.
     * @param op Opcion a usar en el switch.
     * @param tren El tren a usar.
     * @param gestor El gestor que contiene los trenes.
     * @param tipoTren Function del metodo getJSONObject del tipo de tren.
     * @param trenes Nombre del archivo que contiene trenes.
     * @param vagones Nombre del archivo que contiene vagones.
     */
    public static void switchTrenes(int op, Tren tren, GestionTren<Tren> gestor, Function<JSONObject, Tren> tipoTren, String trenes, String vagones) {
        int subOp;
        Scanner sc = new Scanner(System.in);

        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if(GestionTren.agregarRegistro(ingresarTren(tren), tipoTren, trenes)) {
                        System.out.println("El tren se agrego exitosamente");
                    }
                } catch (ElementAlreadyExistsException e) {
                    System.out.println("El tren ya existe");
                }
                break;
            case 2:
                if (GestionTren.reactivarRegistro(tren.getPatente(), tipoTren, trenes)) {
                    System.out.println("El tren se recupero exitosamente");
                } else {
                    System.out.println("Patente inexistente");
                }
                break;
            case 3:
                do {
                    System.out.println(Menu.modificarTren());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    sc.nextLine();
                    try {
                        if(subOp != 0) {
                            if(modificarTren(subOp, tren, tipoTren, trenes, vagones)) {
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
                break;
            case 4:
                if (GestionTren.eliminarRegistro(gestor.verificarTren(tren.getPatente()), tipoTren, trenes)) {
                    System.out.println("El tren se elimino con exito");
                } else {
                    System.out.println("No se encontró");
                }
                break;
            case 5:
                System.out.println(gestor);
                break;
        }
    }

    /**
     * Menu principal para gestionar los trenes.
     * @param op Opcion a usar en el switch.
     * @param almacenamiento Clase que contiene todos nombres de todos los archivos del sistema.
     */
    public static void administrarTrenes (int op, Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        Function<JSONObject, Tren> tipoTren = null;
        String trenes = null;
        String vagones = null;
        GestionTren<Tren> gt = null;
        Tren tren = null;

        if(op > 0) {
            tren = seleccionarTipo();
            if(tren instanceof TrenDeCarga) {
                tipoTren = TrenDeCarga::getJSONObject;
                trenes = almacenamiento.getTrenesDeCarga();
                vagones = almacenamiento.getVagonesDeCarga();
            } else {
                tipoTren = TrenComercial::getJSONObject;
                trenes = almacenamiento.getTrenesComerciales();
                vagones = almacenamiento.getVagonesComerciales();
            }

            if(op > 1) {
                gt = leerTrenes(tipoTren, trenes);
                if(gt == null) {
                    System.out.println("No existe el archivo");
                    op = 0;
                }

                if(op < 4) {
                    if(op == 2) {
                        System.out.println(gt.mostrarEliminados());
                    } else {
                        System.out.println(gt);
                    }
                    System.out.println("Patente: ");
                    tren.setPatente(sc.nextLine());
                    try {
                        tren = gt.verificarTren(tren.getPatente());
                    } catch (NoSuchElementException e) {
                        System.out.println("ID invalido.");
                        op = 0;
                    }
                }
            }
        }

        switchTrenes(op, tren, gt, tipoTren, trenes, vagones);
    }
}