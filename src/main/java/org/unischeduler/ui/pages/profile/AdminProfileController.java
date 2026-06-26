package org.unischeduler.ui.pages.profile;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.unischeduler.backend.application.service.auth.ChangePasswordCommand;
import org.unischeduler.backend.application.service.auth.ChangePasswordResponse;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.service.PerfilUiService;
// Asegúrate de importar la clase UserInfo dependiendo de su paquete
// import org.unischeduler.backend.domain.model.user.UserInfo;

public class AdminProfileController {

    // =====================================
    // Servicios
    // =====================================

    private final PerfilUiService perfilUiService = new PerfilUiService(null);

    // =====================================
    // Elementos de la UI
    // =====================================

    @FXML
    private ImageView profileImage;

    // Información Personal (Bloqueados desde el FXML)
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtPrograma;

    @FXML
    private TextField txtAnio;

    @FXML
    private Button btnGuardar;

    // Cambio de Contraseña (Editables)
    @FXML
    private PasswordField txtActual;

    @FXML
    private PasswordField txtNueva;

    @FXML
    private PasswordField txtConfirmar;

    @FXML
    private Button btnActualizarPassword;

    // =====================================
    // Inicialización
    // =====================================

    @FXML
    public void initialize() {
        btnActualizarPassword.setOnAction(event -> actualizarContrasena());

        // Cargar los datos del usuario al inicializar la vista
        cargarDatosUsuario();
    }

    // =====================================
    // Lógica de Negocio
    // =====================================

    private void cargarDatosUsuario() {
        try {
            var currentUser = AppContext.getCurrentUser();

            if (currentUser != null) {
                // Cargar la información principal disponible en UserInfo
                txtNombre.setText(currentUser.getFullName() != null ? currentUser.getFullName() : "");
                txtCorreo.setText(currentUser.getEmail() != null ? currentUser.getEmail() : "");

                // Mapear el rol al programa académico por el momento
                txtPrograma.setText(currentUser.getRole() != null ? currentUser.getRole() : "Sin asignar");

                /* * NOTA: Los siguientes campos pueden ser actualizados
                 * si tu objeto 'UserRoleInfo' contiene getters para ellos.
                 * Ejemplo: txtTelefono.setText(currentUser.getUserRoleInfo().getPhone());
                 */
                txtTelefono.setText("No registrado");
                txtAnio.setText("No registrado");
            }
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Aviso", "Hubo un problema al cargar algunos datos del perfil.");
        }
    }

    private void actualizarContrasena() {
        String actual = txtActual.getText();
        String nueva = txtNueva.getText();
        String confirmar = txtConfirmar.getText();

        // Validaciones en la vista
        if (actual.isBlank() || nueva.isBlank() || confirmar.isBlank()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Advertencia", "Debe completar todos los campos de contraseña.");
            return;
        }

        if (!nueva.equals(confirmar)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Las nuevas contraseñas no coinciden.");
            return;
        }

        try {
            String userId = AppContext.getCurrentUser().getUserId();

            // Creamos el comando con los datos de la vista
            ChangePasswordCommand command = new ChangePasswordCommand(userId, actual, nueva, confirmar);

            // Ejecutamos el caso de uso a través del UiService
            ChangePasswordResponse response = perfilUiService.changePassword(command);

            // Evaluamos la respuesta
            if (response.isSuccessfully()) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", response.getMessage());

                // Limpiamos los campos solo si fue exitoso
                txtActual.clear();
                txtNueva.clear();
                txtConfirmar.clear();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al cambiar contraseña", response.getMessage());
            }

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error del sistema", "Ocurrió un problema inesperado: " + e.getMessage());
        }
    }

    // =====================================
    // Métodos Auxiliares
    // =====================================

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}