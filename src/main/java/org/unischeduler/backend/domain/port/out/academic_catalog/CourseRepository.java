package org.unischeduler.backend.domain.port.out.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Optional<Course> findById(String id);
    List<Course> findAll();
    boolean existsByCode(String code);
    Optional<Course> findByCode(String code);
    Course save(Course course);
    Course update(Course course);
    boolean deleteById(String id);
}
