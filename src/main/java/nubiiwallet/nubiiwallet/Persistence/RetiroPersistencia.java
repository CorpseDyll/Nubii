package nubiiwallet.nubiiwallet.Persistence;

import nubiiwallet.nubiiwallet.Model.Retiro;
import nubiiwallet.nubiiwallet.Services.ArchiveService;

import java.io.IOException;
import java.util.ArrayList;

public class RetiroPersistencia {
    private final String ruta = Persistencia.obtenerRutaProperties("rutaArchivoTransaccion");

    public void guardarRetiro(Retiro retiro) throws IOException {
        ArchiveService.guardarRegistro(ruta, retiro.toFileString(), true);
        ArchiveService.guardarRegistroLog("Se ha realizado un nuevo retiro.", 1,
                "retirarButton", "C:/td/persistencia/log/log.txt");
    }

    public ArrayList<Retiro> cargarRetiros() throws IOException {
        ArrayList<Retiro> retiros = new ArrayList<>();
        ArrayList<String> dataRetiros = ArchiveService.leerRegistro(ruta);
        for(String ob : dataRetiros){
            retiros.add(Retiro.fromFileString(ob));
        }
        return retiros;
    }
}
