package org.example.Clases.FamiliaTren;

import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Excepciones.ElementAlreadyExistsException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.function.Function;

public class Tren <E extends Vagon>{
    //Atributos
    private StringBuilder modelo;
    private StringBuilder patente;
    private StringBuilder ubicacion;
    private Set<E> vagones;
    private boolean estado;
    //Atributos

    //Constructores
    public Tren() {
        this.modelo = new StringBuilder();
        this.patente = new StringBuilder();
        this.ubicacion = new StringBuilder();
        this.vagones = new LinkedHashSet<>();
        this.estado = true;
    }

    public Tren(String modelo, String patente, String ubicacion) {
        this.modelo = new StringBuilder(modelo);
        this.patente = new StringBuilder(patente);
        this.ubicacion = new StringBuilder(ubicacion);
        this.vagones = new LinkedHashSet<>();
        this.estado = true;
    }
    //Constructores

    //Getter
    public String getModelo() {return modelo.toString();}

    public String getPatente() {return patente.toString();}

    public String getUbicacion() {return ubicacion.toString();}

    public Set<E> getVagones() {
        return vagones;
    }

    public boolean isEstado() {return estado;}
    //Getter

    //Setter
    public void setModelo(String modelo) {this.modelo.replace(0,this.modelo.length(), modelo);}

    public void setPatente(String patente) {this.patente.replace(0,this.patente.length(),patente);}

    public void setUbicacion(String ubicacion) {this.ubicacion.replace(0,this.ubicacion.length(),ubicacion);}

    public void setEstado(boolean estado) {this.estado = estado;}
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tren tren = (Tren) o;
        return estado && tren.estado &&
               Objects.equals(modelo.toString(), tren.modelo.toString()) &&
               Objects.equals(patente.toString(), tren.patente.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, modelo.toString(), patente.toString());
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Modelo: ").append(this.modelo).append("\n");
        sb.append("Patente: ").append(this.patente).append("\n");
        sb.append("Ubicacion: ").append(this.ubicacion).append("\n");
        for(E e: this.vagones) {
            sb.append(e);
        }
        return sb.toString();
    }
    //Mostrar

    //Alta
    public boolean acoplarVagon (E e) {
        if(!this.vagones.(e)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }
    //Alta

    //Baja
    public boolean desacoplarVagon (E e) {
        if(!this.vagones.remove(e)) {
            throw new NoSuchElementException();
        }
        return true;
    }

    public boolean desarmarTren () {
        for (E e: this.vagones) {
            this.desacoplarVagon(e);
        }
        return true;
    }
    //Baja

    //JSON
    public JSONArray convertirAJSONArray () {
        JSONArray json = new JSONArray();
        for(E e: this.vagones) {
            json.put(e.convertirAJSONObject());
        }
        return json;
    }

    public JSONObject convertirAJSONObject () {
        JSONObject json = new JSONObject();
        json.put("estado", this.estado);
        json.put("modelo", this.modelo);
        json.put("patente", this.patente);
        json.put("ubicacion", this.ubicacion);
        json.put("vagones", this.convertirAJSONArray());
        return json;
    }

    public static boolean verificarJSON (JSONObject json) {
        return json.has("estado") &&
                json.has("modelo") &&
                json.has("patente") &&
                json.has("ubicacion") &&
                json.has("vagones");
    }

    public static LinkedHashSet<Vagon> getJSONArray (JSONArray json, Function<JSONObject, Vagon> tipoVagon) {
        LinkedHashSet<Vagon> l = new LinkedHashSet<>();
        for (int i = 0; i < json.length(); i++) {
            l.add(tipoVagon.apply(json.getJSONObject(i)));
        }
        return l;
    }

    public static Tren<Vagon> getJSONObject (JSONObject json, Function<JSONObject, Vagon> vagon) {
        Tren<Vagon> t = new Tren<>(json.getString("modelo"), json.getString("patente"), json.getString("ubicacion"));
        for(int i = 0; i < json.getJSONArray("vagones").length(); i++) {
            t.acoplarVagon(vagon.apply(json.getJSONArray("vagones").getJSONObject(i)));
        }
        return t;
    }
    //JSON

    
    /*public void iniciarViaje(Ruta ruta){
        if(ruta.)
        this.estado = true;
    }

    public void finalizarViaje(){
        System.out.println("Viaje finalizado.");
        this.estado = false;
    }
    //Viaje*/
}