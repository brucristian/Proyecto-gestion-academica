package org.unischeduler.backend.infrastructure.out.repository.enrollment;

import org.unischeduler.backend.domain.exceptions.academic_catalog.PeriodActiveNotFoundException;
import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentEntity;
import org.unischeduler.backend.infrastructure.out.mapper.enrollment.EnrollmentMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentRepository;

import java.util.ArrayList;
import java.util.Optional;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private final ExcelEnrollmentRepository enrollmentRepository;
    private final AcademicProgramRepository programRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final AcademicPeriodRepository academicPeriodRepository;

    public EnrollmentRepositoryImpl(ExcelEnrollmentRepository enrollmentRepository, AcademicProgramRepository programRepository, StudentRepository studentRepository, EnrollmentDetailRepository enrollmentDetailRepository, AcademicPeriodRepository academicPeriodRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.programRepository = programRepository;
        this.studentRepository = studentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentEntity entity = EnrollmentMapper.toEntity(enrollment);

        EnrollmentEntity entitySaved = enrollmentRepository.save(entity);
        AcademicProgram program = programRepository.findById(entitySaved.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un programa academico con el id: " + entitySaved.getAcademicProgramId()));
        Student student = studentRepository.findById(entitySaved.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un estudiante con el id: " + entitySaved.getStudentId()));
        ArrayList<EnrollmentDetail> details = enrollmentDetailRepository.findByEnrollmentId(entitySaved.getEnrollmentId());

        AcademicPeriod academicPeriod = academicPeriodRepository.findById(entitySaved.getAcademicPeriodId())
                .orElseThrow(() -> new EntityNotFoundException("No se encontro un periodo academico con id " + entitySaved.getAcademicPeriodId()));

        return EnrollmentMapper.toDomain(entitySaved, student, program, details, academicPeriod);
    }

    @Override
    public Optional<Enrollment> findByStudentAndActivePeriod(String studentId) {
        AcademicPeriod academicPeriod = academicPeriodRepository.findActive()
                .orElseThrow(() -> new PeriodActiveNotFoundException("No se encontro un periodo academico activo"));

        EnrollmentEntity entity = enrollmentRepository.findByStudentAndActivePeriod(
                studentId,
                academicPeriod.getAcademicPeriodId()
        ).orElseThrow(() -> new EntityNotFoundException("No se encontro la matricula del estudiante " + studentId
        + " en el periodo academico " + academicPeriod.getAcademicPeriodId()));

        AcademicProgram program = programRepository.findById(entity.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un programa academico con el id: " + entity.getAcademicProgramId()));
        Student student = studentRepository.findById(entity.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un estudiante con el id: " + entity.getStudentId()));
        ArrayList<EnrollmentDetail> details = enrollmentDetailRepository.findByEnrollmentId(entity.getEnrollmentId());

        /*
        * Deno arreglar la linea numero 69
        * (En este momento est intentando obtener los detalles de una matricula que no existe todavia)*/

        return Optional.of(EnrollmentMapper.toDomain(entity, student, program, details, academicPeriod));
    }
}
