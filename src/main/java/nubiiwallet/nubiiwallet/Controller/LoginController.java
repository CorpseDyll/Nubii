package nubiiwallet.nubiiwallet.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nubiiwallet.nubiiwallet.Model.GestorSesion;
import nubiiwallet.nubiiwallet.Model.Usuario;
import nubiiwallet.nubiiwallet.Persistence.UsuarioPersistencia;
import nubiiwallet.nubiiwallet.Services.ArchiveService;

import java.io.IOException;

public class LoginController {
    @FXML public TextField emailTxtField;
    @FXML public PasswordField passwordField;
    @FXML public Button loginBtn, backBtn;

    private final UsuarioPersistencia usuarioPersistencia = new UsuarioPersistencia();

    @FXML
    protected void onLoginButtonClick(){
        try{
            String email = emailTxtField.getText();
            String password = passwordField.getText();
            Usuario usuario = usuarioPersistencia.findUserByEmail(email); // Obtiene el usuario por el correo electrónico.
            // Verificar si el usuario existe y la contraseña es correcta:
            if(usuario != null && usuario.getPassword().equals(password)){
                if (email.equals("admin@nubiiwallet.com") && password.equals("admin123")){
                    loadAdminDashboard(); // Cargar la pagina del admin.
                } else {
                    showAlert("Inicio de sesión exitoso", "Bienvenid@ " + usuario.getName(), Alert.AlertType.INFORMATION);
                    loadUserpageBoard(usuario); // Método para cargar la página del usuario.
                    ArchiveService.guardarRegistroLog("El usuario " + usuario.getName() +
                            " ha iniciado sesión.", 1, "loginBtn", "C:/td/persistencia/log/log.txt");
                }
            } else {
                showAlert("Inicio de Sesión fallido",
                        "Credenciales inválidas.", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to load user data", Alert.AlertType.ERROR);
        }
    }

    private void loadUserpageBoard(Usuario usuario) {
        String email = usuario.getEmail();
        GestorSesion.setCurrentUserEmail(email);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nubiiwallet/nubiiwallet/userpage.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1300, 700);
            Stage newStage  = new Stage();
            newStage.setTitle("Pagina de Usuario");
            newStage.setScene(scene);
            newStage.show();
            newStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAdminDashboard() {
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro que " +
                "desea cancelar el inicio de sesión?", ButtonType.YES, ButtonType.NO);
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

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
