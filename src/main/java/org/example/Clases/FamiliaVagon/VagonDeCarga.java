package org.example.Clases.FamiliaVagon;

import org.example.Excepciones.HeightOffLimitsException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class VagonDeCarga extends Vagon {
    //Atributos
    protected double pesoMax;
    protected List<Cargamento> contenido;
    //Atributos

    //Constructores
    public VagonDeCarga() {
        super();
        this.pesoMax = 0;
        this.contenido = new LinkedList<>();
    }

    public VagonDeCarga(String idVagon, String capacidad, double pesoMax, LinkedList<Cargamento> contenido) {
        super(idVagon, capacidad);
        this.pesoMax = pesoMax;
        this.contenido = contenido;
    }
    //Constructores

    //Getter
    public double getPesoMax() {
        return pesoMax;
    }

    public LinkedList<Cargamento> getContenido() {
        return (LinkedList<Cargamento>) contenido;
    }
    //Getter

    //Setter
    public void setPesoMax(double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public void setContenido(LinkedList<Cargamento> contenido) {
        this.contenido = contenido;
    }
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VagonDeCarga that = (VagonDeCarga) o;
        return Double.compare(pesoMax, that.pesoMax) == 0 && Objects.equals(contenido, that.contenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pesoMax, contenido);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Peso Maximo: ").append(this.pesoMax).append('\n');
        for(Cargamento c: contenido) {
            sb.append(c.toString());
        }
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    //Alta
    public boolean agregarCargamento (Cargamento cargamento) throws HeightOffLimitsException {
        if (cargamento.CalcularPeso() > this.pesoMax - this.calcularPesoTotal()) {
            throw new HeightOffLimitsException("No hay espacio suficiente para el cargamento.");
        } else {
            this.contenido.add(cargamento);
            return true;
        }
    }
    //Alta

    //Baja
    public boolean quitarCargamento(Cargamento cargamento){
        return this.contenido.remove(cargamento);
    }

    @Override
    public boolean vaciarVagon() {
        if(this.contenido.isEmpty()) {
            throw new NullPointerException();
        } else {
            for(Cargamento c: this.contenido) {
                this.quitarCargamento(c);
            }
            return true;
        }
    }
    //Baja

    //Verificacion
    public double calcularPesoTotal(){
        double pesoTotal = 0;
        for(Cargamento c: this.contenido) {
            pesoTotal += c.CalcularPeso();
        }
        return pesoTotal;
    }
    //Verificacion

}
