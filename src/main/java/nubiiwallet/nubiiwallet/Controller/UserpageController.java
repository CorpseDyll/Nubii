package nubiiwallet.nubiiwallet.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nubiiwallet.nubiiwallet.Model.GestorSesion;
import nubiiwallet.nubiiwallet.Model.Usuario;
import nubiiwallet.nubiiwallet.Persistence.UsuarioPersistencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserpageController implements Initializable {
        @FXML public TextField nameTextField, saldoTextField;
        @FXML public Button depositarBtn, retirarBtn, transferirBtn;

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

    public void onDepositarButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nubiiwallet/nubiiwallet/depositar.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage newStage  = new Stage();
        newStage.setTitle("Depósito");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
    }

    public void onRetirarButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nubiiwallet/nubiiwallet/retiro.fxml"));
        Scene scene = null;
        scene = new Scene(fxmlLoader.load(), 400, 400);
        Stage newStage  = new Stage();
        newStage.setTitle("Retiro");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
    }

    public void onTransferirButtonClick(){

    }
}
