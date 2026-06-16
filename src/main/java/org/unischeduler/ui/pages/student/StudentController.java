package org.unischeduler.ui.pages.student;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.unischeduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unischeduler.ui.mapper.StudentMapper;
import org.unischeduler.ui.service.StudentUiService;
import org.unischeduler.ui.viewmodel.student.ProgramViewModel;
import org.unischeduler.ui.viewmodel.student.StudentViewModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudentController {

    private final StudentUiService studentUiService = new StudentUiService();

    private List<ProgramViewModel> programs;
    private Map<String, ProgramViewModel> programsByName;

    // =====================
    // Datos personales
    // =====================

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<String> documentTypeCombo;

    @FXML
    private TextField documentNumberField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ComboBox<String> genderCombo;

    // =====================
    // Contacto
    // =====================

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    // =====================
    // Académica
    // =====================

    @FXML
    private ComboBox<String> programCombo;

    @FXML
    private ComboBox<Integer> semesterCombo;

    @FXML
    private DatePicker entryDatePicker;

    @FXML
    private ComboBox<String> statusCombo;

    // =====================
    // Botones
    // =====================

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void initialize() {

        programs = studentUiService.listAllPrograms();

        programsByName = programs.stream()
                .collect(Collectors.toMap(
                        ProgramViewModel::getName,
                        programViewModel -> programViewModel
                ));

        programCombo.getItems().addAll(programsByName.keySet());

        programCombo.setOnAction(event -> updateSemesterCombo());

        documentTypeCombo.getItems().addAll(
                "CC",
                "TI",
                "CE",
                "PASAPORTE"
        );

        genderCombo.getItems().addAll(
                "MALE",
                "FEMALE",
                "OTHER"
        );

        statusCombo.getItems().addAll(
                "ACTIVE",
                "INACTIVE"
        );
    }

    private void updateSemesterCombo() {

        String selectedProgramName = programCombo.getValue();

        if (selectedProgramName == null) {
            semesterCombo.getItems().clear();
            return;
        }

        ProgramViewModel selectedProgram =
                programsByName.get(selectedProgramName);

        if (selectedProgram == null) {
            semesterCombo.getItems().clear();
            return;
        }

        List<Integer> semesters = IntStream
                .rangeClosed(1, selectedProgram.getTotalSemesters())
                .boxed()
                .toList();

        semesterCombo.getItems().setAll(semesters);
        semesterCombo.getSelectionModel().clearSelection();
    }

    @FXML
    private void onSaveStudent() {

        try {
            String programId =programsByName.get(programCombo.getValue())
                    .getAcademicProgramId();

            RegisterStudentCommand command =
                    new RegisterStudentCommand(
                            firstNameField.getText(),
                            lastNameField.getText(),
                            documentTypeCombo.getValue(),
                            documentNumberField.getText(),
                            birthDatePicker.getValue(),
                            genderCombo.getValue(),
                            phoneField.getText(),
                            addressField.getText(),
                            emailField.getText(),
                            programId,
                            semesterCombo.getValue(),
                            entryDatePicker.getValue(),
                            statusCombo.getValue()
                    );

            RegisterStudentResponse response =
                    studentUiService.registerStudent(command);

            StudentViewModel student =
                    StudentMapper.toViewModel(response.getStudent());

            showSuccess(student);

            clearForm();

        } catch (Exception e) {

            showError(e.getMessage());

        }
    }

    @FXML
    private void onClearFields() {
        clearForm();
    }

    @FXML
    private void onCancel() {
        clearForm();
    }

    private void clearForm() {

        firstNameField.clear();
        lastNameField.clear();

        documentTypeCombo.setValue(null);
        documentNumberField.clear();

        birthDatePicker.setValue(null);
        genderCombo.setValue(null);

        emailField.clear();
        phoneField.clear();
        addressField.clear();

        programCombo.setValue(null);

        semesterCombo.getItems().clear();
        semesterCombo.setValue(null);

        entryDatePicker.setValue(null);
        statusCombo.setValue(null);
    }

    private void showSuccess(StudentViewModel student) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Registro exitoso");
        alert.setHeaderText("Estudiante registrado");

        alert.setContentText(
                """
                Código: %s

                Contraseña temporal:
                %s
                """
                        .formatted(
                                student.getStudentCode(),
                                student.getStudentPassword()
                        )
        );

        alert.showAndWait();
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("No fue posible registrar el estudiante");
        alert.setContentText(message);

        alert.showAndWait();
    }
}