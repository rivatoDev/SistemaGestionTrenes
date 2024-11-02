package org.example.Clases.FamiliaVagon;

import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Interfaces.GestionCarga;
import org.example.Interfaces.JSON;
import org.json.JSONObject;

import java.util.Objects;

/**
 * Clase que representa a los distintos tipos de cargamento que va a tener un vagon.
 */
public class Cargamento implements JSON, GestionCarga {
    //Atributos
    private Usuario titular;
    private final StringBuilder nombre;
    private final StringBuilder tipo;
    private double peso;
    private int unidades;

    //Constructores
    public Cargamento() {
        this.titular = new Usuario();
        this.nombre = new StringBuilder();
        this.tipo = new StringBuilder();
        this.peso = 0;
        this.unidades = 0;
    }

    public Cargamento(Usuario titular, String nombre, String tipo, double peso, int unidades) {
        this.titular = new Usuario(titular.getDni(),
                                   titular.getNombre(),
                                   titular.getApellido(),
                                   titular.getNombreUsuario(),
                                   titular.getContrasenia(),
                                   titular.getTipoUsuario());
        this.nombre = new StringBuilder(nombre);
        this.tipo = new StringBuilder(tipo);
        this.peso = peso;
        this.unidades = unidades;
    }
    //Constructores

    //Getter
    public Usuario getTitular() {
        return titular;
    }

    public String getNombre() {
        return nombre.toString();
    }

    public String getTipo() {
        return tipo.toString();
    }

    public double getPeso() {
        return peso;
    }

    public int getUnidades() {
        return unidades;
    }
    //Getter

    //Setter
    public void setTitular(Usuario titular) {
        this.titular = titular;
    }

    public void setNombre(String nombre) {
        this.nombre.replace(0, this.nombre.length(), nombre);
    }

    public void setTipo(String tipo) {
        this.tipo.replace(0, this.tipo.length(), tipo);
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    //Setter

    //Comparar
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargamento that = (Cargamento) o;
        return Double.compare(peso, that.peso) == 0 &&
                              unidades == that.unidades &&
                              Objects.equals(titular, that.titular) &&
                              Objects.equals(nombre.toString(), that.nombre.toString()) &&
                              Objects.equals(tipo.toString(), that.tipo.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(titular, nombre, tipo, peso, unidades);
    }
    //Comparar

    //Mostrar
    @Override
    public String toString() {
        return "----------------------------------------------------------------------------------------------------\n" +
               "Titular: " + this.titular.toString() +
               "Nombre: " + this.nombre + '\n' +
               "Tipo: " + this.tipo + '\n' +
               "Peso(kg): " + this.peso + '\n' +
               "Unidades: " + this.nombre + '\n' +
               "----------------------------------------------------------------------------------------------------\n";
    }

    //JSON
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = new JSONObject();
        json.put("titular", this.titular.convertirAJSONObject());
        json.put("nombre", this.nombre);
        json.put("tipo", this.tipo);
        json.put("peso", this.peso);
        json.put("unidades", this.unidades);
        return json;
    }

    public static boolean verificarJSON (JSONObject json) {
        return json.has("titular") &&
                json.has("nombre") &&
                json.has("tipo") &&
                json.has("peso") &&
                json.has("unidades");
    }

    public static Cargamento JSONxCargamento (JSONObject json){
        if(Cargamento.verificarJSON(json)) {
            return new Cargamento((Usuario)json.get("titular"),
                                           json.getString("nombre"),
                                           json.getString("tipo"),
                                           json.getDouble("peso"),
                                           json.getInt("unidades"));
        } else {
            throw new IllegalArgumentException();
        }
    }
    //JSON

    //Verificacion
    /**
     * Calcula el peso total del cargamento
     * @return El peso total de cargamento.
     */
    @Override
    public double CalcularPeso() {
        return this.peso * this.unidades;
    }
    //Verificacion
}