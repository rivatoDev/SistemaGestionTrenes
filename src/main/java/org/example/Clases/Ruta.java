package org.example.Clases;

import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.json.JSONObject;

import java.util.function.Function;

public class Ruta {
    //Atributos
    private Tren tren;
    private StringBuilder salida;
    private StringBuilder llegada;
    private Maquinista maquinista;
    private int fecha;
    private static int contadorID = 0;
    private int id = 0;
    //Atributos

    //Constructor
    public Ruta(Tren tren, StringBuilder salida, StringBuilder llegada, Maquinista maquinista, int fecha) {
        this.tren = tren;
        this.salida = salida;
        this.llegada = llegada;
        this.maquinista = maquinista;
        this.fecha = fecha;
        this.contadorID = id++;
    }
    //Constructor

    //Constructor vacio
    public Ruta() {
    }
    //Constructor vacio


    //Getter
    public Tren getTren() {
        return tren;
    }
    public StringBuilder getSalida() {
        return salida;
    }
    public StringBuilder getLlegada() {
        return llegada;
    }
    public Maquinista getMaquinista() {
        return maquinista;
    }
    public int getFecha() {
        return fecha;
    }
    public int getContadorID(){return contadorID;}
    //Getter

    //Setter
    public void setTren(Tren tren) {
        this.tren = tren;
    }
    public void setSalida(StringBuilder salida) {
        this.salida = salida;
    }
    public void setLlegada(StringBuilder llegada) {
        this.llegada = llegada;
    }
    public void setMaquinista(Maquinista maquinista) {
        this.maquinista = maquinista;
    }
    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
    public void setContadorID(){this.contadorID = contadorID;}
    //Setter

    //Mostrar
    @Override
    public String toString() {
        return "Ruta{" +
                "tren=" + tren +
                ", salida=" + salida +
                ", llegada=" + llegada +
                ", maquinista=" + maquinista +
                ", fecha=" + fecha +
                '}';
    }
    //Mostrar

    //Pasar a JSONObject
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("tren", this.tren);
        json.put("salida", this.salida);
        json.put("llegada", this.llegada);
        json.put("maquinista", this.maquinista);
        json.put("fecha", this.fecha);
        return json;
    }
    //Pasar a JSONObject

    //Verificar
    public static boolean verificarJSON (JSONObject json) {
        return json.has("tren") &&
                json.has("salida") &&
                json.has("llegada") &&
                json.has("maquinista") &&
                json.has("fecha");
    }
    //Verificar


    /**
     * Pasa de json a un objeto Ruta
     * @throws JSONObjectEliminatedException
     */
    public static Ruta JSONxRuta(JSONObject json, Function<JSONObject, Tren> trenConverter) throws JSONObjectEliminatedException {
        if (Ruta.verificarJSON(json)) {
            Tren tren = trenConverter.apply(json);
            StringBuilder salida = new StringBuilder(json.getString("salida"));
            StringBuilder llegada = new StringBuilder(json.getString("llegada"));
            Maquinista maquinista = Maquinista.JSONxMaquinista(json.getJSONObject("maquinista"));
            int fecha = json.getInt("fecha");

            return new Ruta(tren, salida, llegada, maquinista, fecha);
        } else {
            throw new IllegalArgumentException();
        }
    }
}