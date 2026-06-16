package org.unischeduler.ui.service;

import org.unischeduler.backend.application.service.academic_programming.in.DeleteGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.RegisterGroupCommand;
import org.unischeduler.backend.application.service.academic_programming.in.UpdateGroupCommand;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.DeleteGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.RegisterGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.UpdateGroupUseCase;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.mapper.GroupMapper;
import org.unischeduler.ui.viewmodel.group.CourseSelectionViewModel;
import org.unischeduler.ui.viewmodel.group.GroupViewModel;
import org.unischeduler.ui.viewmodel.group.TeacherViewModel;


import java.util.List;

public class GroupUiService {

    private final ListAllGroupsUseCase listAllGroupsUseCase;
    private final RegisterGroupUseCase registerGroupUseCase;
    private final UpdateGroupUseCase updateGroupUseCase;
    private final DeleteGroupUseCase deleteGroupUseCase;

    private final ListAllCoursesUseCase listAllCoursesUseCase;

    public GroupUiService() {

        this.listAllGroupsUseCase =
                AppContext.getListAllGroupsService();

        this.registerGroupUseCase =
                AppContext.getRegisterGroupService();

        this.updateGroupUseCase =
                AppContext.getUpdateGroupService();

        this.deleteGroupUseCase =
                AppContext.getDeleteGroupService();

        this.listAllCoursesUseCase =
                AppContext.getListAllCoursesService();

    }

    public List<GroupViewModel> loadGroups() {

        return listAllGroupsUseCase
                .execute()
                .getGroups()
                .stream()
                .map(GroupMapper::toViewModel)
                .toList();
    }

    public List<CourseSelectionViewModel> loadCourses() {

        return listAllCoursesUseCase
                .execute()
                .getCourses()
                .stream()
                .map(course ->
                        new CourseSelectionViewModel(
                                course.getCourseId(),
                                course.getCode(),
                                course.getName()
                        )
                )
                .toList();
    }

    public List<TeacherViewModel> loadTeachers() {

        return List.of(

                new TeacherViewModel(
                        "T001",
                        "Juan Pérez"
                ),

                new TeacherViewModel(
                        "T002",
                        "María Gómez"
                ),

                new TeacherViewModel(
                        "T003",
                        "Carlos Rodríguez"
                )

        );
    }

    public void registerGroup(
            RegisterGroupCommand command
    ) {
        registerGroupUseCase.execute(command);
    }

    public void updateGroup(
            UpdateGroupCommand command
    ) {
        updateGroupUseCase.execute(command);
    }

    public void deleteGroup(
            String groupId
    ) {

        deleteGroupUseCase.execute(
                new DeleteGroupCommand(groupId)
        );
    }
}