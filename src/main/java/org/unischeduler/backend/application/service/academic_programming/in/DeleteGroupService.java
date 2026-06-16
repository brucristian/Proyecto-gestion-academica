package org.unischeduler.backend.application.service.academic_programming.in;

import org.unischeduler.backend.application.service.academic_programming.in.dtos.DeleteGroupResponse;
import org.unischeduler.backend.domain.port.in.academic_programming.DeleteGroupUseCase;
import org.unischeduler.backend.domain.port.out.academic_programming.GroupRepository;

public class DeleteGroupService implements DeleteGroupUseCase {
    private final GroupRepository groupRepository;

    public DeleteGroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public DeleteGroupResponse execute(DeleteGroupCommand command) {
        if(!groupRepository.deleteById(command.getGroupId())) {
            return new DeleteGroupResponse(
                    false,
                    "No existe el grupo con id " + command.getGroupId()
            );
        }

        return new DeleteGroupResponse(
                true,
                "Se elimino el grupo con exito"
        );
    }
}
