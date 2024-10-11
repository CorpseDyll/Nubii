package nubiiwallet.nubiiwallet.Persistence;

import nubiiwallet.nubiiwallet.Model.Usuario;
import nubiiwallet.nubiiwallet.Services.ArchiveService;

import java.io.IOException;
import java.util.ArrayList;

public class UsuarioPersistencia {

    private final String ruta= Persistencia.obtenerRutaProperties("rutaArchivoUsuarios");

    public void guardarUsuario(Usuario usuario) throws IOException {
        ArchiveService.guardarRegistro(ruta, usuario.toFileString(), false);
        ArchiveService.guardarRegistroLog("Nuevo usuario registrado en la plataforma: "
                + usuario.getName(), 1, "registerBtn", "C:/td/persistencia/log/log.txt");
    }

    public ArrayList<Usuario> cargarUsuarios() throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<String> dataUsuarios = ArchiveService.leerRegistro(ruta);
        for(String ob : dataUsuarios){
            usuarios.add(Usuario.fromFileString(ob));
        }
        return usuarios;
    }

    public Usuario findUserByEmail(String email) throws IOException {
        ArrayList<Usuario> usuarios = cargarUsuarios();
        for(Usuario u: usuarios){
            if(u.getEmail().equalsIgnoreCase(email)){
                return u;
            }
        }
        return null;
    }
}
