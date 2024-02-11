package vista;

import java.util.List;
import java.util.Scanner;

import controlador.Controlador;
import modelo.entidad.Coche;
import modelo.entidad.Pasajeros;

public class MainMenu {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Scanner leer = new Scanner(System.in);
		
		
				
		String eleccion = "";
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
			
			//Me aseguro de recibir un número
			try {
				int choice = Integer.parseInt(eleccion);
				
				if(choice == 1) {
					System.out.println("Has elegido añadir un nuevo coche.");
					
					Coche c = new Coche();
					
					//Verificación para marca y modelo != null.
					String marca = "";
					while(marca.trim().isEmpty()) {
						System.out.println("Introduce la marca del coche: ");
						marca = leer.nextLine().trim();
						if(marca.isEmpty()) {
							System.out.println("La marca no puede estar vacía.");
						}else {
							c.setMarca(marca);
						}
					}
					 
					String modelo = "";
					while(modelo.trim().isEmpty()) {
						System.out.println("Introduce el modelo del coche: ");
						modelo = leer.nextLine();
						if(modelo.isEmpty()) {
							System.out.println("El modelo no puede estar vacío.");
						}else {
							c.setModelo(modelo);
						}
					}
					
					
					System.out.println("Introduce un año de fabricación para el coche: ");
					int año = leer.nextInt();
					leer.nextLine();
					c.setAñoFabricacion(año);
					System.out.println("Introduce los kilómetros del coche: ");
					double km = leer.nextDouble();
					leer.nextLine();
					c.setKm(km);
										
					//Así tiene el ID correcto
					Coche cocheNuevo = controlador.addCoche(c);
					
					System.out.println("has añadido el coche: " + cocheNuevo);
					
				}else if(choice == 2) {
					System.out.println("Has elegido borrar un coche por su id.");
					System.out.println("Elige qué coche quieres borrar mediante su ID. Aquí tienes el listado: ");
					System.out.println(controlador.getListaCoches());
					int idElegido = leer.nextInt();	
					leer.nextLine();
					
					System.out.println("has borrado el coche: " + controlador.getCoche(idElegido));
					controlador.deleteCoche(idElegido);
					
							
				}else if(choice == 3) {
					
					System.out.println("Has elegido consultar un coche por id.");
					System.out.println("Elige qué coche consultar mediante su ID. Aquí tienes el listado: ");
					System.out.println(controlador.getListaCoches());
					int idElegido = leer.nextInt();	
					leer.nextLine();
					
					Coche coche = controlador.getCoche(idElegido);
					
					if(coche != null) {
						System.out.println("El coche con id " + idElegido + " es: " + coche);
					}else {
						System.out.println("No se encontró el coche con ID: " + idElegido);
					}
					
					
					
					
				}else if(choice == 4) {
					System.out.println("Has elegido modificar un coche por su ID.");
					System.out.println("Elige qué coche modificar mediante su ID. Aquí tienes el listado: ");
					System.out.println(controlador.getListaCoches());
					int idElegido = leer.nextInt();	
					leer.nextLine();
					
					Coche c = controlador.getCoche(idElegido);
									
					System.out.println("Introduce la marca del coche: ");
					String marca = leer.nextLine();
					c.setMarca(marca);
					System.out.println("Introduce el modelo del coche: ");
					String modelo = leer.nextLine();
					c.setModelo(modelo);
					System.out.println("Introduce un año de fabricación para el coche: ");
					int año = leer.nextInt();
					leer.nextLine();
					c.setAñoFabricacion(año);
					System.out.println("Introduce los kilómetros del coche: ");
					double km = leer.nextDouble();
					leer.nextLine();
					c.setKm(km);
										
					controlador.updateCoche(c);
					System.out.println("Has modificado el coche con ID: " + idElegido + "\n"
							+ "El coche es: " + controlador.getCoche(idElegido));
					
										
				}else if(choice == 5) {
					System.out.println("Has elegido recibir un listado de todos los coches.");
					
					List<Coche> listaCoches = controlador.getListaCoches();
					
					//Si hay artículos los imprime, sino lo avisa.
					if(listaCoches.isEmpty()) {
						System.out.println("No hay coches en el listado.");
					}else {
						for(Coche c: listaCoches) {
							System.out.println(c);
						}
					}
					
									
				}else if(choice == 6) {
					
					
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
						
						//Me aseguro de recibir un número
						try {
							int choiceSubmenu = Integer.parseInt(eleccionSubmenu);
							
							if(choiceSubmenu == 1) {
								System.out.println("Has elegido añadir un nuevo pasajer@.");
								
																
								Pasajeros p = new Pasajeros();
								System.out.println("Introduce el nombre del pasajero: ");
								String nombre = leer.nextLine();
								p.setNombre(nombre);
								System.out.println("Introduce la edad del pasajero: ");
								int edad = leer.nextInt();
								leer.nextLine();
								p.setEdad(edad);
								System.out.println("Introduce su peso: ");
								double peso = leer.nextDouble();
								leer.nextLine();
								p.setPeso(peso);
								;
															
								Pasajeros pasajeroNuevo = controlador.addPasajero(p);
								
								System.out.println("has añadido el pasajero: " + pasajeroNuevo);
								
							}else if(choiceSubmenu == 2) {
								System.out.println("Has elegido borrar un pasajero por su id.");
								System.out.println("Elige qué pasajero quieres borrar mediante su ID. Aquí tienes el listado: ");
								System.out.println(controlador.getListaPasajeros());
								int idElegido = leer.nextInt();	
								leer.nextLine();
								
								System.out.println("has borrado el pasajero: " + controlador.getPasajero(idElegido));
								controlador.deletePasajero(idElegido);
								
										
							}else if(choiceSubmenu == 3) {
								
								System.out.println("Has elegido consultar un pasajero por id.");
								System.out.println("Elige qué pasajero consultar mediante su ID. Aquí tienes el listado: ");
								System.out.println(controlador.getListaPasajeros());
								int idElegido = leer.nextInt();	
								leer.nextLine();
								
								Pasajeros pasajero = controlador.getPasajero(idElegido);
								
								if(pasajero != null) {
									System.out.println("El pasajero con id " + idElegido + " es: " + pasajero);
								}else {
									System.out.println("No se encontró el pasajero con ID: " + idElegido);
								}
								
													
							}else if(choiceSubmenu == 4) {
								System.out.println("Has elegido recibir un listado de todos los pasajeros.");
								
								List<Pasajeros> listaPasajeros = controlador.getListaPasajeros();
								
								
								if(listaPasajeros.isEmpty()) {
									System.out.println("No hay pasajeros en el listado.");
								}else {
									for(Pasajeros p: listaPasajeros) {
										System.out.println(p);
									}
								}
								
													
							}else if(choiceSubmenu == 5) {
								System.out.println("Has elegido añadir un pasajero a un coche.");
								System.out.println("De la siguiente lista de pasajeros, elige uno mediante su ID: ");
								System.out.println(controlador.getListaPasajeros());
								int idPasajero = leer.nextInt();
								System.out.println("Ahora de la siguiente lista de coches, elige uno mediante su ID: ");
								System.out.println(controlador.getListaCoches());
								int idCoche = leer.nextInt();
								
								controlador.addPasajeroACoche(idPasajero, idCoche);
								
								System.out.println("Has añadido correctamente el pasajero a su coche ");
								
												
								
												
							}else if(choiceSubmenu == 6) {
								System.out.println("Has elegido eliminar un pasajero de un coche.");
								System.out.println("De la siguiente lista de pasajeros, elige uno mediante su ID, para eliminarlo de su coche: ");
								System.out.println(controlador.getListaPasajeros());
								int idPasajero = leer.nextInt();
								
								controlador.deletePasajerosCoche(idPasajero);
								
								System.out.println("Has eliminado correctamente al pasajero " + controlador.getPasajero(idPasajero) + " de su coche.");
								
								
								
							}else if(choiceSubmenu == 7) {
								System.out.println("Has elegido listar todos los pasajeros de un coche.");
								
								System.out.println("Ahora de la siguiente lista de coches, elige uno mediante su ID: ");
								System.out.println(controlador.getListaCoches());
								int idCoche = leer.nextInt();
								
								System.out.println("Has elegido el coche: " + controlador.getCoche(idCoche));
								System.out.println("Este es el listado de sus pasajeros: ");
								System.out.println(controlador.listPasajeroCoche(idCoche));
								
								
							}else if(choiceSubmenu == 8) {
								System.out.println("Has elegido volver al menú principal.");
								
											
								continuarSubmenu = false;
								controlador.cerrarConexionPasajero();
								
								
							}
							
							
						}catch(NumberFormatException e) {
							System.out.println("La elección no es un número válido, vuelve a intentarlo.");
						}
						
						
						
					}while(continuarSubmenu);
					
					
					
				}else if(choice == 7) {
					System.out.println("Has elegido terminar el programa.");
					
					
										
					continuar = false;
					controlador.cerrarConexionCar();
					
					
				}
				
				
			}catch(NumberFormatException e) {
				System.out.println("La elección no es un número válido, vuelve a intentarlo.");
			}
			
			
			
		}while(continuar);
		
		leer.close();
	}
	

	}


