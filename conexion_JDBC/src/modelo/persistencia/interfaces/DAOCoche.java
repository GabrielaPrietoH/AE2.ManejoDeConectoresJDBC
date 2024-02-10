package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;

public interface DAOCoche {
	public Coche alta(Coche c);
	public boolean baja(int id);
	public boolean modificar(Coche c);
	public Coche obtener(int id);
	public List<Coche> listar();
	
}