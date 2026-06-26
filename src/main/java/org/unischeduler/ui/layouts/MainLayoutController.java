package org.unischeduler.ui.layouts;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import org.unischeduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.app.SceneManager;

import java.io.IOException;

public class
MainLayoutController {

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

            SceneManager.setContentContainer(contentContainer);

            UserInfo currentUser = AppContext.getCurrentUser();

            if ("ADMIN".equals(currentUser.getRole())) {

                sidebarContainer.getChildren().add(
                        FXMLLoader.load(
                                getClass().getResource(
                                        "/ui/fxml/components/sidebar/AdminSidebar.fxml"
                                )
                        )
                );

                SceneManager.loadPage(
                        "/ui/fxml/pages/dashboard/AdminDashboardView.fxml"
                );

            } else {

                sidebarContainer.getChildren().add(
                        FXMLLoader.load(
                                getClass().getResource(
                                        "/ui/fxml/components/sidebar/StudentSidebar.fxml"
                                )
                        )
                );

                SceneManager.loadPage(
                        "/ui/fxml/pages/dashboard/StudentDashboardView.fxml"
                );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAdminLayout() {

        try {

            sidebarContainer.getChildren().clear();

            sidebarContainer.getChildren().add(
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/ui/fxml/components/sidebar/AdminSidebar.fxml"
                            )
                    )
            );

            SceneManager.loadPage(
                    "/ui/fxml/pages/dashboard/AdminDashboardView.fxml"
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadStudentLayout() {

        try {

            sidebarContainer.getChildren().clear();

            sidebarContainer.getChildren().add(
                    FXMLLoader.load(
                            getClass().getResource(
                                    "/ui/fxml/components/sidebar/StudentSidebar.fxml"
                            )
                    )
            );

            SceneManager.loadPage(
                    "/ui/fxml/pages/dashboard/StudentDashboardView.fxml"
            );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
