package org.unischeduler.ui.viewmodel.group;

public class CourseSelectionViewModel {

    private String id;
    private String code;
    private String name;

    public CourseSelectionViewModel() {
    }

    public CourseSelectionViewModel(
            String id,
            String code,
            String name
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {

        return code + " - " + name;
    }
}