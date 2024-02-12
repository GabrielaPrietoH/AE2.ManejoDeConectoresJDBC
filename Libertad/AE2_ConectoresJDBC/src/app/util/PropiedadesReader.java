package util;

import java.io.InputStream;
import java.util.Properties;

public class PropiedadesReader {
    private static final String ARCHIVO_CONFIG = "config.properties";
    private Properties properties;

    public PropiedadesReader() {
        properties = new Properties();
        cargarPropiedades();
    }

    private void cargarPropiedades() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(ARCHIVO_CONFIG)) {
            if (input == null) {
                System.out.println("Lo siento, no se puede encontrar el archivo " + ARCHIVO_CONFIG);
                return;
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerValor(String clave) {
        return properties.getProperty(clave);
    }
}