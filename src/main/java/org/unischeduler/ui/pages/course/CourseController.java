package org.unischeduler.ui.pages.course;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unischeduler.backend.application.service.academic_catalog.in.course.RegisterCourseCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.course.UpdateCourseCommand;
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

    private void configureCoursesTable() {

        codeColumn.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        creditsColumn.setCellValueFactory(
                new PropertyValueFactory<>("credits")
        );
    }

    private void configurePrerequisitesTable() {

        prerequisiteCodeColumn.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );

        prerequisiteNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
    }

    private void configureCreditsComboBox() {

        creditsComboBox.getItems().addAll(
                1, 2, 3, 4
        );
    }

    private void configureCourseSelection() {

        coursesTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue != null) {

                        showCourseDetails(newValue);

                    }

                });
    }

    private void configureSearch() {

        filteredCourses =
                new FilteredList<>(courses, p -> true);

        coursesTable.setItems(filteredCourses);

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    filteredCourses.setPredicate(course -> {

                        if (newValue == null ||
                                newValue.isBlank()) {

                            return true;
                        }

                        String filter =
                                newValue.toLowerCase();

                        return course.getCode()
                                .toLowerCase()
                                .contains(filter)

                                ||

                                course.getName()
                                        .toLowerCase()
                                        .contains(filter);
                    });
                }
        );
    }

    private void loadCourses() {

        courses.setAll(
                courseUiService.loadCourses()
        );
    }

    private void showCourseDetails(
            CourseViewModel course
    ) {

        codeField.setText(
                course.getCode()
        );

        nameField.setText(
                course.getName()
        );

        creditsComboBox.setValue(
                course.getCredits()
        );

        descriptionArea.setText(
                course.getDescription()
        );

        prerequisitesTable.setItems(
                course.getPrerequisites()
        );
    }

    @FXML
    private void onNewCourse() {

        coursesTable
                .getSelectionModel()
                .clearSelection();

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

        CourseViewModel selectedCourse =
                coursesTable.getSelectionModel().getSelectedItem();

        try {

            if (selectedCourse == null) {

                RegisterCourseCommand command =
                        new RegisterCourseCommand(
                                codeField.getText().trim(),
                                nameField.getText().trim(),
                                creditsComboBox.getValue(),
                                descriptionArea.getText().trim(),
                                getPrerequisiteCodes()
                        );

                courseUiService.registerCourse(command);

            } else {

                UpdateCourseCommand command =
                        new UpdateCourseCommand(
                                selectedCourse.getId(),
                                codeField.getText().trim(),
                                nameField.getText().trim(),
                                creditsComboBox.getValue(),
                                descriptionArea.getText().trim(),
                                getPrerequisiteCodes()
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

        CourseViewModel selectedCourse =
                coursesTable.getSelectionModel().getSelectedItem();

        if (selectedCourse == null) {

            showWarning(
                    "Seleccione un curso para eliminar."
            );

            return;
        }

        try {

            courseUiService.deleteCourse(
                    selectedCourse.getId()
            );

            refreshCourses();

        } catch (Exception e) {

            showError(
                    e.getMessage()
            );
        }
    }

    private void refreshCourses() {

        loadCourses();

        clearForm();

        coursesTable.getSelectionModel().clearSelection();
    }

    private List<String> getPrerequisiteCodes() {

        return prerequisitesTable
                .getItems()
                .stream()
                .map(PrerequisiteViewModel::getCode)
                .toList();
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

        Alert alert =
                new Alert(Alert.AlertType.WARNING);

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Error");

        alert.setContentText(message);

        alert.showAndWait();
    }

}