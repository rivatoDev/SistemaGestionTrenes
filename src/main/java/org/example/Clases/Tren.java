package org.example.Clases;

public class Tren {
    //Atributos
    protected StringBuilder modelo;
    protected StringBuilder patente;
    protected StringBuilder ubicacion;
    protected boolean estado;
    //Atributos

    //Constructores

    public Tren() {
        this.modelo = new StringBuilder();
        this.patente = new StringBuilder();
        this.ubicacion = new StringBuilder();
        this.estado = true;
    }

    public Tren(StringBuilder modelo, StringBuilder patente, StringBuilder ubicacion, boolean estado) {
        this.modelo = new StringBuilder(modelo);
        this.patente = new StringBuilder(patente);
        this.ubicacion = new StringBuilder(ubicacion);
        this.estado = true;
    }
    //Constructores

    //Getter

    public StringBuilder getModelo() {return modelo;}

    public StringBuilder getPatente() {return patente;}

    public StringBuilder getUbicacion() {return ubicacion;}

    public boolean isEstado() {return estado;}

    //Getter

    //Setter

    public void setModelo(String modelo) {this.modelo.replace(0,this.patente.length(), modelo);}

    public void setPatente(String patente) {this.patente.replace(0,this.patente.length(),patente);}

    public void setUbicacion(String ubicacion) {this.ubicacion.replace(0,this.patente.length(),ubicacion);}

    public void setEstado(boolean estado) {this.estado = estado;}

    //Setter

    //Mostrar

    @Override
    public String toString() {
        return "Tren{" +
                "modelo=" + modelo +
                ", patente=" + patente +
                ", ubicacion=" + ubicacion +
                ", estado=" + estado +
                '}';
    }

    //Mostrar

    //Metodo para comenzar el viaje.
    public void iniciarViaje(String destino){
        System.out.println("Viaje con destino a " + destino);
        this.estado = true;
    }
    //Metodo para finalizar el viaje.
    public void finalizarViaje(){
        System.out.println("Viaje finalizado.");
        this.estado = false;
    }

}