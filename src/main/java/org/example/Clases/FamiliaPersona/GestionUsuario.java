package org.example.Clases.FamiliaPersona;


import org.example.Main;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Clase que gestiona a los usuarios.
 */
public class GestionUsuario {
    //Atributos
    private Set<Usuario> usuarios;
    //Atributos

    //Constructor
    public GestionUsuario() {
        this.usuarios = new HashSet<>();
    }

    public GestionUsuario(HashSet<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    //Constructor

    //Getter
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }
    //Getter

    //Setter
    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    //Setter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("-------------------------------------------------------------------------------------------------------------------\n");
        for(Usuario u: this.usuarios) {
            if(u.isEstado()) {
                sb.append(u);
            }
        }
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    //Alta
    public boolean agregarUsuario(Usuario usuario) {
        return this.usuarios.add(usuario);
    }
    //Alta

    //Baja
    public boolean eliminarUsuario (Usuario usuario) {
        if(!this.usuarios.remove(usuario) || !usuario.isEstado()) {
            throw new NoSuchElementException();
        } else {
            usuario.setEstado();
            this.agregarUsuario(usuario);
        }
        return true;
    }
    //Baja

    //Modificacion
    public boolean modificarUsuario (Usuario usuarioViejo, Usuario usuarioNuevo) {
        if(!this.usuarios.remove(usuarioViejo) || !usuarioViejo.isEstado()) {
            throw new NoSuchElementException();
        } else {
            return this.usuarios.add(usuarioNuevo);
        }
    }
    //Modificacion

    //JSON
    public JSONArray convertirAJSONArray () {
        JSONArray json = new JSONArray();
        for(Usuario u: this.usuarios) {
            json.put(u.convertirAJSONObject());
        }
        return json;
    }

    public static HashSet<Usuario> getJSONArray(JSONArray json) {
        HashSet<Usuario> hs = new HashSet<>();
            for(int i = 0; i < json.length(); i++) {
                hs.add(Usuario.JSONxUsuario(json.getJSONObject(i)));
        }
            return hs;
    }
    //JSON

    //Archivos
    public static boolean agregarRegistro (Usuario usuario, String archivo) {
        GestionUsuario gu = new GestionUsuario(GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo))));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            if(gu.agregarUsuario(usuario)) {
                bf.write(gu.convertirAJSONArray().toString(2));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean eliminarRegistro (Usuario usuario, String archivo) {
        GestionUsuario gu = new GestionUsuario(GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo))));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            if (gu.eliminarUsuario(usuario)) {
                bf.write(gu.convertirAJSONArray().toString(2));
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean modificarRegistro (Usuario usuarioViejo, Usuario usuarioNuevo, String archivo) {
        GestionUsuario gu = new GestionUsuario(GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo))));
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            if (gu.modificarUsuario(usuarioViejo, usuarioNuevo)) {
                bf.write(gu.convertirAJSONArray().toString());
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos
}