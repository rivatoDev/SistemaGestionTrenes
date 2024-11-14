package org.example.Clases.Menus;

/**
 * Clase que contiene los nombres de los archivos necesarios del sistema.
 */
public class Almacenamiento {
    //Atributos
    private final String nombreArchivoTrenes;
    private final String nombreArchivoVagonesDeCarga;
    private final String nombreArchivoVagonesComerciales;
    private final String nombreArchivoRutas;
    private final String nombreArchivoMaquinistas;
    private final String nombreArchivoUsuarios;
    //Atributos

    //Constructor
    public Almacenamiento(String nombreArchivoTrenes, String nombreArchivoVagonesDeCarga, String nombreArchivoVagonesComerciales, String nombreArchivoRutas, String nombreArchivoMaquinistas, String nombreArchivoUsuarios) {
        this.nombreArchivoTrenes = nombreArchivoTrenes;
        this.nombreArchivoVagonesDeCarga = nombreArchivoVagonesDeCarga;
        this.nombreArchivoVagonesComerciales = nombreArchivoVagonesComerciales;
        this.nombreArchivoRutas = nombreArchivoRutas;
        this.nombreArchivoMaquinistas = nombreArchivoMaquinistas;
        this.nombreArchivoUsuarios = nombreArchivoUsuarios;
    }
    //Constructor

    //Getter
    public String getNombreArchivoTrenes() {
        return nombreArchivoTrenes;
    }

    public String getNombreArchivoVagonesDeCarga() {
        return nombreArchivoVagonesDeCarga;
    }

    public String getNombreArchivoVagonesComerciales() {
        return nombreArchivoVagonesComerciales;
    }

    public String getNombreArchivoRutas() {
        return nombreArchivoRutas;
    }

    public String getNombreArchivoMaquinistas() {
        return nombreArchivoMaquinistas;
    }

    public String getNombreArchivoUsuarios() {
        return nombreArchivoUsuarios;
    }
    //Getter


    //Mostrar
    @Override
    public String toString() {
        return "GestionSistema{" +
                "nombreArchivoTrenes='" + nombreArchivoTrenes + '\'' +
                ", nombreArchivoVagonesDeCarga='" + nombreArchivoVagonesDeCarga + '\'' +
                ", nombreArchivoVagonesComerciales='" + nombreArchivoVagonesComerciales + '\'' +
                ", nombreArchivoRutas='" + nombreArchivoRutas + '\'' +
                ", nombreArchivoMaquinistas='" + nombreArchivoMaquinistas + '\'' +
                ", nombreArchivoUsuarios='" + nombreArchivoUsuarios + '\'' +
                '}';
    }
    //Mostrar
}
