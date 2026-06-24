package org.unischeduler.ui.pages.schedule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.unischeduler.backend.application.service.academic_programming.out.GetScheduleCommand;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.components.schedule_event.ScheduleEventController;
import org.unischeduler.ui.service.ScheduleService;
import org.unischeduler.ui.viewmodel.schedule.DayOfWeek;
import org.unischeduler.ui.viewmodel.schedule.ScheduleItem;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class ScheduleController {
    private final ScheduleService scheduleService = new ScheduleService();

    @FXML
    private GridPane scheduleGrid;

    @FXML
    public void initialize() {

        List<ScheduleItem> items = scheduleService.getSchedule(
                new GetScheduleCommand(AppContext.getCurrentUser().getUserId())
        );

        buildSchedule(items);
    }

    private void buildSchedule(List<ScheduleItem> items) {

        scheduleGrid.getChildren().clear();

        createColumns();
        createRows(items);
        addEvents(items);
    }

    private void createColumns() {

        scheduleGrid.getColumnConstraints().clear();

        ColumnConstraints hourColumn = new ColumnConstraints();
        hourColumn.setPercentWidth(10);

        scheduleGrid.getColumnConstraints().add(hourColumn);

        String[] days = {
                "DOM",
                "LUN",
                "MAR",
                "MIÉ",
                "JUE",
                "VIE",
                "SÁB"
        };

        for (int i = 0; i < 7; i++) {

            ColumnConstraints dayColumn =
                    new ColumnConstraints();

            dayColumn.setPercentWidth(13);

            scheduleGrid.getColumnConstraints()
                    .add(dayColumn);

            scheduleGrid.add(
                    createHeader(days[i]),
                    i + 1,
                    0
            );
        }

        scheduleGrid.add(
                createHeader("HORA"),
                0,
                0
        );
    }

    private void createRows(List<ScheduleItem> items) {

        scheduleGrid.getRowConstraints().clear();

        List<Integer> hours =
                items.stream()
                        .flatMap(item ->
                                item.hoursUsed().stream()
                        )
                        .distinct()
                        .sorted()
                        .toList();

        int row = 1;

        for (Integer hour : hours) {

            RowConstraints rc =
                    new RowConstraints();

            rc.setPrefHeight(80);

            scheduleGrid.getRowConstraints()
                    .add(rc);

            Label label =
                    new Label(
                            String.format("%02d:00", hour)
                    );

            label.setStyle("""
                    -fx-font-size:14px;
                    -fx-font-weight:bold;
                    """);

            StackPane hourPane =
                    new StackPane(label);

            hourPane.setStyle("""
                    -fx-background-color:#F9FAFB;
                    -fx-border-color:#E5E7EB;
                    """);

            scheduleGrid.add(hourPane, 0, row);

            row++;
        }
    }

    private void addEvents(List<ScheduleItem> items) {

        List<Integer> visibleHours =
                items.stream()
                        .flatMap(item ->
                                item.hoursUsed().stream()
                        )
                        .distinct()
                        .sorted()
                        .toList();

        for (ScheduleItem item : items) {

            try {

                FXMLLoader loader =
                        new FXMLLoader(
                                getClass().getResource(
                                        "/ui/fxml/components/schedule_event/schedule_event.fxml"
                                )
                        );

                Node node = loader.load();

                ScheduleEventController controller =
                        loader.getController();

                controller.setData(
                        item.courseName(),
                        item.room(),
                        item.start() + " - " + item.end()
                );

                int column =
                        item.day().columnIndex();

                int row =
                        visibleHours.indexOf(
                                item.start().getHour()
                        ) + 1;

                int span =
                        item.end().getHour()
                                - item.start().getHour();

                scheduleGrid.add(
                        node,
                        column,
                        row
                );

                GridPane.setRowSpan(
                        node,
                        span
                );

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private StackPane createHeader(
            String text
    ) {

        Label label = new Label(text);

        label.setStyle("""
                -fx-font-weight:bold;
                -fx-font-size:14px;
                """);

        StackPane pane =
                new StackPane(label);

        pane.setStyle("""
                -fx-background-color:#F3F4F6;
                -fx-border-color:#E5E7EB;
                -fx-padding:10;
                """);

        return pane;
    }
}