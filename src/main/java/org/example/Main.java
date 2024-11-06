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
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //Archivos
        final String archivo = "vagones.json";
        //Archivos

        VagonComercial vagon = new VagonComercial("123", "50", new HashMap<>());
        vagon.cargarPasajero(new Usuario("44336431", "Tomas", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE), "12345");

        VagonDeCarga vagoncito = new VagonDeCarga("12345678", "57,7", 109.7, new LinkedList<>());
        vagoncito.agregarCargamento(new Cargamento(new Usuario("44336478", "Samot", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE), "Samot", "Alimento", 23.7, 3));
        Usuario usuario = new Usuario("44336430", "Tomas", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE);
        try {
            GestionVagon.agregarRegistro(vagoncito, VagonDeCarga::getJSONObject, archivo);
            System.out.println(GestionVagon.getJSONArray(new JSONArray(leerArchivo(archivo)), VagonDeCarga::getJSONObject));
        } catch (JSONEmptyFileException e) {
            System.out.println("El archivo esta vacio");
        }
    }

    //Archivos
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

    public static JSONTokener leerArchivo (String archivo) throws FileDoesntExistException, JSONEmptyFileException {
        JSONTokener json;
        try {
            json = new JSONTokener(new BufferedReader(new FileReader(archivo)));
            System.out.println(new File(archivo));
            if (new File(archivo).length() == 0) {
                System.out.println("Hola");
                throw new JSONEmptyFileException();
            }
        } catch (FileNotFoundException e) {
            throw new FileDoesntExistException();
        }
        return json;
    }
    //Archivos
}