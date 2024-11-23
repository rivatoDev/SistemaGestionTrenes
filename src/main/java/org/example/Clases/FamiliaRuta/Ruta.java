package org.example.Clases.FamiliaRuta;

import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
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
    private final StringBuilder id;
    //Atributos

    //Constructor
    public Ruta() {
        this.tren = null;
        this.salida = new StringBuilder();
        this.llegada = new StringBuilder();
        this.maquinista = new Maquinista();
        this.fecha = new StringBuilder();
        this.estado = true;
        this.id = new StringBuilder();
    }

    public Ruta(Tren tren, String salida, String llegada, Maquinista maquinista, String fecha, String id) {
        this.tren = tren;
        this.salida = new StringBuilder(salida);
        this.llegada = new StringBuilder(llegada);
        this.maquinista = maquinista;
        this.fecha = new StringBuilder(fecha);
        this.estado = true;
        this.id = new StringBuilder(id);
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
    public String getId(){return this.id.toString();}

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

    public void setId(String id) {
        this.id.replace(0, this.id.length(), id);
    }

    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruta ruta = (Ruta) o;
        return Objects.equals(this.id.toString(), ruta.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
       return "-----------------------------------TREN-----------------------------------\n" +
              this.tren.toString() +
              "-----------------------------------TREN-----------------------------------\n" +
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

        if(this.tren instanceof TrenDeCarga) {
            TrenDeCarga t = (TrenDeCarga) tren;
            json.put("trenDeCarga", t.convertirAJSONObject());
        } else {
            TrenComercial t = (TrenComercial) tren;
            json.put("trenComercial", t.convertirAJSONObject());
        }

        json.put("id", this.id);
        json.put("salida", this.salida);
        json.put("llegada", this.llegada);
        json.put("maquinista", this.maquinista.convertirAJSONObject());
        json.put("fecha", this.fecha);
        return json;
    }

    /**
     * Verifica que el JSONObject sea del tipo correcto.
     * @param json el JSONObject a utilizar.
     * @return true si el JSONObject es del tipo correcto.
     */
    public static boolean verificarJSON (JSONObject json) {
        return (json.has("trenDeCarga") || json.has("trenComercial")) &&
                json.has("salida") &&
                json.has("llegada") &&
                json.has("maquinista") &&
                json.has("fecha") &&
                json.has("id");
    }

    /**
     * Convierte a un JSONObject en una ruta.
     * @param json el JSONObject a deserializar.
     * @param trenConverter Function con el metodo de GETJSONObject del tipo de tren.
     * @return Una ruta con los datos del JSONObject.
     * @throws IllegalArgumentException si el json no es del tipo correcto.
     */
    public static Ruta JSONxRuta(JSONObject json, Function<JSONObject, Tren> trenConverter) {
        Ruta t;
        JSONObject tren;
        if(json.has("trenDeCarga")) {
            tren = json.getJSONObject("trenDeCarga");
        } else {
            tren = json.getJSONObject("trenComercial");
        }

        if (Ruta.verificarJSON(json)) {
            t = new Ruta(trenConverter.apply(tren),
                         json.getString("salida"),
                         json.getString("llegada"),
                         Maquinista.JSONxMaquinista(json.getJSONObject("maquinista")),
                         json.getString("fecha"),
                         json.getString("id"));
        } else {
            throw new IllegalArgumentException();
        }
        return t;
    }
    //JSON
}