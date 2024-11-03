package org.example;


import org.example.Excepciones.FileDoesntExistException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystemException;

public class Main {
    public static void main(String[] args) {
        
    }

    public static JSONTokener leerArchivo (String archivo) throws FileDoesntExistException {
        JSONTokener json;
        try {
            json = new JSONTokener(new BufferedReader(new FileReader(archivo)));
        } catch (FileNotFoundException e) {
            throw new FileDoesntExistException();
        }
        return json;
    }
}