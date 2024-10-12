package nubiiwallet.nubiiwallet.Enums;

public enum TipoCuenta {
    AHORRO("Ahorro"),
    CORRIENTE("Corriente");

    private String nombre;

    TipoCuenta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
