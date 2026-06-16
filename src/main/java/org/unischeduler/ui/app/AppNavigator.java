package org.unischeduler.ui.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppNavigator {

    private static Stage primaryStage;

    private AppNavigator() {}

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void navigateToMainLayout() {

        try {

            Parent root = FXMLLoader.load(
                    AppNavigator.class.getResource(
                            "/ui/fxml/layouts/MainLayout.fxml"
                    )
            );

            Scene scene = new Scene(root, 1400, 800);

            primaryStage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}