package org.example.Clases;

public class Vagon {
    //Atributos
    protected StringBuilder idVagon;
    protected StringBuilder capacidad;
    protected boolean estado;

    //Constructores

    public Vagon(StringBuilder idVagon, StringBuilder capacidad, boolean estado) {
        this.idVagon = new StringBuilder(idVagon);
        this.capacidad = new StringBuilder(capacidad);
        this.estado = true;
    }

    public Vagon() {
        this.idVagon = new StringBuilder();
        this.capacidad = new StringBuilder();
        this.estado = true;
    }

    //Getters

    public StringBuilder getIdVagon() {
        return idVagon;
    }

    public StringBuilder getCapacidad() {
        return capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    //Setter

    public void setIdVagon(StringBuilder idVagon) {
        this.idVagon = idVagon;
    }

    public void setCapacidad(StringBuilder capacidad) {
        this.capacidad = capacidad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //Mostrar

    @Override
    public String toString() {
        return "Vagon{" +
                "idVagon=" + idVagon +
                ", capacidad=" + capacidad +
                ", estado=" + estado +
                '}';
    }

    //Metodo para ingresar contenido

    public boolean ingresarContenido(){
        System.out.println("Ingresando contenido");
        return true;
    }

    //Metodo para quitar contenido

    public boolean quitarContenido(){
        System.out.println("Quitando contenido");
        return true;
    }
}
