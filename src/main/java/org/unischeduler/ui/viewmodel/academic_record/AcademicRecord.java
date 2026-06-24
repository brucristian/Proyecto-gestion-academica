package org.unischeduler.ui.viewmodel.academic_record;

import javafx.beans.property.*;

public class AcademicRecord {

    private final StringProperty code = new SimpleStringProperty();
    private final StringProperty subject = new SimpleStringProperty();
    private final IntegerProperty credits = new SimpleIntegerProperty();
    private final StringProperty period = new SimpleStringProperty();
    private final DoubleProperty grade = new SimpleDoubleProperty();
    private final StringProperty status = new SimpleStringProperty();

    public AcademicRecord(
            String code,
            String subject,
            int credits,
            String period,
            double grade,
            String status
    ) {
        this.code.set(code);
        this.subject.set(subject);
        this.credits.set(credits);
        this.period.set(period);
        this.grade.set(grade);
        this.status.set(status);
    }

    public StringProperty codeProperty() {
        return code;
    }

    public StringProperty subjectProperty() {
        return subject;
    }

    public IntegerProperty creditsProperty() {
        return credits;
    }

    public StringProperty periodProperty() {
        return period;
    }

    public DoubleProperty gradeProperty() {
        return grade;
    }

    public StringProperty statusProperty() {
        return status;
    }
}