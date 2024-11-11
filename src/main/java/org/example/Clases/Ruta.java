package org.example.Clases;

import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.json.JSONObject;

public class Ruta {
    //Atributos
    private Tren tren;
    private StringBuilder salida;
    private StringBuilder llegada;
    private Maquinista maquinista;
    private int fecha;

    //Constructor
    public Ruta(Tren tren, StringBuilder salida, StringBuilder llegada, Maquinista maquinista, int fecha) {
        this.tren = tren;
        this.salida = salida;
        this.llegada = llegada;
        this.maquinista = maquinista;
        this.fecha = fecha;
    }
    //Constructor vacio
    public Ruta() {
    }


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

    //Pasar a jsonobject
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("tren", this.tren);
        json.put("salida", this.salida);
        json.put("llegada", this.llegada);
        json.put("maquinista", this.maquinista);
        json.put("fecha", this.fecha);
        return json;
    }

    //Verificar
    public static boolean verificarJSON (JSONObject json) {
        return json.has("tren") &&
                json.has("salida") &&
                json.has("llegada") &&
                json.has("maquinista") &&
                json.has("fecha");
    }


    //Pasar de JSON a Ruta
    //Faltaria ver el tema de como saber que tipo de tren es
    /*public static Ruta JSONxRuta(JSONObject json) throws JSONObjectEliminatedException {
        if (Ruta.verificarJSON(json)) {
            Tren tren = Tren.JSONxTren(json.getJSONObject("tren"));
            StringBuilder salida = new StringBuilder(json.getString("salida"));
            StringBuilder llegada = new StringBuilder(json.getString("llegada"));
            Maquinista maquinista = Maquinista.JSONxMaquinista(json.getJSONObject("maquinista"));
            int fecha = json.getInt("fecha");

            return new Ruta(tren, salida, llegada, maquinista, fecha);
        } else {
            throw new IllegalArgumentException();
        }
    }*/
}
