package org.example.Clases.FamiliaVagon;

import org.example.Interfaces.JSON;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase padre abstracta de VagonDeCarga y VagonComercial que representa a los vagones.
 */
public abstract class Vagon implements JSON {
    //Atributos
    protected StringBuilder idVagon;
    protected StringBuilder capacidad;
    protected boolean estado;
    //Atributos

    //Constructores
    public Vagon() {
        this.idVagon = new StringBuilder();
        this.capacidad = new StringBuilder();
        this.estado = true;
    }

    public Vagon(String idVagon, String capacidad) {
        this.idVagon = new StringBuilder(idVagon);
        this.capacidad = new StringBuilder(capacidad);
        this.estado = true;
    }
    //Constructores

    //Getter
    public String getIdVagon() {
        return idVagon.toString();
    }

    public String getCapacidad() {
        return capacidad.toString();
    }

    public boolean isEstado() {
        return estado;
    }
    //Getter

    //Setter
    public void setIdVagon(String idVagon) {
        this.idVagon.replace(0, this.idVagon.length(), idVagon);
    }

    public void setCapacidad(String capacidad) {
        this.capacidad.replace(0, this.capacidad.length(), capacidad);
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vagon vagon = (Vagon) o;
        return estado == vagon.estado &&
               Objects.equals(idVagon.toString(), vagon.idVagon.toString()) &&
               Objects.equals(capacidad.toString(), vagon.capacidad.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVagon, capacidad, estado);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return "-------------------------------------------------------------------------------------------------------------------\n" +
                "ID: " + this.idVagon + '\n' +
                "Capacidad: " + this.capacidad + '\n';
    }
    //Mostrar

    //JSON
    /**
     * Transforma al vagonDeCarga en un JSONObject.
     * @return El VagonDeCarga como un JSONObject.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("idVagon", this.idVagon);
        json.put("capacidad", this.capacidad);
        json.put("estado", this.estado);
        return json;
    }
    //JSON

    //Baja
    public abstract boolean vaciarVagon();
    //Baja
}