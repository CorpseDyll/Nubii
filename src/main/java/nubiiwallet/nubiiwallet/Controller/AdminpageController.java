package nubiiwallet.nubiiwallet.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import nubiiwallet.nubiiwallet.Enums.TipoCuenta;
import nubiiwallet.nubiiwallet.Model.Cuenta;
import nubiiwallet.nubiiwallet.Persistence.CuentaPersistencia;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminpageController implements Initializable {
        @FXML public TextField nombreBancoTxtField, nroCuentaTxtField, cedulaUsuarioTxtField;
        @FXML public ComboBox<TipoCuenta> tipoCuentaComboBox;
        @FXML public Button agregarCuentaBtn;

        private final CuentaPersistencia cuentaPersistencia = new CuentaPersistencia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoCuentaComboBox.getItems().setAll(TipoCuenta.values());
    }

    public void onAgregarCuentaButtonClick(){
        String nombreBanco = nombreBancoTxtField.getText();
        String nroCuenta = nroCuentaTxtField.getText();
        String cedulaUsuario = cedulaUsuarioTxtField.getText();
        TipoCuenta tipoCuenta = tipoCuentaComboBox.getValue();

        Cuenta cuenta = new Cuenta(nombreBanco, nroCuenta, tipoCuenta, cedulaUsuario);
        try{
            cuentaPersistencia.guardarCuenta(cuenta);
            showAlert("Cuenta creada exitosamente!!", "Cuenta:  " + cuenta, Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
