package org.example.Clases.Menus;

import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;

import java.io.File;
import java.util.Scanner;

public class SwitchMenuTrenes <T extends Tren> {
    public SwitchMenuTrenes() {
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

    public static String mostrarTrenes (Almacenamiento almacenamiento) {
        if(seleccionarTipo() instanceof TrenDeCarga) {
            try {
                if(new File(almacenamiento.getTrenesDeCarga()).length() > 0) {

                    return "carga" + GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesDeCarga())), TrenDeCarga::getJSONObject).toString();
                }
            } catch (FileDoesntExistException e) {
                return "El archivo no existe";
            }
        } else {
            try {
                if(new File(almacenamiento.getTrenesComerciales()).length() > 0) {
                    return "comercial" + GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject).toString();
                }
            } catch (FileDoesntExistException e) {
                return "El archivo no existe";
            }
        }
        return null;
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
     * @param almacenamiento clase que contiene todos los archivos.
     * @return true si se pudo modificar el archivo sin problema.
     * @throws IllegalArgumentException si la opcion ingresada no es valida.
     */
    public static boolean modificarTren (int op, Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionTren<Tren> gt;
        Tren trenModificado = seleccionarTipo();

        switch(op) {
            case 0:
                break;
            case 1:
                try {
                    if(trenModificado instanceof TrenDeCarga) {
                        if (new File(almacenamiento.getVagonesDeCarga()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), TrenDeCarga::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Modelo: ");
                            trenModificado.setModelo(sc.nextLine());
                            System.out.println(trenModificado.equals(gt.verificarTren(trenModificado.getPatente())));
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
                        }
                    } else {
                        if (new File(almacenamiento.getTrenesComerciales()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Modelo: ");
                            trenModificado.setModelo(sc.nextLine());
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
                        }
                    }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
                break;
            case 2:
                try {
                    if(trenModificado instanceof TrenDeCarga) {
                        if (new File(almacenamiento.getVagonesDeCarga()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), TrenDeCarga::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Patente: ");
                            trenModificado.setPatente(sc.nextLine());
                            System.out.println(trenModificado.equals(gt.verificarTren(trenModificado.getPatente())));
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
                        }
                    } else {
                        if (new File(almacenamiento.getTrenesComerciales()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Patente: ");
                            trenModificado.setPatente(sc.nextLine());
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
                        }
                    }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
                break;
            case 3:
                try {
                    if(trenModificado instanceof TrenDeCarga) {
                        if (new File(almacenamiento.getVagonesDeCarga()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), TrenDeCarga::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Ubicacion: ");
                            trenModificado.setUbicacion(sc.nextLine());
                            System.out.println(trenModificado.equals(gt.verificarTren(trenModificado.getPatente())));
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
                        }
                    } else {
                        if (new File(almacenamiento.getTrenesComerciales()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Ubicacion: ");
                            trenModificado.setUbicacion(sc.nextLine());
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
                        }
                    }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
                break;
            case 4:
                try {
                    if(trenModificado instanceof TrenDeCarga) {
                        if (new File(almacenamiento.getVagonesDeCarga()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), TrenDeCarga::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Capacidad: ");
                            trenModificado.setCapacidad(sc.nextInt());
                            System.out.println(trenModificado.equals(gt.verificarTren(trenModificado.getPatente())));
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
                        }
                    } else {
                        if (new File(almacenamiento.getTrenesComerciales()).length() > 0) {
                            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject);
                            System.out.println(gt);
                            System.out.println("Capacidad: ");
                            trenModificado.setCapacidad(sc.nextInt());
                            return GestionTren.modificarRegistro(gt.verificarTren(trenModificado.getPatente()), trenModificado, TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
                        }
                    }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
            default:
                throw new IllegalArgumentException("Opcion no valida.");
        }
        return false;
    }

    public static void administrarTrenes (int op, Almacenamiento almacenamiento) {
        int subOp;
        Scanner sc = new Scanner(System.in);
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
                do {
                    System.out.println(Menu.modificarTren());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                    try {
                        if(modificarTren(subOp, almacenamiento)) {
                            System.out.println("El tren se modifico exitosamente");
                        }
                        else {
                            System.out.println("Ocurrio un error");
                        }
                    } catch (ElementAlreadyExistsException e) {
                        System.out.println("El tren ya existe");
                    }
                } while (subOp != 0);
                break;
            case 3:
                if (eliminarTren(almacenamiento)) {
                    System.out.println("El tren se elimino con exito");
                }else{
                    System.out.println("No se encontró");
                }
                break;
            case 4:
                System.out.println(mostrarTrenes(almacenamiento));
                break;
            case 5:
                break;
        }
    }
}
