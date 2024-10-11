module nubiiwallet.nubiiwallet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens nubiiwallet.nubiiwallet to javafx.fxml;
    exports nubiiwallet.nubiiwallet;
    exports nubiiwallet.nubiiwallet.Controller;
    opens nubiiwallet.nubiiwallet.Controller to javafx.fxml;
}