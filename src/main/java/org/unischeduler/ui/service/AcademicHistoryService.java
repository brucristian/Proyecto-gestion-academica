package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryCommand;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryResponse;
import org.unischeduler.backend.domain.port.in.academic_history.GetAcademicHistoryUseCase;
import org.unischeduler.ui.app.AppContext;

public class AcademicHistoryService {
    private final GetAcademicHistoryUseCase getAcademicHistoryUseCase;

    public AcademicHistoryService() {
        this.getAcademicHistoryUseCase = AppContext.getGetAcademicHistoryService();
    }

    public GetAcademicHistoryResponse getAcademicHistory(GetAcademicHistoryCommand command) {
        return getAcademicHistoryUseCase.execute(command);
    }
}
