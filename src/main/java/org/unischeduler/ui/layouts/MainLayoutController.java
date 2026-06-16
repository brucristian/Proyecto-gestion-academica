package org.unischeduler.ui.layouts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.unischeduler.ui.app.SceneManager;

import java.io.IOException;

public class MainLayoutController {

    @FXML
    private StackPane navbarContainer;

    @FXML
    private StackPane sidebarContainer;

    @FXML
    private StackPane contentContainer;

    @FXML
    public void initialize() {

        try {

            navbarContainer.getChildren().add(
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/ui/fxml/components/navbar/Navbar.fxml"
                            )
                    )
            );

            sidebarContainer.getChildren().add(
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/ui/fxml/components/sidebar/Sidebar.fxml"
                            )
                    )
            );

            SceneManager.setContentContainer(contentContainer);

            SceneManager.loadPage(
                    "/ui/fxml/pages/dashboard/DashboardView.fxml"
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
