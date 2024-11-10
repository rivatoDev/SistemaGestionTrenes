package org.example.Clases.FamiliaTren;

import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
import org.example.Excepciones.WrongUserException;
import org.example.Interfaces.GestionCarga;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class TrenComercial extends Tren {
    //Atributos
    private LinkedHashSet<VagonComercial> vagones;
    //Atributos

    //Constructor
    public TrenComercial() {
        super();
        this.vagones = new LinkedHashSet<>();
    }

    public TrenComercial(String modelo, String patente, String ubicacion) {
        super(modelo, patente, ubicacion);
        this.vagones = new LinkedHashSet<>();
    }
    //Constructor

    //Getter
    public LinkedHashSet<VagonComercial> getVagones() {
        return vagones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if(!this.vagones.isEmpty()) {
            for(VagonComercial vagon: this.vagones) {
                sb.append("--------------------------------------------------VAGON--------------------------------------------------\n");
                sb.append(vagon);
                sb.append("--------------------------------------------------VAGON--------------------------------------------------\n");
            }
        }
        return sb.toString();
    }
    //Mostrar

    //Alta
    public boolean acoplarVagon(VagonComercial vagon) {
        if (this.vagones.contains(vagon)) {
            throw new ElementAlreadyExistsException();
        } else if(!vagon.isEstado()){
            throw new JSONObjectEliminatedException();
        } else  if (this.pesoActual() > Integer.parseInt(this.capacidad.toString())){
            throw new OffLimitsException();
        } else {
            this.vagones.addLast(vagon);
            return true;
        }
    }
    //Alta

    //Baja
    public boolean desacoplarVagon() {
        if(this.vagones.isEmpty()) {
            throw new NullPointerException();
        } else {
            this.vagones.removeLast();
            return true;
        }
    }

    public boolean quitarVagon (VagonComercial vagon) {
        LinkedHashSet<VagonComercial> set = new LinkedHashSet<>();
        if(!this.vagones.contains(vagon)) {
            throw new NoSuchElementException();
        } else {
            while(!this.vagones.isEmpty() && !this.vagones.getLast().equals(vagon)) {
                set.addLast(this.vagones.removeLast());
            }
            this.desacoplarVagon();

            while(!this.vagones.isEmpty()) {
                this.acoplarVagon(set.removeLast());
            }
        }
        return true;
    }

    public boolean desarmarTren() {
        for (VagonComercial vc: this.vagones) {
            this.desacoplarVagon();
        }
        return true;
    }
    //Baja

    //Verificacion
    public double pesoActual () {
        double peso = 0;
        for(VagonComercial vc: this.vagones) {
            peso += vc.contarAsientos() * 80;
        }
        return peso;
    }
    //Verificacion

    //JSON
    public JSONArray convertirAJSONArray() {
        JSONArray json = new JSONArray();
        for(VagonComercial vc: this.vagones) {
            json.put(vc.convertirAJSONObject());
        }
        return json;
    }

    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = super.convertirAJSONObject();
        json.put("vagones", convertirAJSONArray());
        return json;
    }

    public static boolean verificarJSON(JSONObject json) {
        return json.has("estado") &&
                json.has("modelo") &&
                json.has("patente") &&
                json.has("ubicacion") &&
                json.has("estadoViaje") &&
                json.has("vagones");
    }

    public static LinkedHashSet<VagonComercial> getJSONArray (JSONArray json) {
        LinkedHashSet<VagonComercial> vagones = new LinkedHashSet<>();
        for(int i = 0; i < json.length(); i++) {
            if(verificarJSON(json.getJSONObject(i))) {
                vagones.add(VagonComercial.getJSONObject(json.getJSONObject(i)));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return vagones;
    }

    public static TrenComercial getJSONObject (JSONObject json) throws JSONObjectEliminatedException {
        if(TrenComercial.verificarJSON(json) && json.getBoolean("estado")) {
            TrenComercial tc = new TrenComercial();
            tc.setModelo(json.getString("modelo"));
            tc.setPatente(json.getString("patente"));
            tc.setUbicacion(json.getString("ubicacion"));
            tc.setUbicacion(json.getString("estadoViaje"));
            for(VagonComercial vc: getJSONArray(json.getJSONArray("pasajeros"))) {
                tc.acoplarVagon(vc);
            }
            return tc;
        } else if (!TrenComercial.verificarJSON(json)) {
            throw new IllegalArgumentException();
        } else {
            throw new JSONObjectEliminatedException();
        }
    }
    //JSON
}