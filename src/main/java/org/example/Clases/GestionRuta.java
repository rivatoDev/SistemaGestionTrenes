package org.example.Clases;

import org.example.Clases.*;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
import org.example.Main;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class GestionRuta {
    //Atributos
    private final Set<Ruta> rutas;


    //Constructor
    public GestionRuta() {
        this.rutas = new HashSet<>();
    }

    public Set<Ruta> getRutas() {
        return rutas;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for(Ruta r: this.rutas) {
            sb.append(r);
        }
        return sb.toString();
    }

    public boolean agregarRuta(Ruta ruta) {
        if(!this.rutas.add(ruta)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }

    public boolean eliminarRuta (Ruta ruta) {
        if(!this.rutas.remove(ruta)) {
            throw new NoSuchElementException();
        } else {
            this.agregarRuta(ruta);
        }
        return true;
    }

    public boolean modificarRuta (Ruta rutaVieja, Ruta rutaNueva) {
        if(!this.rutas.remove(rutaVieja)) {
            throw new NoSuchElementException();
        }
        return this.agregarRuta(rutaNueva);
    }

    public JSONArray convertirAJSONArray () {
        JSONArray json = new JSONArray();
        for(Ruta r: this.rutas) {
            json.put(r.convertirAJSONObject());
        }
        return json;
    }

    public static HashSet<Ruta> getJSONArray(JSONArray json) {
        HashSet<Ruta> hs = new HashSet<>();
        for(int i = 0; i < json.length(); i++) {
            if(json.getJSONObject(i).getBoolean("estado")) {
                hs.add(Ruta.JSONxRuta(json.getJSONObject(i)));
            }
        }
        return hs;
    }

    public static boolean agregarRegistro (Ruta ruta, String archivo) {
        GestionRuta gu = new GestionRuta();
        try {
            if (new File(archivo).length() > 0) {
                for (Ruta r: GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))) {
                    gu.agregarRuta(r);
                }
            }
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        }
        gu.agregarRuta(ruta);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gu.convertirAJSONArray().toString(2));
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean eliminarRegistro (Ruta ruta, String archivo) {
        GestionRuta gu = new GestionRuta();

        for (Ruta r: GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))) {
            gu.agregarRuta(r);
        }
        gu.eliminarRuta(ruta);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gu.convertirAJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
