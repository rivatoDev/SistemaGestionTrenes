package org.example.Clases.FamiliaRuta;

import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONException;
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

public class GestionRuta {
    //Atributos
    private final Set<Ruta> rutas;


    //Constructor
    public GestionRuta() {
        this.rutas = new HashSet<>();
    }
    //Constructor

    //Getter
    public Set<Ruta> getRutas() {
        return rutas;
    }
    //Getter

    //Mostrar
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        for(Ruta r: this.rutas) {
            sb.append("--------------------------------------------------RUTA--------------------------------------------------\n");
            sb.append(r);
            sb.append("--------------------------------------------------RUTA--------------------------------------------------\n");
        }
        sb.append("----------------------------------------------------------------------------------------------------");
        return sb.toString();
    }
    //Mostrar

    //Alta
    /**
     * Agrega una ruta.
     * @param ruta Ruta a agregar.
     * @return true si se pudo agregar la ruta sin problemas.
     * @throws ElementAlreadyExistsException si el elemento ya existe.
     */
    public boolean agregarRuta(Ruta ruta) {
        if(!this.rutas.add(ruta)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }
    //Alta

    //Baja
    /**
     * Elimina una ruta.
     * @param ruta Ruta a eliminar.
     * @return true si se pudo eliminar la ruta sin problemas.
     * @throws NoSuchElementException si la ruta no existe.
     */
    public boolean eliminarRuta (Ruta ruta) {
        if(!this.rutas.contains(ruta) || !ruta.isEstado()) {
            throw new NoSuchElementException();
        } else {
            this.rutas.remove(ruta);
            ruta.setEstado(false);
            this.agregarRuta(ruta);
            return true;
        }
    }
    //Baja

    //Modificacion

    /**
     * Modifica una ruta.
     * @param rutaVieja La ruta a modificar.
     * @param rutaNueva La ruta modificada.
     * @return true si se pudo modificar la ruta sin problemas.
     * @throws NoSuchElementException si la ruta no existe.
     */
    public boolean modificarRuta (Ruta rutaVieja, Ruta rutaNueva) {
        if(!this.rutas.contains(rutaVieja) || !rutaNueva.isEstado()) {
            throw new NoSuchElementException();
        } else {
            this.rutas.remove(rutaVieja);
            this.agregarRuta(rutaNueva);
        }
        return true;
    }
    //Modificacion

    //JSON
    /**
     * Convierte al gestor en un JSONArray.
     * @return Un JSONArray con los datos del gestor.
     */
    public JSONArray convertirAJSONArray () {
        JSONArray json = new JSONArray();
        try {
            for(Ruta r: this.rutas) {
                json.put(r.convertirAJSONObject());
            }
        } catch (JSONException e) {
            return null;
        }
        return json;
    }

    /**
     * Convierte a un JSONArray en un gestor de rutas.
     * @param json El JSONArray a deserializar.
     * @return Un gestor de rutas con los datos del JSONArray.
     */
    public static GestionRuta getJSONArray(JSONArray json) {
        GestionRuta gr = new GestionRuta();

        try {
            for (int i = 0; i < json.length(); i++) {
                if(json.getJSONObject(i).has("trenDeCarga")) {
                    gr.agregarRuta(Ruta.JSONxRuta(json.getJSONObject(i), TrenDeCarga::getJSONObject));
                } else {
                    gr.agregarRuta(Ruta.JSONxRuta(json.getJSONObject(i), TrenComercial::getJSONObject));
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return gr;
    }
    //JSON

    //Archivos
    /**
     * Agrega una ruta al archivo.
     * @param ruta La ruta a agregar.
     * @param archivo El archivo a utilizar.
     * @return true si se pudo cargar el registro sin problemas.
     */
    public static boolean agregarRegistro (Ruta ruta, String archivo) {
        GestionRuta gr = new GestionRuta();
        try {
            if (new File(archivo).length() > 0) {
                for (Ruta r: GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo))).getRutas()) {
                    gr.agregarRuta(r);
                }
            }
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        }
        gr.agregarRuta(ruta);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gr.convertirAJSONArray().toString(2));
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifica una ruta del archivo.
     * @param rutaVieja La ruta a modificar.
     * @param rutaNueva La ruta modificada.
     * @param archivo El archivo a utilizar.
     * @return true si se pudo modificar el archivo sin problemas.
     */
    public static boolean modificarRegistro (Ruta rutaVieja, Ruta rutaNueva, String archivo) {
        GestionRuta gr = new GestionRuta();

        for(Ruta r: GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo))).getRutas()) {
            gr.agregarRuta(r);
        }
        gr.modificarRuta(rutaVieja, rutaNueva);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gr.convertirAJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina una ruta del archivo.
     * @param ruta La ruta a eliminar.
     * @param archivo El archivo a utilizar.
     * @return true si se pudo eliminar el archivo sin problemas.
     */
    public static boolean eliminarRegistro (Ruta ruta, String archivo) {
        GestionRuta gr = new GestionRuta();

        for (Ruta r: GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo))).getRutas()) {
            gr.agregarRuta(r);
        }
        gr.eliminarRuta(ruta);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gr.convertirAJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos
    /**
     * Verifica que la ruta exista en el gestor.
     * @param id el id de la ruta
     * @return Si encuentra a la ruta la devuelve con todos sus datos.
     * @throws NoSuchElementException Si no encuentra a la ruta.
     */
    public Ruta verificarRuta(String id) {
        for (Ruta r: this.rutas) {
            if (Objects.equals(id, r.getId())){
                return r;
            }
        }
        throw new NoSuchElementException("Ruta inexistente");
    }
}