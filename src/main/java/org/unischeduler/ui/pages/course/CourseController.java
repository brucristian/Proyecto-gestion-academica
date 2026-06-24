package org.unischeduler.ui.pages.course;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.unischeduler.backend.application.service.academic_catalog.in.course.RegisterCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.course.UpdateCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.RegisterPrerequisiteCommand;
import org.unischeduler.ui.service.CourseUiService;
import org.unischeduler.ui.viewmodel.course.CourseViewModel;
import org.unischeduler.ui.viewmodel.course.PrerequisiteViewModel;

import java.util.List;

public class CourseController {

    private final CourseUiService courseUiService =
            new CourseUiService();

    private final ObservableList<CourseViewModel> courses =
            FXCollections.observableArrayList();

    private FilteredList<CourseViewModel> filteredCourses;

    // ==========================
    // LEFT PANEL
    // ==========================

    @FXML
    private TextField searchField;

    @FXML
    private TableView<CourseViewModel> coursesTable;

    @FXML
    private TableColumn<CourseViewModel, String> codeColumn;

    @FXML
    private TableColumn<CourseViewModel, String> nameColumn;

    @FXML
    private TableColumn<CourseViewModel, Integer> creditsColumn;

    @FXML
    private Button newCourseButton;

    @FXML
    private Button saveCourseButton;

    @FXML
    private Button deleteCourseButton;

    // ==========================
    // RIGHT PANEL
    // ==========================

    @FXML
    private TextField codeField;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Integer> creditsComboBox;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TableView<PrerequisiteViewModel> prerequisitesTable;

    @FXML
    private TableColumn<PrerequisiteViewModel, String> prerequisiteCodeColumn;

    @FXML
    private TableColumn<PrerequisiteViewModel, String> prerequisiteNameColumn;

    @FXML
    private Button addPrerequisiteButton;

    @FXML
    private Button removePrerequisiteButton;

    @FXML
    public void initialize() {

        configureCoursesTable();
        configurePrerequisitesTable();
        configureCreditsComboBox();
        configureCourseSelection();
        configureSearch();
        loadCourses();
    }

    // ==========================
    // CONFIGURATION
    // ==========================

    private void configureCoursesTable() {

        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
    }

    private void configurePrerequisitesTable() {

        prerequisiteCodeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        prerequisiteNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void configureCreditsComboBox() {
        creditsComboBox.getItems().addAll(1, 2, 3, 4);
    }

    private void configureCourseSelection() {

        coursesTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {
                    if (newValue != null) {
                        showCourseDetails(newValue);
                    }
                });
    }

    private void configureSearch() {

        filteredCourses = new FilteredList<>(courses, p -> true);
        coursesTable.setItems(filteredCourses);

        searchField.textProperty().addListener((obs, oldValue, newValue) -> {

            filteredCourses.setPredicate(course -> {

                if (newValue == null || newValue.isBlank()) {
                    return true;
                }

                String filter = newValue.toLowerCase();

                return course.getCode().toLowerCase().contains(filter)
                        || course.getName().toLowerCase().contains(filter);
            });
        });
    }

    // ==========================
    // LOAD DATA
    // ==========================

    private void loadCourses() {
        courses.setAll(courseUiService.loadCourses());
    }

    // ==========================
    // UI BEHAVIOR
    // ==========================

    private void showCourseDetails(CourseViewModel course) {

        codeField.setText(course.getCode());
        nameField.setText(course.getName());
        creditsComboBox.setValue(course.getCredits());
        descriptionArea.setText(course.getDescription());

        prerequisitesTable.setItems(course.getPrerequisites());
    }

    // ==========================
    // PREREQUISITES
    // ==========================

