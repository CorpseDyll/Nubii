package nubiiwallet.nubiiwallet.Model;

public class GestorSesion {
    private static String currentUserEmail;

    // Método estático para establecer el correo electrónico del usuario actual
    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    // Método estático para obtener el correo electrónico del usuario actual
    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }
}
