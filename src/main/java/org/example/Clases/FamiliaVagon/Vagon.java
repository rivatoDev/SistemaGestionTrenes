package org.example.Clases.FamiliaVagon;

import org.example.Excepciones.LowCapacityException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase padre abstracta de VagonDeCarga y VagonComercial que representa a los vagones.
 */
public abstract class Vagon{
    //Atributos
    protected StringBuilder idVagon;
    protected Number capacidad;
    protected boolean estado;
    //Atributos

    //Constructores
    public Vagon() {
        this.idVagon = new StringBuilder();
        this.capacidad = 0;
        this.estado = true;
    }

    public Vagon(String idVagon, Number capacidad) {
        this.idVagon = new StringBuilder(idVagon);
        this.capacidad = capacidad;
        this.estado = true;
    }
    //Constructores

    //Getter
    public String getIdVagon() {
        return idVagon.toString();
    }

    public Number getCapacidad() {
        return capacidad;
    }

    public boolean isEstado() {
        return estado;
    }
    //Getter

    //Setter
    public void setIdVagon(String idVagon) {
        this.idVagon.replace(0, this.idVagon.length(), idVagon);
    }

    public void setCapacidad(Number capacidad) {
        if(Double.parseDouble(capacidad.toString()) <= 0) {
            throw new LowCapacityException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
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
        return estado && vagon.estado && Objects.equals(idVagon.toString(), vagon.idVagon.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, idVagon.toString());
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return  "ID: " + this.idVagon + '\n' +
                "Capacidad: " + this.capacidad + '\n';
    }
    //Mostrar

    /**
     * Convierte al Vagon en un JSONObject
     * @return el JSONObject con los datos del Vagon.
     */
    //JSON
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("estado", this.estado);
        json.put("idVagon", this.idVagon);
        json.put("capacidad", this.capacidad);
        return json;
    }
    //JSON

    //Baja
    public abstract boolean vaciarVagon();
    //Baja
}