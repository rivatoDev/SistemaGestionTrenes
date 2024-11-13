package org.example.Clases.Menus;

import org.example.Clases.FamiliaTren.GestionTren;
import org.example.Clases.FamiliaTren.Tren;
import org.example.Clases.FamiliaVagon.GestionVagon;

import java.util.Scanner;

public class SwitchMenuTrenes <T extends Tren>{
    public SwitchMenuTrenes() {
    }
    public void trenAdministrador(int op, GestionTren gestor, T t, T trenViejo, T trenNuevo){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------MENU TRENES ADMINISTRADOR---------------------------------------------------------");
        System.out.println("Ingrese 1 para añadir tren.");
        System.out.println("Ingrese 2 para eliminar tren.");
        System.out.println("Ingrese 3 para modificar tren.");
        System.out.println("Ingrese 0 para salir.");
        System.out.println("---------------------MENU TRENES ADMINISTRADOR---------------------------------------------------------");
        op = sc.nextInt();
        sc.nextLine();
        switch (op) {

            case 0:
                System.out.println("--------------------------------------------------FIN--------------------------------------------------");
                break;
            case 1:
                Boolean proceso = false;
                proceso = gestor.agregarTren(T t);
                if (proceso == true){
                    System.out.println("Tren agregado correctamente");
                }
                else {
                    System.out.println("Error al agregar tren");
                }
                break;
            case 2:
                proceso = false;
                proceso = gestor.eliminarTren(T t);
                if (proceso == true){
                    System.out.println("Tren eliminado correctamente");
                }
                else {
                    System.out.println("Error al eliminar tren");
                }
                break;
            case 3:
                proceso = false;
                proceso = gestor.modificarTren(T trenViejo,T trenNuevo);
                if (proceso == true){
                    System.out.println("Tren modificado correctamente");
                }
                else {
                    System.out.println("Error al modificar tren");
                }
                break;
            default:
                System.out.println("Opcion no valida");
                break;

        }
    }
}