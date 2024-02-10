package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.LecturaProperties;
import entidad.Pasajero;
import persistencia.interfaces.DaoPasajeros;

public class GestionPasajerosMySQL implements DaoPasajeros {
	
private Connection conexion;
private LecturaProperties propiedades;	

public GestionPasajerosMySQL() {
	propiedades = new LecturaProperties();
	propiedades.inicializar();

}
	public boolean abrirConexion(){

		String url = propiedades.getProperty("url");
		String usuario = propiedades.getProperty("usuario");
		String password = propiedades.getProperty("password");
		
		/*
		String url = "jdbc:mysql://localhost/empresa";
		String usuario = "root";
		String password = "";
		*/
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean crear(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajeros (id,nombre,edad,peso) "
				+ " values(?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getEdad());
			ps.setDouble(4, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}

	@Override
	public boolean borrar(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado;
	}

	@Override
	public Pasajero consultar(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "select ID,NOMBRE,EDAD,PESO from pasajeros "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "pasajero con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return pasajero;
	}

	@Override
	public List<Pasajero> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPersonas = new ArrayList<>();
		
		String query = "select ID,NOMBRE,EDAD,PESO from pasajeros";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getDouble(4));
				
				listaPersonas.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPersonas;
	}

	@Override
	public boolean añadirPasajeroCoche(int id, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		//Se actualiza la referencia de Coche en el pasajero.
		String query = "UPDATE pasajeros SET id_coche = ? WHERE id = ?";
		try (PreparedStatement ps = conexion.prepareStatement(query)){
			
			ps.setInt(1, idCoche);
			ps.setInt(2, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir el pasajero al coche");
	        e.printStackTrace();
	    }
		
		System.out.println("Coche añadido");
		return true;
		

	}

	@Override
	public boolean EliminarPasajeroCoche(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "UPDATE PASAJEROS SET id_coche = NULL WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			//sustituimos la primera interrgante por la id
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja el pasajero"
					+ " de coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado;
	}
	


	@Override
	public List<Pasajero>listarPasajerosCoches(int idCoche) {
	
		if(!abrirConexion()){
			return null;
		}	
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "select ID,NOMBRE,EDAD,PESO,id_coche from pasajeros WHERE id_coche=?";
		try (PreparedStatement ps = conexion.prepareStatement(query)){
						
			ps.setInt(1, idCoche);	
			
			try(ResultSet rs = ps.executeQuery()){
				
				boolean tienePasajeros = false;
				while(rs.next()){
					
					tienePasajeros = true;
					
					Pasajero p = new Pasajero();
					
					p.setId(rs.getInt(1));
					p.setNombre(rs.getString(2));
					p.setEdad(rs.getInt(3));
					p.setPeso(rs.getDouble(4));
					p.setIdCoche(rs.getInt(5));
					listaPasajeros.add(p);
				}
				
				if(!tienePasajeros) {
					System.out.println("El coche seleccionado no tiene pasajeros.");
					return null;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "pasajeros");
			e.printStackTrace();
			return null;
		} finally {
			cerrarConexion();
		}
		
		
		return listaPasajeros;
	}
}
