package org.unischeduler.backend.infrastructure.out.mapper.academic_catalog;

import org.unischeduler.backend.domain.model.academic_catalog.entity.Course;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {

    //================// Entity -> Domain //================//
    public static Course toDomain(CourseEntity entity) {
        if (entity == null) return null;

        return new Course(
                entity.getCourseId(),
                entity.getName(),
                entity.getCode(),
                entity.getCredits(),
                entity.getDescription(),
                new ArrayList<>()
        );
    }

    //================// Domain -> Entity //================//
    public static CourseEntity toEntity(Course course) {
        if (course == null) return null;

        CourseEntity entity = new CourseEntity();
        entity.setCourseId(course.getCourseId());
        entity.setName(course.getName());
        entity.setCode(course.getCode());
        entity.setCredits(course.getCredits());
        entity.setDescription(course.getDescription());

        return entity;
    }

    //================// List conversions //================//
    public static List<Course> toDomainList(List<CourseEntity> entities) {
        if (entities == null) return new ArrayList<>();

        List<Course> courses = new ArrayList<>();
        for (CourseEntity entity : entities) {
            courses.add(toDomain(entity));
        }
        return courses;
    }

    public static List<CourseEntity> toEntityList(List<Course> courses) {
        if (courses == null) return new ArrayList<>();

        List<CourseEntity> entities = new ArrayList<>();
        for (Course course : courses) {
            entities.add(toEntity(course));
        }
        return entities;
    }
}