package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos.AcademicProgramInfo;
import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos.ListAllProgramsResponse;
import org.unischeduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unischeduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_program.ListAllProgramsUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.mapper.ProgramMapper;
import org.unischeduler.ui.mapper.StudentMapper;
import org.unischeduler.ui.viewmodel.student.ProgramViewModel;

import java.util.List;

public class StudentUiService {
    private final RegisterStudentUseCase registerStudentUseCase;
    private final ListAllProgramsUseCase listAllProgramsUseCase;
    private final StudentMapper studentMapper;

    public StudentUiService() {
        this.registerStudentUseCase = AppContext.getRegisterStudentService();
        this.listAllProgramsUseCase = AppContext.getListAllProgramsService();
        studentMapper = new StudentMapper();
    }

    public RegisterStudentResponse registerStudent(RegisterStudentCommand command) {
        return registerStudentUseCase.execute(command);
    }

    public List<ProgramViewModel> listAllPrograms() {
        return listAllProgramsUseCase.execute()
                .getProgram()
                .stream()
                .map(ProgramMapper::toViewModel)
                .toList();
    }

}
