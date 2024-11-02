package org.example.Clases;

import java.util.LinkedList;

public class VagonDeCarga extends Vagon{
    //Atributos

    protected double pesoMax;
    protected LinkedList<Cargamento> contenido;

    //Constructores

    public VagonDeCarga(StringBuilder idVagon, StringBuilder capacidad, boolean estado, double pesoMax, LinkedList<Cargamento> contenido) {
        super(idVagon, capacidad, estado);
        this.pesoMax = pesoMax;
        this.contenido = contenido;
    }

    public VagonDeCarga(double pesoMax, LinkedList<Cargamento> contenido) {
        this.pesoMax = pesoMax;
        this.contenido = contenido;
    }

    public VagonDeCarga() {
        super();
        this.pesoMax = 0;
        this.contenido = null;
    }

    //Getters

    public double getPesoMax() {
        return pesoMax;
    }

    public LinkedList<Cargamento> getContenido() {
        return contenido;
    }

    //Setters

    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public void setContenido(LinkedList<Cargamento> contenido) {
        this.contenido = contenido;
    }

    //Mostrar

    @Override
    public String toString() {
        return "VagonDeCarga{" +
                "pesoMax=" + pesoMax +
                ", contenido=" + contenido +
                ", idVagon=" + idVagon +
                ", capacidad=" + capacidad +
                ", estado=" + estado +
                '}';
    }

    //Metodo para calcular el peso total

    public int calcularPesoTotalCargamento(){
        System.out.println("Calculando peso total");
        return 0;
    }

    //Metodo para quitar cargamento

    public boolean quitarCargamento(){
        System.out.println("Quitando cargamento");
        return true;
    }

    //Metodo para agregar cargamento

    public boolean agregarCargamento(){
        System.out.println("Agregando cargamento");
        return true;
    }
}
