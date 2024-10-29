package org.example.Clases.FamiliaPersona;

/**
 * Clase abstracta que representa al usuario.
 */
public abstract class Usuario extends Persona {
    //Atributos
    private StringBuilder nombreUsuario;
    private StringBuilder contrasenia;
    //Atributos

    //Constructor
    public Usuario() {
        super();
        this.nombreUsuario = new StringBuilder();
        this.contrasenia = new StringBuilder();
    }

    public Usuario(String dni, String nombre, String apellido, String nombreUsuario, String contrasenia) {
        super(dni, nombre, apellido);
        this.nombreUsuario = new StringBuilder(nombreUsuario);
        this.contrasenia = new StringBuilder(contrasenia);
    }
    //Constructor

    //Getter
    public StringBuilder getNombreUsuario() {
        return nombreUsuario;
    }

    public StringBuilder getContrasenia() {
        return contrasenia;
    }
    //Getter

    //Setter
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.replace(0, this.nombreUsuario.length(), nombreUsuario);
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia.replace(0, this.contrasenia.length(), contrasenia);
    }
    //Setter

    //Mostrar

    @Override
    public String toString() {
        return super.toString() +
               "Usuario: " + this.nombreUsuario + '\n' +
               "Usuario: " + this.contrasenia + '\n' +
               "-------------------------------------------------------------------------------------------------------\n";
    //Mostrar
}
