package org.unischeduler.ui.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class SceneManager {

    private static StackPane contentContainer;

    private SceneManager() {
    }

    public static void setContentContainer(StackPane container) {
        contentContainer = container;
    }

    public static void loadPage(String fxmlPath) {

        try {

            Node page = FXMLLoader.load(
                    SceneManager.class.getResource(fxmlPath)
            );

            contentContainer.getChildren().clear();
            contentContainer.getChildren().add(page);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FXMLLoader loadPageWithLoader(String fxmlPath) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    SceneManager.class.getResource(fxmlPath)
            );

            Node page = loader.load();

            contentContainer.getChildren().clear();
            contentContainer.getChildren().add(page);

            return loader;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}