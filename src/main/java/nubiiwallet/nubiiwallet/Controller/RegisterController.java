package nubiiwallet.nubiiwallet.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nubiiwallet.nubiiwallet.Model.Usuario;
import nubiiwallet.nubiiwallet.Persistence.UsuarioPersistencia;
import nubiiwallet.nubiiwallet.Services.ArchiveService;

import java.io.IOException;

public class RegisterController {
    @FXML public TextField nameTxtField, cedulaTxtField, phoneTxtField, direccionTxtField, emailTxtField;
    @FXML public PasswordField passwordField;
    @FXML public Button toGoBackBtn, registerBtn;

    private final ArchiveService archiveService = new ArchiveService();
    private final UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia();

    public void onRegisterButtonClick(){
        String name = nameTxtField.getText();
        String cedula = cedulaTxtField.getText();
        String telefono = phoneTxtField.getText();
        String direccion = direccionTxtField.getText();
        String email = emailTxtField.getText();
        String password = passwordField.getText();

        Usuario usuario = new Usuario(email, password, telefono, name, cedula, direccion);
        try{
            usuarioPersistencia.guardarUsuario(usuario);
            showAlert("Registro exitoso!!", "Bienvenid@ " + usuario.getNombre(), Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para mostrar una alerta.
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onToGoBackButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro que " +
                "desea regresar?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                // Se cierra la ventana de registro y vuelve a la ventana principal.
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nubiiwallet/nubiiwallet/homepage.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 1300, 700);
                    Stage newStage  = new Stage();
                    newStage.setTitle("Pagina Principal");
                    newStage.setScene(scene);
                    newStage.show();
                    newStage.setResizable(false);
                    // Cerrar la pestaña anterior.
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
