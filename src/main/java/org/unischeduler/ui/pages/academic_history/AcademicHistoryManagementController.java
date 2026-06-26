package org.unischeduler.ui.pages.academic_history;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import org.unischeduler.backend.application.service.academc_history.AcademicHistoryInfo;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryCommand;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryResponse;
import org.unischeduler.ui.service.AcademicHistoryManagementService;
import org.unischeduler.ui.viewmodel.academic_record.AcademicRecord;

public class AcademicHistoryManagementController {

    private final AcademicHistoryManagementService historyManagementService = new AcademicHistoryManagementService();

    @FXML private TextField searchStudentField;
    @FXML private Button btnSearch;
    @FXML private Button btnCancel;
    @FXML private Button btnSave;

    @FXML private Label studentNameLabel;
    @FXML private Label programLabel;
    @FXML private Label approvedCreditsLabel;
    @FXML private Label gpaLabel;

    @FXML private TableView<AcademicRecord> coursesTable;
    @FXML private TableColumn<AcademicRecord, String> periodColumn;
    @FXML private TableColumn<AcademicRecord, String> codeColumn;
    @FXML private TableColumn<AcademicRecord, String> subjectColumn;
    @FXML private TableColumn<AcademicRecord, Integer> creditsColumn;
    @FXML private TableColumn<AcademicRecord, Double> gradeColumn;
    @FXML private TableColumn<AcademicRecord, String> statusColumn; // Esta será la columna visual con Checkbox

    private final ObservableList<AcademicRecord> academicRecordsList = FXCollections.observableArrayList();
    private String currentSearchedStudentId = "";

    @FXML
    public void initialize() {

        coursesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);


        periodColumn.setCellValueFactory(cellData -> cellData.getValue().periodProperty());
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());
        creditsColumn.setCellValueFactory(cellData -> cellData.getValue().creditsProperty().asObject());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());


        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());


        statusColumn.setCellFactory(param -> new TableCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.setOnAction(event -> {
                    AcademicRecord record = getTableRow().getItem();
                    if (record != null) {
                        if (checkBox.isSelected()) {
                            record.statusProperty().set("APROBADO");
                        } else {
                            record.statusProperty().set("REPROBADO");
                        }
                        recalcularMetricasLocales();
                    }
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected("APROBADO".equalsIgnoreCase(item));
                    setGraphic(checkBox);
                    setStyle("-fx-alignment: CENTER;"); // Centrar el checkbox en la celda
                }
            }
        });

        coursesTable.setItems(academicRecordsList);

        // Habilitar edición de notas numéricas
        coursesTable.setEditable(true);
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        gradeColumn.setOnEditCommit(event -> {
            AcademicRecord record = event.getRowValue();
            Double newGrade = event.getNewValue();

            if (newGrade != null) {
                record.gradeProperty().set(newGrade);

                if (newGrade >= 3.0) {
                    record.statusProperty().set("APROBADO");
                } else {
                    record.statusProperty().set("REPROBADO");
                }
                coursesTable.refresh();
                recalcularMetricasLocales();
            }
        });

        btnSearch.setOnAction(this::handleSearchStudent);
        btnSave.setOnAction(this::handleSaveChanges);
        btnCancel.setOnAction(this::handleClearFields);
    }

    private void handleSearchStudent(ActionEvent event) {
        String studentId = searchStudentField.getText().trim();

        if (studentId.isEmpty()) {
            mostrarAlerta("Búsqueda", "Por favor ingrese un código de estudiante válido.", Alert.AlertType.WARNING);
            return;
        }

        GetAcademicHistoryResponse response = historyManagementService.getStudentHistory(
                new GetAcademicHistoryCommand(studentId)
        );

        if (!response.isSuccessfully()) {
            mostrarAlerta("Error", "Estudiante no encontrado en el sistema.", Alert.AlertType.ERROR);
            handleClearFields(null);
            return;
        }

        currentSearchedStudentId = studentId;

        studentNameLabel.setText("Código: " + studentId);
        programLabel.setText(response.getProgramName());
        approvedCreditsLabel.setText(String.valueOf(response.getApprovedCredits()));
        gpaLabel.setText(String.format("%.2f", response.getAverage()));

        academicRecordsList.setAll(
                response.getAcademicHistory()
                        .stream()
                        .map(this::toAcademicRecord)
                        .toList()
        );
    }

    private void handleSaveChanges(ActionEvent event) {
        if (academicRecordsList.isEmpty() || currentSearchedStudentId.isEmpty()) {
            mostrarAlerta("Guardar", "No hay un historial cargado o modificado para actualizar.", Alert.AlertType.WARNING);
            return;
        }

        boolean success = historyManagementService.saveUpdatedHistory(currentSearchedStudentId, academicRecordsList);

        if (success) {
            mostrarAlerta("Éxito", "El historial académico ha sido actualizado de manera correcta.", Alert.AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error", "Ocurrió un problema interno al intentar guardar los cambios.", Alert.AlertType.ERROR);
        }
    }

    private void handleClearFields(ActionEvent event) {
        searchStudentField.clear();
        academicRecordsList.clear();
        studentNameLabel.setText("Ninguno seleccionado");
        programLabel.setText("---");
        approvedCreditsLabel.setText("0");
        gpaLabel.setText("0.00");
        currentSearchedStudentId = "";
    }

    private void recalcularMetricasLocales() {
        double totalPoints = 0;
        int totalCredits = 0;
        int approvedCredits = 0;

        for (AcademicRecord record : academicRecordsList) {
            double grade = record.gradeProperty().get();
            int credits = record.creditsProperty().get();
            String status = record.statusProperty().get();

            totalPoints += (grade * credits);
            totalCredits += credits;

            if ("APROBADO".equalsIgnoreCase(status)) {
                approvedCredits += credits;
            }
        }

        double newGpa = totalCredits > 0 ? (totalPoints / totalCredits) : 0.0;
        gpaLabel.setText(String.format("%.2f", newGpa));
        approvedCreditsLabel.setText(String.valueOf(approvedCredits));
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

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}