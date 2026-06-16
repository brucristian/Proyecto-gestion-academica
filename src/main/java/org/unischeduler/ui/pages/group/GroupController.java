package org.unischeduler.ui.pages.group;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unischeduler.backend.application.service.academic_programming.in.RegisterGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.UpdateGroupCommand;
import org.unischeduler.ui.service.GroupUiService;
import org.unischeduler.ui.viewmodel.group.CourseSelectionViewModel;
import org.unischeduler.ui.viewmodel.group.GroupScheduleViewModel;
import org.unischeduler.ui.viewmodel.group.GroupViewModel;
import org.unischeduler.ui.viewmodel.group.TeacherViewModel;

public class GroupController {

    private final GroupUiService groupUiService =
            new GroupUiService();

    private final ObservableList<GroupViewModel> groups =
            FXCollections.observableArrayList();

    private FilteredList<GroupViewModel> filteredGroups;

    // ==========================
    // FORM
    // ==========================

    @FXML
    private ComboBox<CourseSelectionViewModel> courseComboBox;

    @FXML
    private TextField groupCodeField;

    @FXML
    private ComboBox<TeacherViewModel> teacherComboBox;

    @FXML
    private Spinner<Integer> capacitySpinner;

    @FXML
    private Spinner<Integer> availableSlotsSpinner;

    // ==========================
    // SCHEDULES
    // ==========================

    @FXML
    private TableView<GroupScheduleViewModel> scheduleTable;

    @FXML
    private TableColumn<GroupScheduleViewModel, String> dayColumn;

    @FXML
    private TableColumn<GroupScheduleViewModel, String> startTimeColumn;

    @FXML
    private TableColumn<GroupScheduleViewModel, String> endTimeColumn;

    @FXML
    private TableColumn<GroupScheduleViewModel, String> roomColumn;

    // ==========================
    // GROUPS TABLE
    // ==========================

    @FXML
    private TextField searchField;

    @FXML
    private TableView<GroupViewModel> groupsTable;

    @FXML
    private TableColumn<GroupViewModel, String> courseColumn;

    @FXML
    private TableColumn<GroupViewModel, String> groupColumn;

    @FXML
    private TableColumn<GroupViewModel, String> teacherColumn;

    @FXML
    private TableColumn<GroupViewModel, Integer> capacityColumn;

    @FXML
    private TableColumn<GroupViewModel, Integer> availableColumn;

    // ==========================
    // INITIALIZE
    // ==========================

    @FXML
    public void initialize() {

        configureGroupsTable();

        configureScheduleTable();

        configureSearch();

        configureGroupSelection();

        configureSpinners();

        loadCourses();

        loadTeachers();

        loadGroups();
    }

    // ==========================
    // CONFIGURATION
    // ==========================

    private void configureGroupsTable() {

        courseColumn.setCellValueFactory(
                new PropertyValueFactory<>("courseName")
        );

        groupColumn.setCellValueFactory(
                new PropertyValueFactory<>("groupCode")
        );

        teacherColumn.setCellValueFactory(
                new PropertyValueFactory<>("teacherName")
        );

        capacityColumn.setCellValueFactory(
                new PropertyValueFactory<>("capacity")
        );

        availableColumn.setCellValueFactory(
                new PropertyValueFactory<>("capacity")
        );
    }

    private void configureScheduleTable() {

        dayColumn.setCellValueFactory(
                new PropertyValueFactory<>("day")
        );

        startTimeColumn.setCellValueFactory(
                new PropertyValueFactory<>("startTime")
        );

        endTimeColumn.setCellValueFactory(
                new PropertyValueFactory<>("endTime")
        );

        roomColumn.setCellValueFactory(
                new PropertyValueFactory<>("room")
        );
    }

