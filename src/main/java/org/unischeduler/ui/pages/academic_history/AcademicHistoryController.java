package org.unischeduler.ui.pages.academic_history;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.unischeduler.backend.application.service.academc_history.AcademicHistoryInfo;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryCommand;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryResponse;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.service.AcademicHistoryService;
import org.unischeduler.ui.viewmodel.academic_record.AcademicRecord;

public class AcademicHistoryController {

    private final AcademicHistoryService academicHistoryService =
            new AcademicHistoryService();

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label programLabel;

    @FXML
    private Label approvedCreditsLabel;

    @FXML
    private Label gpaLabel;

    @FXML
    private TableView<AcademicRecord> coursesTable;

    @FXML
    private TableColumn<AcademicRecord, String> codeColumn;

    @FXML
    private TableColumn<AcademicRecord, String> subjectColumn;

    @FXML
    private TableColumn<AcademicRecord, Integer> creditsColumn;

    @FXML
    private TableColumn<AcademicRecord, String> periodColumn;

    @FXML
    private TableColumn<AcademicRecord, Double> gradeColumn;

    @FXML
    private TableColumn<AcademicRecord, String> statusColumn;

    @FXML
    public void initialize() {

        coursesTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        codeColumn.setCellValueFactory(cellData ->
                cellData.getValue().codeProperty());

        subjectColumn.setCellValueFactory(cellData ->
                cellData.getValue().subjectProperty());

        creditsColumn.setCellValueFactory(cellData ->
                cellData.getValue().creditsProperty().asObject());

        periodColumn.setCellValueFactory(cellData ->
                cellData.getValue().periodProperty());

        gradeColumn.setCellValueFactory(cellData ->
                cellData.getValue().gradeProperty().asObject());

        statusColumn.setCellValueFactory(cellData ->
                cellData.getValue().statusProperty());

        loadAcademicHistory();
    }

    private void loadAcademicHistory() {

        String studentId = AppContext
                .getCurrentUser()
                .getUserId();

        GetAcademicHistoryResponse response =
                academicHistoryService.getAcademicHistory(
                        new GetAcademicHistoryCommand(studentId)
                );

        if (!response.isSuccessfully()) {
            programLabel.setText("-");
            approvedCreditsLabel.setText("0");
            gpaLabel.setText("0.0");
            return;
        }

        studentNameLabel.setText(
                AppContext.getCurrentUser().getFullName()
        );

        programLabel.setText(response.getProgramName());

        approvedCreditsLabel.setText(
                String.valueOf(response.getApprovedCredits())
        );

        gpaLabel.setText(
                String.format("%.2f", response.getAverage())
        );

        coursesTable.getItems().setAll(
                response.getAcademicHistory()
                        .stream()
                        .map(this::toAcademicRecord)
                        .toList()
        );
    }

    private AcademicRecord toAcademicRecord(AcademicHistoryInfo info) {
        return new AcademicRecord(
                info.getCode(),
                info.getName(),
                info.getCredits(),
                info.getPeriod(),
                info.getNote(),
                info.getStatus()
        );
    }
}