package org.example;

public class Persona {
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
    public StringBuilder getDni() {
        return dni;
    }

    public StringBuilder getNombre() {
        return nombre;
    }

    public StringBuilder getApellido() {
        return apellido;
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
        this.nombre.replace(0, this.dni.length(), nombre);
    }

    public void setApellido(String apellido) {
        this.apellido.replace(0, this.dni.length(), apellido);
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    //Setter

    //Mostrar
    @Override
    public String toString() {
        return
                "-------------------------------------------------------------------------------------------------------\n" +
                "DNI: " + this.dni + '\n' +
                "DNI: " + this.nombre + '\n' +
                "DNI: " + this.apellido + '\n';
    }
    //Mostrar
}
