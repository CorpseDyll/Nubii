package nubiiwallet.nubiiwallet.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import nubiiwallet.nubiiwallet.Model.Retiro;
import nubiiwallet.nubiiwallet.Persistence.RetiroPersistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RetiroController {

    @FXML public TextField txtRetiro, txtFecha, txtMonto, txtCategoria, txtCuentaOrigen;
    @FXML public Button btnConfirmarRetiro;

    RetiroPersistencia retiroPersistencia = new RetiroPersistencia();

    // Formato deseado para mostrar y parsear la fecha
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Método que se ejecuta al inicializar la ventana
    @FXML
    public void initialize() {
        // Inicializar el campo "Retiro" con un valor fijo y hacerlo no editable
        txtRetiro.setText("Retiro");
        txtRetiro.setEditable(false);

        // Inicializar el campo de la fecha con la fecha actual en formato dd/MM/yyyy
        LocalDate fechaActual = LocalDate.now();
        txtFecha.setText(fechaActual.format(formatter));  // Formatear la fecha actual
        txtFecha.setEditable(false);  // Asegurar que no se pueda editar
    }

    // Método que se ejecuta cuando se pulsa el botón "Confirmar Retiro"
    public void onConfirmarRetiroButtonClick() {
        // Obtener los valores ingresados por el usuario
        String tipoRetiro = txtRetiro.getText();
        String fechaActual = txtFecha.getText();
        String monto = txtMonto.getText();
        String categoria = txtCategoria.getText();
        String cuentaOrigen = txtCuentaOrigen.getText();

        // Validaciones simples.
        if (monto.isEmpty() || categoria.isEmpty() || cuentaOrigen.isEmpty()) {
            showAlert("Campos incompletos", "Todos los campos deben estar llenos.", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Parsear la fecha en formato dd/MM/yyyy
            LocalDate fecha = LocalDate.parse(fechaActual, formatter);

            // Crear el objeto Retiro
            Retiro retiro = new Retiro(tipoRetiro, fecha, Double.parseDouble(monto), categoria, cuentaOrigen);

            // Guardar el retiro en persistencia
            retiroPersistencia.guardarRetiro(retiro);

            // Mostrar confirmación
            showAlert("Retiro exitoso!!", "El retiro ha sido realizado con éxito.", Alert.AlertType.INFORMATION);

        } catch (DateTimeParseException e) {
            showAlert("Error en la fecha", "La fecha ingresada no tiene el formato correcto (dd/MM/yyyy).", Alert.AlertType.ERROR);
        } catch (NumberFormatException e) {
            showAlert("Error de formato", "El monto debe ser un número válido.", Alert.AlertType.ERROR);
        } catch (IOException e) {
            showAlert("Error en persistencia", "Hubo un error al guardar el retiro.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            showAlert("Error inesperado", e.getMessage(), Alert.AlertType.ERROR);
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

