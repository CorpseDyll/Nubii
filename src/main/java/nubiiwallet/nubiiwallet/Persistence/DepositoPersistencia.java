package nubiiwallet.nubiiwallet.Persistence;

import nubiiwallet.nubiiwallet.Model.Deposito;
import nubiiwallet.nubiiwallet.Services.ArchiveService;

import java.io.IOException;
import java.util.ArrayList;

public class DepositoPersistencia {
    private final String ruta = Persistencia.obtenerRutaProperties("rutaArchivoTransaccion");

    public void guardarDeposito(Deposito deposito) throws IOException {
        ArchiveService.guardarRegistro(ruta, deposito.toFileString(), true);
        ArchiveService.guardarRegistroLog("Se ha realizado un nuevo depósito.", 1,
                "guardarButton", "C:/td/persistencia/log/log.txt");
    }

    public ArrayList<Deposito> cargarDepositos() throws IOException {
        ArrayList<Deposito> depositos = new ArrayList<>();
        ArrayList<String> dataDepositos = ArchiveService.leerRegistro(ruta);
        for(String ob : dataDepositos){
            depositos.add(Deposito.fromFileString(ob));
        }
        return depositos;
    }
}
