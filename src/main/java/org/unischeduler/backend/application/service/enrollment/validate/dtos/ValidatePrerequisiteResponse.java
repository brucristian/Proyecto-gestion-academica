package org.unischeduler.backend.application.service.enrollment.validate.dtos;

import java.util.List;

public class ValidatePrerequisiteResponse {
    private final boolean successfully;
    private final List<String> message;

    public ValidatePrerequisiteResponse(boolean successfully, List<String> message) {
        this.successfully = successfully;
        this.message = message;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public List<String> getMessage() {
        return message;
    }
}
