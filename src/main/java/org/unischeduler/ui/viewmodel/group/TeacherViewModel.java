package org.unischeduler.ui.viewmodel.group;

import org.unischeduler.backend.application.service.academic_programming.out.dtos.TeacherInfo;

import javax.swing.text.TabableView;

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

    public static TeacherViewModel toModel(TeacherInfo info) {
        return new TeacherViewModel(
                info.getTeacherId(),
                info.getTeacherName()
        );
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