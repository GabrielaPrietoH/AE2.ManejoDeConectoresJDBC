package app.vista;

import java.util.Scanner;

import app.controlador.GestorOperaciones;

public class MenuPrincipal {
    private CocheUI cocheUI;
    private PasajeroUI pasajeroUI;
    private GestorOperaciones gestor;

    public MenuPrincipal(GestorOperaciones gestor) {
        this.gestor = gestor;
        this.cocheUI = new CocheUI(gestor);
        this.pasajeroUI = new PasajeroUI(gestor);
    }

    public void mostrarMenuPrincipal() {
        Scanner leer = new Scanner(System.in);

        String eleccion = "";
        boolean continuar = true;

        do {
            System.out.println("\nElige una opción del menú numérico: \n");
            System.out.println("1. Gestionar coches.");
            System.out.println("2. Gestionar pasajeros.");
            System.out.println("3. Terminar el programa.");

            eleccion = leer.nextLine();

            try {
                int choice = Integer.parseInt(eleccion);

                switch (choice) {
                    case 1:
                        cocheUI.mostrarMenuCoches();
                        break;
                    case 2:
                        pasajeroUI.mostrarMenuPasajeros();
                        break;
                    case 3:
                        System.out.println("Has elegido terminar el programa.");
                        continuar = false;
                        gestor.cerrarConexion();
                        break;
                    default:
                        System.out.println("Opción no válida. Introduce un número del 1 al 3.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("La elección no es un número válido. Vuelve a intentarlo.");
            }

        } while (continuar);

        leer.close();
    }
}