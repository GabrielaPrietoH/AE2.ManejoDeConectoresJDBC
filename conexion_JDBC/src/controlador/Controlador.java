package controlador;


import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajeros;
import modelo.persistencia.DAOCocheMysql;
import modelo.persistencia.DAOPasajeroMysql;
import modelo.persistencia.interfaces.DAOPasajeros;

public class Controlador {
	private DAOCocheMysql daoCar;
	private DAOPasajeroMysql daoPasajero;
	
	public Controlador() {
		this.daoCar = new DAOCocheMysql();
		this.daoPasajero = new DAOPasajeroMysql();
		daoCar.abrirConexion();
		daoPasajero.abrirConexion();
		
	}
	
	public boolean cerrarConexionCar() {
		daoCar.cerrarConexion();
		return true;
	}
	public boolean cerrarConexionPasajero() {
		daoPasajero.cerrarConexion();
		return true;
	}
	
	/*
	 * Métodos de conexión con la BBDD de los Coches
	 */
	
	/**
	 * Método que añade un coche a la BBDD
	 * @param c,  usuario a agregar al motor de persistencia
	 * recogido mediante el menú.
	 */
	public Coche addCoche(Coche c) {
		
		Coche cocheNuevo = daoCar.alta(c);
		return cocheNuevo;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteCoche(int id) {
		
		daoCar.baja(id);
	}
	
	//Update
	public void updateCoche(Coche c) {
		daoCar.modificar(c);
	}
		
	//get
	public Coche getCoche(int id) {
		for(Coche c: daoCar.listar()) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	//list
	public List<Coche> getListaCoches() {
		return daoCar.listar();
	}
	
	public boolean cerrarConexion() {
		daoCar.cerrarConexion();
		return true;
	}
	
	
	/*
	 * Métodos de conexión con la BBDD de los Pasajeros
	 */
	
	//add pasajero
    public Pasajeros addPasajero(Pasajeros p) {
		
    	Pasajeros pasajeroNuevo = daoPasajero.alta(p);
		return pasajeroNuevo;
	}
    
    //Delete pasajero
    public void deletePasajero(int id) {
		
		daoPasajero.baja(id);
	}
    
    //Get pasajero
    public Pasajeros getPasajero(int id) {
		for(Pasajeros p: daoPasajero.listar()) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
    
    //List pasajeros
   	public List<Pasajeros> getListaPasajeros() {
  		return daoPasajero.listar();
  	}

	//add pasajero a coche
	public boolean addPasajeroACoche(int idPasajero,int idCoche) {
		daoPasajero.añadirPasajeroACoche(idPasajero, idCoche);
		return true;
	}
	
	//Delete pasajero de coche
	public boolean deletePasajerosCoche(int idPasajero){
		daoPasajero.eliminarPasajeroCoche(idPasajero);
		return true;
	}
	
	//List todos los pasajeros de un coche
	public List<Pasajeros> listPasajeroCoche(int idCoche){
		return daoPasajero.listPasajerosEnCoche(idCoche);
	}
		

}

