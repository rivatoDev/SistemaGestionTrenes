package org.example.Clases.FamiliaPersona;

import org.example.Enums.TipoUsuario;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase que representa a todos los usuarios que existen en l sistema, tanto de los administradores como de los clientes.
 */
public final class Usuario extends Persona {
    //Atributos
    private final StringBuilder nombreUsuario;
    private final StringBuilder contrasenia;
    private TipoUsuario tipoUsuario;
    //Atributos

    //Constructores
    public Usuario() {
        super();
        this.nombreUsuario = new StringBuilder();
        this.contrasenia = new StringBuilder();
    }

    public Usuario(String dni, String nombre, String apellido, String nombreUsuario, String contrasenia, TipoUsuario tipoUsuario) {
        super(dni, nombre, apellido);
        this.nombreUsuario = new StringBuilder(nombreUsuario);
        this.contrasenia = new StringBuilder(contrasenia);
        this.tipoUsuario = tipoUsuario;
    }
    //Constructores

    //Getter
    public String getNombreUsuario() {return this.nombreUsuario.toString();}

    public String getContrasenia() {return this.contrasenia.toString();}

    public TipoUsuario getTipoUsuario() {return this.tipoUsuario;}
    //Getter

    //Setter
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.replace(0, this.nombreUsuario.length(), nombreUsuario);
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia.replace(0, this.contrasenia.length(), contrasenia);
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombreUsuario.toString(), usuario.nombreUsuario.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario.toString());
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return  "Nombre de Usuario: " + this.nombreUsuario + '\n' +
                "Tipo de Usuario: " + this.tipoUsuario.toString() + '\n' +
                super.toString();
    }
    //Mostrar

    //JSON
    /**
     * Transforma al usuario en un JSONObject.
     * @return El usuario como un JSONObject.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = super.convertirAJSONObject();
        try {
            json.put("nombreUsuario", this.nombreUsuario);
            json.put("contrasenia", this.contrasenia);
            json.put("tipoUsuario", this.tipoUsuario);
        } catch (JSONException e) {
            return null;
        }
        return json;
    }

    /**
     * Verifica que el JSON pasado por parametro sea del tipo correcto.
     * @param json JSONObject a comprobar.
     * @return true si el JSON contiene los datos de un usuario y false si es al contrario.
     */
    public static boolean verificarJSON (JSONObject json) {
        try {
            return json.has("dni") &&
                    json.has("nombre") &&
                    json.has("apellido") &&
                    json.has("nombreUsuario") &&
                    json.has("contrasenia") &&
                    json.has("tipoUsuario") &&
                    json.has("estado");
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * Transforma al un JSONObject con los datos de un usuario.
     * @return El JSONObject como un usuario.
     */
    public static Usuario JSONxUsuario (JSONObject json) throws JSONObjectEliminatedException {
        try {
            if(json.getBoolean("estado") && Usuario.verificarJSON(json)) {
                return new Usuario(json.getString("dni"),
                        json.getString("nombre"),
                        json.getString("apellido"),
                        json.getString("nombreUsuario"),
                        json.getString("contrasenia"),
                        TipoUsuario.valueOf(json.getString("tipoUsuario")));
            } else if (!json.getBoolean("estado")){
                throw new JSONObjectEliminatedException();
            } else {
                throw new IllegalArgumentException();
            }
        } catch (JSONException e) {
            return null;
        }

    }
    //JSON
}