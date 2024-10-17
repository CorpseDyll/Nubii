package nubiiwallet.nubiiwallet.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Deposito extends Transaccion {

    private String cuentaDestino;

    // Constructor principal.
    public Deposito(String tipoTransaccion, String idTransaccion, LocalDate fechaActual,
                    double monto, String categoria,  String cuentaDestino) {
        super(tipoTransaccion, idTransaccion, fechaActual, monto, categoria);
        this.cuentaDestino = cuentaDestino;
    }

    // Constructor secundario.
    public Deposito(String tipoTransaccion, LocalDate fechaActual , double monto, String categoria,
                    String cuentaDestino){
        this(tipoTransaccion, UUID.randomUUID().toString(), fechaActual, monto, categoria, cuentaDestino);
    }

    public String toFileString(){
        return getTipoTransaccion() + "@@" + getIdTransaccion() + "@@" + getFechaActual() + "@@" +
                getMonto() + "@@" + getCategoria() + "@@" + cuentaDestino + "\n";
    }

    public static Deposito fromFileString(String fileCategoria){
        String[] parts = fileCategoria.split("@@");
        if (parts.length != 6) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 6 elementos pero se encontraron " + parts.length);
        }
        return new Deposito(parts[0], parts[1], LocalDate.parse(parts[2],
                DateTimeFormatter.ofPattern("dd/MM/yyyy")), Double.parseDouble(parts[3]),
                parts[4], parts[5]);
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
