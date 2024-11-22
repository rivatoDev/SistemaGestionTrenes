package org.example.Clases.FamiliaPersona;

import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;

public class GestionMaquinista {
    //Atributos
    private final HashSet<Maquinista> maquinistas;
    //Atributos

    //Constructor
    public GestionMaquinista() {
        this.maquinistas = new HashSet<>();
    }
    //Constructor

    //Getter
    public HashSet<Maquinista> getMaquinistas() {
        return maquinistas;
    }
    //Getter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Maquinista m: this.maquinistas) {
            sb.append(m).append("\n");
        }
        return sb.toString();
    }
    //Mostrar

    //Alta
    /**
     * Carga un Maquinista.
     * @param maquinista Usuario a agregar.
     * @return true si se pudo agregar sin problema el maquinista.
     * @throws ElementAlreadyExistsException si el maquinista ya existe.
     */
    public boolean agregarMaquinista(Maquinista maquinista) {
        try {
            if(!this.maquinistas.add(maquinista)) {
                throw new ElementAlreadyExistsException();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Alta

    //Baja
    /**
     * Elimina un maquinista.
     * @param maquinista Maquinista a eliminar.
     * @return true si se pudo eliminar sin problema el maquinista.
     * @throws NoSuchElementException si el Maquinista no existe.
     */
    public boolean eliminarMaquinista(Maquinista maquinista) {
        try {
            if(!this.maquinistas.contains(maquinista) || !maquinista.isEstado()) {
                throw new NoSuchElementException();
            } else {
                this.maquinistas.remove(maquinista);
                maquinista.setEstado(false);
                this.agregarMaquinista(maquinista);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //Baja

    //Modificacion
    /**
     * Modifica un Maquinista.
     * @param maquinistaViejo Maquinista a modificar.
     * @param maquinistaNuevo Maquinista modificado.
     * @return true si se pudo modificar sin problema al Maquinista.
     * @throws NoSuchElementException si el maquinista no existe.
     */
    public boolean modificarMaquinista (Maquinista maquinistaViejo, Maquinista maquinistaNuevo) {
        try {
            if(!this.maquinistas.contains(maquinistaViejo) || !maquinistaNuevo.isEstado()) {
                throw new NoSuchElementException();
            }
            this.maquinistas.remove(maquinistaViejo);
            this.agregarMaquinista(maquinistaNuevo);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    //Modificacion

    //JSON
    /**
     * Convierte al Maquinista en un JSONArray.
     * @return el JSONArray con los datos del Maquinista.
     */
    public JSONArray convertirAJSONArray () {
        JSONArray json = new JSONArray();
        try {
            for(Maquinista m: this.maquinistas) {
                json.put(m.convertirAJSONObject());
            }
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * Convierte a un JSONArray en un Maquinista.
     * @param json El JSONArray a convertir.
     * @return Un GestionMaquinista con los datos del JSONArray.
     */
    public static GestionMaquinista getJSONArray(JSONArray json) {
        GestionMaquinista gm = new GestionMaquinista();
        try {
            for(int i = 0; i < json.length(); i++) {
                gm.agregarMaquinista(Maquinista.JSONxMaquinista(json.getJSONObject(i)));
            }
            return gm;
        } catch (JSONException e) {
            return null;
        }
    }
    //JSON

    //Archivos
    /**
     * Carga un Maquinista en un archivo.
     * Si el archivo no existe lo crea.
     * @param maquinista El Maquinista a crear.
     * @param archivo El nombre del archivo.
     * @return true si se pudo guardar el Maquinista sin problemas, si no false.
     */
    public static boolean agregarRegistro (Maquinista maquinista, String archivo) {
        GestionMaquinista gm = new GestionMaquinista();
        try {
            if (new File(archivo).length() > 0) {
                for (Maquinista m: Objects.requireNonNull(GestionMaquinista.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))).getMaquinistas()) {
                    gm.agregarMaquinista(m);
                }
            }
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        } catch (NullPointerException e) {
            return false;
        }
        gm.agregarMaquinista(maquinista);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gm.convertirAJSONArray().toString(2));
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina un Maquinista en un archivo.
     * @param maquinista El Maquinista a eliminar.
     * @param archivo El nombre del archivo.
     * @return true si se pudo eliminar el Maquinista sin problemas, si no false.
     */
    public static boolean eliminarRegistro (Maquinista maquinista, String archivo) {
        GestionMaquinista gm = new GestionMaquinista();

        try {
            for (Maquinista u: Objects.requireNonNull(GestionMaquinista.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))).getMaquinistas()) {
                gm.agregarMaquinista(u);
            }
            gm.eliminarMaquinista(maquinista);
        } catch (NullPointerException e) {
            return false;
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gm.convertirAJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifica un Maquinista en un archivo.
     * @param maquinistaViejo El Maquinista a modificar.
     * @param maquinistaNuevo El Maquinista modificado.
     * @param archivo El nombre del archivo.
     * @return true si se pudo modificar el Maquinista sin problemas, sino false.
     */
    public static boolean modificarRegistro (Maquinista maquinistaViejo, Maquinista maquinistaNuevo, String archivo) {
        GestionMaquinista gm = new GestionMaquinista();

        try {
            for (Maquinista u: Objects.requireNonNull(GestionMaquinista.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))).getMaquinistas()) {
                gm.agregarMaquinista(u);
            }
            gm.modificarMaquinista(maquinistaViejo, maquinistaNuevo);
        } catch (NullPointerException e) {
            return false;
        }

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gm.convertirAJSONArray().toString());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos

    /**
     * Verifica si un maquinista existe en la clase gestora.
     * @param idMaquinista id del maquinista
     * @return si el maquinista existe lo devuelve con todos sus datos.
     * @throws NoSuchElementException si el id del maquinista es el incorrecto.
     */
    public Maquinista verificarMaquinista(String idMaquinista) {
        for (Maquinista m : this.maquinistas) {
            if (Objects.equals(m.getIdMaquinista().toString(), idMaquinista)) {
                return m;
            }
        }
        throw new NoSuchElementException("Maquinista inexistente");
    }
}