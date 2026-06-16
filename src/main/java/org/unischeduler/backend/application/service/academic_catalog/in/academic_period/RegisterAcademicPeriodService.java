package org.unischeduler.backend.application.service.academic_catalog.in.academic_period;

import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.AcademicPeriodInfo;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.dtos.RegisterAcademicPeriodResponse;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.enums.AcademicPeriodStatus;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.RegisterAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;

import java.util.Set;

public class RegisterAcademicPeriodService implements RegisterAcademicPeriodUseCase {
    private final AcademicPeriodRepository academicPeriodRepository;

    public RegisterAcademicPeriodService(AcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public RegisterAcademicPeriodResponse execute(RegisterAcademicPeriodCommand command) {
        if(academicPeriodRepository.existsByCode(command.getCode())) {
            return new RegisterAcademicPeriodResponse(
                    false,
                    "El codigo " + command.getCode() + " ya se encuentra en uso",
                    null
            );
        }

        if(!command.getStartDate().isBefore(command.getEndDate())) {
            return new RegisterAcademicPeriodResponse(
                    false,
                    "La fecha de inicio debe ser anterior a la fecha de fin",
                    null
            );
        }

        Set<String> validStatuses = Set.of(
                "ACTIVE",
                "SCHEDULED",
                "CLOSED"
        );

        if(!validStatuses.contains(command.getStatus().toUpperCase())) {
            return new RegisterAcademicPeriodResponse(
                    false,
                    "El estado debe ser ACTIVE, CLOSED o SCHEDULED",
                    null
            );
        }

        AcademicPeriod academicPeriod = new AcademicPeriod(
                null,
                command.getCode(),
                command.getName(),
                command.getStartDate(),
                command.getEndDate(),
                AcademicPeriodStatus.valueOf(command.getStatus().toUpperCase())
        );

        AcademicPeriod academicPeriodSaved = academicPeriodRepository.save(academicPeriod);

        AcademicPeriodInfo academicPeriodInfo = AcademicPeriodInfo.fromDomain(academicPeriodSaved);

        return new RegisterAcademicPeriodResponse(
                true,
                "Se creo el periodo academico con exito",
                academicPeriodInfo
        );
    }
}
