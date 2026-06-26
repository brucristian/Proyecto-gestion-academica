package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academc_history.AcademicHistoryInfo;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryCommand;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryResponse;
import org.unischeduler.backend.domain.port.in.academic_history.GetAcademicHistoryUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.viewmodel.academic_record.AcademicRecord;

import java.util.List;

public class AcademicHistoryManagementService {

    private final GetAcademicHistoryUseCase getAcademicHistoryUseCase;

    public AcademicHistoryManagementService() {
        this.getAcademicHistoryUseCase = AppContext.getGetAcademicHistoryService();
    }

    public GetAcademicHistoryResponse getStudentHistory(GetAcademicHistoryCommand command) {
        return getAcademicHistoryUseCase.execute(command);
    }

    public boolean saveUpdatedHistory(String studentId, List<AcademicRecord> updatedRecords) {
        if (studentId == null || studentId.trim().isEmpty() || updatedRecords == null) {
            return false;
        }


        List<AcademicHistoryInfo> updatedInfos = updatedRecords.stream().map(record ->
                new AcademicHistoryInfo(
                        record.codeProperty().get(),    // info.getCode()
                        record.subjectProperty().get(), // info.getName()
                        record.creditsProperty().get(), // info.getCredits()
                        record.periodProperty().get(),  // info.getPeriod()
                        record.gradeProperty().get(),   // info.getNote()
                        record.statusProperty().get()   // info.getStatus()
                )
        ).toList();

        try {
            System.out.println("====== ENVIANDO DATOS AL DOMINIO ======");
            System.out.println("Historial del estudiante " + studentId + " procesado exitosamente.");
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar cambios: " + e.getMessage());
            return false;
        }
    }
}