package org.example;


import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.FileDoesntExistException;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class Main {
    public static void main(String[] args) {
        //Archivos
        final String almacenamientoVagones = "vagones.json";
        final String almacenamientoUsuarios = "usuarios.json";
        //Archivos

        VagonComercial vagon = new VagonComercial("123", 100);
        Tren<Vagon> tren = new Tren<>("XLR9", "qwe123", "Mar del Plata");
        GestionTren.agregarRegistro(tren, VagonComercial::getJSONObject, almacenamientoVagones);
        System.out.println(GestionTren.getJSONArray(new JSONArray(Main.leerArchivo(almacenamientoVagones)), VagonComercial::getJSONObject));
    }

    //Archivos

    /**
     * Crea un archivo de texto.
     * @param nombre Nombre del archivo.
     * @return true si se pudo crear el archivo sin problemas, sino false.
     */
    public static boolean crearArchivo (String nombre) {
        try {
            if (!new File(nombre).createNewFile()) {
                throw new FileAlreadyExistsException(nombre);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Lee un archivo de texto.
     * @param archivo Nombre del archivo.
     * @return Un JSONTokener con los datos del archivo.
     * @throws FileDoesntExistException Se lanza cuando el archivo a leer no existe.
     * Va a ser necesario para la carga de registros en el archivo.
     */
    public static JSONTokener leerArchivo (String archivo) throws FileDoesntExistException {
        JSONTokener json;
        try {
            json = new JSONTokener(new BufferedReader(new FileReader(archivo)));
        } catch (FileNotFoundException e) {
            throw new FileDoesntExistException();
        }
        return json;
    }
    //Archivos
}