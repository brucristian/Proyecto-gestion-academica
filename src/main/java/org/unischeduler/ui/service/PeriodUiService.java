package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.DeleteAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.RegisterAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.UpdateAcademicPeriodCommand;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.DeleteAcademicPeriodResponse;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.ListAllAcademicPeriodsUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.RegisterAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.UpdateAcademicPeriodUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.mapper.PeriodMapper;
import org.unischeduler.ui.viewmodel.period.PeriodViewModel;

import java.util.List;

public class PeriodUiService {
    private final ListAllAcademicPeriodsUseCase listAllAcademicPeriodsUseCase;
    private final RegisterAcademicPeriodUseCase registerAcademicPeriodUseCase;
    private final UpdateAcademicPeriodUseCase updateAcademicPeriodUseCase;
    private final DeleteAcademicPeriodUseCase deleteAcademicPeriodUseCase;

    public PeriodUiService() {
        this.listAllAcademicPeriodsUseCase = AppContext.getListAllAcademicPeriodsService();
        this.registerAcademicPeriodUseCase = AppContext.getRegisterAcademicPeriodService();
        this.updateAcademicPeriodUseCase = AppContext.getUpdateAcademicPeriodService();
        this.deleteAcademicPeriodUseCase = AppContext.getDeleteAcademicPeriodService();
    }

    public List<PeriodViewModel> listAllPeriods() {
        return PeriodMapper.toViewModels(
                listAllAcademicPeriodsUseCase.execute()
                        .getAcademicPeriods()
        );
    }

    public void registerPeriod(RegisterAcademicPeriodCommand command) {
        registerAcademicPeriodUseCase.execute(command);
    }

    public void updatePeriod(UpdateAcademicPeriodCommand command) {
        updateAcademicPeriodUseCase.execute(command);
    }

    public DeleteAcademicPeriodResponse deletePeriod(DeleteAcademicPeriodCommand command) {
        return deleteAcademicPeriodUseCase.execute(command);
    }
}
