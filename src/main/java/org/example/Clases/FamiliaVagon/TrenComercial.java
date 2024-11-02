package org.example.Clases.FamiliaVagon;

import org.example.Clases.Tren;
import org.example.Interfaces.GestionCarga;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class TrenComercial extends Tren implements GestionCarga {
    //Lista de vagones
    LinkedHashSet<VagonComercial> vagones = null;

    //Constructor
    public TrenComercial(StringBuilder modelo, StringBuilder patente, StringBuilder ubicacion, boolean estado) {
        super(modelo, patente, ubicacion, estado);
        this.vagones = new LinkedHashSet<>();
    }

    //Constructor vacio
    public TrenComercial() {
    }

    //Getter
    public LinkedHashSet getVagones() {
        return vagones;
    }

    //Setter
    public void setVagones(LinkedHashSet vagones) {
        this.vagones = vagones;
    }

    //Mostrar
    @Override
    public String toString() {
        return "TrenComercial{" +
                "vagones=" + vagones +
                ", modelo=" + modelo +
                ", patente=" + patente +
                ", ubicacion=" + ubicacion +
                ", estado=" + estado +
                "} " + super.toString();
    }

    //METODOS

    //Iniciar viaje
    @Override
    public void iniciarViaje(String destino) {
        super.iniciarViaje(destino);
    }

    //Finalizar viaje
    @Override
    public void finalizarViaje() {
        super.finalizarViaje();
    }

    //Agregar un vagon
    @Override
    public void agregarVagon(VagonComercial a){

    }

    //Sacar vagon
    @Override
    public void sacarVagon(String idVagon){

    }

    //Calcular el peso
    @Override
    public double CalcularPeso() {
        int peso = 0;
        int flag = 0;

        for(VagonComercial a : vagones){

        }
    }
}
