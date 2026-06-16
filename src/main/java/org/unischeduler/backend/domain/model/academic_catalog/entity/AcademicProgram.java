package org.unischeduler.backend.domain.model.academic_catalog.entity;

public class AcademicProgram {

    //================// Atributos //================//
    private String academicProgramId;
    private String name;
    private int totalSemesters;

    //================// Constructores //================//

    public AcademicProgram() {
    }

    public AcademicProgram(String academicProgramId, String name, int totalSemesters) {
        this.academicProgramId = academicProgramId;
        this.name = name;
        this.totalSemesters = totalSemesters;
    }

    //================// Setters y Getters //================//

    public String getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(String academicProgramId) {
        this.academicProgramId = academicProgramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSemesters() {
        return totalSemesters;
    }

    //================// Funciones Adicionales //================//


}
