package entidad;

import java.util.Objects;

public class Pasajero {


	private int id, idCoche;
	private String nombre;
	private int edad;
	private double peso;
	
	
	public Pasajero(int id, String nombre, int edad, double peso, int idCoche) {
		super();
		this.id = id;
		this.idCoche = idCoche;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	
	}
	public Pasajero() {
		super();
		
	}
	

	
	public int getIdCoche() {
		return idCoche;
	}
	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", idCoche=" + idCoche + ", nombre=" + nombre + ", edad=" + edad + ", peso="
				+ peso + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pasajero other = (Pasajero) obj;
		return id == other.id;
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	
}
