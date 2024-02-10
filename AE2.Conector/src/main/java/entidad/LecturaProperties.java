package entidad;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LecturaProperties {
	
		private Properties properties;
		
		public void inicializar() {
			
			try (InputStream ficheroPropiedades = LecturaProperties.class.getClassLoader()
					.getResourceAsStream("application.properties")){
				
				properties = new Properties();
				
				properties.load(ficheroPropiedades);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public String getProperty(String key) {
			return properties.getProperty(key);
		}

}
