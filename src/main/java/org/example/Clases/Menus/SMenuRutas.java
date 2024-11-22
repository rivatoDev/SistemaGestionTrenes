package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.GestionMaquinista;
import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Clases.GestionRuta;
import org.example.Clases.Ruta;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

public class SMenuRutas {
    public SMenuRutas() {
    }

    //Mostrar
    public static GestionRuta leerRutas (String archivo) {
        GestionRuta gt;
        try {
            if(new File(archivo).length() > 0) {
                gt = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));
            } else {
                gt = new GestionRuta();
            }
        } catch (FileDoesntExistException e) {
            return null;
        }
        return gt;
    }
    //Mostrar

    //Alta
    /**
     * Carga una ruta
     * @param almacenamiento Objeto que contiene los nombres de todos los archivos del sistema.
     * @return La Ruta cargada.
     * @throws NoSuchElementException Si no se encontro el tren o al maquinista.
     */
    public static Ruta ingresarRuta (Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        String archivo;
        Function<JSONObject, Tren> tipoTren;
        Ruta r = new Ruta();
        GestionTren<Tren> gt;
        GestionMaquinista gm = GestionMaquinista.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getMaquinistas())));

        if (SMenuTrenes.seleccionarTipo() instanceof TrenDeCarga) {
            tipoTren = TrenDeCarga::getJSONObject;
            archivo = almacenamiento.getTrenesDeCarga();
        } else {
            tipoTren = TrenComercial::getJSONObject;
            archivo = almacenamiento.getTrenesComerciales();
        }
        gt = GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoTren);
        System.out.println(gt);

        System.out.println("----------------------------------------------------------------------------------------------------");
        try {
            System.out.println("Patente del Tren: ");
            r.setTren(gt.verificarTren(sc.nextLine()));

            System.out.println("ID del Maquinista: ");
            r.setMaquinista(gm.verificarMaquinista(sc.nextLine()));
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        }

        System.out.println("Salida: ");
        r.setSalida(sc.nextLine());
        System.out.println("Llegada: ");
        r.setLlegada(sc.nextLine());
        System.out.println("Fecha: ");
        r.setFecha(sc.nextInt());
        return r;
    }

    //Baja
    public static boolean eliminarRuta (String archivo) {
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