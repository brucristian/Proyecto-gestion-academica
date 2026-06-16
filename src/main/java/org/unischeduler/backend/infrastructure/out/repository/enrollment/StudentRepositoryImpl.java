package org.unischeduler.backend.infrastructure.out.repository.enrollment;

import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.auth.entity.User;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.StudentEntity;
import org.unischeduler.backend.infrastructure.out.mapper.enrollment.StudentMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelStudentRepository;

import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {
    private final ExcelStudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentRepositoryImpl(ExcelStudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Student save(Student student) {
        StudentEntity entity = StudentMapper.toEntity(student);
        StudentEntity entitySaved = studentRepository.save(entity);

        User user = userRepository.findById(entitySaved.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + entitySaved.getUserId()));
        return StudentMapper.toDomain(entitySaved, user);
    }

    @Override
    public Optional<Student> findById(String id) {
        Optional<StudentEntity> entityOptional = studentRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        StudentEntity entity = entityOptional.get();
        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + entity.getUserId()));

        return Optional.of(StudentMapper.toDomain(entity, user));
    }

    @Override
    public boolean existsByStudentCode(String code) {
        return studentRepository.existsByStudentCode(code);
    }

    @Override
    public Optional<Student> findByUserId(String userId) {
        Optional<StudentEntity> entityOptional = studentRepository.findStudentByUserId(userId);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        StudentEntity entity = entityOptional.get();
        User user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con id: " + entity.getUserId()));

        return Optional.of(StudentMapper.toDomain(entity, user));
    }
}
