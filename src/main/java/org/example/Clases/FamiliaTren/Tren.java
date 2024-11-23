package org.example.Clases.FamiliaTren;

import org.example.Clases.FamiliaRuta.Ruta;
import org.example.Excepciones.LowCapacityException;
import org.json.JSONObject;

import java.util.*;

/**
 * Clase abstracta que representa a los trenes.
 */
public abstract class Tren {
    //Atributos
    protected final StringBuilder modelo;
    protected final StringBuilder patente;
    protected final StringBuilder ubicacion;
    protected Number capacidad;
    protected boolean estado;
    protected boolean estadoViaje;
    //Atributos

    //Constructores
    public Tren() {
        this.modelo = new StringBuilder();
        this.patente = new StringBuilder();
        this.ubicacion = new StringBuilder();
        this.capacidad = 0;
        this.estado = true;
        this.estadoViaje = false;
    }

    public Tren(String modelo, String patente, String ubicacion) {
        this.modelo = new StringBuilder(modelo);
        this.patente = new StringBuilder(patente);
        this.ubicacion = new StringBuilder(ubicacion);
        this.capacidad = 0;
        this.estado = true;
        this.estadoViaje = false;
    }
    //Constructores

    //Getter
    public String getModelo() {
        return modelo.toString();
    }

    public String getPatente() {
        return patente.toString();
    }

    public String getUbicacion() {
        return ubicacion.toString();
    }

    public Number getCapacidad() {
        return capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isEstadoViaje() {
        return estadoViaje;
    }
    //Getter

    //Setter
    public void setModelo(String modelo) {
        this.modelo.replace(0, this.modelo.length(), modelo);
    }

    public void setPatente(String patente) {
        this.patente.replace(0, this.patente.length(), patente);
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion.replace(0, this.ubicacion.length(), ubicacion);
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

    public void setEstadoViaje(boolean estadoViaje) {
        this.estadoViaje = estadoViaje;
    }
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tren tren = (Tren) o;
        return Objects.equals(patente.toString(), tren.patente.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente.toString());
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return "Patente: " + this.patente + "\n" +
               "Modelo: " + this.modelo + "\n" +
               "Capacidad: " + this.capacidad + "\n" +
               "Ubicacion: " + this.ubicacion + "\n";
    }
    //Mostrar

    /**
     * Convierte a un Tren en un JSONObject.
     * @return Un JSONObject con los datos del tren.
     */
    //JSON
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("estado", this.estado);
        json.put("patente", this.patente);
        json.put("modelo", this.modelo);
        json.put("capacidad", this.capacidad);
        json.put("ubicacion", this.ubicacion);
        json.put("estadoViaje", this.estadoViaje);
        return json;
    }

    /**
     * Verifica que el JSONObject sea del tipo correcto.
     * @param json el JSONObject a comparar.
     * @return true si el JSONObject es del tipo correcto, sino false.
     */
    public static boolean verificarJSON(JSONObject json) {
        return  json.has("modelo") &&
                json.has("patente") &&
                json.has("ubicacion") &&
                json.has("capacidad") &&
                json.has("estadoViaje") &&
                json.has("vagones") &&
                json.has("estado");
    }
    //JSON

    //Viajes
    /**
     * Metodo para iniciar un viaje.
     * Cambia el estadoViaje a true si el tren no se encuentra en ningun viaje.
     * @param ruta La ruta que tiene que tomar el tren.
     * @return true si se pudo iniciar el viaje sin ningun problema.
     * @throws IllegalStateException si el tren ya se encuentra viajando.
     * @throws IllegalArgumentException si el tren ingresado en la ruta no es el correcto.
     * @throws NoSuchElementException si el tren no se encuentra en la ubicacion inicial del viaje.
     */
    public boolean iniciarViaje(Ruta ruta) {
        if (this.estadoViaje) {
            throw new IllegalStateException();
        } else if (!this.equals(ruta.getTren())) {
            throw new IllegalArgumentException();
        } else
            if (!Objects.equals(this.ubicacion.toString(), ruta.getSalida().toString())) {
                throw new NoSuchElementException();
            } else {
                this.estadoViaje = true;
                return true;
            }
        }

    /**
     * Finaliza un viaje.
     * @return true si se pudo finalizar el viaje sin problemas.
     * @throws IllegalStateException si el tren no se encuentra viajando.
     */
    public boolean finalizarViaje() {
        if (!this.estadoViaje) {
            throw new IllegalStateException();
        } else {
            this.estadoViaje = false;
            return true;
        }
    }
    //Viaje

    public abstract boolean desarmarTren ();
}