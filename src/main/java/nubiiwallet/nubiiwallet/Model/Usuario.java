package nubiiwallet.nubiiwallet.Model;

import java.util.UUID;

public class Usuario {

    private String id, name, cedula, phone, direccion, email, password;

    // Constructor principal.
    public Usuario(String id, String email, String password, String phone, String name, String cedula,
                   String direccion) {
        this.id = id;
        this.name = name;
        this.cedula = cedula;
        this.phone = phone;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
    }
    // Constructor secundario.
    public Usuario(String email, String password, String phone, String name, String cedula,
                   String direccion) {
        this(UUID.randomUUID().toString(), email, password, phone, name, cedula, direccion);
    }

    // Método para convertir el usuario a una cadena de texto en formato de archivo
    public String toFileString() {
        return id + "@@" + email + "@@" + password + "@@" + phone + "@@" + name + "@@" + cedula +
                "@@" + direccion;
    }

    // Método estático para crear un usuario a partir de una cadena de texto
    public static Usuario fromFileString(String fileString) {
        String[] parts = fileString.split("@@");
        if (parts.length != 7) {
            throw new IllegalArgumentException("El formato del archivo no es válido, se esperaban 8 elementos pero se encontraron " + parts.length);
        }
        return new Usuario(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}