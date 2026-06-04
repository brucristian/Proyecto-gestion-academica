package org.unischeduler.backend.application.service.academic_catalog.in.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.AcademicPeriodInfo;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.UpdateAcademicPeriodResponse;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.enums.AcademicPeriodStatus;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.UpdateAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;

import java.util.Optional;
import java.util.Set;

public class UpdateAcademicPeriodService implements UpdateAcademicPeriodUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public UpdateAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public UpdateAcademicPeriodResponse execute(UpdateAcademicPeriodCommand command) {
        Optional<AcademicPeriod> optionalAcademicPeriod = academicPeriodRepository.findById(command.getAcademicPeriodId());
        if(optionalAcademicPeriod.isEmpty()) {
            return new UpdateAcademicPeriodResponse(
                    false,
                    "El periodo academico seleccionado no existe",
                    null
            );
        }

        if(!command.getStartDate().isBefore(command.getEndDate())) {
            return new UpdateAcademicPeriodResponse(
                    false,
                    "La fecha de inicio debe ser anterior a la fecha de fin",
                    null
            );
        }

        Set<String> validStatuses = Set.of(
                "ACTIVE",
                "CLOSED",
                "PROGRAMED"
        );

        if(!validStatuses.contains(command.getStatus().toUpperCase())) {
            return new UpdateAcademicPeriodResponse(
                    false,
                    "El estado debe ser ACTIVE, CLOSED o PROGRAMED",
                    null
            );
        }

        AcademicPeriod academicPeriod = optionalAcademicPeriod.get();
        academicPeriod.setStatus(AcademicPeriodStatus.valueOf(command.getStatus()));
        academicPeriod.setName(command.getName());

        AcademicPeriod academicPeriodUpdated = academicPeriodRepository.update(academicPeriod);

        AcademicPeriodInfo academicPeriodInfo = AcademicPeriodInfo.fromDomain(academicPeriodUpdated);

        return new UpdateAcademicPeriodResponse(
                true,
                "Se actualizo el periodo academico con exito",
                academicPeriodInfo
        );
    }
}
