package org.example;


import org.example.Clases.FamiliaPersona.GestionUsuario;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaVagon.Cargamento;
import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.FamiliaVagon.VagonDeCarga;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Excepciones.JSONEmptyFileException;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //Archivos
        final String almacenamientoVagones = "vagones.json";
        final String almacenamientoUsuarios = "usuarios.json";
        //Archivos


        Usuario usuario = new Usuario("44336430", "Tomas", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE);
        /*GestionUsuario gu = new GestionUsuario();
        gu.agregarUsuario(usuario);
        gu.agregarUsuario(usuario);
        System.out.println(gu);*/

        try {
            GestionUsuario.agregarRegistro(usuario, almacenamientoUsuarios);
            //System.out.println(GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(almacenamientoUsuarios))));
            //GestionUsuario.eliminarRegistro(usuario, almacenamientoUsuarios);
            //System.out.println(GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(almacenamientoUsuarios))));
        } catch (JSONEmptyFileException e) {
            System.out.println("El archivo esta vacio");
        }
    }

    //Archivos

    /**
     * Crea un archivo de texto.
     * @param nombre Nombre del archivo.
     * @return true si se pudo crear el archivo sin problemas, sino false.
     * @throws FileAlreadyExistsException Se lanza cuando ya existe un archivo ya existe.
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
     * @throws JSONEmptyFileException Se lanza cuando el archivo esta vacio.
     * Va a ser necesario para la carga de registros en el archivo.
     */
    public static JSONTokener leerArchivo (String archivo) throws FileDoesntExistException, JSONEmptyFileException {
        JSONTokener json;
        try {
            json = new JSONTokener(new BufferedReader(new FileReader(archivo)));
            if (new File(archivo).length() == 0) {
                throw new JSONEmptyFileException();
            }
        } catch (FileNotFoundException e) {
            throw new FileDoesntExistException();
        }
        return json;
    }
    //Archivos
}