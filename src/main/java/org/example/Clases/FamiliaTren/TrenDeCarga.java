package org.example.Clases.FamiliaTren;

import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Interfaces.GestionCarga;

import java.util.ArrayList;
import java.util.List;

public class TrenDeCarga extends Tren implements GestionCarga {
    //Peso maximo
    private static double pesoMax = 100;
    //Lista
    List<VagonDeCarga> vagones;

    //Constructor
    public TrenDeCarga(StringBuilder modelo, StringBuilder patente, StringBuilder ubicacion, boolean estado) {
        super(modelo, patente, ubicacion, estado);
        this.estado = true;
        this.vagones = new ArrayList<>();
    }

    //Getter
    public static double getPesoMax() {
        return pesoMax;
    }

    public List<VagonDeCarga> getVagones() {
        return vagones;
    }

    //Setter
    public static void setPesoMax(double pesoMax) {
        TrenDeCarga.pesoMax = pesoMax;
    }

    public void setVagones(List<VagonDeCarga> vagones) {
        this.vagones = vagones;
    }

    //Mostrar
    @Override
    public String toString() {
        return "TrenDeCarga{" +
                "vagones=" + vagones +
                ", modelo=" + modelo +
                ", patente=" + patente +
                ", ubicacion=" + ubicacion +
                ", estado=" + estado +
                "} " + super.toString();
    }


    //Metodos
    //Iniciar un viaje
    @Override
    public void iniciarViaje(String destino) {
        super.iniciarViaje(destino);
        this.estado = true;
    }

    //Finalizar un viaje
    @Override
    public void finalizarViaje() {
        super.finalizarViaje();
        this.estado = false;
    }

    //Agregar un vagon
    @Override
    public void agregarVagon(){
        if(CalcularPeso()>pesoMax){}
    }

    @Override
    public double CalcularPeso() {

        return 0;
    }

    @Override
    public void sacarVagon(String idVagon){
    }


}