package org.unischeduler.ui.viewmodel.group;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GroupViewModel {

    private String id;

    private String courseId;
    private String courseName;

    private String teacherId;
    private String teacherName;

    private String groupCode;

    private Integer capacity;

    private ObservableList<GroupScheduleViewModel> schedules =
            FXCollections.observableArrayList();

    private final BooleanProperty selected = new SimpleBooleanProperty(false);

    public GroupViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public ObservableList<GroupScheduleViewModel> getSchedules() {
        return schedules;
    }

    public void setSchedules(ObservableList<GroupScheduleViewModel> schedules) {
        this.schedules = schedules;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}