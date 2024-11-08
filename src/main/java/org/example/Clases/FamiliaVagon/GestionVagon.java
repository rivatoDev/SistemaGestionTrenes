package org.example.Clases.FamiliaVagon;

import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;

/**
 * Clase generica para gestionar a los vagones
 */
public class GestionVagon<T extends Vagon> {
    //Atributos
    private Set<T> vagones;
    //Atributos

    //Constructor

    public GestionVagon() {
        this.vagones = new HashSet<>();
    }

    public GestionVagon(HashSet<T> vagones) {
        this.vagones = (vagones == null) ? new HashSet<>() : vagones;
    }
    //Constructor

    //Getter
    public Set<T> getVagones() {
        return vagones;
    }
    //Getter

    //Setter
    public void setVagones(Set<T> vagones) {
        this.vagones = vagones;
    }
    //Setter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n-------------------------------------------------------------------------------------------------------------------\n");
        for(T t: this.vagones) {
            if(t.isEstado()) {
                sb.append(t);
            }
        }
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    /**
     * Carga un Vagon.
     * @param t Vagon a agregar.
     * @return true si se pudo agregar sin problemas, false de lo contrario.
     */
    //Alta
    public boolean agregarVagon(T t) {
        return this.vagones.add(t);
    }
    //Alta

    /**
     * Elimina un Vagon.
     * @param t Vagon a eliminar.
     * @return true si se pudo eliminar el vagon sin problemas, sino devuelve false
     */
    //Baja
    public boolean eliminarVagon (T t) {
        if(!this.vagones.remove(t) || !t.isEstado()) {
            throw new NoSuchElementException();
        } else {
            t.setEstado();
            this.vagones.add(t);
        }
        return true;
    }
    //Baja

    /**
     * Modifica un Vagon.
     * @param vagonViejo Vagon a modificar.
     * @param vagonNuevo Vagon modificado.
     * @return true si se pudo modificar el vagon sin problemas, sino devuelve false
     */
    //Modificacion
    public boolean modificarVagon (T vagonViejo, T vagonNuevo) {
        if(!this.vagones.remove(vagonViejo) || !vagonNuevo.isEstado()) {
            throw new NoSuchElementException();
        } else {
            return this.eliminarVagon(vagonViejo) && this.agregarVagon(vagonNuevo);
        }
    }
    //Modificacion

    /**
     * Convierte al objeto en un JSONArray
     * @return el objeto como un JSONArray.
     */
    //JSON
    public JSONArray convertirJSONArray () {
        JSONArray json = new JSONArray();
        for(T t: this.vagones) {
            json.put(t.convertirAJSONObject());
        }
        return json;
    }

    /**
     * Convierte el JSONArray en un HashSet.
     * @param json JSONArray a convertir.
     * @param vagon Function a utilizar, tiene que ser el metodo estatico de convertirAJSONObject de algun Vagon.
     * @return un HashSet con los datos del JSONArray
     */
    public static HashSet<Vagon> getJSONArray(JSONArray json, Function<JSONObject, Vagon> vagon) {
        HashSet<Vagon> hs = new HashSet<>();
        for (int i = 0; i < json.length(); i++) {
            hs.add(vagon.apply(json.getJSONObject(i)));
        }
        return hs;
    }
    //JSON

    //Archivos
    /**
     * Carga un vagon en un archivo.
     * Si el archivo no existe lo crea.
     * @param vagon Vagon a agregar.
     * @param tipoVagon Function que debe contener el metodo estatico de ConvertirAJSONObject de un Vagon.
     * @param archivo Nombre del archivo que se va a agregar el Vagon.
     * @return true si se pudo agregar sin problema, sino false.
     */
    public static boolean agregarRegistro (Vagon vagon, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionVagon<Vagon> gv = new GestionVagon<>();

        try {
            gv.setVagones(GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon));
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            System.out.println(gv);
            if(gv.agregarVagon(vagon)) {
                bf.write(gv.convertirJSONArray().toString(5));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina un vagon en un archivo.
     * @param vagon Vagon a eliminar.
     * @param tipoVagon Function que debe contener el metodo estatico de ConvertirAJSONObject de un Vagon.
     * @param archivo Nombre del archivo que se va a eliminar el Vagon.
     * @return true si se pudo eliminar sin problema, sino false.
     */
    public static boolean eliminarRegistro (Vagon vagon, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionVagon<Vagon> gv = new GestionVagon<>(GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            if(gv.eliminarVagon(vagon)) {
                bf.write(gv.convertirJSONArray().toString(2));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifica un vagon en un archivo.
     * @param vagonViejo Vagon a modificar.
     * @param tipoVagon Function que debe contener el metodo estatico de ConvertirAJSONObject de un Vagon.
     * @param archivo Nombre del archivo que se va a modificar el Vagon.
     * @return true si se pudo modificar sin problema, sino false.
     */
    public static boolean modificarRegistro (Vagon vagonViejo, Vagon vagonNuevo, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionVagon<Vagon> gv = new GestionVagon<>(GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            if (gv.modificarVagon(vagonViejo, vagonNuevo)) {
                bf.write(gv.convertirJSONArray().toString(2));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos
}