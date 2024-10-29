package org.example.Clases.FamiliaPersona;

public class Pasajero {
    //Atributos
    protected StringBuilder asiento;
    protected StringBuilder idVagon;
    protected StringBuilder codigoEntrada;
    //Atributos

    //Constructores

    public Pasajero() {
        this.asiento = new StringBuilder();
        this.idVagon = new StringBuilder();
        this.codigoEntrada = new StringBuilder();
    }

    public Pasajero(StringBuilder asiento, StringBuilder idVagon, StringBuilder codigoEntrada) {
        this.asiento = new StringBuilder(asiento);
        this.idVagon = new StringBuilder(idVagon);
        this.codigoEntrada = new StringBuilder(codigoEntrada);
    }
    //Constructores

    //Getter
    public StringBuilder getAsiento() {return asiento;}

    public StringBuilder getIdVagon() {return idVagon;}

    public StringBuilder getCodigoEntrada() {return codigoEntrada;}
    //Getter

    //Setter

    public void setAsiento(StringBuilder asiento) {
        this.asiento = asiento;
    }

    public void setIdVagon(StringBuilder idVagon) {
        this.idVagon = idVagon;
    }

    public void setCodigoEntrada(StringBuilder codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }
    //Setter
    //Mostrar

    @Override
    public String toString() {
        return "Pasajeros{" +
                "asiento=" + asiento +
                ", idVagon=" + idVagon +
                ", codigoEntrada=" + codigoEntrada +
                '}';
    }

    //Mostrar


}