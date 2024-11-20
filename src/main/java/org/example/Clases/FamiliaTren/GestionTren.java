package org.example.Clases.FamiliaTren;

import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * Clase para gestionar los trenes del sistema.
 * @param <T> El tipo de tren que va tener la clase.
 */
public class GestionTren<T extends Tren> {
    //Atributos
    private final Set<T> trenes;
    //Atributos

    //Constructor
    public GestionTren() {
        this.trenes = new HashSet<>();
    }
    //Constructor

    //Getter
    public Set<T> getTrenes() {
        return trenes;
    }
    //Getter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
        for(T t: this.trenes) {
            if(t.isEstado()) {
                sb.append("------------------------------------------------------------TREN-------------------------------------------------------------\n");
                sb.append(t);
                System.out.println(t.isEstado());
                sb.append("------------------------------------------------------------TREN-------------------------------------------------------------\n");
            }
        }
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    //Alta
    /**
     * Carga un tren.
     * @param t Tren a cargar..
     * @return true si se pudo agregar el tren sin problemas.
     * @throws ElementAlreadyExistsException si el tren ya existe.
     */
    public boolean agregarTren (T t) {
        if(!this.trenes.add(t)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }
    //Alta

    //Baja
    /**
     * Elimina un tren.
     * @param t Tren a eliminar.
     * @return true si se pudo eliminar el tren sin problema.
     */
    public boolean eliminarTren (T t) {
        if(!this.trenes.remove(t) || !t.isEstado()) {
            throw new NoSuchElementException();
        } else {
            t.setEstado(false);
            this.agregarTren(t);
        }
        return true;
    }
    //Baja

    //Modificacion
    /**
     * Modifica a un tren.
     * @param trenViejo el tren a modificar.
     * @param trenNuevo El mismo tren ya modificado.
     * @return true si se pudo modificar el vagon sin problema.
     * @throws NoSuchElementException si sel tren a modificar no existe.
     */
    public boolean modificarTren (T trenViejo, T trenNuevo) {
        if(!this.trenes.remove(trenViejo) || !trenNuevo.isEstado()) {
            throw new NoSuchElementException();
        } else {
            return this.agregarTren(trenNuevo);
        }
    }
    //Modificacion

    //JSON
    /**
     * Convierte al gestor en un JSONArray.
     * @return El JSONArray con los datos del gestor de trenes.
     */
    public JSONArray convertirJSONArray () {
        JSONArray json = new JSONArray();
        for(T t: this.trenes) {
            json.put(t.convertirAJSONObject());
        }
        return json;
    }

    /**
     * Transforma a un JSONArray en un gestor de trenes.
     * @param json el JSONArray a transformar.
     * @param tipoTren function que contenga el tipo de tren y su funcion de convertir el tren a un JSONObject.
     * @return un gestor de trenes con los datos del JSONArray.
     */
    public static GestionTren<Tren> getJSONArray(JSONArray json, Function<JSONObject, Tren> tipoTren) {
        GestionTren<Tren> gt = new GestionTren<>();
        for (int i = 0; i < json.length(); i++) {
            gt.agregarTren(tipoTren.apply(json.getJSONObject(i)));
        }
        return gt;
    }
    //JSON

    //Archivos
    /**
     * Guarda un tren en un archivo.
     * @param tren Tren a guardar.
     * @param tipoTren Function con el tipo de tren a guardar y su funcion de convertir un tren a un JSONObject.
     * @param archivo Nombre del archivo donde se va a guardar al tren.
     * @return true si se pudo guardar al tren sin problemas.
     */
    public static boolean agregarRegistro (Tren tren, Function<JSONObject, Tren> tipoTren, String archivo) {
        GestionTren<Tren> gt = new GestionTren<>();

        try {
            if(new File(archivo).length() > 0) {
                for(Tren t: GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoTren).getTrenes()) {
                    gt.agregarTren(t);
                }
            }
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        }
        gt.agregarTren(tren);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gt.convertirJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina un tren en un archivo.
     * @param tren Tren a eliminar.
     * @param tipoTren Function con el tipo de tren a eliminar y su funcion de convertir un tren a un JSONObject.
     * @param archivo Nombre del archivo donde se va a eliminar al tren.
     * @return true si se pudo eliminar al tren sin problemas.
     */
    public static boolean eliminarRegistro (Tren tren, Function<JSONObject, Tren> tipoTren, String archivo) {
        GestionTren<Tren> gt = new GestionTren<>();

        for(Tren t: GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoTren).getTrenes()) {
            gt.agregarTren(t);
        }
        gt.eliminarTren(tren);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gt.convertirJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifica un tren en un archivo.
     * @param trenViejo Tren a modificar.
     * @param trenNuevo Tren modificado.
     * @param tipoTren Function con el tipo de tren a modificar y su funcion de convertir un tren a un JSONObject.
     * @param archivo Nombre del archivo donde se va a modificar al tren.
     * @return true si se pudo modificar al tren sin problemas.
     */
    public static boolean modificarRegistro (Tren trenViejo, Tren trenNuevo, Function<JSONObject, Tren> tipoTren, String archivo) {
        GestionTren<Tren> gt = new GestionTren<>();

        for(Tren t: GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoTren).getTrenes()) {
            gt.agregarTren(t);
        }
        gt.modificarTren(trenViejo, trenNuevo);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gt.convertirJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos

    public Tren verificarTren(String patente) {
        for (T t : trenes) {
            if (Objects.equals(t.getPatente(), patente)) {
                if (t instanceof TrenDeCarga) {
                    return t;
                } else if (t instanceof TrenComercial) {
                    return t;
                }
            }
        }
        return null;
    }
}