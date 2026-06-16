package org.unischeduler.ui.pages.period;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.DeleteAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.UpdateAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.DeleteAcademicPeriodResponse;
import org.unischeduler.ui.app.SceneManager;
import org.unischeduler.ui.service.PeriodUiService;
import org.unischeduler.ui.viewmodel.period.PeriodViewModel;

import java.time.LocalDate;
import java.util.List;

public class PeriodController {

    private final PeriodUiService periodUiService = new PeriodUiService();

    @FXML
    private TableView<PeriodViewModel> periodTable;

    @FXML
    private TableColumn<PeriodViewModel, String> codeColumn;

    @FXML
    private TableColumn<PeriodViewModel, String> nameColumn;

    @FXML
    private TableColumn<PeriodViewModel, LocalDate> startDateColumn;

    @FXML
    private TableColumn<PeriodViewModel, LocalDate> endDateColumn;

    @FXML
    private TableColumn<PeriodViewModel, String> statusColumn;

    @FXML
    private Button newPeriodButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button activateButton;

    @FXML
    public void initialize() {

        configureTable();
        loadPeriods();
    }

    private void configureTable() {

        codeColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getCode()
                )
        );

        nameColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getName()
                )
        );

        startDateColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleObjectProperty<>(
                        cellData.getValue().getStartDate()
                )
        );

        endDateColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleObjectProperty<>(
                        cellData.getValue().getEndDate()
                )
        );

        statusColumn.setCellValueFactory(
                cellData -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getStatus()
                )
        );
    }

    private void loadPeriods() {

        try {

            List<PeriodViewModel> periods =
                    periodUiService.listAllPeriods();

            periodTable.setItems(
                    FXCollections.observableArrayList(periods)
            );

        } catch (Exception e) {

            showError(e.getMessage());
        }
    }

    @FXML
    private void onNewPeriod() {

        FXMLLoader loader = SceneManager.loadPageWithLoader(
                "/ui/fxml/pages/academic_period/AcademicPeriodFormView.fxml"
        );

        AcademicPeriodFormController controller =
                loader.getController();

        controller.configureCreateMode();
    }

    @FXML
    private void onEditPeriod() {

        PeriodViewModel selectedPeriod =
                periodTable.getSelectionModel().getSelectedItem();

        if (selectedPeriod == null) {
            showWarning("Seleccione un período académico.");
            return;
        }

        FXMLLoader loader = SceneManager.loadPageWithLoader(
                "/ui/fxml/pages/academic_period/AcademicPeriodFormView.fxml"
        );

        AcademicPeriodFormController controller =
                loader.getController();

        controller.configureEditMode(selectedPeriod);
    }

    @FXML
    private void onDeletePeriod() {

        PeriodViewModel selectedPeriod =
                periodTable.getSelectionModel().getSelectedItem();

        if (selectedPeriod == null) {
            showWarning("Seleccione un período académico.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);

        confirmation.setTitle("Eliminar período");
        confirmation.setHeaderText(null);
        confirmation.setContentText(
                "¿Desea eliminar el período seleccionado?"
        );

        confirmation.showAndWait()
                .filter(button -> button == ButtonType.OK)
                .ifPresent(button -> {

                    try {

                        DeleteAcademicPeriodResponse response = periodUiService.deletePeriod(
                                new DeleteAcademicPeriodCommand(selectedPeriod.getAcademicPeriodId())
                        );

                        Alert success = new Alert(Alert.AlertType.INFORMATION);
                        String title = response.isSuccessfully() ?
                                "Eliminado con exito" : "Error al emilinar el periodo academico";

                        success.setTitle(title);
                        success.setContentText(response.getMessage());

                        success.show();

                        loadPeriods();

                    } catch (Exception e) {

                        showError(e.getMessage());
                    }
                });
    }

    @FXML
    private void onActivatePeriod() {

        PeriodViewModel selectedPeriod =
                periodTable.getSelectionModel().getSelectedItem();

        if (selectedPeriod == null) {
            showWarning("Seleccione un período académico.");
            return;
        }

        try {
            UpdateAcademicPeriodCommand command = new UpdateAcademicPeriodCommand(
                    selectedPeriod.getAcademicPeriodId(),
                    selectedPeriod.getCode(),
                    selectedPeriod.getName(),
                    selectedPeriod.getStartDate(),
                    selectedPeriod.getEndDate(),
                    "ACTIVE"
            );

            periodUiService.updatePeriod(
                    command
            );

            loadPeriods();

        } catch (Exception e) {

            showError(e.getMessage());
        }
    }

    private void showWarning(String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("Ocurrió un problema");
        alert.setContentText(message);

        alert.showAndWait();
    }
}