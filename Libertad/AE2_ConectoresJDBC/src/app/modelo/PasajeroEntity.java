package app.modelo;

public class PasajeroEntity {
    private int id;
    private String nombre;
    private int edad;
    private double peso;
    private CocheEntity coche;

    // Constructor
    public PasajeroEntity() {
    }

    // Getters y Setters

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

    public CocheEntity getCoche() {
        return coche;
    }

    public void setCoche(CocheEntity coche) {
        this.coche = coche;
    }

    // Método toString para representación de cadena
    @Override
    public String toString() {
        return "PasajeroEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", coche=" + coche +
                '}';
    }
}

