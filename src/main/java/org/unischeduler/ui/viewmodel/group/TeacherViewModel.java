package org.unischeduler.ui.viewmodel.group;

public class TeacherViewModel {

    private String id;
    private String fullName;

    public TeacherViewModel() {
    }

    public TeacherViewModel(
            String id,
            String fullName
    ) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}