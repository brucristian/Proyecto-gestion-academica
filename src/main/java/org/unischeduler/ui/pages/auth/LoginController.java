package org.unischeduler.ui.pages.auth;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.unischeduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.app.AppNavigator;
import org.unischeduler.ui.service.AuthUiService;

public class LoginController {
    private final AuthUiService authUiService = new AuthUiService();

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void login() {

        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username);
        System.out.println(password);
        LoginUserResponse response =
                authUiService.login(username, password);

        if (response.isSuccessfully()) {
            AppContext.setCurrentUser(response.getUser());
            AppNavigator.navigateToMainLayout();
            System.out.println("usuario encontrado");
        } else {
            messageLabel.setText(response.getMessage());
            System.out.println("usuario no encontrado");
        }
    }
}
