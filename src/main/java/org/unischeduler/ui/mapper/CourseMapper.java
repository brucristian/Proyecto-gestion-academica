package org.unischeduler.ui.mapper;

import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.CourseInfo;
import org.unischeduler.backend.application.service.academic_catalog.out.course.dtos.PrerequisiteInfo;
import org.unischeduler.ui.viewmodel.course.CourseViewModel;
import org.unischeduler.ui.viewmodel.course.PrerequisiteViewModel;

public class CourseMapper {

    public CourseViewModel toViewModel(CourseInfo dto){

        CourseViewModel vm = new CourseViewModel();

        vm.setId(dto.getCourseId());
        vm.setCode(dto.getCode());
        vm.setName(dto.getName());
        vm.setCredits(dto.getCredits());
        vm.setDescription(dto.getDescription());

        dto.getPrerequisites()
                .stream()
                .map(this::toPrerequisiteViewModel)
                .forEach(vm.getPrerequisites()::add);

        return vm;
    }

    public PrerequisiteViewModel toPrerequisiteViewModel(
            PrerequisiteInfo dto){

        return new PrerequisiteViewModel(
                dto.getId(),
                dto.getCode(),
                dto.getName()
        );
    }
}