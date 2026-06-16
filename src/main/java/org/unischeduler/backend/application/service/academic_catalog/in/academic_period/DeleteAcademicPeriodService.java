package org.unischeduler.backend.application.service.academic_catalog.in.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.DeleteAcademicPeriodResponse;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;

public class DeleteAcademicPeriodService implements DeleteAcademicPeriodUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public DeleteAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public DeleteAcademicPeriodResponse execute(DeleteAcademicPeriodCommand command) {
        if(!academicPeriodRepository.deleteById(command.getId())) {
            return new DeleteAcademicPeriodResponse(
                    false,
                    "No existe un periodo academico con id " + command.getId()
            );
        }

        return new DeleteAcademicPeriodResponse(
                true,
                "Se elimino el periodo academico con exito"
        );
    }
}