    @FXML
    private void onAddPrerequisite() {

        CourseViewModel selectedCourse =
                coursesTable.getSelectionModel().getSelectedItem();

        if (selectedCourse == null) {
            showWarning("Seleccione un curso primero.");
            return;
        }

        List<CourseViewModel> availableCourses =
                courses.stream()
                        .filter(course ->
                                prerequisitesTable.getItems()
                                        .stream()
                                        .noneMatch(p ->
                                                p.getId().equals(course.getId())
                                        )
                        )
                        .toList();

        if (availableCourses.isEmpty()) {
            showWarning("No hay cursos disponibles para agregar.");
            return;
        }

        ChoiceDialog<CourseViewModel> dialog =
                new ChoiceDialog<>(availableCourses.get(0), availableCourses);

        dialog.setTitle("Agregar prerrequisito");
        dialog.setHeaderText("Seleccione un curso");
        dialog.setContentText("Curso:");

        dialog.showAndWait().ifPresent(course -> {

            try {

                List<String> updatedPrerequisites =
                        prerequisitesTable.getItems()
                                .stream()
                                .map(PrerequisiteViewModel::getId)
                                .collect(java.util.stream.Collectors.toList());

                updatedPrerequisites.add(course.getId());

                courseUiService.registerPrerequisite(
                        new RegisterPrerequisiteCommand(
                                selectedCourse.getId(),
                                updatedPrerequisites
                        )
                );

                showInfo("Prerrequisito agregado correctamente.");

                refreshCourses();

            } catch (Exception e) {
                showError(e.getMessage());
            }
        });
    }


    @FXML
    private void onRemovePrerequisite() {

        CourseViewModel selectedCourse =
                coursesTable.getSelectionModel().getSelectedItem();

        if (selectedCourse == null) {
            showWarning("Seleccione un curso primero.");
            return;
        }

        PrerequisiteViewModel selected =
                prerequisitesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning("Seleccione un prerrequisito para eliminar.");
            return;
        }

        // 🔔 warning de confirmación (antes de ejecutar)
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar eliminación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Desea eliminar este prerrequisito?");

        confirm.showAndWait().ifPresent(result -> {

            if (result != javafx.scene.control.ButtonType.OK) {
                return;
            }

            try {
                // 1. construir nueva lista sin el seleccionado
                List<String> updatedPrerequisites =
                        prerequisitesTable.getItems()
                                .stream()
                                .filter(p -> !p.getId().equals(selected.getId()))
                                .map(PrerequisiteViewModel::getId)
                                .toList();

                // 2. persistir
                courseUiService.registerPrerequisite(
                        new RegisterPrerequisiteCommand(
                                selectedCourse.getId(),
                                updatedPrerequisites
                        )
                );

                // 3. mensaje de éxito
                showInfo("Prerrequisito eliminado correctamente.");

                // 4. refrescar UI
                refreshCourses();

            } catch (Exception e) {
                showError(e.getMessage());
            }
        });
    }

    // ==========================
    // COURSE ACTIONS
    // ==========================

    @FXML
    private void onNewCourse() {

        coursesTable.getSelectionModel().clearSelection();
        clearForm();
    }

    private void clearForm() {

        codeField.clear();
        nameField.clear();
        descriptionArea.clear();
        creditsComboBox.setValue(null);
        prerequisitesTable.getItems().clear();
    }

    @FXML
    private void onSaveCourse() {

        if (!validateForm()) {
            return;
        }

        try {

            CourseViewModel selected =
                    coursesTable.getSelectionModel().getSelectedItem();

            List<String> prerequisiteIds =
                    prerequisitesTable.getItems()
                            .stream()
                            .map(PrerequisiteViewModel::getId)
                            .toList();

            if (selected == null) {

                RegisterCourseCommand command =
                        new RegisterCourseCommand(
                                codeField.getText().trim(),
                                nameField.getText().trim(),
                                creditsComboBox.getValue(),
                                descriptionArea.getText().trim(),
                                prerequisiteIds
                        );

                courseUiService.registerCourse(command);

            } else {

                UpdateCourseCommand command =
                        new UpdateCourseCommand(
                                selected.getId(),
                                codeField.getText().trim(),
                                nameField.getText().trim(),
                                creditsComboBox.getValue(),
                                descriptionArea.getText().trim(),
                                prerequisiteIds
                        );

                courseUiService.updateCourse(command);
            }

            refreshCourses();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void onDeleteCourse() {

        CourseViewModel selected =
                coursesTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showWarning("Seleccione un curso para eliminar.");
            return;
        }

        try {

            courseUiService.deleteCourse(selected.getId());
            refreshCourses();

        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    // ==========================
    // UTIL
    // ==========================

    private void refreshCourses() {
        loadCourses();
        clearForm();
        coursesTable.getSelectionModel().clearSelection();
    }

    private boolean validateForm() {

        if (codeField.getText().isBlank()) {
            showWarning("El código es obligatorio.");
            return false;
        }

        if (nameField.getText().isBlank()) {
            showWarning("El nombre es obligatorio.");
            return false;
        }

        if (creditsComboBox.getValue() == null) {
            showWarning("Seleccione los créditos.");
            return false;
        }

        return true;
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}