package nubiiwallet.nubiiwallet.Services;

import nubiiwallet.nubiiwallet.Model.Usuario;
import nubiiwallet.nubiiwallet.Persistence.UsuarioPersistencia;
import nubiiwallet.nubiiwallet.Persistence.UsuarioPersistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ArchiveService {
    private final UsuarioPersistencia userPersistence;

    // Constructor que inicializa el UserPersistence.
    public ArchiveService(){
        this.userPersistence = new UsuarioPersistencia();
    }

    // Método para guardar una cadena en la ruta especificada.
    public static void guardarRegistro(String ruta, String contenido, Boolean flagAnexarContenido) throws IOException {
        FileWriter fileWriter = new FileWriter(ruta, flagAnexarContenido);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(contenido);
        bufferedWriter.close();
        fileWriter.close();
    }

    // Método para retornar el contenido del archivo especificado en la ruta.
    public static ArrayList<String> leerRegistro(String ruta) throws IOException {
        ArrayList<String> contenido = new ArrayList<String>();

        // Abrir la conexión.
        FileReader fileReader = new FileReader(ruta);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Leer.
        String linea = "";
        while ((linea = bufferedReader.readLine()) != null){
            contenido.add(linea);
        }

        // Cerrar.
        bufferedReader.close();
        fileReader.close();

        return contenido;
    }

    public static void guardarRegistroLog(String msjLog, int nivel, String accion, String ruta){
        Logger LOGGER = Logger.getLogger(accion);
        FileHandler fileHandler = null;

        try{
            fileHandler = new FileHandler(ruta, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);

            switch (nivel){
                case 1:
                    LOGGER.log(Level.INFO, accion + "," + msjLog);
                    break;
                case 2:
                    LOGGER.log(Level.WARNING, accion + "," + msjLog);
                    break;
                case 3:
                    LOGGER.log(Level.SEVERE, accion + "," + msjLog);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
        }
        finally {
            fileHandler.close();
        }
    }
}
