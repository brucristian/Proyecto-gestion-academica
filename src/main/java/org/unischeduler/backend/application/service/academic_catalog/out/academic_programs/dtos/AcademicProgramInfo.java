package org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.dtos;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;

public class AcademicProgramInfo {
    private final String academicProgramId;
    private final String name;
    private final int totalSemesters;

    public AcademicProgramInfo(String academicProgramId, String name, int totalSemesters) {
        this.academicProgramId = academicProgramId;
        this.name = name;
        this.totalSemesters = totalSemesters;
    }

    public String getAcademicProgramId() {
        return academicProgramId;
    }

    public String getName() {
        return name;
    }

    public int getTotalSemesters() {
        return totalSemesters;
    }

     public static AcademicProgramInfo fromDomain(AcademicProgram program) {
        return new AcademicProgramInfo(
                program.getAcademicProgramId(),
                program.getName(),
                program.getTotalSemesters()
        );
    }
}
