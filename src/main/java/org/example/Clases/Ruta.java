package org.example.Clases;

import org.example.Clases.FamiliaPersona.Maquinista;

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

    public Tren getTren() {
        return tren;
    }

    public void setTren(Tren tren) {
        this.tren = tren;
    }

    public StringBuilder getSalida() {
        return salida;
    }

    public void setSalida(StringBuilder salida) {
        this.salida = salida;
    }

    public StringBuilder getLlegada() {
        return llegada;
    }

    public void setLlegada(StringBuilder llegada) {
        this.llegada = llegada;
    }

    public Maquinista getMaquinista() {
        return maquinista;
    }

    public void setMaquinista(Maquinista maquinista) {
        this.maquinista = maquinista;
    }

    public int getFecha() {
        return fecha;
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
}
