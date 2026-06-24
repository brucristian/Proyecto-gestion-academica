package org.unischeduler.backend.application.service.academc_history;

import java.util.List;

public class GetAcademicHistoryResponse {
    private final boolean successfully;
    private final String message;
    private final String programName;
    private final  int approvedCredits;
    private final double average;
    private final List<AcademicHistoryInfo> academicHistory;

    public GetAcademicHistoryResponse(boolean successfully, String message, String programName, int approvedCredits, double average, List<AcademicHistoryInfo> academicHistory) {
        this.successfully = successfully;
        this.message = message;
        this.programName = programName;
        this.approvedCredits = approvedCredits;
        this.average = average;
        this.academicHistory = academicHistory;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public String getProgramName() {
        return programName;
    }

    public int getApprovedCredits() {
        return approvedCredits;
    }

    public double getAverage() {
        return average;
    }

    public List<AcademicHistoryInfo> getAcademicHistory() {
        return academicHistory;
    }
}
