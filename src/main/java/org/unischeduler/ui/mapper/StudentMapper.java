package org.unischeduler.ui.mapper;

import org.unischeduler.backend.application.service.enrollment.register.dtos.StudentInfo;
import org.unischeduler.ui.viewmodel.student.StudentViewModel;

public final class StudentMapper {

    public static StudentViewModel toViewModel(StudentInfo studentInfo) {
        if (studentInfo == null) {
            return null;
        }

        return new StudentViewModel(
                studentInfo.getUserId(),
                studentInfo.getStudentId(),
                studentInfo.getStudentCode(),
                studentInfo.getStudentPassword()
        );
    }

    public StudentInfo toDto(StudentViewModel viewModel) {
        if (viewModel == null) {
            return null;
        }

        return new StudentInfo(
                viewModel.getUserId(),
                viewModel.getStudentId(),
                viewModel.getStudentCode(),
                viewModel.getStudentPassword()
        );
    }
}