package nubiiwallet.nubiiwallet.Model;

import nubiiwallet.nubiiwallet.Enums.TipoCuenta;

import java.util.UUID;

public class Cuenta {
    private String idCuenta, nombreBanco, nroCuenta, cedulaUsuario;
    private TipoCuenta tipoCuenta;

    public Cuenta() {
    }

    public Cuenta(String idCuenta, String nombreBanco, String nroCuenta, TipoCuenta tipoCuenta,
                  String cedulaUsuario) {
        this.idCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.nroCuenta = nroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.cedulaUsuario = cedulaUsuario;
    }

    public Cuenta(String nombreBanco, String nroCuenta, TipoCuenta tipoCuenta, String cedulaUsuario) {
        this(UUID.randomUUID().toString(), nombreBanco, nroCuenta, tipoCuenta, cedulaUsuario);
    }

    public String toFileString(){
        return idCuenta + "@@" + nombreBanco + "@@" + nroCuenta + "@@" + tipoCuenta + "@@" +
                cedulaUsuario;
    }

    public static Cuenta fromFileString(String fileCategoria){
        String[] parts = fileCategoria.split("@@");
        if (parts.length != 5) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 5 elementos pero se encontraron " + parts.length);
        }
        return new Cuenta(parts[0], parts[1], parts[2], TipoCuenta.valueOf(parts[3]), parts[4]);
    }

    public String getNroCuenta() {
        return nroCuenta;
    }
}
