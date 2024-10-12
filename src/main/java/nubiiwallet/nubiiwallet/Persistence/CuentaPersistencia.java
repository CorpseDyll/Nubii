package nubiiwallet.nubiiwallet.Persistence;

import nubiiwallet.nubiiwallet.Model.Cuenta;
import nubiiwallet.nubiiwallet.Services.ArchiveService;

import java.io.IOException;
import java.util.ArrayList;

public class CuentaPersistencia {
    private final String ruta= Persistencia.obtenerRutaProperties("rutaArchivoCuentas");

    public void guardarCuenta(Cuenta cuenta) throws IOException {
        ArchiveService.guardarRegistro(ruta, cuenta.toFileString(), false);
        ArchiveService.guardarRegistroLog("El administrador ha creado una cuenta: " +
                cuenta.getNroCuenta(), 1, "agregarCuentaBtn", "C:/td/persistencia/log/log.txt");
    }

    public ArrayList<Cuenta> cargarCuentas() throws IOException{
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        ArrayList<String> dataCuentas = ArchiveService.leerRegistro(ruta);
        for(String ob : dataCuentas){
            cuentas.add(Cuenta.fromFileString(ob));
        }
        return cuentas;
    }
}
