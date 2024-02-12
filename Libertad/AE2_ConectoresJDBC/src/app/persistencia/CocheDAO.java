package app.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.modelo.CocheEntity;

public class CocheDao {
    private Connection conexion;

    public CocheDao(Connection conexion) {
        this.conexion = conexion;
    }

    public CocheEntity alta(CocheEntity coche) {
        String query = "INSERT INTO coches (marca, modelo, año, kilometros) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setInt(3, coche.getAñoFabricacion());
            ps.setDouble(4, coche.getKm());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                return null;
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    coche.setId(generatedKeys.getInt(1));
                    return coche;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean baja(int id) {
        String query = "DELETE FROM coches WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificar(CocheEntity coche) {
        String query = "UPDATE coches SET marca = ?, modelo = ?, año = ?, kilometros = ? WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, coche.getMarca());
            ps.setString(2, coche.getModelo());
            ps.setInt(3, coche.getAñoFabricacion());
            ps.setDouble(4, coche.getKm());
            ps.setInt(5, coche.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CocheEntity obtener(int id) {
        String query = "SELECT * FROM coches WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCoche(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CocheEntity> listar() {
        List<CocheEntity> listaCoches = new ArrayList<>();
        String query = "SELECT * FROM coches";

        try (PreparedStatement ps = conexion.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CocheEntity coche = mapearCoche(rs);
                listaCoches.add(coche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCoches;
    }

    private CocheEntity mapearCoche(ResultSet rs) throws SQLException {
        CocheEntity coche = new CocheEntity();
        coche.setId(rs.getInt("id"));
        coche.setMarca(rs.getString("marca"));
        coche.setModelo(rs.getString("modelo"));
        coche.setAñoFabricacion(rs.getInt("año"));
        coche.setKm(rs.getDouble("kilometros"));
        return coche;
    }
}