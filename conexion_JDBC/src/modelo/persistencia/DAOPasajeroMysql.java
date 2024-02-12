package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.LecturaProperties;
import modelo.entidad.Pasajeros;
import modelo.persistencia.interfaces.DAOPasajeros;

public class DAOPasajeroMysql implements DAOPasajeros{
	private Connection conexion;
	private LecturaProperties propiedades;
	
	public DAOPasajeroMysql() {
		propiedades = new LecturaProperties();
		propiedades.inicializar();

	}
		
	/**
	 * 
	 * @return
	 */
	public boolean abrirConexion() {
		
		/*
		 * String url = propiedades.getProperty("url");
		String usuario = propiedades.getProperty("usuario");
		String password = propiedades.getProperty("password");
		
		 */
		
		
		String url = "jdbc:mysql://localhost:3306/coches";
		String usuario = "root";
		String password = "";
		
		 
		
		try {
						
			conexion = DriverManager.getConnection(url, usuario, password);
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
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
	public Pasajeros alta(Pasajeros p) {
		if(!abrirConexion()) {
			//return false;
			return null;
		}
		//boolean alta = true;
		Pasajeros pasajero = null;
		
		String query = "insert into pasajeros(NOMBRE,EDAD,PESO)VALUES(?,?,?)";
		try(PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
						
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setDouble(3, p.getPeso());
			
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				//alta = false;
				return null;
			}
			
			//Obtener el ID autoincrementado y completar el objeto return.
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					//Se asigna el ID, 1= primera columna autoincremental
					int idGenerado = generatedKeys.getInt(1);
					//Se crea el objeto con el ID y los valores del controlador
					pasajero = new Pasajeros();
					pasajero.setId(idGenerado);
					pasajero.setNombre(p.getNombre());
					pasajero.setEdad(p.getEdad());
					pasajero.setPeso(p.getPeso());
					
				}
			}catch (SQLException e) {
				System.out.println("alta -> Error al insertar: " + p);
			    e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			e.printStackTrace();
			//alta = false;
			return null;
			
		} 
		//return alta;
		return pasajero;
	}

	@Override
	public boolean baja(int id) {
		if(!abrirConexion()) {
			return false;
		}
		
		boolean borrado = true;
		
		String query = "delete from pasajeros where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				borrado = false;
			}
		}catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja"
					+ " la persona con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public Pasajeros obtener(int id) {
	if(!abrirConexion()){
			
			return null;
		}		
		Pasajeros p = null;
		
		String query = "select ID,NOMBRE,EDAD,PESO from pasajeros "
				+ "where id = ?";
		try(PreparedStatement ps = conexion.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {
			
			ps.setInt(1, id);
						
			  
			while(rs.next()){
				p = new Pasajeros();
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setEdad(rs.getInt(3));
				p.setPeso(rs.getDouble(4));
				
				
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "pasajero con id " + id);
			e.printStackTrace();
		}		
		
		return p;
	}

	@Override
	public List<Pasajeros> listar() {
		if(!abrirConexion()){
			return null;
		}	
		List<Pasajeros> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT p.ID, p.NOMBRE, p.EDAD, p.PESO, p.COCHE_ID, c.MARCA,"
				+ "c.MODELO, c.AÑO, c.KILOMETROS FROM pasajeros p LEFT JOIN coche c ON p.COCHE_ID = c.ID";
		try (PreparedStatement ps = conexion.prepareStatement(query);
				ResultSet rs = ps.executeQuery()){
							
			while(rs.next()){
				Pasajeros p = new Pasajeros();
				
				p.setId(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setEdad(rs.getInt(3));
				p.setPeso(rs.getDouble(4));
				
				//verificación de que id no apunta a null
				Integer cocheId = (Integer)rs.getObject("COCHE_ID");
				if(cocheId != null) {
					//Se crea el objeto que meteré en Pasajeros
					Coche coche = new Coche();
	                coche.setId(cocheId);
	                coche.setMarca(rs.getString("MARCA"));
	                coche.setModelo(rs.getString("MODELO"));
	                coche.setAñoFabricacion(rs.getInt("AÑO"));
	                coche.setKm(rs.getDouble("KILOMETROS"));
	                p.setCoche(coche);
				}else {
					p.setCoche(null);
				}
				
				
				listaPasajeros.add(p);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "pasajeros");
			e.printStackTrace();
		} 
		
		
		return listaPasajeros;
	}

	@Override
	public boolean añadirPasajeroACoche(int idPasajero, int idCoche) {
		if(!abrirConexion()){
			return false;
		}
		//Se actualiza la referencia de Coche en el pasajero.
		String query = "UPDATE pasajeros SET coche_id = ? WHERE id = ?";
		try (PreparedStatement ps = conexion.prepareStatement(query)){
			
			ps.setInt(1, idCoche);
			ps.setInt(2, idPasajero);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error al añadir el pasajero al coche");
	        e.printStackTrace();
	    }
		
		return true;
	}

	@Override
	public boolean eliminarPasajeroCoche(int idPasajero) {

		if(!abrirConexion()){
			return false;
		}
		

		String query ="UPDATE PASAJEROS SET COCHE_ID = NULL WHERE ID=?";
		try(PreparedStatement ps = conexion.prepareStatement(query)){
			
			ps.setInt(1,idPasajero);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				return false;
			}
			
		}catch (SQLException e) {
			System.out.println("Error al eliminar el pasajero de coche");
	        e.printStackTrace();
	    }
				
		return true;
	}
	
	
	//Método para listar los pasajeros del coche.
	@Override
	public List<Pasajeros> listPasajerosEnCoche(int idCoche) {
	    if (!abrirConexion()) {
	        return null;
	    }
	    List<Pasajeros> listaPasajeros = new ArrayList<>();

	    String query = "SELECT p.ID, p.NOMBRE, p.EDAD, p.PESO, p.COCHE_ID, c.MARCA, c.MODELO, c.AÑO, c.KILOMETROS FROM pasajeros p INNER JOIN coche c ON p.COCHE_ID = c.ID WHERE c.ID = ?";
	    try (PreparedStatement ps = conexion.prepareStatement(query)) {
	        ps.setInt(1, idCoche);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Pasajeros p = new Pasajeros();
	                p.setId(rs.getInt("ID"));
	                p.setNombre(rs.getString("NOMBRE"));
	                p.setEdad(rs.getInt("EDAD"));
	                p.setPeso(rs.getDouble("PESO"));

	                // Construcción del objeto Coche asociado
	                Coche c = new Coche();
	                c.setId(rs.getInt("COCHE_ID"));
	                c.setMarca(rs.getString("MARCA"));
	                c.setModelo(rs.getString("MODELO"));
	                c.setAñoFabricacion(rs.getInt("AÑO"));
	                c.setKm(rs.getDouble("KILOMETROS"));

	                p.setCoche(c);

	                listaPasajeros.add(p);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("listar -> error al obtener los pasajeros");
	        e.printStackTrace();
	        return null;
	    } finally {
	        cerrarConexion();
	    }

	    return listaPasajeros;
	}


		

}
