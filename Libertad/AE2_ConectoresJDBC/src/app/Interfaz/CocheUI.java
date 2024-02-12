package app.interfaz;

import java.util.List;
import java.util.Scanner;
import controlador.Controlador;
import modelo.entidad.CocheEntity;

public class CocheUI {

    private static final Controlador controlador = new Controlador();
    private static final Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        String eleccion;
        boolean continuar = true;

        do {
            System.out.println("\n Elige una opción del menú numérico: \n");
            System.out.println("1. Añadir nuevo coche.");
            System.out.println("2. Borrar coche por id.");
            System.out.println("3. Consulta coche por id.");
            System.out.println("4. Modificar coche por id.");
            System.out.println("5. Listado de todos los coches.");
            System.out.println("6. Gestión de pasajeros");
            System.out.println("7. Terminar el programa.");

            eleccion = leer.nextLine();

            try {
                int choice = Integer.parseInt(eleccion);

                switch (choice) {
                    case 1:
                        System.out.println("Has elegido añadir un nuevo coche.");
                        CocheEntity cocheNuevo = obtenerDatosCoche();
                        CocheEntity cocheAñadido = controlador.addCoche(cocheNuevo);
                        System.out.println("Has añadido el coche: " + cocheAñadido);
                        break;

                    case 2:
                        System.out.println("Has elegido borrar un coche por su id.");
                        borrarCoche();
                        break;

                    case 3:
                        System.out.println("Has elegido consultar un coche por id.");
                        consultarCochePorId();
                        break;

                    case 4:
                        System.out.println("Has elegido modificar un coche por su ID.");
                        modificarCoche();
                        break;

                    case 5:
                        System.out.println("Has elegido recibir un listado de todos los coches.");
                        listarTodosLosCoches();
                        break;

                    case 6:
                        gestionarPasajeros();
                        break;

                    case 7:
                        System.out.println("Has elegido terminar el programa.");
                        continuar = false;
                        controlador.cerrarConexionCar();
                        break;

                    default:
                        System.out.println("Opción no válida, vuelve a intentarlo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("La elección no es un número válido, vuelve a intentarlo.");
            }

        } while (continuar);

        leer.close();
    }

    private static CocheEntity obtenerDatosCoche() {
        CocheEntity coche = new CocheEntity();

        System.out.println("Introduce la marca del coche: ");
        coche.setMarca(leer.nextLine().trim());

        System.out.println("Introduce el modelo del coche: ");
        coche.setModelo(leer.nextLine().trim());

        System.out.println("Introduce un año de fabricación para el coche: ");
        coche.setAñoFabricacion(leer.nextInt());
        leer.nextLine(); // Consumir salto de línea

        System.out.println("Introduce los kilómetros del coche: ");
        coche.setKm(leer.nextDouble());
        leer.nextLine(); // Consumir salto de línea

        return coche;
    }

    private static void borrarCoche() {
        System.out.println("Elige qué coche quieres borrar mediante su ID. Aquí tienes el listado: ");
        System.out.println(controlador.getListaCoches());
        int idElegido = leer.nextInt();
        leer.nextLine(); // Consumir salto de línea

        System.out.println("Has borrado el coche: " + controlador.getCoche(idElegido));
        controlador.deleteCoche(idElegido);
    }

    private static void consultarCochePorId() {
        System.out.println("Elige qué coche consultar mediante su ID. Aquí tienes el listado: ");
        System.out.println(controlador.getListaCoches());
        int idElegido = leer.nextInt();
        leer.nextLine(); // Consumir salto de línea

        CocheEntity coche = controlador.getCoche(idElegido);

        if (coche != null) {
            System.out.println("El coche con id " + idElegido + " es: " + coche);
        } else {
            System.out.println("No se encontró el coche con ID: " + idElegido);
        }
    }

    private static void modificarCoche() {
        System.out.println("Elige qué coche modificar mediante su ID. Aquí tienes el listado: ");
        System.out.println(controlador.getListaCoches());
        int idElegido = leer.nextInt();
        leer.nextLine(); // Consumir salto de línea

        CocheEntity coche = controlador.getCoche(idElegido);

        if (coche != null) {
            System.out.println("Introduce la marca del coche: ");
            coche.setMarca(leer.nextLine().trim());

            System.out.println("Introduce el modelo del coche: ");
            coche.setModelo(leer.nextLine().trim());

            System.out.println("Introduce un año de fabricación para el coche: ");
            coche.setAñoFabricacion(leer.nextInt());
            leer.nextLine(); // Consumir salto de línea

            System.out.println("Introduce los kilómetros del coche: ");
            coche.setKm(leer.nextDouble());
            leer.nextLine(); // Consumir salto de línea

            controlador.updateCoche(coche);
            System.out.println("Coche modificado exitosamente.");
        } else {
            System.out.println("No se encontró el coche con ID: " + idElegido);
        }
    }

    private static void listarTodosLosCoches() {
        List<CocheEntity> coches = controlador.getListaCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches disponibles.");
        } else {
            for (CocheEntity coche : coches) {
                System.out.println(coche);
            }
        }
    }

    private static void gestionarPasajeros() {
        
    }
}