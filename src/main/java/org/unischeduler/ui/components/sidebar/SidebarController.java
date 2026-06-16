package org.unischeduler.ui.components.sidebar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.unischeduler.ui.app.SceneManager;

public class SidebarController {

    @FXML
    private void goToDashboard(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/dashboard/DashboardView.fxml"
        );
    }

    @FXML
    private void goToAcademicPeriods(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/academic_period/AcademicPeriodView.fxml"
        );
    }

    @FXML
    private void goToCourses(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/course/CourseView.fxml"
        );
    }

    @FXML
    private void goToGroups(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/group/GroupView.fxml"
        );
    }

    @FXML
    private void goToStudents(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/student/StudentView.fxml"
        );
    }

    @FXML
    private void goToEnrollment(ActionEvent event) {

        SceneManager.loadPage(
                "/ui/fxml/pages/enrollment/EnrollmentView.fxml"
        );
    }
}