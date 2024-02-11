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
import modelo.persistencia.interfaces.DAOCoche;

public class DAOCocheMysql implements DAOCoche {
	private Connection conexion;
	private LecturaProperties propiedades;
	
	public DAOCocheMysql() {
		propiedades = new LecturaProperties();
		propiedades.inicializar();

	}
		
	/**
	 * Método para abrir la conexión con la BBDD
	 * @return boolean dependiendo de si se ha logrado establecer o no.
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
	 * Método para cerrar la conexión con la BBDD
	 * @return boolean dependiendo de si se ha logrado cerrar o no.
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

	
	
	/**
	 * Método para dar de alta un coche
	 * @return un objeto de tipo coche.
	 */
	@Override
	public Coche alta(Coche c) {
		if(!abrirConexion()) {
			
			return null;
		}
		
		Coche coche = null;
		
		String query = "insert into coche(MARCA,MODELO,AÑO,KILOMETROS)VALUES(?,?,?,?)";
		try(PreparedStatement ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			
			
			
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAñoFabricacion());
			ps.setDouble(4, c.getKm());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				
				return null;
			}
			
			//Obtener el ID autoincrementado y completar el objeto return.
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					//Se asigna el ID, 1= primera columna autoincremental
					int idGenerado = generatedKeys.getInt(1);
					//Se crea el objeto con el ID y los valores del controlador
					coche = new Coche();
					coche.setId(idGenerado);
					coche.setMarca(c.getMarca());
					coche.setModelo(c.getModelo());
	                coche.setAñoFabricacion(c.getAñoFabricacion());
	                coche.setKm(c.getKm());
				}
			}catch (SQLException e) {
			    
			    e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			e.printStackTrace();
			
			return null;
			
		} 
		
		return coche;
	}

	@Override
	public boolean baja(int id) {
		if(!abrirConexion()) {
			return false;
		}
		
		boolean borrado = true;
		
		String query = "delete from coche where id = ?";
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
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean modificar(Coche c) {
		if(!abrirConexion()) {
			return false;
		}
		boolean modificado = true;
		String query = "update coche set MARCA=?, MODELO=?,AÑO=?, KILOMETROS=?"
				+ "WHERE ID=?";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAñoFabricacion());
			ps.setDouble(4, c.getKm());
			ps.setInt(5, c.getId());
			
			int numeroFilasAfectadas  = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				modificado = false;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar el "
					+ " coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Coche obtener(int id) {
		if(!abrirConexion()){
			
			return null;
		}		
		Coche c = null;
		
		String query = "select ID,MARCA,MODELO,AÑO,KILOMETROS from coche "
				+ "where id = ?";
		try (PreparedStatement ps = conexion.prepareStatement(query)){
			
			ps.setInt(1, id);
			
			
			ResultSet rs = ps.executeQuery();
			  
			while(rs.next()){
				c = new Coche();
				c.setId(rs.getInt(1));
				c.setMarca(rs.getString(2));
				c.setModelo(rs.getString(3));
				c.setAñoFabricacion(rs.getInt(4));
				c.setKm(rs.getDouble(5));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
		} 
		
		
		return c;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}	
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MARCA,MODELO,AÑO,KILOMETROS from coche";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setMarca(rs.getString(2));
				c.setModelo(rs.getString(3));
				c.setAñoFabricacion(rs.getInt(4));
				c.setKm(rs.getDouble(5));
				
				listaCoches.add(c);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

	
	
	

	
}
