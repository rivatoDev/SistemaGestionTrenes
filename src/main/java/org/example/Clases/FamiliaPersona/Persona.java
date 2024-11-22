package org.example.Clases.FamiliaPersona;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase abstracta padre de {@link Usuario} y Maquinista.
 */
public abstract class Persona{
    //Atributos
    protected StringBuilder dni;
    protected StringBuilder nombre;
    protected StringBuilder apellido;
    protected boolean estado;
    //Atributos

    //Constructores
    public Persona() {
        this.dni = new StringBuilder();
        this.nombre = new StringBuilder();
        this.apellido = new StringBuilder();
        this.estado = true;
    }

    public Persona(String dni, String nombre, String apellido) {
        this.dni = new StringBuilder(dni);
        this.nombre = new StringBuilder(nombre);
        this.apellido = new StringBuilder(apellido);
        this.estado = true;
    }
    //Constructores

    //Getter
    public String getDni() {
        return dni.toString();
    }

    public String getNombre() {
        return nombre.toString();
    }

    public String getApellido() {
        return apellido.toString();
    }

    public boolean isEstado() {
        return estado;
    }
    //Getter

    //Setter
    public void setDni(String dni) {
        this.dni.replace(0, this.dni.length(), dni);
    }

    public void setNombre(String nombre) {
        this.nombre.replace(0, this.nombre.length(), nombre);
    }

    public void setApellido(String apellido) {
        this.apellido.replace(0, this.apellido.length(), apellido);
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    //Setter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return estado && persona.estado &&
               Objects.equals(dni.toString(), persona.dni.toString()) &&
               Objects.equals(nombre.toString(), persona.nombre.toString()) &&
               Objects.equals(apellido.toString(), persona.apellido.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, apellido, estado);
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        return
                "DNI: " + this.dni + '\n' +
                "Nombre: " + this.nombre + '\n' +
                "Apellido: " + this.apellido + '\n';
    }
    //Mostrar

    //JSON
    /**
     * Convierte a la persona en un JSONOBJECT.
     * @return el JSONObject con los datos de las personas.
     */
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("dni", this.dni);
            json.put("nombre", this.nombre);
            json.put("apellido", this.apellido);
            json.put("estado", this.estado);
        } catch (JSONException e) {
            return null;
        }
        return json;
    }
    //JSON
}