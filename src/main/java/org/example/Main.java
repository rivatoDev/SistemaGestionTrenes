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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        //Archivos
        final String archivo = "vagones.json";
        //Archivos

        VagonComercial vagon = new VagonComercial("123", "50", new HashMap<>());
        vagon.cargarPasajero(new Usuario("44336431", "Tomas", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE), "12345");

        VagonDeCarga vagoncito = new VagonDeCarga("12345", "55,7", 108.7, new LinkedList<>());
        vagoncito.agregarCargamento(new Cargamento(new Usuario("44336431", "Tomas", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE), "Samot", "Comida", 23.7, 3));
        Usuario usuario = new Usuario("44336431", "Tomas", "Rivara", "rivato", "tomatoli", TipoUsuario.CLIENTE);
        try {
            //GestionVagon.agregarRegistro(vagoncito, VagonDeCarga::getJSONObject, archivo);
            System.out.println(GestionVagon.getJSONArray(new JSONArray(leerArchivo(archivo)), VagonDeCarga::getJSONObject));
        } catch (JSONEmptyFileException e) {
            System.out.println("El archivo esta vacio");
        }
    }

    public static JSONTokener leerArchivo (String archivo) throws FileDoesntExistException {
        JSONTokener json;
        try {
            json = new JSONTokener(new BufferedReader(new FileReader(archivo)));
            if(!json.more()) {
                throw new JSONEmptyFileException();
            }
        } catch (FileNotFoundException e) {
            throw new FileDoesntExistException();
        }
        return json;
    }
}