package org.example.Clases.FamiliaTren;

import org.example.Clases.FamiliaVagon.Vagon;
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
import java.util.Set;
import java.util.function.Function;

public class GestionTren<T extends Vagon> {
    //Atributos
    private final Set<Tren<T>> trenes;
    //Atributos

    //Constructor
    public GestionTren() {
        this.trenes = new HashSet<>();
    }
    //Constructor

    //Getter
    public Set<Tren<T>> getTrenes() {
        return trenes;
    }
    //Getter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
        for(Tren<T> t: this.trenes) {
            sb.append("------------------------------------------------------------TREN-------------------------------------------------------------\n");
            sb.append(t);
            sb.append("------------------------------------------------------------TREN-------------------------------------------------------------\n");
        }
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    //Alta
    public boolean agregarTren (Tren<T> tren) {
        if(!this.trenes.add(tren)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }
    //Alta

    //Baja
    public boolean eliminarTren (Tren<T> tren) {
        if(!this.trenes.remove(tren) || !tren.isEstado()) {
            throw new NoSuchElementException();
        } else {
            tren.setEstado();
            this.agregarTren(tren);
        }
        return true;
    }
    //Baja

    //Modificacion
    public boolean modificarTren (Tren<T> trenViejo, Tren<T> trenNuevo) {
        if(!this.trenes.remove(trenViejo) || !trenNuevo.isEstado()) {
            throw new NoSuchElementException();
        } else {
            return this.agregarTren(trenNuevo);
        }
    }
    //Modificacion

    //JSON
    public JSONArray convertirJSONArray () {
        JSONArray json = new JSONArray();
        for(Tren<T> t: this.trenes) {
            json.put(t.convertirAJSONObject());
        }
        return json;
    }

    public static GestionTren<Vagon> getJSONArray(JSONArray json, Function<JSONObject, Vagon> tipoVagon) {
        GestionTren<Vagon> gt = new GestionTren<>();
        for (int i = 0; i < json.length(); i++) {
            gt.agregarTren(Tren.getJSONObject(json.getJSONObject(i), tipoVagon));
        }
        return gt;
    }
    //JSON

    //Archivos
    public static boolean agregarRegistro (Tren<Vagon> tren, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionTren<Vagon> gt = new GestionTren<>();

        try {
            if(new File(archivo).length() > 0) {
                for(Tren<Vagon> t: GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon).getTrenes()) {
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

    public static boolean eliminarRegistro (Tren<Vagon> tren, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionTren<Vagon> gt = new GestionTren<>();

        for(Tren<Vagon> t: GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon).getTrenes()) {
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

    public static boolean modificarRegistro (Tren<Vagon> trenViejo, Tren<Vagon> trenNuevo, Function<JSONObject, Vagon> tipoVagon, String archivo) {
        GestionTren<Vagon> gt = new GestionTren<>();

        for(Tren<Vagon> t: GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(archivo)), tipoVagon).getTrenes()) {
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
}
