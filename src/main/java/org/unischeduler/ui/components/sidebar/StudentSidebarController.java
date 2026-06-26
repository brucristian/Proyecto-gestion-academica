package org.unischeduler.ui.components.sidebar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.app.AppNavigator;
import org.unischeduler.ui.app.SceneManager;

public class StudentSidebarController {
    @FXML
    private void goToDashboard(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/dashboard/StudentDashboardView.fxml"
        );
    }

    @FXML
    private void goToAcademicHistory(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/academic_history/StudentAcademicHistoryView.fxml"
        );
    }

    @FXML
    private void goToProvisionalSchedule(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/provisional_schedule/ProvisionalScheduleView.fxml"
        );
    }

    @FXML
    private void goToProfile(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/profile/AdminProfileView.fxml"
        );
    }

    @FXML
    private void goToEnrollment(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/enrollment/EnrollmentView.fxml"
        );
    }

    @FXML
    private void goToLogin(ActionEvent event) {
        AppContext.setCurrentUser(null);
        AppNavigator.navigateToLogin();
    }
}
