package org.example.Clases.Menus;

import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Clases.GestionRuta;
import org.example.Clases.Ruta;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;

import java.io.File;
import java.util.Scanner;

public class SMenuRutas {
    public SMenuRutas() {
    }
    public static int seleccionarTipo () {
        Scanner sc = new Scanner(System.in);
        boolean flag;
        int num = 0;

        do {
            flag = true;
            System.out.println(Menu.menuTipoTren());
            System.out.println("Opcion: ");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    num = 1;
                    break;
                case 2:
                    num = 2;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    flag = false;
            }
        } while (!flag);
        return num;
    }

    //Mostrar
    public static String mostrarRutas (Almacenamiento almacenamiento) {
            try {
                if(new File(almacenamiento.getRutas()).length() > 0) {
                    return GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getRutas()))).toString();
                }
            } catch (FileDoesntExistException e) {
                return "El archivo no existe";
            }
            return null;
        }

    //Mostrar

    //Alta

    /**
     * Devuelve una ruta
     * @param almacenamiento
     * @return
     */
//    public static Ruta  ingresarRuta (Almacenamiento almacenamiento) {
//        Ruta r = new Ruta();
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("----------------------------------------------------------------------------------------------------");
//        System.out.println("Tren: ");
//        int num = seleccionarTipo();
//        if(num == 1) {
//            SwitchMenuTrenes.mostrarTrenes(almacenamiento.getTrenesDeCarga());
//        }
//
//        r.setIdVagon(sc.nextLine());
//        System.out.println("Capacidad: ");
//        v.setCapacidad(sc.nextDouble());
//        System.out.println("----------------------------------------------------------------------------------------------------");
//        return v;
//    }


    /**
     * Agrega una ruta
     * @param almacenamiento
     * @return
     */
//    public static boolean agregarRuta (Almacenamiento almacenamiento) {
//        Scanner sc = new Scanner(System.in);
//        GestionRuta gr;
//        Ruta r = ingresarRuta(almacenamiento);
//        try {
//            return GestionRuta.agregarRegistro(r, almacenamiento.getVagonesDeCarga());
//        } catch (ElementAlreadyExistsException e) {
//            System.out.println("El vagon ya existe");
//        } catch (NullPointerException e) {
//            System.out.println("ID invalido");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Opcion incorrecta.");
//        }
//        return false;
//    }
    //Alta

    //Baja
//    public static boolean eliminarVagon (Almacenamiento almacenamiento) {
//        Scanner sc = new Scanner(System.in);
//        GestionVagon<Vagon> gv;
//
//        try {
//            Vagon vagon = seleccionarTipo();
//            if(vagon instanceof VagonDeCarga) {
//                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesDeCarga())), VagonDeCarga::getJSONObject);
//                System.out.println(gv);
//                System.out.println("ID: ");
//                return GestionVagon.eliminarRegistro(gv.verificarVagon(sc.nextLine()), VagonDeCarga::getJSONObject, almacenamiento.getVagonesDeCarga());
//            } else {
//                gv = GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getVagonesComerciales())), VagonComercial::getJSONObject);
//                System.out.println(gv);
//                System.out.println(gv.verificarVagon(sc.nextLine()));
//                System.out.println("ID: ");
//                return GestionVagon.eliminarRegistro(gv.verificarVagon(sc.nextLine()), VagonComercial::getJSONObject, almacenamiento.getVagonesComerciales());
//            }
//        } catch (FileDoesntExistException e) {
//            System.out.println("El archivo no existe.");
//        } catch (NullPointerException e) {
//            System.out.println("ID invalido");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Opcion incorrecta.");
//        }
//        return false;
//    }
    //Baja

    //Modificacion

    /**
     * Modifica la ruta
     * @param op
     * @param almacenamiento
     * @return
     */
    public static boolean modificarRuta (int op, Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionRuta gr;
        Ruta rutaModificada = new Ruta();

        switch(op) {
            case 0:
                break;
            case 1:
                try {
                        if (new File(almacenamiento.getRutas()).length() > 0) {
                            gr = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getRutas())));
                            System.out.println(gr);
                            System.out.println("Salida: ");
                            StringBuilder sb = new StringBuilder(sc.nextLine());
                            rutaModificada.setSalida(sb);
                            System.out.println(rutaModificada.equals(gr.verificarRuta(rutaModificada, almacenamiento.getRutas())));
                            return GestionRuta.modificarRegistro(gr.verificarRuta(rutaModificada, almacenamiento.getRutas()), rutaModificada, almacenamiento.getRutas());
                        }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
            case 2:
                try {
                    if (new File(almacenamiento.getRutas()).length() > 0) {
                        gr = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getRutas())));
                        System.out.println(gr);
                        System.out.println("Llegada: ");
                        StringBuilder sb = new StringBuilder(sc.nextLine());
                        rutaModificada.setLlegada(sb);
                        System.out.println(rutaModificada.equals(gr.verificarRuta(rutaModificada, almacenamiento.getRutas())));
                        return GestionRuta.modificarRegistro(gr.verificarRuta(rutaModificada, almacenamiento.getRutas()), rutaModificada, almacenamiento.getRutas());
                    }
                } catch (FileDoesntExistException e) {
                    System.out.println("El archivo no existe");
                } catch (NullPointerException e) {
                    System.out.println("ID invalido");
                }
                break;
            case 3:
                try {
                    if (new File(almacenamiento.getRutas()).length() > 0) {
                        gr = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getRutas())));
                        System.out.println(gr);
                        System.out.println("Fecha: ");
                        rutaModificada.setFecha(sc.nextInt());
                        System.out.println(rutaModificada.equals(gr.verificarRuta(rutaModificada, almacenamiento.getRutas())));
                        return GestionRuta.modificarRegistro(gr.verificarRuta(rutaModificada, almacenamiento.getRutas()), rutaModificada, almacenamiento.getRutas());
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
    //Modificacion

    /**
     * Permite agregar rutas, modificarlas y eliminarlas
     * @param op
     * @param almacenamiento
     */
//    public static void administrarRutas (int op, Almacenamiento almacenamiento) {
//        int subOp;
//        Scanner sc = new Scanner(System.in);
//        switch (op) {
//            case 0:
//                break;
//            case 1:
//                try {
//                    if(agregarRuta(almacenamiento)) {
//                        System.out.println("El vagon se agrego exitosamente");
//                    }
//                } catch (ElementAlreadyExistsException e) {
//                    System.out.println("El Vagon ya existe");
//                }
//                break;
//            case 2:
//                do {
//                    System.out.println(Menu.modificarRuta());
//                    System.out.println("Opcion: ");
//                    subOp = sc.nextInt();
//                        if(modificarRuta(subOp, almacenamiento)) {
//                            System.out.println("La ruta se modifico exitosamente");
//                        }
//                        else {
//                            System.out.println("Ocurrio un error");
//                        }
//                } while (subOp != 0);
//                break;
////            case 3:
////                if (eliminarRuta(almacenamiento)) {
////                    System.out.println("El vagon se elimino con exito");
////                }
////                break;
//            case 3:
//                System.out.println(mostrarRutas(almacenamiento));
//                break;
//        }
//    }
}
