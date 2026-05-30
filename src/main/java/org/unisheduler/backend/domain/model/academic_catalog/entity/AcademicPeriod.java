package org.unisheduler.backend.domain.model.academic_catalog.entity;

public class AcademicPeriod {

    //================// Atributos //================//
    private long academicPeriodId;
    private String name;
    private boolean isActive;

    //================// Constructores //================//

    public AcademicPeriod() {
    }

    public AcademicPeriod(long academicPeriodId, String name, boolean isActive) {
        this.academicPeriodId = academicPeriodId;
        this.name = name;
        this.isActive = isActive;
    }

    //================// Setters y Getters //================//

    public long getAcademicPeriodId() {
        return academicPeriodId;
    }

    public void setAcademicPeriodId(long academicPeriodId) {
        this.academicPeriodId = academicPeriodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    //================// Funciones Adicionales //================//

}
