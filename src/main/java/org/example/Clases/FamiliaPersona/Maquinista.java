package org.example.Clases.FamiliaPersona;

import org.example.Excepciones.JSONObjectEliminatedException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase que representa alos maquinistas que controlan los trenes.
 */
public final class Maquinista extends Persona{
    //Atributos
    private final StringBuilder idMaquinista;
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
    public String getIdMaquinista() {
        return idMaquinista.toString();
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
        return Objects.equals(this.idMaquinista.toString(), that.getIdMaquinista());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMaquinista);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return "--------------------------------------------------MAQUINISTA--------------------------------------------------\n" +
                super.toString() +
               "ID: " + this.idMaquinista + '\n' +
               "--------------------------------------------------MAQUINISTA--------------------------------------------------\n";
    }
    //Mostrar

    //JSON
    /**
     * Transforma al maquinista en un JSONObject.
     * @return El maquinista como un JSONObject.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json;
        try {
            json = super.convertirAJSONObject();
            json.put("idMaquinista", this.idMaquinista);
        } catch (JSONException e) {
            return null;
        }
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
                json.has("estado");
    }

    /**
     * Transforma al un JSONObject con los datos de un Maquinista.
     * @return El JSONObject como un Maquinista.
     */
    public static Maquinista JSONxMaquinista(JSONObject json) {
        try {
            if (Maquinista.verificarJSON(json)) {
                return new Maquinista(json.getString("dni"),
                        json.getString("nombre"),
                        json.getString("apellido"),
                        json.getString("idMaquinista"));
            } else if (!json.getBoolean("estado")) {
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