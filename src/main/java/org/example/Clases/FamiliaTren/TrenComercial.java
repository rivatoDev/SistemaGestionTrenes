package org.example.Clases.FamiliaTren;

import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Clase que hereda de Tren y representa a los trenes que llevan pasajeros.
 */
public class    TrenComercial extends Tren {
    //Atributos
    private final LinkedHashSet<VagonComercial> vagones;
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

    //Comparacion
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
    //Comparacion

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
    /**
     * Agrega un vagon al final del LinkedHashSet de vagones.
     * Solo se puede operar el ultimo vagon acoplado.
     * @param vagon el Vagon a agregar.
     * @return true si se pudo agregar el vagon sin problemas.
     * @throws ElementAlreadyExistsException si el vagon ya se encuentra en el tren.
     * @throws JSONObjectEliminatedException si el vagon no esta disponible o fue eliminado.
     * @throws OffLimitsException si el vagon a agregar supera el peso maximo permitido.
     */
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
    /**
     * Quita del tren al ultimo vagon.
     * @return  true si se pudo quitar el vagon sin problemas.
     * @throws NullPointerException si el vagon esta vacio.
     */
    public boolean desacoplarVagon() {
        if(this.vagones.isEmpty()) {
            throw new NullPointerException();
        } else {
            this.vagones.removeLast();
            return true;
        }
    }

    /**
     * Quita el un vagon a eleccion.
     * @param vagon el Vagon a quitar.
     * @return true si se pudo quitar el vagon sin problemas.
     * @throws NoSuchElementException si el tren no contiene al Vagon.
     */
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

    /**
     * Quita al tren todos los vagones que tenga.
     * @return true si se pudo desarmar el tren sin inconvenientes.
     */
    public boolean desarmarTren() {
        for (VagonComercial vc: this.vagones) {
            this.desacoplarVagon();
        }
        return true;
    }
    //Baja


    //Verificacion
    /**
     * Calcula el peso que esta llevando el tren.
     * @return el peso total del tren.
     */
    public double pesoActual () {
        double peso = 0;
        for(VagonComercial vc: this.vagones) {
            peso += vc.contarAsientos() * 80;
        }
        return peso;
    }
    //Verificacion

    //JSON
    /**
     * Convierte a {@link #vagones} en un JSONArray.
     * @return Un JSONArray con los datos del tren.
     */
    public JSONArray convertirAJSONArray() {
        JSONArray json = new JSONArray();
        for(VagonComercial vc: this.vagones) {
            json.put(vc.convertirAJSONObject());
        }
        return json;
    }

    /**
     * Convierte al tren en un JSONObject.
     * @return Un JSONObject con los datos del tren.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = super.convertirAJSONObject();
        json.put("vagones", convertirAJSONArray());
        return json;
    }

    /**
     * Convierte a un JSONArray en un LinkedHashSet de vagones.
     * @param json El JSONArray a convertir.
     * @return Un linkedHashSet de vagones con los datos del JSONArray.
     */
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

    /**
     * Convierte a un JSONObject en un trenComercial.
     * @param json el JSONObject a convertir.
     * @return un TrenComercial con los datos de un JSONObject.
     * @throws IllegalArgumentException si el JSONObject no es del tipo correcto.
     */

    public static TrenComercial getJSONObject (JSONObject json) {
        if(!TrenComercial.verificarJSON(json)) {
            throw new IllegalArgumentException();
        } else  {
            TrenComercial tc = new TrenComercial();
            tc.setEstado(json.getBoolean("estado"));
            tc.setModelo(json.getString("modelo"));
            tc.setPatente(json.getString("patente"));
            tc.setCapacidad(json.getInt("capacidad"));
            tc.setUbicacion(json.getString("ubicacion"));
            tc.setEstadoViaje(json.getBoolean("estadoViaje"));
            for(VagonComercial vc: getJSONArray(json.getJSONArray("vagones"))) {
                tc.acoplarVagon(vc);
            }
            return tc;
        }
    }
    //JSON
}