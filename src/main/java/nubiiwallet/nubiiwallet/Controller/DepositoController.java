package nubiiwallet.nubiiwallet.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nubiiwallet.nubiiwallet.Model.Deposito;
import nubiiwallet.nubiiwallet.Persistence.DepositoPersistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DepositoController {

    @FXML public TextField depositoField, fechaActualField, montoField, categoriaField,
            cuentaDestinoField;
    @FXML public Button guardarButton;

    DepositoPersistencia depositoPersistencia = new DepositoPersistencia();

    @FXML
    public void initialize() {
        // Inicializar el campo de Fecha Actual con la fecha del sistema
        LocalDate fechaActual = LocalDate.now();
        fechaActualField.setText(fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        fechaActualField.setEditable(false);
    }

    public void onGuardarButtonClick(){
        String tipoDeposito = depositoField.getText();
        String fechaActual = fechaActualField.getText();
        String monto = montoField.getText();
        String categoria = categoriaField.getText();
        String cuentaDestino = cuentaDestinoField.getText();

        Deposito deposito = new Deposito(tipoDeposito, LocalDate.parse(fechaActual,
                DateTimeFormatter.ofPattern("dd/MM/yyyy")), Double.parseDouble(monto), categoria,
                cuentaDestino);
        try{
            depositoPersistencia.guardarDeposito(deposito);
            showAlert("Registro exitoso!!","Deposito guardado con éxito en la persistencia",
                    Alert.AlertType.INFORMATION);
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

