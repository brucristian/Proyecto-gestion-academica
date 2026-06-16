package org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos;

import java.util.List;

public class ListAllProgramsResponse {
    private final boolean successfully;
    private final String message;
    private final List<AcademicProgramInfo> program;

    public ListAllProgramsResponse(boolean successfully, String message, List<AcademicProgramInfo> program) {
        this.successfully = successfully;
        this.message = message;
        this.program = program;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public List<AcademicProgramInfo> getProgram() {
        return program;
    }
}
