package nubiiwallet.nubiiwallet.Persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class Persistencia {

    static String obtenerRutaProperties(String rutaRequerida){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(new File("C:/td/rutas/rutas.properties")));
            return properties.get(rutaRequerida).toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