    private void configureSpinners() {

        capacitySpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        1,
                        100,
                        30
                )
        );

        availableSlotsSpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(
                        0,
                        100,
                        30
                )
        );
    }

    private void configureGroupSelection() {

        groupsTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue != null) {

                        showGroupDetails(newValue);
                    }
                });
    }

    private void configureSearch() {

        filteredGroups =
                new FilteredList<>(groups, p -> true);

        groupsTable.setItems(filteredGroups);

        searchField.textProperty().addListener(
                (obs, oldValue, newValue) -> {

                    filteredGroups.setPredicate(group -> {

                        if (newValue == null ||
                                newValue.isBlank()) {

                            return true;
                        }

                        String filter =
                                newValue.toLowerCase();

                        return group.getCourseName()
                                .toLowerCase()
                                .contains(filter)

                                ||

                                group.getGroupCode()
                                        .toLowerCase()
                                        .contains(filter)

                                ||

                                group.getTeacherName()
                                        .toLowerCase()
                                        .contains(filter);
                    });
                }
        );
    }

    // ==========================
    // LOAD DATA
    // ==========================

    private void loadGroups() {

        groups.setAll(
                groupUiService.loadGroups()
        );
    }

    private void loadCourses() {

        courseComboBox.getItems().setAll(
                groupUiService.loadCourses()
        );
    }

    private void loadTeachers() {

        teacherComboBox.getItems().setAll(
                groupUiService.loadTeachers()
        );
    }

    // ==========================
    // SHOW DETAILS
    // ==========================

    private void showGroupDetails(
            GroupViewModel group
    ) {

        groupCodeField.setText(
                group.getGroupCode()
        );

        capacitySpinner.getValueFactory()
                .setValue(group.getCapacity());

        courseComboBox.getItems()
                .stream()
                .filter(c ->
                        c.getId().equals(
                                group.getCourseId()
                        )
                )
                .findFirst()
                .ifPresent(courseComboBox::setValue);

        teacherComboBox.getItems()
                .stream()
                .filter(t ->
                        t.getId().equals(
                                group.getTeacherId()
                        )
                )
                .findFirst()
                .ifPresent(teacherComboBox::setValue);

        scheduleTable.setItems(
                group.getSchedules()
        );
    }

    // ==========================
    // NEW
    // ==========================

    @FXML
    private void onNewGroup() {

        groupsTable
                .getSelectionModel()
                .clearSelection();

        clearForm();
    }

    // ==========================
    // SAVE
    // ==========================

    @FXML
    private void onSaveGroup() {

        if (!validateForm()) {
            return;
        }

        GroupViewModel selectedGroup =
                groupsTable
                        .getSelectionModel()
                        .getSelectedItem();

        try {

            if (selectedGroup == null) {

                RegisterGroupCommand command =
                        new RegisterGroupCommand(

                                courseComboBox
                                        .getValue()
                                        .getId(),

                                groupCodeField
                                        .getText()
                                        .trim(),

                                teacherComboBox
                                        .getValue()
                                        .getId(),

                                capacitySpinner
                                        .getValue()
                        );

                groupUiService.registerGroup(
                        command
                );

            } else {

                UpdateGroupCommand command =
                        new UpdateGroupCommand(

                                selectedGroup.getId(),

                                courseComboBox
                                        .getValue()
                                        .getId(),

                                groupCodeField
                                        .getText()
                                        .trim(),

                                teacherComboBox
                                        .getValue()
                                        .getId(),

                                capacitySpinner
                                        .getValue()
                        );

                groupUiService.updateGroup(
                        command
                );
            }

            refreshGroups();

        } catch (Exception e) {

            showError(
                    e.getMessage()
            );
        }
    }

    // ==========================
    // DELETE
    // ==========================

    @FXML
    private void onDeleteGroup() {

        GroupViewModel selectedGroup =
                groupsTable
                        .getSelectionModel()
                        .getSelectedItem();

        if (selectedGroup == null) {

            showWarning(
                    "Seleccione un grupo."
            );

            return;
        }

        try {

            groupUiService.deleteGroup(
                    selectedGroup.getId()
            );

            refreshGroups();

        } catch (Exception e) {

            showError(
                    e.getMessage()
            );
        }
    }

    // ==========================
    // SCHEDULES
    // ==========================

    @FXML
    private void onAddSchedule() {

        showWarning(
                "Pendiente implementar."
        );
    }

    @FXML
    private void onRemoveSchedule() {

        GroupScheduleViewModel selected =
                scheduleTable
                        .getSelectionModel()
                        .getSelectedItem();

        if (selected != null) {

            scheduleTable
                    .getItems()
                    .remove(selected);
        }
    }

    // ==========================
    // HELPERS
    // ==========================

    private void refreshGroups() {

        loadGroups();

        clearForm();

        groupsTable
                .getSelectionModel()
                .clearSelection();
    }

    private void clearForm() {

        groupCodeField.clear();

        courseComboBox.setValue(null);

        teacherComboBox.setValue(null);

        capacitySpinner.getValueFactory()
                .setValue(30);

        scheduleTable.getItems().clear();
    }

    private boolean validateForm() {

        if (courseComboBox.getValue() == null) {

            showWarning(
                    "Seleccione una asignatura."
            );

            return false;
        }

        if (teacherComboBox.getValue() == null) {

            showWarning(
                    "Seleccione un docente."
            );

            return false;
        }

        if (groupCodeField
                .getText()
                .isBlank()) {

            showWarning(
                    "Ingrese el código del grupo."
            );

            return false;
        }

        return true;
    }

    private void showWarning(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING
                );

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setHeaderText("Error");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
