package org.example.Clases.FamiliaVagon;

import org.example.Main;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        this.vagones = vagones;
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
        StringBuilder sb = new StringBuilder("-------------------------------------------------------------------------------------------------------------------\n");
        for(T t: this.vagones) {
            if(t.isEstado()) {
                sb.append(t);
            }
        }
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    //Alta
    public boolean agregarVagon(T t) {
        return this.vagones.add(t);
    }
    //Alta

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

    //Modificacion
    public boolean modificarVagon (T vagonViejo, T vagonNuevo) {
        if(!this.vagones.remove(vagonViejo) || !vagonNuevo.isEstado()) {
            throw new NoSuchElementException();
        } else {
            return this.vagones.add(vagonNuevo);
        }
    }
    //Modificacion

    //JSON
    public JSONArray convertirJSONArray () {
        JSONArray json = new JSONArray();
        for(T t: this.vagones) {
            json.put(t.convertirAJSONObject());
        }
        return json;
    }

    public static HashSet<Vagon> getJSONArray(JSONArray json, Function<JSONObject, Vagon> vagon) {
        HashSet<Vagon> hs = new HashSet<>();
        for (int i = 0; i < json.length(); i++) {
            hs.add(vagon.apply(json.getJSONObject(i)));
        }
        return hs;
    }
    //JSON

    //Archivos
    public static boolean agregarRegistro (Vagon vagon, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionVagon<Vagon> gv = new GestionVagon<>(GestionVagon.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            if(gv.agregarVagon(vagon)) {
                bf.write(gv.convertirJSONArray().toString(2));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

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