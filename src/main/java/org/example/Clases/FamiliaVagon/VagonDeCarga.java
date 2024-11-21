package org.example.Clases.FamiliaVagon;

import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que hereda de Vagon y representa a los vagones que transportan cargamento.
 */
public class VagonDeCarga extends Vagon {
    //Atributos
    protected List<Cargamento> contenido;
    //Atributos

    //Constructores
    public VagonDeCarga() {
        super();
        this.contenido = new LinkedList<>();
    }

    public VagonDeCarga(String idVagon, double capacidad) {
        super(idVagon, capacidad);
        this.contenido = new LinkedList<>();
    }
    //Constructores

    //Getter
    public LinkedList<Cargamento> getContenido() {
        return (LinkedList<Cargamento>) contenido;
    }
    //Getter

    //Comparacion
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
    //Comparacion

    //Mostrar
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if(!this.contenido.isEmpty()) {
            for(Cargamento c: contenido) {
                sb.append("----------------------------------------CARGAMENTO----------------------------------------\n");
                sb.append(c.toString());
                sb.append("----------------------------------------CARGAMENTO----------------------------------------\n");
            }
        }
        return sb.toString();
    }
    //Mostrar

    //Alta
    /**
     * Carga un Cargamento en el vagon.
     * @param cargamento Cargamento a introducir.
     * @return true si se pudo agregar el Cargamento sin problema.
     * @throws OffLimitsException si el peso total supera al pesoMax del vagon.
     */
    public boolean agregarCargamento (Cargamento cargamento) throws OffLimitsException {
        if (cargamento.CalcularPeso() > (double)this.capacidad - this.calcularPesoTotal()) {
            throw new OffLimitsException();
        }
        return this.contenido.add(cargamento);
    }
    //Alta

    //Baja
    /**
     * Quita el cargamento del vagon.
     * @param cargamento el cargamento a quitar.
     * @return true si se pudo eliminar el cargamento, sino false.
     */
    public boolean quitarCargamento(Cargamento cargamento){
        return this.contenido.remove(cargamento);
    }

    /**
     * Vacia todos los cargamentos del vagon.
     * @return true si el vagon se pudo vaciar sin problema,
     * si no lanza la excepcion NullPointerExcepcion
     */
    @Override
    public boolean vaciarVagon() {
        if(this.contenido.isEmpty()) {
            throw new NullPointerException();
        } else {
            for(Cargamento c: this.contenido) {
                this.quitarCargamento(c);
            }
            return true;
        }
    }
    //Baja

    //Verificacion
    /**
     * Cuenta el peso total del vagon contando todos los cargamentos.
     * @return el peso total del vagon.
     */
    public double calcularPesoTotal(){
        double pesoTotal = 0;
        for(Cargamento c: this.contenido) {
            pesoTotal += c.CalcularPeso();
        }
        return pesoTotal;
    }
    //Verificacion

    //JSON
    /**
     * Transforma al VagonDeCarga en un JSONArray.
     * @return El VagonDeCarga como un JSONArray.
     */
    public JSONArray contenidoAJSONArray () {
        JSONArray json = new JSONArray();
        for(Cargamento c: this.contenido) {
            json.put(c.convertirAJSONObject());
        }
        return json;
    }

    /**
     * Transforma al VagonDeCarga en un JSONObject.
     * @return El VagonDeCarga como un JSONObject.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = super.convertirAJSONObject();
        json.put("contenido", this.contenidoAJSONArray());
        return json;
    }

    /**
     * Verifica que el JSON sea del tipo correcto.
     * @param json JSONObject que se quiere comprobar.
     * @return true si el JSON es del tipo correcto.
     */
    public static boolean verificarJSON (JSONObject json) {
        return json.has("idVagon") &&
               json.has("capacidad") &&
               json.has("estado") &&
               json.has("contenido");
    }

    /**
     * Transforma al JSONArray en una linkedList.
     * @param json El JSONArray que se quiere convertir
     * @return El JSONArray en forma de LinkedList
     */
    public static LinkedList<Cargamento> getJSONArray (JSONArray json) {
        LinkedList<Cargamento> ll = new LinkedList<>();
        for (int i = 0; i < json.length(); i++) {
            if(Cargamento.verificarJSON(json.getJSONObject(i))){
                ll.add(Cargamento.JSONxCargamento(json.getJSONObject(i)));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return ll;
    }

    /**
     * Pasa un JSONObject a un VagonDeCarga.
     * @param json Un cargamento con los datos del JSONObject.
     */
    public static VagonDeCarga getJSONObject (JSONObject json) {
        VagonDeCarga vc = null;
        if(VagonDeCarga.verificarJSON(json)) {
            vc = new VagonDeCarga();
            vc.setEstado(json.getBoolean("estado"));
            vc.setIdVagon(json.getString("idVagon"));
            vc.setCapacidad(json.getNumber("capacidad"));
            for(Cargamento c: getJSONArray(json.getJSONArray("contenido"))) {
                vc.agregarCargamento(c);
            }
            return vc;
        } else if (!VagonDeCarga.verificarJSON(json)){
            throw new IllegalArgumentException();
        }
        return vc;
    }
    //JSON
}