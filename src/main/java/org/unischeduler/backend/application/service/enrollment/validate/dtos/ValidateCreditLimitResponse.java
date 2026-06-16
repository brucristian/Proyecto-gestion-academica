package org.unischeduler.backend.application.service.enrollment.validate.dtos;

import java.util.List;

public class ValidateCreditLimitResponse {

    private final boolean successfully;
    private final List<String> message;
    private final int usedCredits;
    private final int availableCredits;

    public ValidateCreditLimitResponse(
            boolean successfully,
            List<String> message,
            int usedCredits,
            int availableCredits) {

        this.successfully = successfully;
        this.message = message;
        this.usedCredits = usedCredits;
        this.availableCredits = availableCredits;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public List<String> getMessage() {
        return message;
    }

    public int getUsedCredits() {
        return usedCredits;
    }

    public int getAvailableCredits() {
        return availableCredits;
    }
}