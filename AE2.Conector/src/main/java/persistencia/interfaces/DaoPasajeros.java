package persistencia.interfaces;

import java.util.List;

import entidad.Pasajero;


public interface DaoPasajeros {

	public boolean crear(Pasajero p);
	public boolean borrar(int id);
	public Pasajero consultar(int id);
	public List<Pasajero> listar();
	public boolean añadirPasajeroCoche(int id, int idCoche);
	public boolean EliminarPasajeroCoche(int idPasajero);
	public List<Pasajero>listarPasajerosCoches(int idCoche);
	
}
