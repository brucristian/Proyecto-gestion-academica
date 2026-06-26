package org.unischeduler.ui.pages.group;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.unischeduler.ui.viewmodel.group.GroupScheduleViewModel;

import java.time.LocalTime;

public class ScheduleDialog extends Dialog<GroupScheduleViewModel> {

    public ScheduleDialog() {

        setTitle("Agregar horario");
        setHeaderText("Ingrese la información del horario");

        ComboBox<String> dayComboBox = new ComboBox<>();
        dayComboBox.getItems().addAll(
                "LUNES",
                "MARTES",
                "MIERCOLES",
                "JUEVES",
                "VIERNES",
                "SABADO"
        );

        TextField startTimeField = new TextField();
        startTimeField.setPromptText("08:00");

        TextField endTimeField = new TextField();
        endTimeField.setPromptText("10:00");

        TextField roomField = new TextField();
        roomField.setPromptText("A-301");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Día:"), 0, 0);
        grid.add(dayComboBox, 1, 0);

        grid.add(new Label("Hora inicio:"), 0, 1);
        grid.add(startTimeField, 1, 1);

        grid.add(new Label("Hora fin:"), 0, 2);
        grid.add(endTimeField, 1, 2);

        grid.add(new Label("Aula:"), 0, 3);
        grid.add(roomField, 1, 3);

        getDialogPane().setContent(grid);

        ButtonType saveButton =
                new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().getButtonTypes().addAll(
                saveButton,
                ButtonType.CANCEL
        );

        setResultConverter(button -> {

            if (button != saveButton) {
                return null;
            }

            if (dayComboBox.getValue() == null) {
                showError("Seleccione un día.");
                return null;
            }

            try {

                LocalTime start =
                        LocalTime.parse(startTimeField.getText().trim());

                LocalTime end =
                        LocalTime.parse(endTimeField.getText().trim());

                if (!start.isBefore(end)) {
                    showError("La hora de inicio debe ser menor que la hora de fin.");
                    return null;
                }

                return new GroupScheduleViewModel(
                        dayComboBox.getValue(),
                        start.toString(),
                        end.toString(),
                        roomField.getText().trim()
                );

            } catch (Exception e) {

                showError("Las horas deben tener el formato HH:mm.");
                return null;
            }
        });
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}