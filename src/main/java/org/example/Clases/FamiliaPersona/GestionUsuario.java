package org.example.Clases.FamiliaPersona;


import org.example.Excepciones.ElementAlreadyExistsException;
import org.example.Excepciones.FileDoesntExistException;
import org.example.Main;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
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
    private final Set<Usuario> usuarios;
    //Atributos

    //Constructor
    public GestionUsuario() {
        this.usuarios = new HashSet<>();
    }
    //Constructor

    //Getter
    public Set<Usuario> getUsuarios() {
        return usuarios;
    }
    //Getter

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        for(Usuario u: this.usuarios) {
            if(u.isEstado()) {
                sb.append("--------------------------------------------------USUARIO--------------------------------------------------\n");
                sb.append(u);
                sb.append("--------------------------------------------------USUARIO--------------------------------------------------\n");
            }
        }
        sb.append("-------------------------------------------------------------------------------------------------------------------\n");
        return sb.toString();
    }
    //Mostrar

    //Alta
    /**
     * Carga un Usuario.
     * @param usuario Usuario a agregar.
     * @return true si se pudo agregar sin problema al Usuario.
     * @throws ElementAlreadyExistsException si el usuario ya existe.
     */
    public boolean agregarUsuario(Usuario usuario) {
        if(!this.usuarios.add(usuario)) {
            throw new ElementAlreadyExistsException();
        }
        return true;
    }
    //Alta

    //Baja
    /**
     * Elimina un Usuario.
     * @param usuario Usuario a eliminar.
     * @return true si se pudo eliminar sin problema al Usuario.
     * @throws NoSuchElementException si el usuario no existe.
     */
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
    /**
     * Modifica un Usuario.
     * @param usuarioViejo Usuario a modificar.
     * @param usuarioNuevo Usuario a modificado.
     * @return true si se pudo modificar sin problema al Usuario.
     * @throws NoSuchElementException si el usuario no existe.
     */
    public boolean modificarUsuario (Usuario usuarioViejo, Usuario usuarioNuevo) {
        if(!this.usuarios.remove(usuarioViejo) || !usuarioViejo.isEstado()) {
            throw new NoSuchElementException();
        }
         return this.agregarUsuario(usuarioNuevo);
    }
    //Modificacion

    //JSON
    /**
     * Convierte al Usuario en un JSONArray.
     * @return el JSONArray con los datos del Usuario.
     */
    public JSONArray convertirAJSONArray () {
        JSONArray json = new JSONArray();
        for(Usuario u: this.usuarios) {
            json.put(u.convertirAJSONObject());
        }
        return json;
    }

    /**
     * Convierte a un JSONArray en un Usuario.
     * @param json El JSONArray a convertir.
     * @return Un HashSet con los datos del JSONArray.
     */
    public static HashSet<Usuario> getJSONArray(JSONArray json) {
        HashSet<Usuario> hs = new HashSet<>();
        for(int i = 0; i < json.length(); i++) {
            if(json.getJSONObject(i).getBoolean("estado")) {
                hs.add(Usuario.JSONxUsuario(json.getJSONObject(i)));
            }
        }
        return hs;
    }
    //JSON

    //Archivos

    /**
     * Carga un Usuario en un archivo.
     * Si el archivo no existe lo crea.
     * @param usuario El Usuario a crear.
     * @param archivo El nombre del archivo.
     * @return true si se pudo guardar el Usuario sin problemas, sino false.
     */
    public static boolean agregarRegistro (Usuario usuario, String archivo) {
        GestionUsuario gu = new GestionUsuario();
        try {
            if (new File(archivo).length() > 0) {
                for (Usuario u: GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))) {
                    gu.agregarUsuario(u);
                }
            }
        } catch (FileDoesntExistException e) {
            Main.crearArchivo(archivo);
        }
        gu.agregarUsuario(usuario);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gu.convertirAJSONArray().toString(2));
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Elimina un Usuario en un archivo.
     * @param usuario El Usuario a eliminar.
     * @param archivo El nombre del archivo.
     * @return true si se pudo eliminar el Usuario sin problemas, sino false.
     */
    public static boolean eliminarRegistro (Usuario usuario, String archivo) {
        GestionUsuario gu = new GestionUsuario();

        for (Usuario u: GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))) {
            gu.agregarUsuario(u);
        }
        gu.eliminarUsuario(usuario);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gu.convertirAJSONArray().toString(2));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Modifica un Usuario en un archivo.
     * @param usuarioViejo El Usuario a modificar.
     * @param archivo El nombre del archivo.
     * @return true si se pudo modificar el Usuario sin problemas, sino false.
     */
    public static boolean modificarRegistro (Usuario usuarioViejo, Usuario usuarioNuevo, String archivo) {
        GestionUsuario gu = new GestionUsuario();

        for (Usuario u: GestionUsuario.getJSONArray(new JSONArray(Main.leerArchivo(archivo)))) {
            gu.agregarUsuario(u);
        }
        gu.modificarUsuario(usuarioViejo, usuarioNuevo);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(archivo))) {
            bf.write(gu.convertirAJSONArray().toString());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //Archivos

    public Usuario verificarUsuario(String nombreDeUsuario, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreDeUsuario) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }
}