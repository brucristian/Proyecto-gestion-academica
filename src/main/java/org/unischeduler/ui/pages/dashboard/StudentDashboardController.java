package org.unischeduler.ui.pages.dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryCommand;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryResponse;
import org.unischeduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.app.SceneManager;
import org.unischeduler.ui.service.AcademicHistoryService;

public class StudentDashboardController {

    private final AcademicHistoryService academicHistoryService =
            new AcademicHistoryService();

    // ==========================
    // UI
    // ==========================

    @FXML
    private Label lblWelcomeMessage;

    @FXML
    private Label lblCareerName;

    @FXML
    private Label lblAcademicPeriod;

    @FXML
    private Label lblNoticeContent;

    @FXML
    private Button btnQuickEnroll;

    @FXML
    private Button btnQuickHistory;

    @FXML
    private Button btnQuickSchedule;

    @FXML
    private Button btnQuickNotifications;

    @FXML
    private Hyperlink lnkViewAllNotices;

    // ==========================
    // INIT
    // ==========================

    @FXML
    public void initialize() {
        System.out.println("StudentDashboardController initialized");

        loadUserInfo();
        loadAcademicSummary();
        configureActions();
    }

    // ==========================
    // USER INFO
    // ==========================

    private void loadUserInfo() {

        UserInfo user = AppContext.getCurrentUser();

        if (user == null) {
            lblWelcomeMessage.setText("¡Bienvenido!");
            return;
        }

        lblWelcomeMessage.setText("¡Bienvenido, " + user.getFullName() + "!");
    }

    // ==========================
    // ACADEMIC DATA (AQUÍ SE CONECTA TU SERVICE)
    // ==========================

    private void loadAcademicSummary() {

        UserInfo user = AppContext.getCurrentUser();

        if (user == null) return;

        String studentId = user.getUserId();

        GetAcademicHistoryResponse response =
                academicHistoryService.getAcademicHistory(
                        new GetAcademicHistoryCommand(studentId)
                );

        lblCareerName.setText(response.getProgramName());
        lblAcademicPeriod.setText("...");
    }

    // ==========================
    // ACTIONS
    // ==========================

    private void configureActions() {

        btnQuickEnroll.setOnAction(e ->
                SceneManager.loadPage("/ui/fxml/pages/enrollment/EnrollmentView.fxml")
        );

        btnQuickHistory.setOnAction(e ->
                SceneManager.loadPage("/ui/fxml/pages/academic_history/StudentAcademicHistoryView.fxml")
        );

        btnQuickSchedule.setOnAction(e ->
                SceneManager.loadPage("/ui/fxml/pages/provisional_schedule/ProvisionalScheduleView.fxml")
        );

        btnQuickNotifications.setOnAction(e ->
                System.out.println("Pendiente a implementar")
        );

        lnkViewAllNotices.setOnAction(e ->
                System.out.println("Pendiente a implementar")
        );
    }
}