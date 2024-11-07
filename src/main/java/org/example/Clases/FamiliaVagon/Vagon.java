package org.example.Clases.FamiliaVagon;

import org.json.JSONObject;

/**
 * Clase padre abstracta de VagonDeCarga y VagonComercial que representa a los vagones.
 */
public abstract class Vagon{
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

    public void setEstado() {
        this.estado = !this.estado;
    }
    //Setter

    //Mostrar
    @Override
    public String toString() {
        return "\n------------------------------------------------------------VAGON------------------------------------------------------------\n" +
                "ID: " + this.idVagon + '\n' +
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