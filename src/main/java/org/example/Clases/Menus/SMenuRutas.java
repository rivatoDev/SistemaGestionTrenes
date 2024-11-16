package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
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
    public static Ruta  ingresarRuta (Almacenamiento almacenamiento) {
        Ruta r = new Ruta();
        GestionTren gt = new GestionTren<>();
        Scanner sc = new Scanner(System.in);
        Maquinista maquinista = new Maquinista("44535612", "joauqin", "ortega", "25");

        System.out.println("----------------------------------------------------------------------------------------------------");
        if(SwitchMenuTrenes.mostrarTrenes(almacenamiento).toString().contains("carga")){
            System.out.println(GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesDeCarga())), TrenDeCarga::getJSONObject).toString());
            Tren tren = new TrenDeCarga();
            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesDeCarga())), TrenDeCarga::getJSONObject);
            System.out.println("Ingrese la patente del tren a agregar a la ruta: ");
            String patente = sc.nextLine();
            tren = gt.verificarTren(patente);
            r.setTren(tren);
        }else{
            System.out.println(GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject).toString());
            Tren tren = new TrenComercial();
            gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getTrenesComerciales())), TrenComercial::getJSONObject);
            System.out.println("Ingrese la patente del tren a agregar a la ruta: ");
            String patente = sc.nextLine();
            tren = gt.verificarTren(patente);
            r.setTren(tren);
        }

        System.out.println("Salida: ");
        StringBuilder sb = new StringBuilder(sc.nextLine());
        r.setSalida(sb);
        System.out.println("Llegada: ");
        StringBuilder sbb = new StringBuilder(sc.nextLine());
        r.setLlegada(sbb);
        r.setMaquinista(maquinista);
        System.out.println("Fecha: ");
        r.setFecha(sc.nextInt());
        return r;
    }


    /**
     * Agrega una ruta
     * @param almacenamiento
     * @return
     */
    public static boolean agregarRuta (Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionRuta gr;
        Ruta r = ingresarRuta(almacenamiento);
        try {
            return GestionRuta.agregarRegistro(r, almacenamiento.getRutas());
        } catch (ElementAlreadyExistsException e) {
            System.out.println("La ruta ya existe");
        } catch (NullPointerException e) {
            System.out.println("ID invalido");
        } catch (IllegalArgumentException e) {
            System.out.println("Opcion incorrecta.");
        }
        return false;
    }
    //Alta

    //Baja
    public static boolean eliminarRuta (Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionRuta gr;

        try {
                gr = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getRutas())));
                System.out.println(gr);
                System.out.println("ID: ");
                return GestionRuta.eliminarRegistro(gr.verificarRutaID(sc.nextLine(), almacenamiento.getRutas()), almacenamiento.getRutas());
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
    public static void administrarRutas (int op, Almacenamiento almacenamiento) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if(agregarRuta(almacenamiento)) {
                        System.out.println("El vagon se agrego exitosamente");
                    }
                } catch (ElementAlreadyExistsException e) {
                    System.out.println("El Vagon ya existe");
                }
                break;
            case 2:
                do {
                    System.out.println(Menu.modificarRuta());
                    System.out.println("Opcion: ");
                    subOp = sc.nextInt();
                        if(modificarRuta(subOp, almacenamiento)) {
                            System.out.println("La ruta se modifico exitosamente");
                        }
                        else {
                            System.out.println("Ocurrio un error");
                        }
                } while (subOp != 0);
                break;
            case 3:
                if (eliminarRuta(almacenamiento)) {
                    System.out.println("El vagon se elimino con exito");
                }
                break;
            case 4:
                System.out.println(mostrarRutas(almacenamiento));
                break;
        }
    }
}
