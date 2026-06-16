package org.unischeduler.ui.pages.period;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.RegisterAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.UpdateAcademicPeriodCommand;
import org.unischeduler.ui.app.SceneManager;
import org.unischeduler.ui.service.PeriodUiService;
import org.unischeduler.ui.viewmodel.period.PeriodViewModel;

public class AcademicPeriodFormController {

    public enum Mode {
        CREATE,
        EDIT
    }

    private final PeriodUiService periodUiService = new PeriodUiService();

    private Mode mode = Mode.CREATE;

    private PeriodViewModel periodToEdit;

    // =====================
    // Fields
    // =====================

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> statusCombo;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button closeButton;

    @FXML
    public void initialize() {

        statusCombo.getItems().addAll(
                "ACTIVE",
                "SCHEDULED",
                "CLOSED"
        );
    }

    // =====================================================
    // CREATE MODE
    // =====================================================

    public void configureCreateMode() {

        mode = Mode.CREATE;

        saveButton.setText("Guardar Período");

        codeField.clear();
        nameField.clear();

        startDatePicker.setValue(null);
        endDatePicker.setValue(null);

        statusCombo.setValue("SCHEDULED");
    }

    // =====================================================
    // EDIT MODE
    // =====================================================

    public void configureEditMode(PeriodViewModel period) {

        mode = Mode.EDIT;
        periodToEdit = period;

        saveButton.setText("Actualizar Período");

        codeField.setText(period.getCode());
        nameField.setText(period.getName());

        startDatePicker.setValue(period.getStartDate());
        endDatePicker.setValue(period.getEndDate());

        statusCombo.setValue(period.getStatus());

    }

    // =====================================================
    // EVENTS
    // =====================================================

    @FXML
    private void onSave() {

        try {

            validateForm();

            if (mode == Mode.CREATE) {
                createPeriod();
            } else {
                updatePeriod();
            }

        } catch (Exception e) {

            showError(e.getMessage());
        }

        closeWindow();
    }

    @FXML
    private void onCancel() {
        closeWindow();
    }

    @FXML
    private void onClose() {
        closeWindow();
    }

    // =====================================================
    // CREATE
    // =====================================================

    private void createPeriod() {

        RegisterAcademicPeriodCommand command =
                new RegisterAcademicPeriodCommand(
                        codeField.getText(),
                        nameField.getText(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue(),
                        statusCombo.getValue()
                );

        periodUiService.registerPeriod(command);
    }

    // =====================================================
    // UPDATE
    // =====================================================

    private void updatePeriod() {

        UpdateAcademicPeriodCommand command =
                new UpdateAcademicPeriodCommand(
                        periodToEdit.getAcademicPeriodId(),
                        codeField.getText(),
                        nameField.getText(),
                        startDatePicker.getValue(),
                        endDatePicker.getValue(),
                        statusCombo.getValue()
                );

        periodUiService.updatePeriod(command);
    }

    // =====================================================
    // VALIDATIONS
    // =====================================================

    private void validateForm() {

        if (codeField.getText().isBlank()) {
            throw new IllegalArgumentException(
                    "Debe ingresar el código."
            );
        }

        if (nameField.getText().isBlank()) {
            throw new IllegalArgumentException(
                    "Debe ingresar el nombre."
            );
        }

        if (startDatePicker.getValue() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar la fecha de inicio."
            );
        }

        if (endDatePicker.getValue() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar la fecha de fin."
            );
        }

        if (endDatePicker.getValue()
                .isBefore(startDatePicker.getValue())) {

            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser menor que la fecha de inicio."
            );
        }

        if (statusCombo.getValue() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un estado."
            );
        }
    }

    // =====================================================
    // HELPERS
    // =====================================================

    private void closeWindow() {

        SceneManager.loadPage("/ui/fxml/pages/academic_period/AcademicPeriodView.fxml");
    }

    private void showError(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");

        alert.setHeaderText(
                "No fue posible guardar el período"
        );

        alert.setContentText(message);

        alert.showAndWait();
    }
}
