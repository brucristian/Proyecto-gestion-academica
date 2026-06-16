package org.unischeduler.ui.viewmodel.group;

public class GroupScheduleViewModel {
    private String id;
    private String day;
    private String startTime;
    private String endTime;
    private String room;

    public GroupScheduleViewModel() {
    }

    public GroupScheduleViewModel(
            String day,
            String startTime,
            String endTime,
            String room
    ) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}