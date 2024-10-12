package nubiiwallet.nubiiwallet.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import nubiiwallet.nubiiwallet.Model.GestorSesion;
import nubiiwallet.nubiiwallet.Model.Usuario;
import nubiiwallet.nubiiwallet.Persistence.UsuarioPersistencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserpageController implements Initializable {
        @FXML public TextField nameTextField, saldoTextField;

        private final UsuarioPersistencia userPersistence = new UsuarioPersistencia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userEmail = GestorSesion.getCurrentUserEmail();
        Usuario usuario;
        try {
            usuario = userPersistence.findUserByEmail(userEmail);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (usuario != null){
            nameTextField.setText(usuario.getNombre());
            saldoTextField.setText("$ 10.000");
        }
    }
}
