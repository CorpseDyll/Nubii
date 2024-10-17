package nubiiwallet.nubiiwallet.Model;

import java.time.LocalDate;

public class Transaccion {
    private String idTransaccion;
    private LocalDate fechaActual;
    private String tipoTransaccion;
    private double monto;
    private String categoria;

    public Transaccion(String tipoTransaccion, String idTransaccion, LocalDate fechaActual,
                       double monto, String categoria) {
        this.idTransaccion = idTransaccion;
        this.fechaActual = fechaActual;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.categoria = categoria;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDate getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String categoria) {
        this.tipoTransaccion = categoria;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
