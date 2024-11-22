package org.example.Clases.Menus;

import org.example.Clases.FamiliaPersona.GestionMaquinista;
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
        r.setFecha(sc.nextLine());
        return r;
    }
    //Baja

    //Modificacion
    /**
     * Modifica la ruta
     * @param op La opcion a entrar en el switch.
     * @return true si se pudo modificar los datos sin problema.
     */
    public static boolean modificarRuta (int op, Ruta ruta, Almacenamiento almacenamiento) {
        Scanner sc = new Scanner(System.in);
        GestionRuta gr = leerRutas(almacenamiento.getRutas());
        GestionMaquinista gm = GestionMaquinista.getJSONArray(new JSONArray(Main.leerArchivo(almacenamiento.getMaquinistas())));
        GestionTren<Tren> gt;
        System.out.println(gr);

        if(new File(almacenamiento.getRutas()).length() > 0) {
            switch(op) {
                case 0:
                    break;
                case 1:
                    System.out.println("Salida: ");
                    ruta.setSalida(sc.nextLine());
                    break;
                case 2:
                    System.out.println("Llegada: ");
                    ruta.setLlegada(sc.nextLine());
                    break;
                case 3:
                    System.out.println("Fecha: ");
                    ruta.setFecha(sc.nextLine());
                    break;
                case 4:
                    if(ruta.getTren() instanceof TrenDeCarga) {
                        gt = SMenuTrenes.leerTrenes(TrenDeCarga::getJSONObject, almacenamiento.getTrenesDeCarga());
                    } else {
                        gt = SMenuTrenes.leerTrenes(TrenComercial::getJSONObject, almacenamiento.getTrenesComerciales());
                    }
                    System.out.println(gt);
                    System.out.println("Patente: ");
                    try {
                        ruta.setTren(gt.verificarTren(sc.nextLine()));
                    } catch (NoSuchElementException e) {
                        System.out.println("Patente invalida");
                        return false;
                    }
                    break;
                case 5:
                    System.out.println(gm);
                    System.out.println("ID: ");
                    try {
                        ruta.setMaquinista(gm.verificarMaquinista(sc.nextLine()));
                    } catch (NoSuchElementException e) {
                        System.out.println("ID invalido.");
                        return false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Opcion no valida.");
            }
            return GestionRuta.modificarRegistro(gr.verificarRuta(ruta.getId()), ruta, almacenamiento.getRutas());
        }
        return false;
    }
    //Modificacion

    /**
     * Permite agregar rutas, modificarlas y eliminarlas
     * @param op La opcion en la que va a entrar en el switch
     * @param almacenamiento El objeto que contiene los nombres de todos los archivos del sistema.
     */
    public static void administrarRutas (int op, Almacenamiento almacenamiento) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        GestionRuta gr = leerRutas(almacenamiento.getRutas());
        Ruta r = null;
        if(op > 1 && op < 4) {
            System.out.println(gr);
            System.out.println("ID: ");
            try {
               r = gr.verificarRuta(sc.nextInt());
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }

        switch (op) {
            case 0:
                break;
            case 1:
                try {
                    if(GestionRuta.agregarRegistro(ingresarRuta(almacenamiento), almacenamiento.getRutas())) {
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
                        if(modificarRuta(subOp, r, almacenamiento)) {
                            System.out.println("La ruta se modifico exitosamente");
                        }
                        else {
                            System.out.println("Ocurrio un error");
                        }
                } while (subOp != 0);
                break;
            case 3:
                if (GestionRuta.eliminarRegistro(r, almacenamiento.getRutas())) {
                    System.out.println("El vagon se elimino con exito");
                }
                break;
            case 4:
                System.out.println(gr);
                break;
        }
    }
}