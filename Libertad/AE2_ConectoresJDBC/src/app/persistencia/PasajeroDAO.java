package app.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.modelo.PasajeroEntity;

public class PasajeroDao {
    private Connection conexion;

    public PasajeroDao(Connection conexion) {
        this.conexion = conexion;
    }

    public PasajeroEntity alta(PasajeroEntity pasajero) {
        String query = "INSERT INTO pasajeros (nombre, edad, peso) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, pasajero.getNombre());
            ps.setInt(2, pasajero.getEdad());
            ps.setDouble(3, pasajero.getPeso());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                return null;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pasajero.setId(generatedKeys.getInt(1));
                    return pasajero;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean baja(int id) {
        String query = "DELETE FROM pasajeros WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PasajeroEntity obtener(int id) {
        String query = "SELECT * FROM pasajeros WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPasajero(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PasajeroEntity> listar() {
        List<PasajeroEntity> listaPasajeros = new ArrayList<>();
        String query = "SELECT * FROM pasajeros";

        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PasajeroEntity pasajero = mapearPasajero(rs);
                listaPasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPasajeros;
    }

    private PasajeroEntity mapearPasajero(ResultSet rs) throws SQLException {
        PasajeroEntity pasajero = new PasajeroEntity();
        pasajero.setId(rs.getInt("id"));
        pasajero.setNombre(rs.getString("nombre"));
        pasajero.setEdad(rs.getInt("edad"));
        pasajero.setPeso(rs.getDouble("peso"));
        return pasajero;
    }
}