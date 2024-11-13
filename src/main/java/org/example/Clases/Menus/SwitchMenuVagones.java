package org.example;

import org.example.Clases.FamiliaVagon.GestionVagon;
import org.example.Clases.FamiliaVagon.Vagon;

import java.util.Scanner;

public class SwitchMenuVagones <T extends Vagon>{
    public SwitchMenuVagones() {
    }
    public void vagonAdministrador(int op, GestionVagon gestor, T t, T vagonViejo, T vagonNuevo){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------MENU VAGONES ADMINISTRADOR---------------------------------------------------------");
        System.out.println("Ingrese 1 para añadir vagon.");
        System.out.println("Ingrese 2 para eliminar vagon.");
        System.out.println("Ingrese 3 para modificar vagon.");
        System.out.println("Ingrese 0 para salir.");
        System.out.println("---------------------MENU VAGONES ADMINISTRADOR---------------------------------------------------------");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {

            case 0:
                System.out.println("--------------------------------------------------FIN--------------------------------------------------");
                break;
            case 1:
                Boolean proceso = false;
                proceso = gestor.agregarVagon(T t);
                if (proceso == true){
                    System.out.println("Vagon agregado correctamente");
                }
                else {
                    System.out.println("Error al agregar vagon");
                }
                break;
            case 2:
                Boolean proceso = false;
                proceso = gestor.eliminarVagon(T t);
                if (proceso == true){
                    System.out.println("Vagon eliminado correctamente");
                }
                else {
                    System.out.println("Error al eliminar vagon");
                }
                break;
            case 3:
                Boolean proceso = false;
                proceso = gestor.modificarVagon(T vagonViejo,T vagonNuevo);
                if (proceso == true){
                    System.out.println("Vagon modificado correctamente");
                }
                else {
                    System.out.println("Error al modificar vagon");
                }
                break;
            default:
                System.out.println("Opcion no valida");
                break;

        }
    }
}