package nubiiwallet.nubiiwallet.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Retiro extends Transaccion {
    private String cuentaOrigen;

    // Constructor primario.
    public Retiro(String tipoTransaccion, String idTransaccion, LocalDate fechaActual, double monto,
                  String categoria, String cuentaOrigen) {
        super(tipoTransaccion, idTransaccion, fechaActual, monto, categoria);
        this.cuentaOrigen = cuentaOrigen;
    }

    // Constructor secundario.
    public Retiro(String tipoTransaccion, LocalDate fechaActual , double monto, String categoria,
                    String cuentaOrigen){
        this(tipoTransaccion, UUID.randomUUID().toString(), fechaActual, monto, categoria, cuentaOrigen);
    }

    public String toFileString(){
        return getTipoTransaccion() + "@@" + getIdTransaccion() + "@@" + getFechaActual() + "@@" +
                getMonto() + "@@" + getCategoria() + cuentaOrigen + "\n";
    }

    public static Retiro fromFileString(String fileString){
        String[] parts = fileString.split("@@");
        if (parts.length != 6) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 6 elementos pero se encontraron " + parts.length);
        }
        return new Retiro(parts[0], parts[1], LocalDate.parse(parts[2],
                DateTimeFormatter.ofPattern("dd/MM/yyyy")), Double.parseDouble(parts[3]),
                parts[4], parts[5]);
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }
}
