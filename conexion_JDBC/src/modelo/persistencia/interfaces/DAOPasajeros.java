package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajeros;

public interface DAOPasajeros {
	public Pasajeros alta(Pasajeros p);
	public boolean baja(int id);
	
	public Pasajeros obtener(int id);
	public List<Pasajeros> listar();
	
	public boolean añadirPasajeroACoche(int idPasajero, int idCoche);
	public boolean eliminarPasajeroCoche(int idPasajero);
	public List<Pasajeros> listPasajerosEnCoche(int idCoche);
}
