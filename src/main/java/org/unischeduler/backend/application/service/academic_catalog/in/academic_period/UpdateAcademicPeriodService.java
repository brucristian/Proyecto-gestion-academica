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
        Optional<AcademicPeriod> optionalAcademicPeriod =
                academicPeriodRepository.findById(command.getAcademicPeriodId());

        if (optionalAcademicPeriod.isEmpty()) {
            return new UpdateAcademicPeriodResponse(
                    false,
                    "El periodo academico seleccionado no existe",
                    null
            );
        }

        String status = command.getStatus().toUpperCase();

        Set<String> validStatuses = Set.of(
                "ACTIVE",
                "CLOSED",
                "PROGRAMED"
        );

        if (!validStatuses.contains(status)) {
            return new UpdateAcademicPeriodResponse(
                    false,
                    "El estado debe ser ACTIVE, CLOSED o PROGRAMED",
                    null
            );
        }

        AcademicPeriod academicPeriod = optionalAcademicPeriod.get();

        // Si este periodo se va a activar, desactivar el que esté activo actualmente
        if ("ACTIVE".equals(status)) {
            academicPeriodRepository.findActive().ifPresent(activePeriod -> {
                if (!activePeriod.getAcademicPeriodId()
                        .equals(academicPeriod.getAcademicPeriodId())) {

                    activePeriod.setStatus(AcademicPeriodStatus.CLOSED);
                    academicPeriodRepository.update(activePeriod);
                }
            });
        }

        academicPeriod.setName(command.getName());
        academicPeriod.setStatus(AcademicPeriodStatus.valueOf(status));

        AcademicPeriod academicPeriodUpdated =
                academicPeriodRepository.update(academicPeriod);

        return new UpdateAcademicPeriodResponse(
                true,
                "Se actualizó el período académico con éxito",
                AcademicPeriodInfo.fromDomain(academicPeriodUpdated)
        );
    }
}
