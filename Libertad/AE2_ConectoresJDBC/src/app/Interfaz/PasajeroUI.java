package app.interfaz;

import java.util.List;
import java.util.Scanner;

import app.controlador.GestorOperaciones;
import app.modelo.PasajeroEntity;

public class PasajeroUI {
    private GestorOperaciones gestor;

    public PasajeroUI(GestorOperaciones gestor) {
        this.gestor = gestor;
    }

    public void mostrarMenuPasajeros() {
        Scanner leer = new Scanner(System.in);

        String eleccionSubmenu = "";
        boolean continuarSubmenu = true;

        do {
            System.out.println("\nHas elegido gestionar los pasajeros. ");
            System.out.println("\n Elige una opción del menú numérico: \n");
            System.out.println("1. Crear nuevo pasajero.");
            System.out.println("2. Borrar pasajero por id.");
            System.out.println("3. Consulta pasajero por id.");
            System.out.println("4. Listar todos los pasajeros.");
            System.out.println("5. Añadir pasajero a un coche.");
            System.out.println("6. Eliminar pasajero de un coche");
            System.out.println("7. Listar todos los pasajeros de un coche.");
            System.out.println("8. Volver al menú principal.");

            eleccionSubmenu = leer.nextLine();

            try {
                int choiceSubmenu = Integer.parseInt(eleccionSubmenu);

                switch (choiceSubmenu) {
                    case 1:
                        crearNuevoPasajero();
                        break;
                    case 2:
                        borrarPasajeroPorId();
                        break;
                    case 3:
                        consultaPasajeroPorId();
                        break;
                    case 4:
                        listarTodosLosPasajeros();
                        break;
                    case 5:
                        añadirPasajeroACoche();
                        break;
                    case 6:
                        eliminarPasajeroDeCoche();
                        break;
                    case 7:
                        listarPasajerosDeCoche();
                        break;
                    case 8:
                        volverAlMenuPrincipal();
                        continuarSubmenu = false;
                        gestor.cerrarConexionPasajero();
                        break;
                    default:
                        System.out.println("Opción no válida. Introduce un número del 1 al 8.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("La elección no es un número válido. Vuelve a intentarlo.");
            }

        } while (continuarSubmenu);

        leer.close();
    }

    private void crearNuevoPasajero() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Has elegido añadir un nuevo pasajero.");

        PasajeroEntity pasajero = new PasajeroEntity();
        System.out.println("Introduce el nombre del pasajero: ");
        String nombre = leer.nextLine();
        pasajero.setNombre(nombre);
        System.out.println("Introduce la edad del pasajero: ");
        int edad = leer.nextInt();
        leer.nextLine();
        pasajero.setEdad(edad);
        System.out.println("Introduce su peso: ");
        double peso = leer.nextDouble();
        leer.nextLine();
        pasajero.setPeso(peso);

        PasajeroEntity pasajeroNuevo = gestor.altaPasajero(pasajero);

        System.out.println("Has añadido el pasajero: " + pasajeroNuevo);
    }

    private void borrarPasajeroPorId() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Has elegido borrar un pasajero por su id.");
        System.out.println("Elige qué pasajero quieres borrar mediante su ID. Aquí tienes el listado: ");
        System.out.println(gestor.listarPasajeros());
        int idElegido = leer.nextInt();
        leer.nextLine();

        System.out.println("Has borrado el pasajero: " + gestor.obtenerPasajero(idElegido));
        gestor.bajaPasajero(idElegido);
    }

    private void consultaPasajeroPorId() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Has elegido consultar un pasajero por id.");
        System.out.println("Elige qué pasajero consultar mediante su ID. Aquí tienes el listado: ");
        System.out.println(gestor.listarPasajeros());
        int idElegido = leer.nextInt();
        leer.nextLine();

        PasajeroEntity pasajero = gestor.obtenerPasajero(idElegido);

        if (pasajero != null) {
            System.out.println("El pasajero con id " + idElegido + " es: " + pasajero);
        } else {
            System.out.println("No se encontró el pasajero con ID: " + idElegido);
        }
    }

    private void listarTodosLosPasajeros() {
        System.out.println("Has elegido recibir un listado de todos los pasajeros.");

        List<PasajeroEntity> listaPasajeros = gestor.listarPasajeros();

        if (listaPasajeros.isEmpty()) {
            System.out.println("No hay pasajeros en el listado.");
        } else {
            for (PasajeroEntity p : listaPasajeros) {
                System.out.println(p);
            }
        }
    }

    private void añadirPasajeroACoche() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Has elegido añadir un pasajero a un coche.");
        System.out.println("De la siguiente lista de pasajeros, elige uno mediante su ID: ");
        System.out.println(gestor.listarPasajeros());
        int idPasajero = leer.nextInt();
        System.out.println("Ahora de la siguiente lista de coches, elige uno mediante su ID: ");
        System.out.println(gestor.listarCoches());
        int idCoche = leer.nextInt();

        gestor.añadirPasajeroACoche(idPasajero, idCoche);

        System.out.println("Has añadido correctamente el pasajero a su coche ");
    }

    private void eliminarPasajeroDeCoche() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Has elegido eliminar un pasajero de un coche.");
        System.out.println("De la siguiente lista de pasajeros, elige uno mediante su ID, para eliminarlo de su coche: ");
        System.out.println(gestor.listarPasajeros());
        int idPasajero = leer.nextInt();

        gestor.eliminarPasajeroDeCoche(idPasajero);

        System.out.println("Has eliminado correctamente al pasajero " + gestor.obtenerPasajero(idPasajero) + " de su coche.");
    }

    private void listarPasajerosDeCoche() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Has elegido listar todos los pasajeros de un coche.");
        System.out.println("Ahora de la siguiente lista de coches, elige uno mediante su ID: ");
        System.out.println(gestor.listarCoches());
        int idCoche = leer.nextInt();

        System.out.println("Has elegido el coche: " + gestor.obtenerCoche(idCoche));
        System.out.println("Este es el listado de sus pasajeros: ");
        System.out.println(gestor.listarPasajerosEnCoche(idCoche));
    }

    private void volverAlMenuPrincipal() {
        System.out.println("Has elegido volver al menú principal.");
    }
}