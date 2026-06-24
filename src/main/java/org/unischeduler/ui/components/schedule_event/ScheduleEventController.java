package org.unischeduler.ui.components.schedule_event;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScheduleEventController {

    @FXML
    private Label courseLabel;

    @FXML
    private Label roomLabel;

    @FXML
    private Label timeLabel;

    public void setData(
            String course,
            String room,
            String time
    ) {
        courseLabel.setText(course);
        roomLabel.setText(room);
        timeLabel.setText(time);
    }
}