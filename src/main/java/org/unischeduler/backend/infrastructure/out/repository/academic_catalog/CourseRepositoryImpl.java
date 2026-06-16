package org.unischeduler.backend.infrastructure.out.repository.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;
import org.unischeduler.backend.infrastructure.out.mapper.academic_catalog.CourseMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelCourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository {
    private final ExcelCourseRepository courseRepository;

    public CourseRepositoryImpl(ExcelCourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Course> findById(String id) {
        Optional<CourseEntity> entityOptional = courseRepository.findById(id);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        CourseEntity entity = entityOptional.get();
        return Optional.of(CourseMapper.toDomain(entity));
    }

    @Override
    public List<Course> findAll() {
        List<CourseEntity> entities = courseRepository.findAll();

        List<Course> courses = new ArrayList<>();
        for(CourseEntity entity : entities) {
            Course course = CourseMapper.toDomain(entity);
            courses.add(course);
        }

        return courses;
    }

    @Override
    public boolean existsByCode(String code) {
        return courseRepository.existsByCode(code);
    }

    @Override
    public Optional<Course> findByCode(String code) {
        Optional<CourseEntity> entityOptional = courseRepository.findByCode(code);
        if(entityOptional.isEmpty()) {
            return Optional.empty();
        }

        CourseEntity entity = entityOptional.get();

        return Optional.of(CourseMapper.toDomain(entity));
    }

    @Override
    public Course save(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);

        CourseEntity entitySaved = courseRepository.save(entity);
        return CourseMapper.toDomain(entitySaved);
    }

    @Override
    public Course update(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);

        CourseEntity entitySaved = courseRepository.update(entity);
        return CourseMapper.toDomain(entitySaved);
    }

    @Override
    public boolean deleteById(String id) {
        return courseRepository.deleteById(id);
    }
}
