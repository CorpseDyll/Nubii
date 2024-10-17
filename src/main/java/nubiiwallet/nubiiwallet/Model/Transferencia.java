package nubiiwallet.nubiiwallet.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transferencia extends Transaccion {
    private String descripcion;
    private Cuenta cuentaOrigen, cuentaDestino;

    public Transferencia(String tipoTransaccion, String idTransaccion, LocalDate fechaActual,
                         double monto, String categoria, Cuenta cuentaOrigen,
                         Cuenta cuentaDestino, String descripcion) {
        super(tipoTransaccion, idTransaccion, fechaActual, monto, categoria);
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.descripcion = descripcion;
    }

    public String toFileString(){
        return getTipoTransaccion() + "@@" + getIdTransaccion() + "@@" + getFechaActual() + "@@" +
                getMonto() + "@@" + getCategoria() + cuentaOrigen + "@@" + cuentaDestino + "@@" +
                descripcion + "\n";
    }

    public static Transferencia fromFileString(String fileString){
        String[] parts = fileString.split("@@");
        if (parts.length != 8) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 8 elementos pero se encontraron " + parts.length);
        }
        return new Transferencia(parts[0], parts[1], LocalDate.parse(parts[2],
                DateTimeFormatter.ofPattern("dd/MM/yyyy")), Double.parseDouble(parts[3]),
                parts[4], convertirCuenta(parts[5]), convertirCuenta(parts[6]),
                parts[7]);
    }

    private static Cuenta convertirCuenta(String part) {
        return new Cuenta();
    }

    static Categoria convertirCategoria(String part) {
        return new Categoria();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
