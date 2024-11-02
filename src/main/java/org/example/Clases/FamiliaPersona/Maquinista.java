package org.example.Clases.FamiliaPersona;

import org.example.Excepciones.JSONObjectEliminatedException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase que representa alos maquinistas que controlan los trenes.
 */
public final class Maquinista extends Persona{
    //Atributos
    private StringBuilder idMaquinista;
    //Atributos

    //Constructor
    public Maquinista() {
        super();
        this.idMaquinista = new StringBuilder();
    }

    public Maquinista(String dni, String nombre, String apellido, String idMaquinista) {
        super(dni, nombre, apellido);
        this.idMaquinista = new StringBuilder(idMaquinista);
    }
    //Constructor

    //Getter
    public StringBuilder getIdMaquinista() {
        return idMaquinista;
    }
    //Getter

    //Setter
    public void setIdMaquinista(String idMaquinista) {
        this.idMaquinista.replace(0, this.idMaquinista.length(), idMaquinista);
    }
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Maquinista that = (Maquinista) o;
        return super.equals(o) &&
               Objects.equals(idMaquinista.toString(), that.idMaquinista.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idMaquinista);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return super.toString() +
               "ID: " + this.idMaquinista + '\n' +
               "-------------------------------------------------------------------------------------------------------\n";
    }
    //Mostrar

    //JSON
    /**
     * Transforma al maquinista en un JSONObject.
     * @return El maquinista como un JSONObject.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = super.convertirAJSONObject();
        json.put("idMaquinista", this.idMaquinista);
        return json;
    }

    /**
     * Verifica que el JSON pasado por parametro sea del tipo correcto.
     * @param json JSONObject a comprobar.
     * @return true si el JSON contiene los datos de un Maquinista y false si es al contrario.
     */
    public static boolean verificarJSON (JSONObject json) {
        return json.has("dni") &&
                json.has("nombre") &&
                json.has("apellido") &&
                json.has("idMaquinista") &&
                json.has("contrasenia") &&
                json.has("tipoUsuario") &&
                json.has("estado");
    }

    /**
     * Transforma al un JSONObject con los datos de un Maquinista.
     * @return El JSONObject como un Maquinista.
     */
    public static Maquinista JSONxMaquinista(JSONObject json) throws JSONObjectEliminatedException {
        if (json.getBoolean("estado") && Usuario.verificarJSON(json)) {
            return new Maquinista(json.getString("dni"),
                    json.getString("nombre"),
                    json.getString("apellido"),
                    json.getString("idMaquinista"));
        } else if (!json.getBoolean("estado")) {
            throw new JSONObjectEliminatedException("El maquinista esta eliminado.");
        } else {
            throw new IllegalArgumentException("El registro seleccionado no es del tipo especificado.");
        }
    }
    //JSON
}