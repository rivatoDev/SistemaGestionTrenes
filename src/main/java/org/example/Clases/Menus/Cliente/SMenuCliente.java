package org.example.Clases.Menus.Cliente;

import org.example.Clases.FamiliaPersona.Maquinista;
import org.example.Clases.FamiliaPersona.Usuario;
import org.example.Clases.FamiliaRuta.GestionRuta;
import org.example.Clases.FamiliaRuta.Ruta;
import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaTren.TrenComercial;
import org.example.Clases.FamiliaTren.TrenDeCarga;
import org.example.Clases.FamiliaVagon.Cargamento;
import org.example.Clases.FamiliaVagon.VagonComercial;
import org.example.Clases.Menus.Administrador.SMenuTrenes;
import org.example.Clases.Menus.Almacenamiento;
import org.example.Clases.Menus.Menu;
import org.example.Clases.Menus.SMenuPrincipal;
import org.example.Clases.Menus.Taquillero.SMenuRutas;
import org.example.Enums.TipoUsuario;
import org.example.Excepciones.JSONObjectEliminatedException;
import org.example.Excepciones.OffLimitsException;
import org.example.Excepciones.WrongUserException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que contiene el menu para gestionar a los clientes.
 */
public class SMenuCliente {
    public SMenuCliente() {
    }

    //Alta
    /**
     * Carga un cargamento.
     * @param usuario El usuario con el que se inicio sesion.
     * @return El cargamento cargado. Si el peso o las unidades no son del tipo numerico correcto devuelve null.
     */
    public static Cargamento ingresarCargamento(Usuario usuario) {
        Scanner sc = new Scanner(System.in);
        Cargamento c = new Cargamento();
        System.out.println("----------------------------------------------------------------------------------------------------");
        c.setTitular(usuario);
        System.out.println("Nombre: ");
        c.setNombre(sc.nextLine());
        System.out.println("Tipo");
        c.setTipo(sc.nextLine());

        try {
            System.out.println("Peso: ");
            c.setPeso(sc.nextDouble());

            System.out.println("Unidades");
            c.setUnidades(sc.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Opcion invalida");
            return null;
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        return c;
    }

    /**
     * Agrega un Cargamento o Pasajero al tren..
     * @param tren El tren a usar.
     * @param usuario El usuario con el que se inicio sesion.
     * @return La ruta con el nuevo elemento
     */
    public static Tren cargar(Tren tren, Usuario usuario) {
        Scanner sc = new Scanner(System.in);

        if (tren instanceof TrenDeCarga t) {
            t.agregarCargamento(ingresarCargamento(usuario));
        } else if (tren instanceof TrenComercial t) {
            int asientos = (int) (t.pesoActual() / 80);
            System.out.println("Asientos disponibles: " + asientos);
            System.out.println("Entradas: ");
            int entradas = sc.nextInt();

            if (entradas > asientos) {
                System.out.println("No hay suficiente espacio.");
                return null;
            } else {
                for (int i = 0; i < entradas; i++) {
                    t.agregarPasajero(usuario);
                }
            }
        }
        return tren;
    }
    //Alta

    /**
     * Menu principal de los clientes.
     * @param op opcion a usar en el switch.
     * @param usuario el usuario con el que se inicio sesion
     * @param almacenamiento Almacenamiento que contiene los nombres de todos los archivos del sistema.
     */
    public static void usuarioCliente (int op, Usuario usuario, Almacenamiento almacenamiento) {
        int subOp;
        Scanner sc = new Scanner(System.in);
        GestionRuta gr = SMenuRutas.leerRutas(almacenamiento.getRutas());
        Ruta r;

        try {
            switch (op) {
                case 0:
                    break;
                case 1:
                    try {
                        System.out.println(gr);
                        System.out.println("ID de la ruta: ");
                        r = gr.verificarRuta(sc.nextLine());
                        r.setTren(cargar(r.getTren(), usuario));

                        if(r.getTren() != null) {
                            if (GestionRuta.modificarRegistro(gr.verificarRuta(r.getId()), r, almacenamiento.getRutas())) {
                                System.out.println("Se compro el pasaje con exito.");
                            }
                        }
                    } catch (JSONObjectEliminatedException e) {
                        System.out.println("El pasajero no existe");
                    } catch (WrongUserException e) {
                        System.out.println("El usuario no es el correcto");
                    }
                    break;
                case 2:
                    do {
                        System.out.println(Menu.configuracion());
                        System.out.println("Opcion: ");
                        subOp = sc.nextInt();
                        sc.nextLine();
                        SMenuPrincipal.configuracion(subOp, usuario, almacenamiento.getUsuarios());
                    } while (subOp != 0);
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("La opcion debe ser un numero entero.");
            sc.nextLine();
        }
    }
}