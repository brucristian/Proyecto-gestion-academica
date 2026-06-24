package org.unischeduler.ui.viewmodel.course;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseViewModel {

    private String id;
    private String code;
    private String name;
    private Integer credits;
    private String description;

    private ObservableList<PrerequisiteViewModel> prerequisites =
            FXCollections.observableArrayList();

    public CourseViewModel() {
    }

    public CourseViewModel(
            String id,
            String code,
            String name,
            Integer credits,
            String description) {

        this.id = id;
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.description = description;
    }

    // getters setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObservableList<PrerequisiteViewModel> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ObservableList<PrerequisiteViewModel> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {

        return code + " - " + name;
    }
}