package nubiiwallet.nubiiwallet.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {
    @FXML public Button loginBtn;
    @FXML public Button registerBtn;

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nubiiwallet/nubiiwallet/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Stage newStage  = new Stage();
        newStage.setTitle("Inicio Sesion");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
        // Cerrar la pestaña anterior.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nubiiwallet/nubiiwallet/register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 700);
        Stage newStage = new Stage();
        newStage.setTitle("Registro");
        newStage.setScene(scene);
        newStage.show();
        newStage.setResizable(false);
        // Cerrar la pestaña anterior.
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}