package org.unisheduler.backend.domain.model.academic_catalog.entity;

public class AcademicProgram {

    //================// Atributos //================//
    private long academicProgramId;
    private String name;

    //================// Constructores //================//

    public AcademicProgram() {
    }

    public AcademicProgram(long academicProgramId, String name) {
        this.academicProgramId = academicProgramId;
        this.name = name;
    }

    //================// Setters y Getters //================//

    public long getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(long academicProgramId) {
        this.academicProgramId = academicProgramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //================// Funciones Adicionales //================//


}
