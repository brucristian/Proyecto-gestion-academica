package org.unischeduler.backend.infrastructure.out.repository.academic_history;

import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_history.AcademicHistoryRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_history.AcademicHistoryEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_history.AcademicHistoryMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_history.ExcelAcademicHistoryRepository;

import java.util.List;
import java.util.Optional;

public class AcademicHistoryRepositoryImpl implements AcademicHistoryRepository {
    private final ExcelAcademicHistoryRepository academicHistoryRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public AcademicHistoryRepositoryImpl(ExcelAcademicHistoryRepository academicHistoryRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.academicHistoryRepository = academicHistoryRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<AcademicHistory> findByStudentId(String studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()) {
            return Optional.empty();
        }

        Student student = studentOptional.get();

        Optional<AcademicHistoryEntity> entityOptional =
                academicHistoryRepository.findByStudentId(studentId);

        if (entityOptional.isEmpty()) {
            return Optional.empty();
        }

        AcademicHistoryEntity entity = entityOptional.get();

        List<Course> courses =
                entity.getCompletedCoursesId()
                        .stream()
                        .map(courseRepository::findById)
                        .flatMap(Optional::stream)
                        .toList();

        AcademicHistory history =
                AcademicHistoryMapper.toDomain(
                        entity,
                        student,
                        courses
                );

        return Optional.of(history);
    }

    @Override
    public AcademicHistory save(AcademicHistory academicHistory) {
        AcademicHistoryEntity entity = academicHistoryRepository.save(
                AcademicHistoryMapper.toEntity(academicHistory)
        );

        List<Course> courses =
                entity.getCompletedCoursesId()
                        .stream()
                        .map(courseRepository::findById)
                        .flatMap(Optional::stream)
                        .toList();

        Student student = studentRepository.findById(entity.getStudentId())
                .orElseThrow(()
                -> new EntityNotFoundException("No se encontro un estudiante con id " + entity.getStudentId()));

        return AcademicHistoryMapper.toDomain(entity, student, courses);
    }
}
