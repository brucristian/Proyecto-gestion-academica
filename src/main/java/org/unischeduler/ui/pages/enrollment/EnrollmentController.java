package org.unischeduler.ui.pages.enrollment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import org.unischeduler.backend.application.service.enrollment.validate.ValidateCreditLimitCommand;
import org.unischeduler.backend.application.service.enrollment.validate.ValidatePrerequisiteCommand;
import org.unischeduler.backend.application.service.enrollment.validate.ValidateScheduleConflictsCommand;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateCreditLimitResponse;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidatePrerequisiteResponse;
import org.unischeduler.backend.application.service.enrollment.validate.dtos.ValidateScheduleConflictsResponse;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.mapper.GroupScheduleMapper;
import org.unischeduler.ui.service.EnrollmentUiService;
import org.unischeduler.ui.viewmodel.group.GroupScheduleViewModel;
import org.unischeduler.ui.viewmodel.group.GroupViewModel;

import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentController {

    private final EnrollmentUiService enrollmentUiService =
            new EnrollmentUiService();
    private ObservableList<GroupViewModel> masterGroups;
    private FilteredList<GroupViewModel> filteredGroups;

    // =====================================
    // Oferta académica
    // =====================================

    @FXML
    private TableView<GroupViewModel> availableGroupsTable;

    @FXML
    private TableColumn<GroupViewModel, Boolean> selectedColumn;

    @FXML
    private TableColumn<GroupViewModel, String> codeColumn;

    @FXML
    private TableColumn<GroupViewModel, String> courseColumn;

    @FXML
    private TableColumn<GroupViewModel, String> groupColumn;

    @FXML
    private TableColumn<GroupViewModel, String> teacherColumn;

    @FXML
    private TableColumn<GroupViewModel, String> scheduleColumn;

    @FXML
    private TableColumn<GroupViewModel, Number> capacityColumn;

    // =====================================
    // Horario provisional
    // =====================================

    @FXML
    private TableView<GroupViewModel> selectedGroupsTable;

    @FXML
    private TableColumn<GroupViewModel, String> selectedCourseColumn;

    @FXML
    private TableColumn<GroupViewModel, String> selectedGroupColumn;

    @FXML
    private TableColumn<GroupViewModel, String> selectedScheduleColumn;

    @FXML
    private TableColumn<GroupViewModel, String> removeColumn;

    // =====================================
    // Filtros
    // =====================================

    @FXML
    private ComboBox<String> courseFilterCombo;

    @FXML
    private TextField searchField;

    // =====================================
    // Validaciones
    // =====================================

    @FXML
    private ListView<String> validationListView;

    @FXML
    private Label totalCreditsLabel;

    // =====================================
    // Botones
    // =====================================

    @FXML
    private Button clearSelectionButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {

        configureAvailableGroupsTable();
        configureSelectedGroupsTable();

        enableTextWrapping(courseColumn);
        enableTextWrapping(teacherColumn);
        enableTextWrapping(scheduleColumn);

        enableTextWrapping(selectedCourseColumn);
        enableTextWrapping(selectedScheduleColumn);

        availableGroupsTable.setRowFactory(tv -> {
            TableRow<GroupViewModel> row = new TableRow<>();
            row.setPrefHeight(Control.USE_COMPUTED_SIZE);
            return row;
        });

        selectedGroupsTable.setRowFactory(tv -> {
            TableRow<GroupViewModel> row = new TableRow<>();
            row.setPrefHeight(Control.USE_COMPUTED_SIZE);
            return row;
        });

        loadData();
    }

    private void configureAvailableGroupsTable() {
        selectedColumn.setCellValueFactory(
                cell -> cell.getValue().selectedProperty()
        );

        selectedColumn.setCellFactory(
                CheckBoxTableCell.forTableColumn(selectedColumn)
        );

        selectedColumn.setEditable(true);

        availableGroupsTable.setEditable(true);

        codeColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        cell.getValue().getCourseId()
                )
        );

        courseColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        cell.getValue().getCourseName()
                )
        );

        groupColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        cell.getValue().getGroupCode()
                )
        );

        teacherColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        cell.getValue().getTeacherName()
                )
        );

        scheduleColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        formatSchedule(cell.getValue())
                )
        );

        capacityColumn.setCellValueFactory(
                cell -> new SimpleIntegerProperty(
                        cell.getValue().getCapacity()
                )
        );
    }

    private void configureSelectedGroupsTable() {

        selectedCourseColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        cell.getValue().getCourseName()
                )
        );

        selectedGroupColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        cell.getValue().getGroupCode()
                )
        );

        selectedScheduleColumn.setCellValueFactory(
                cell -> new SimpleStringProperty(
                        formatSchedule(cell.getValue())
                )
        );
    }

    private void loadData() {

        try {

            masterGroups = FXCollections.observableArrayList(
                    enrollmentUiService.listGroups()
            );

            masterGroups.forEach(group ->
                    group.selectedProperty().addListener(
                            (obs, oldValue, newValue) -> updateSelectedGroups()
                    )
            );

            filteredGroups = new FilteredList<>(masterGroups, p -> true);

            SortedList<GroupViewModel> sortedGroups =
                    new SortedList<>(filteredGroups);

            sortedGroups.comparatorProperty().bind(
                    availableGroupsTable.comparatorProperty()
            );

            availableGroupsTable.setItems(sortedGroups);

            configureSearch();

            validationListView.getItems().clear();
            totalCreditsLabel.setText("0");

        } catch (Exception e) {

            showError(e.getMessage());
        }
    }

    private String formatSchedule(GroupViewModel group) {

        if (group.getSchedules() == null ||
                group.getSchedules().isEmpty()) {
            return "";
        }

        return group.getSchedules()
                .stream()
                .map(schedule ->
                        schedule.getDay()
                                + " "
                                + schedule.getStartTime()
                                + " - "
                                + schedule.getEndTime())
                .collect(Collectors.joining("\n"));
    }

    @FXML
    private void onClearSelection() {

        availableGroupsTable.getItems()
                .forEach(group -> group.setSelected(false));

        selectedGroupsTable.getItems().clear();

        validationListView.getItems().clear();

        totalCreditsLabel.setText("0");

        searchField.clear();
    }

    @FXML
    private void onNext() {

        List<GroupViewModel> selectedGroups =
                availableGroupsTable.getItems()
                        .stream()
                        .filter(GroupViewModel::isSelected)
                        .toList();

        if (selectedGroups.isEmpty()) {

            showWarning(
                    "Debe seleccionar al menos un grupo."
            );

            return;
        }

        selectedGroupsTable.setItems(
                FXCollections.observableArrayList(selectedGroups)
        );
    }

    private void updateSelectedGroups() {

        List<GroupViewModel> selectedGroups =
                availableGroupsTable.getItems()
                        .stream()
                        .filter(GroupViewModel::isSelected)
                        .toList();

        List<ObservableList<GroupScheduleViewModel>> scheduleViewModels = selectedGroups.stream()
                .map(GroupViewModel::getSchedules)
                .toList();

        ValidateScheduleConflictsCommand command = new ValidateScheduleConflictsCommand(
                selectedGroups.stream()
                        .flatMap(group -> group.getSchedules().stream())
                        .map(GroupScheduleMapper::toInfo)
                        .toList()
        );

        ValidateScheduleConflictsResponse validateScheduleConflictsResponse
                = enrollmentUiService.validateSchedule(command);

        ValidatePrerequisiteCommand validatePrerequisiteCommand = new ValidatePrerequisiteCommand(
                AppContext.getCurrentUser()
                        .getUserRoleInfo()
                        .getUserRoleId(),
                selectedGroups.stream()
                        .map(GroupViewModel::getCourseId)
                        .toList()
        );
        ValidatePrerequisiteResponse validatePrerequisiteResponse
                = enrollmentUiService.validatePrerequisite(validatePrerequisiteCommand);

        ValidateCreditLimitCommand validateCreditLimitCommand = new ValidateCreditLimitCommand(
                AppContext.getCurrentUser()
                        .getUserRoleInfo()
                        .getUserRoleId(),
                selectedGroups.stream()
                        .map(GroupViewModel::getCourseId)
                        .toList()
        );
        ValidateCreditLimitResponse validateCreditLimitResponse = enrollmentUiService.validateCreditLimit(
                validateCreditLimitCommand
        );

        validationListView.getItems().clear();

        List<String> validations = new ArrayList<>();

        validations.addAll(validateScheduleConflictsResponse.getMessage());
        validations.addAll(validatePrerequisiteResponse.getMessage());
        validations.addAll(validateCreditLimitResponse.getMessage());

        validationListView.getItems().setAll(validations);

        selectedGroupsTable.setItems(
                FXCollections.observableArrayList(selectedGroups)
        );
    }

    private void showWarning(String message) {

        Alert alert =
                new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(String message) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("Ocurrió un problema");
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void enableTextWrapping(TableColumn<GroupViewModel, String> column) {

        column.setCellFactory(tc -> new TableCell<>() {

            private final Text text = new Text();

            {
                text.wrappingWidthProperty().bind(
                        tc.widthProperty().subtract(15)
                );

                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(text);
            }

            @Override
            protected void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    text.setText("");
                    setGraphic(null);

                } else {

                    text.setText(item);
                    setGraphic(text);
                }
            }
        });
    }

    private void configureSearch() {

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    String search = newValue == null
                            ? ""
                            : newValue.trim().toLowerCase();

                    filteredGroups.setPredicate(group -> {

                        if (search.isBlank()) {
                            return true;
                        }

                        return group.getCourseId()
                                .toLowerCase()
                                .contains(search)
                                || group.getCourseName()
                                .toLowerCase()
                                .contains(search)
                                || group.getGroupCode()
                                .toLowerCase()
                                .contains(search)
                                || group.getTeacherName()
                                .toLowerCase()
                                .contains(search);
                    });
                }
        );
    }
}