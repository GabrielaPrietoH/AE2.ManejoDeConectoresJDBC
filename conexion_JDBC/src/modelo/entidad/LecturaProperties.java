package modelo.entidad;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LecturaProperties {
	
		private Properties properties;
		
		public void inicializar() {
			
			try (InputStream ficheroPropiedades = LecturaProperties.class.getClassLoader()
					.getResourceAsStream("config.properties");){
				
				properties = new Properties();
				
				properties.load(ficheroPropiedades);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Método que devuelve un valor de una propiedad a partir de la clave
		 * @param key la clave de la propiedad
		 * @return valor de la propiedad. Null en caso de que no exista.
		 */
		public String getProperty(String key) {
			return properties.getProperty(key);
		}

}
