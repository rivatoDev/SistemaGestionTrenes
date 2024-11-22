package org.example.Clases;

import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.function.Function;

public class Ruta {
    //Atributos
    private boolean estado;
    private Tren tren;
    private final StringBuilder salida;
    private final StringBuilder llegada;
    private Maquinista maquinista;
    private final StringBuilder fecha;
    private static int contadorID = 0;
    private final int id = contadorID++;
    //Atributos

    //Constructor
    public Ruta() {
        this.tren = null;
        this.salida = new StringBuilder();
        this.llegada = new StringBuilder();
        this.maquinista = new Maquinista();
        this.fecha = new StringBuilder();
        this.estado = true;
    }

    public Ruta(Tren tren, String salida, String llegada, Maquinista maquinista, String fecha) {
        this.tren = tren;
        this.salida = new StringBuilder(salida);
        this.llegada = new StringBuilder(llegada);
        this.maquinista = maquinista;
        this.fecha = new StringBuilder(fecha);
        this.estado = true;
    }
    //Constructor

    //Getter
    public Tren getTren() {
        return tren;
    }
    public String getSalida() {
        return salida.toString();
    }
    public String getLlegada() {
        return llegada.toString();
    }
    public Maquinista getMaquinista() {
        return maquinista;
    }
    public String getFecha() {
        return fecha.toString();
    }
    public int getId(){return this.id;}

    public boolean isEstado() {
        return estado;
    }
    //Getter

    //Setter
    public void setTren(Tren tren) {
        this.tren = tren;
    }

    public void setSalida(String salida) {
        this.salida.replace(0, this.salida.length(), salida);
    }

    public void setLlegada(String llegada) {
        this.llegada.replace(0, this.llegada.length(), llegada);
    }

    public void setFecha(String fecha) {
        this.fecha.replace(0, this.fecha.length(), fecha);
    }

    public void setMaquinista(Maquinista maquinista) {
        this.maquinista = maquinista;
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
        Ruta ruta = (Ruta) o;
        return this.id == ruta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
       return this.tren.toString() +
              this.maquinista.toString() +
              "Salida: " + this.salida + '\n' +
              "Llegada: " + this.llegada + '\n' +
              "Fecha: " + this.fecha + '\n';
    }
    //Mostrar

    //JSON
    /**
     * Convierte a una ruta en un JSONObject.
     * @return Un JSONObject con los datos de la ruta.
     */
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("tren", this.tren);
        json.put("salida", this.salida);
        json.put("llegada", this.llegada);
        json.put("maquinista", this.maquinista);
        json.put("fecha", this.fecha);
        return json;
    }

    /**
     * Verifica que el JSONObject sea del tipo correcto.
     * @param json el JSONObject a utilizar.
     * @return true si el JSONObject es del tipo correcto.
     */
    public static boolean verificarJSON (JSONObject json) {
        return json.has("tren") &&
                json.has("salida") &&
                json.has("llegada") &&
                json.has("maquinista") &&
                json.has("fecha");
    }

    /**
     * Convierte a un JSONObject en una ruta.
     * @param json el JSONObject a deserializar.
     * @param trenConverter Function con el metodo de GETJSONObject del tipo de tren.
     * @return
     */
    public static Ruta JSONxRuta(JSONObject json, Function<JSONObject, Tren> trenConverter) {
        Ruta t;
        if (Ruta.verificarJSON(json)) {
            t = new Ruta(trenConverter.apply(json),
                         json.getString("salida"),
                         json.getString("llegada"),
                         Maquinista.JSONxMaquinista(json.getJSONObject("maquinista")),
                         json.getString("fecha"));
        } else {
            throw new IllegalArgumentException();
        }
        return t;
    }
    //JSON
}