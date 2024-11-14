package org.example.Clases.Menus;

/**
 * Clase que contiene los nombres de los archivos necesarios del sistema.
 */
public class Almacenamiento {
    //Atributos
    private final String trenesDeCarga;
    private final String trenesComerciales;
    private final String vagonesDeCarga;
    private final String vagonesComerciales;
    private final String rutas;
    private final String maquinistas;
    private final String usuarios;
    //Atributos

    //Constructor
    public Almacenamiento(String trenesDeCarga, String trenesComerciales, String vagonesDeCarga, String vagonesComerciales, String rutas, String maquinistas, String usuarios) {
        this.trenesDeCarga = trenesDeCarga;
        this.trenesComerciales = trenesComerciales;
        this.vagonesDeCarga = vagonesDeCarga;
        this.vagonesComerciales = vagonesComerciales;
        this.rutas = rutas;
        this.maquinistas = maquinistas;
        this.usuarios = usuarios;
    }
    //Constructor

    //Getter
    public String getTrenesDeCarga() {
        return trenesDeCarga;
    }

    public String getTrenesComerciales() {
        return trenesComerciales;
    }

    public String getVagonesDeCarga() {
        return vagonesDeCarga;
    }

    public String getVagonesComerciales() {
        return vagonesComerciales;
    }

    public String getRutas() {
        return rutas;
    }

    public String getMaquinistas() {
        return maquinistas;
    }

    public String getUsuarios() {
        return usuarios;
    }
    //Getter

    //Mostrar

    @Override
    public String toString() {
        return "Almacenamiento{" +
                "trenesDeCarga='" + trenesDeCarga + '\'' +
                ", trenesComerciales='" + trenesComerciales + '\'' +
                ", vagonesDeCarga='" + vagonesDeCarga + '\'' +
                ", vagonesComerciales='" + vagonesComerciales + '\'' +
                ", rutas='" + rutas + '\'' +
                ", maquinistas='" + maquinistas + '\'' +
                ", usuarios='" + usuarios + '\'' +
                '}';
    }
    //Mostrar
}
