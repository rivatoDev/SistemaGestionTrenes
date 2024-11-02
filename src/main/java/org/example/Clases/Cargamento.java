package org.example.Clases;

public class Cargamento {
    //Atributos

    protected StringBuilder nombre;
    protected StringBuilder tipo;
    protected double peso;
    protected int unidades;

    //Constructores

    public Cargamento() {
        this.nombre = new StringBuilder(nombre);
        this.tipo = new StringBuilder(tipo);
        this.peso = 0;
        this.unidades = 0;
    }

    public Cargamento(StringBuilder nombre, StringBuilder tipo, double peso, int unidades) {
        this.nombre = new StringBuilder(nombre);
        this.tipo = new StringBuilder(tipo);
        this.peso = peso;
        this.unidades = unidades;
    }

    //Getters

    public StringBuilder getNombre() {
        return nombre;
    }

    public StringBuilder getTipo() {
        return tipo;
    }

    public double getPeso() {
        return peso;
    }

    public int getUnidades() {
        return unidades;
    }

    //Setters

    public void setNombre(StringBuilder nombre) {
        this.nombre = nombre;
    }

    public void setTipo(StringBuilder tipo) {
        this.tipo = tipo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    //Mostrar

    @Override
    public String toString() {
        return "Cargamento{" +
                "nombre=" + nombre +
                ", tipo=" + tipo +
                ", peso=" + peso +
                ", unidades=" + unidades +
                '}';
    }
}
