package org.example.Clases.FamiliaVagon;

import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
import org.example.Excepciones.WrongUserException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Clase que representa a los vagones que transportan personas.
 */
public class VagonComercial extends Vagon{
    //Atributos
    private final Map<String, Usuario> pasajeros;
    //Atributos

    //Constructor
    public VagonComercial() {
        super();
        this.pasajeros = new HashMap<>();
    }

    public VagonComercial(String idVagon, int capacidad) {
        super(idVagon, capacidad);
        this.pasajeros = new HashMap<>();
    }
    //Constructor

    //Getter
    public Map<String, Usuario> getPasajeros() {
        return pasajeros;
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
        if(!this.pasajeros.isEmpty()) {
            for(Map.Entry<String, Usuario> pasajero: pasajeros.entrySet()) {
                sb.append("----------------------------------------PASAJERO----------------------------------------\n");
                sb.append("Ticket: "). append(pasajero.getKey()).append('\n');
                sb.append(pasajero.getValue().toString());
                sb.append("----------------------------------------PASAJERO----------------------------------------\n");
            }
        }
        return sb.toString();
    }
    //Mostrar

    //Alta
    /**
     * Carga un pasajero al Vagoncomercial.
     * @param pasajero Usuario que debe ser del tipo CLIENTE a agregar.
     * @param idTicket El identidicador de la entrada del viaje.
     * @return true si se pudo agregar sin problemas el pasajero al vagon.
     * @throws JSONObjectEliminatedException si el pasajero que se envio esta eliminado.
     * @throws WrongUserException si el tipo de usuario es el incorrecto.
     */
    public boolean cargarPasajero (Usuario pasajero, String idTicket) throws JSONObjectEliminatedException {
        if (pasajero.isEstado() && pasajero.getTipoUsuario() == TipoUsuario.CLIENTE && this.contarAsientos() <= Integer.parseInt(this.capacidad.toString())) {
            this.pasajeros.put(idTicket, pasajero);
           return true;
        } else if(!pasajero.isEstado()){
            throw new JSONObjectEliminatedException();
        } else if (pasajero.getTipoUsuario() != TipoUsuario.CLIENTE){
            throw new WrongUserException();
        } else {
            throw new OffLimitsException();
        }
    }
    //Alta

    //Baja
    /**
     * Elimina al usuario del viaje.
     * @param idTicket el identificador de la entrada del viaje.
     * @return true si se pudo remover al pasajero sin ningun problema
     * @throws NoSuchElementException si no se encontro al pasajero en el vagon.
     */
    public boolean cancelarViaje(String idTicket) {
        if(pasajeros.containsKey(idTicket)) {
           pasajeros.remove(idTicket);
           return true;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Vacia todos los pasajeros del vagon.
     * @return true si el vagon se pudo vaciar sin problema,
     * si no lanza la excepcion NullPointerExcepcion
     */
    @Override
    public boolean vaciarVagon() {
        if(this.pasajeros.isEmpty()) {
            throw new NullPointerException();
        } else {
            for(Map.Entry<String, Usuario> usuario: this.pasajeros.entrySet()) {
                this.pasajeros.remove(usuario.getKey());
            }
            return true;
        }
    }
    //Baja

    //Verificacion

    /**
     * Cuenta los asientos que estan ocupados.
     * @return La cantidad de asientos ocupados.
     */
    public int contarAsientos () {
        int i = 0;
        for (Map.Entry<String, Usuario> usuario: this.pasajeros.entrySet()) {
            i++;
        }
        return i;
    }
    //Verificacion

    //JSON
    /**
     * Convierte al pasajero en un JSONObject agregando su identificador del viaje.
     * @param key el id de la entrada del viaje.
     * @return el pasajero convertido en un JSONObject junto con su id de la entrada.
     */
    public JSONObject convertirPasajeroAJSONObject (String key) {
        JSONObject json = this.pasajeros.get(key).convertirAJSONObject();
        json.put("idEntrada", key);
        return json;
    }

    /**
     * Convierte al map de pasajeros en un JSONArray.
     * @return Los pasajeros como un JSONArray.
     */
    public JSONArray convertirAJSONArray() {
        JSONArray json = new JSONArray();
        for(Map.Entry<String, Usuario> usuario: this.pasajeros.entrySet()) {
            json.put(this.convertirPasajeroAJSONObject(usuario.getValue().toString()));
        }
        return json;
    }

    /**
     * Convierte al VagonComercial en un JSONObject.
     * @return el VagonComercial como un JSONObject.
     */
    @Override
    public JSONObject convertirAJSONObject() {
        JSONObject json = super.convertirAJSONObject();
        json.put("pasajeros", convertirAJSONArray());
        return json;
    }

    /**
     * Verifica que el JSON sea del tipo correcto.
     * @param json JSONObject a verificar.
     * @return true si es del tipo correcto.
     */
    public static boolean verificarJSON (JSONObject json) {
        return json.has("idVagon") &&
               json.has("capacidad") &&
               json.has("estado") &&
               json.has("pasajeros");
    }

    /**
     * Convierte un JSONArray en un map.
     * @param json el JSONArray a convertir
     * @return Un Hashmap con los datos del JSONArray.
     */
    public static HashMap<String, Usuario> getJSONArray (JSONArray json) {
        HashMap<String, Usuario> pasajeros = new HashMap<>();
        for(int i = 0; i < json.length(); i++) {
            if(verificarJSON(json.getJSONObject(i))) {
                pasajeros.put(json.getJSONObject(i).getString("idEntrada"),
                              Usuario.JSONxUsuario(json.getJSONObject(i)));
            } else {
                throw new IllegalArgumentException();
            }
        }
        return pasajeros;
    }

    public static VagonComercial getJSONObject (JSONObject json) throws JSONObjectEliminatedException {
        if(VagonComercial.verificarJSON(json) && json.getBoolean("estado")) {
            VagonComercial vg = new VagonComercial();
            vg.setIdVagon(json.getString("idVagon"));
            vg.setCapacidad(json.getNumber("capacidad"));
            for(Map.Entry<String, Usuario> pasajero: getJSONArray(json.getJSONArray("pasajeros")).entrySet()) {
                vg.cargarPasajero(pasajero.getValue(), pasajero.getKey());
            }
            return vg;
        } else if (!VagonComercial.verificarJSON(json)) {
            throw new IllegalArgumentException();
        } else {
            throw new JSONObjectEliminatedException();
        }
    }
    //JSON
}