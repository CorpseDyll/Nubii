package nubiiwallet.nubiiwallet.Model;

import nubiiwallet.nubiiwallet.Enums.TipoTransaccion;

import java.time.LocalDate;

public class Transaccion {
    private String idTransaccion, descripcion;
    private LocalDate fechaActual;
    private TipoTransaccion tipoTransaccion;
    private double monto;
    private Cuenta cuentaOrigen, cuentaDestino;
}
