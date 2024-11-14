package org.example.Clases.FamiliaVagon;

import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

/**
 * Clase generica para gestionar a los vagones
 */
public class GestionVagon<T extends Vagon> {
    //Atributos
    private final HashSet<T> vagones;
    //Atributos

    //Constructor
    public GestionVagon() {
        this.vagones = new HashSet<>();
    }
    //Constructor

    //Getter
    public Set<T> getVagones() {
        return vagones;
    }
    //Getter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("------------------------------------------------------------------------------------------------------------------------\n");
        for(T t: this.vagones) {
            if(t.isEstado()) {
                sb.append("--------------------------------------------------VAGON--------------------------------------------------\n");
                sb.append(t);
                sb.append("--------------------------------------------------VAGON--------------------------------------------------\n");
            }
        }
        sb.append("------------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    /**
     * Carga un Vagon.
     * @param t Vagon a agregar.
     * @return true si se pudo agregar sin problemas.
     * @throws ElementAlreadyExistsException si el vagon ya existe.
     */
    //Alta
    public boolean agregarVagon(T t) {
        if(!this.vagones.add(t)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }
    //Alta

    /**
     * Elimina un Vagon.
     * @param t Vagon a eliminar.
     * @return true si se pudo eliminar el vagon sin problemas.
     * @throws NoSuchElementException si el vagon no existe.
     */
    //Baja
    public boolean eliminarVagon (T t) {
        if(!this.vagones.remove(t) || !t.isEstado()) {
            throw new NoSuchElementException();
        } else {
            t.setEstado();
            this.agregarVagon(t);
        }
        return true;
    }
    //Baja

    /**
     * Modifica un Vagon.
     * @param vagonViejo Vagon a modificar.
     * @param vagonNuevo Vagon modificado.
     * @return true si se pudo modificar el vagon sin problemas.
     * @throws NoSuchElementException si el vagon no existe.
     */
    //Modificacion
    public boolean modificarVagon (T vagonViejo, T vagonNuevo) {
        if(!this.vagones.remove(vagonViejo) || !vagonNuevo.isEstado()) {
            throw new NoSuchElementException();
        } else {
            return this.agregarVagon(vagonNuevo);
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
     * @return un GestionVagon con los datos del JSONArray.
     * @throws NullPointerException si ocurre una JSONException.
     */
    public static GestionVagon<Vagon> getJSONArray(JSONArray json, Function<JSONObject, Vagon> vagon) {
        try {
            GestionVagon<Vagon> gv = new GestionVagon<>();
            for (int i = 0; i < json.length(); i++) {
                gv.agregarVagon(vagon.apply(json.getJSONObject(i)));
            }
            return gv;
        } catch (JSONException e) {
            throw new NullPointerException();
        }
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
            if(new File(archivo).length() > 0) {
                for(Vagon v: GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon).getVagones()) {
                    gv.agregarVagon(v);
                }
            }
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        }
        gv.agregarVagon(vagon);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gv.convertirJSONArray().toString(2));
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
        GestionVagon<Vagon> gv = new GestionVagon<>();


        for(Vagon v: GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon).getVagones()) {
            gv.agregarVagon(v);
        }
        System.out.println("Hola");
        System.out.println(gv.getVagones().contains(vagon));
        gv.eliminarVagon(vagon);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gv.convertirJSONArray().toString(2));
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
        GestionVagon<Vagon> gv = new GestionVagon<>();

        for(Vagon v: GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon).getVagones()) {
            gv.agregarVagon(v);
        }
        gv.modificarVagon(vagonViejo, vagonNuevo);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gv.convertirJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos

    public Vagon verificarVagon(String idVagon) {
        for (Vagon v: vagones) {
            if (Objects.equals(v.getIdVagon(), idVagon)) {
                return v;
            }
        }
        throw new NullPointerException();
    }
}