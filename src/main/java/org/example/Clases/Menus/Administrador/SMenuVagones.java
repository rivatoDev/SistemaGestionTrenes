package org.example.Clases.Menus.Administrador;

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
import java.util.Scanner;
import java.util.function.Function;

/**
 * Clase que contiene el menu para gestionar los vagones.
 */
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

    /**
     * Lee todos los vagones de un archivo.
     * @param tipoVagon Function con el metodo getJSONObject del tipo de vagon.
     * @param archivo Nombre del archivo que contiene los vagones.
     * @return Una clase gestora que contiene todos los vagones del archivo.
     */
    public static GestionVagon<Vagon> leerVagones (Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionVagon<Vagon> gv = null;
        try {
            if(new File(archivo).length() > 0) {
                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon);
            } else {
                gv = new GestionVagon<>();
            }
        } catch (FileDoesntExistException e) {
            return null;
        }
        return gv;
    }
    //Mostrar

    //Alta
    /**
     * Ingresa un vagon por consola.
     * @return el vagon ingresado.
     */
    public static Vagon ingresarVagon (Vagon vagon) {
        Vagon v = vagon;
        Scanner sc = new Scanner(System.in);
        Number capacidad;

        if(v != null) {
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("ID: ");
            v.setIdVagon(sc.nextLine());
            System.out.println("Capacidad: ");
            try {
                if(v instanceof VagonDeCarga) {
                    capacidad = sc.nextDouble();
                } else {
                    capacidad = sc.nextInt();
                }
                v.setCapacidad(capacidad);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número válido.");
            } catch (LowCapacityException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        return v;
    }
    //Alta

    //Modificacion
    /**
     * Modifica la capacidad del vagon, el id es inmutable.
     * @param op el numero del menu a acceder.
     * @return true si se pudo modificar el archivo sin problema.
     * @throws IllegalArgumentException si la opcion ingresada no es valida.
     */
    public static boolean modificarVagon (int op, Vagon vagon, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        Scanner sc = new Scanner(System.in);
        Number capacidad;
        GestionVagon<Vagon> gv = leerVagones(tipoVagon, archivo);
        System.out.println(gv.verificarVagon(vagon.getIdVagon()));

        if (new File(archivo).length() > 0) {
            switch(op) {
                case 0:
                    break;
                case 1:
                    System.out.println("Capacidad: ");
                    try {
                        if(vagon instanceof VagonDeCarga) {
                            capacidad = sc.nextDouble();
                        } else {
                            capacidad = sc.nextInt();
                        }
                        vagon.setCapacidad(capacidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida. Por favor ingrese un número válido.");
                    } catch (LowCapacityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no valida.");
            }
            return GestionVagon.modificarRegistro(gv.verificarVagon(vagon.getIdVagon()), vagon, tipoVagon, archivo);
        }
        return false;
    }
    //Modificacion

    /**
     * Switch para gestionar los vagones.
     * @param op Opcion a acceder
     * @param vagon Vagon a utilizar.
     * @param gestor El gestor que contiene todos los vagones.
     * @param tipoVagon Function de el metodo GetJSONObject del tipo de vagon.
     * @param archivo Nombre del archivo a utilizar.
     */
    public static void switchVagones (int op, Vagon vagon, GestionVagon<Vagon> gestor, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        int subOp;
        Scanner sc = new Scanner(System.in);

        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if (GestionVagon.agregarRegistro(ingresarVagon(vagon), tipoVagon, archivo)) {
                        System.out.println("El vagon se agrego exitosamente");
                    }
                } catch (ElementAlreadyExistsException e) {
                    System.out.println("El Vagon ya existe");
                }
                break;
            case 2:
                if(GestionVagon.reactivarRegistro(vagon.getIdVagon(), tipoVagon, archivo)) {
                    System.out.println("El vagon se recupero exitosamente");
                } else {
                    System.out.println("Vagon inexistente");
                }
                break;
            case 3:
                do {
                    System.out.println(Menu.modificarVagon());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    try {
                        if(subOp != 0) {
                            if (modificarVagon(subOp, vagon, tipoVagon, archivo)) {
                                System.out.println("El Vagon se modifico exitosamente");
                            } else {
                                System.out.println("Ocurrio un error");
                            }
                        }
                    } catch (ElementAlreadyExistsException e) {
                        System.out.println("El Vagon ya existe");
                    }
                } while (subOp != 0);
                break;
            case 4:
                if (GestionVagon.eliminarRegistro(gestor.verificarVagon(vagon.getIdVagon()), tipoVagon, archivo)) {
                    System.out.println("El vagon se elimino con exito");
                } else {
                    System.out.println("No se encontro.");
                }
                break;
            case 5:
                System.out.println(gestor);
                break;
        }
    }

    /**
     * Carga, elimina y modifica los vagones.
     * @param op opcion a acceder.
     * @param almacenamiento Clase que contiene los nombres de todos los archivos.
     */
    public static void administrarVagones (int op, Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        Function<JSONObject, Vagon> tipoVagon = null;
        String archivo = null;
        GestionVagon<Vagon> gv = null;
        Vagon vagon = null;
        if(op > 0) {
            vagon = seleccionarTipo();
            if(vagon instanceof VagonDeCarga) {
                tipoVagon = VagonDeCarga::getJSONObject;
                archivo = almacenamiento.getVagonesDeCarga();
            } else {
                tipoVagon = VagonComercial::getJSONObject;
                archivo = almacenamiento.getVagonesComerciales();
            }

            if(op > 1) {
                gv = leerVagones(tipoVagon, archivo);
                if(gv == null) {
                    System.out.println("No existe el archivo");
                    op = 0;
                }

                if (op < 4) {
                    if(op == 2) {
                        System.out.println(gv.mostrarEliminados());
                    } else {
                        System.out.println(gv);
                    }
                    System.out.println("ID: ");
                    vagon = gv.verificarVagon(sc.nextLine());
                    if(vagon == null) {
                        System.out.println("ID invalido.");
                        op = 0;
                    }
                }
            }
        }

        switchVagones(op, vagon, gv, tipoVagon, archivo);
    }
}