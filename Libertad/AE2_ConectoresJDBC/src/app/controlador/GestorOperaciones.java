package app.controlador;

import java.util.List;

import app.modelo.CocheEntity;
import app.modelo.PasajeroEntity;
import app.persistencia.CocheDao;
import app.persistencia.PasajeroDao;

public class GestorOperaciones {
    private CocheDao cocheDao;
    private PasajeroDao pasajeroDao;

    public GestorOperaciones(CocheDao cocheDao, PasajeroDao pasajeroDao) {
        this.cocheDao = cocheDao;
        this.pasajeroDao = pasajeroDao;
    }

    // Operaciones relacionadas con Coches

    public CocheEntity altaCoche(CocheEntity coche) {
        return cocheDao.alta(coche);
    }

    public boolean bajaCoche(int id) {
        return cocheDao.baja(id);
    }

    public boolean modificarCoche(CocheEntity coche) {
        return cocheDao.modificar(coche);
    }

    public CocheEntity obtenerCoche(int id) {
        return cocheDao.obtener(id);
    }

    public List<CocheEntity> listarCoches() {
        return cocheDao.listar();
    }

    // Operaciones relacionadas con Pasajeros

    public PasajeroEntity altaPasajero(PasajeroEntity pasajero) {
        return pasajeroDao.alta(pasajero);
    }

    public boolean bajaPasajero(int id) {
        return pasajeroDao.baja(id);
    }

    public PasajeroEntity obtenerPasajero(int id) {
        return pasajeroDao.obtener(id);
    }

    public List<PasajeroEntity> listarPasajeros() {
        return pasajeroDao.listar();
    }

    public boolean añadirPasajeroACoche(int idPasajero, int idCoche) {
        // Lógica para añadir un pasajero a un coche
        return pasajeroDao.añadirPasajeroACoche(idPasajero, idCoche);
    }

    public boolean eliminarPasajeroDeCoche(int idPasajero) {
        // Lógica para eliminar un pasajero de un coche
        return pasajeroDao.eliminarPasajeroCoche(idPasajero);
    }

    public List<PasajeroEntity> listarPasajerosEnCoche(int idCoche) {
        return pasajeroDao.listPasajerosEnCoche(idCoche);
    }
}