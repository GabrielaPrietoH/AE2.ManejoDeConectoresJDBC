package persistencia.interfaces;

import java.util.List;

import entidad.Coche;



public interface DaoCoche { 
	
	public boolean añadir(Coche c);
	public boolean borrar(int id);
	public boolean modificar(Coche c);
	public Coche consultar(int id);
	public List<Coche> listar();
	
}
