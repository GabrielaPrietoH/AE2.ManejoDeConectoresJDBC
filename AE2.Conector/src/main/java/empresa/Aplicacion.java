package empresa;

import java.text.DateFormat;
import java.util.Scanner;

import entidad.Coche;
import entidad.Pasajero;
import modelo.persistencia.GestionCocheMySQL;
import modelo.persistencia.GestionPasajerosMySQL;

public class Aplicacion {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		Coche co = new Coche();
		GestionCocheMySQL gc= new GestionCocheMySQL();
		
		int opcion;
		int id = 0;
		do{
			System.out.println("Bienvenido al programa\n"
					+ "Menu:\r\n"
					+ "1.Añadir nuevo coche\r\n"
					+ "2.Borrar coche por ID\r\n"
					+ "3.Consulta coche por ID\r\n"
					+ "4.Moficar coche por ID\r\n"
					+ "5.Listar coches\r\n"
					+ "6.Gestionar pasajeros\r\n"
					+ "7.Terminar programa\r\n"
					+ "Escoge una opcion:");
			opcion = sc.nextInt();
			if(opcion < 7) {
				
			  switch(opcion) {
        
			case 1:
				
				System.out.println("Añadir nuevo coche");
				System.out.println("Introduce Marca: ");
				co.setMarca(sc.nextLine());
				co.setMarca(sc.nextLine());
				System.out.println("Introduce Modelo: ");
				co.setModelo(sc.nextLine());
				System.out.println("Introduce Año de Fabricacion: ");
				co.setAñoFabricacion(sc.nextInt());
				System.out.println("Introduce los Km: ");
				co.setKm(sc.nextDouble());
				gc.añadir(co);
				System.out.println("Coche intrucido con exito");
				System.out.println(gc.listar());
				
				break;
			
			case 2:
				
				System.out.println("Borrar coche\r\n"
						+ "Introduce el ID: ");
				id = sc.nextInt();
				System.out.println("Coche borrado: "+ gc.borrar(id));
				
				break;
			
			case 3:
	
				System.out.println("Consultar coche\r\n"
						+ "Introduce el ID: ");
				id = sc.nextInt();
				
				System.out.println("El coches es: " + gc.consultar(id));
				break;
			
			case 4:
				
				System.out.println("Moficar coche\r\n"
					+ "Introduce el ID del coche a modificar: ");
				    id = sc.nextInt();

					System.out.println("El coche seleccionado es:" + gc.consultar(id));
					System.out.println("Modificar Marca: ");
					co.setMarca(sc.nextLine());
					co.setMarca(sc.nextLine());
					System.out.println("Modificar Modelo: ");
					co.setModelo(sc.nextLine());
					System.out.println("Modifica el Año de Fabricacion: ");
					co.getAñoFabricacion();;
					co.setAñoFabricacion(sc.nextInt());
					System.out.println("Modifica los Km: ");
					co.setKm(sc.nextDouble());
					co.setId(id);
					System.out.println("Coche Modificado " + gc.modificar(co));
				    System.out.println(gc.consultar(id));
				break;
				
			case 5:
				
				System.out.println(gc.listar());
				
				break;
				
			case 6:
				do {
				if(opcion < 8) {
				System.out.println("Gestionar Pasajeros\r\n"
				+ "Menu:\r\n"
				+ "1.Crear nuevo Pasajero\r\n"
				+ "2.Borrar Pasajero por ID\r\n"
				+ "3.Consulta Pasajero por ID\r\n"
				+ "4.Listar Pasajeros\r\n"
				+ "5.Añadir Pasajero a un Coche por ID\r\n"
				+ "6.Eliminar pasajero de un Coche por ID\r\n"
				+ "7.Listar pasajeros de un Coche por ID\r\n"
				+ "8.Salir de pasajeros\r\n"
				+ "Escoge una opcion:");
				
				Pasajero pa = new Pasajero();
				GestionPasajerosMySQL gp = new GestionPasajerosMySQL();
				opcion = sc.nextInt();
				switch(opcion) {
				case 1:
					
					System.out.println("Crear nuevo pasajero");
					System.out.println("Introduce el Nombre : ");
					pa.setNombre(sc.nextLine());
					pa.setNombre(sc.nextLine());
					System.out.println("Introduce la Edad: ");
					pa.setEdad(sc.nextInt());
					System.out.println("Introduce el Peso: ");
					pa.setPeso(sc.nextInt());
					System.out.println(gp.crear(pa));
					
					break;
				
				case 2:
					
					System.out.println("Borrar Pasajero\r\n"
							+ "Introduce el ID: ");
					id = sc.nextInt();
				
					System.out.println("Pasajero borrado: " + gp.borrar(id));
					
					break;
				
				case 3:
					
					System.out.println("Consultar pasajero\r\n"
							+ "Introduce el ID: ");
					id = sc.nextInt();
			
				   System.out.println(gp.consultar(id));
				
					break;
					
				case 4:
					
					System.out.println(gp.listar());
					
					break;
				
				case 5:
					
					System.out.println("Introduce el ID coche");
					System.out.println(gc.listar());
					int idPasajero = sc.nextInt();
				
					System.out.println("Escoge el ID del pasajero que quieres agregar");
					int idCoche = sc.nextInt();
					
					System.out.println(gp.consultar(idCoche));
					System.out.println(gp.añadirPasajeroCoche(idCoche, idPasajero));
					break;
				
				case 6:
					System.out.println("Eliminar Pasajero del Coche\r\n"
							+ "Introduce el ID del pasajero: ");
						
						System.out.println(gp.listar());
						id = sc.nextInt();
						
					System.out.println(gp.EliminarPasajeroCoche(id));
					
					
					break;
					
				case 7:
					
					System.out.println("Listar pasajeros del coche\r\n"
							+ "Introduce el ID del coche: ");
					System.out.println(gc.listar());
					idCoche = sc.nextInt();
	
					System.out.println(gp.listarPasajerosCoches(idCoche));
					break;
					
			    case 8:
			    	
				  System.out.println("Salimos de Pasajeros");
				  
				  break;
				  
			  }
				}else {
		        	System.out.println("Opción no valida");
				  }
				
			}while(opcion != 8);
			
			 case 7:
			    	
				  System.out.println("A elegido terminar el programa");
				  
				  break;
             
			}
		}else {
        	 System.out.println("Opción no valida");
		  }

		
		}while(opcion != 7);
		
		System.out.println("Programa terminado");
	
	}

}
