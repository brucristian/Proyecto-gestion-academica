package org.unisheduler.backend.domain.model.enrollment.entity;

import org.unisheduler.backend.domain.model.academic_programming.entity.Group;

public class EnrollmentDetail {

    //================// Atributos //================//
    private long enrollmentDetailld;
    private Group group;

    //================// Constructores //================//

    public EnrollmentDetail() {
    }

    public EnrollmentDetail(long enrollmentDetailld, Group group) {
        this.enrollmentDetailld = enrollmentDetailld;
        this.group = group;
    }

    //================// Setters y Getters //================//

    public long getEnrollmentDetailld() {
        return enrollmentDetailld;
    }

    public void setEnrollmentDetailld(long enrollmentDetailld) {
        this.enrollmentDetailld = enrollmentDetailld;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    //================// Funciones Adicionales //================//

}
