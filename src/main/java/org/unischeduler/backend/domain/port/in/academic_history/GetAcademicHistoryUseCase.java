package org.unischeduler.backend.domain.port.in.academic_history;

import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryCommand;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryResponse;

public interface GetAcademicHistoryUseCase {
    GetAcademicHistoryResponse execute(GetAcademicHistoryCommand command);
}
