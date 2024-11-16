package org.example.Clases;

import org.example.Clases.*;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.Menus.Almacenamiento;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
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

    public static GestionRuta getJSONArray(JSONArray json) {
        GestionRuta rutini = new GestionRuta();

        for (int i = 0; i < json.length(); i++) {
            JSONObject jsonObject = json.getJSONObject(i);

            // Determina el tipo de tren a partir del JSON
            Function<JSONObject, Tren> trenConverter = jsonObj -> {
                if (jsonObj.has("vagones")) { // Solo se espera que tenga "vagones"
                    JSONArray vagones = jsonObj.getJSONArray("vagones");
                    if (vagones.length() > 0 && vagones.getJSONObject(0).has("tipo")) {
                        String tipoVagon = vagones.getJSONObject(0).getString("tipo");
                        if ("comercial".equals(tipoVagon)) {
                            return TrenComercial.getJSONObject(jsonObj);
                        } else if ("carga".equals(tipoVagon)) {
                            return TrenDeCarga.getJSONObject(jsonObj);
                        }
                    }
                }
                return null;
            };
            Ruta ruta = Ruta.JSONxRuta(jsonObject, trenConverter);
            if (ruta != null) {
                rutini.rutas.add(ruta);
            }
        }
        return rutini;
    };

    public static boolean agregarRegistro (Ruta ruta, String archivo) {
        GestionRuta gu = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));
        try {
            if (new File(archivo).length() > 0) {
                for (Ruta r: gu.rutas) {
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

    public static boolean eliminarRegistro (Ruta ruta, String archivo) {
        GestionRuta gu = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));

        for (Ruta r: gu.rutas) {
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

    public Ruta verificarRuta(Ruta ruta, String archivo) {
        GestionRuta gu = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));
        for (Ruta r: gu.rutas) {
            if (Objects.equals(ruta, r)){
                return r;
            }
        }
        throw new NullPointerException();
    }

    public Ruta verificarRutaID(String id, String archivo) {
        GestionRuta gu = GestionRuta.getJSONArray(new JSONArray(Main.leerArchivo(archivo)));
        for (Ruta r: gu.rutas) {
            if (Objects.equals(id, r.getContadorID())){
                return r;
            }
        }
        throw new NullPointerException();
    }
}
