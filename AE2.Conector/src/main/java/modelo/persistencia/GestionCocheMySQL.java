package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Coche;
import entidad.LecturaProperties;
import persistencia.interfaces.DaoCoche;

public class GestionCocheMySQL implements DaoCoche  {

	private Connection conexion;
	private LecturaProperties propiedades;	

	public GestionCocheMySQL() {
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
	public boolean añadir(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into coches (id,marca,modelo,añoFabricacion,km) "
				+ " values(?,?,?,?,?)";
		try {
			//preparamos la query con valores parametrizables(?)
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getAñoFabricacion());
			ps.setDouble(5, c.getKm());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0) {
				alta = false;
			}
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
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
		String query = "delete from coches where id = ?";
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
	public boolean modificar(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coches set MARCA=?, MODELO=?, "
				+ "AÑOFABRICACION=?, KM=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setInt(3, c.getAñoFabricacion());
			ps.setDouble(4, c.getKm());
			ps.setInt(5, c.getId());
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
			else
				modificado = true;
		} catch (SQLException e) {
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
	public Coche consultar(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		
		String query = "select ID,MARCA,MODELO,AÑOFABRICACION,KM from coches "
				+ "where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAñoFabricacion(rs.getInt(4));
				coche.setKm(rs.getDouble(5));
			
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return coche;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,MARCA,MODELO,AÑOFABRICACION,KM from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMarca(rs.getString(2));
				coche.setModelo(rs.getString(3));
				coche.setAñoFabricacion(rs.getInt(4));
				coche.setKm(rs.getDouble(5));
				
				listaCoches.add(coche);
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
