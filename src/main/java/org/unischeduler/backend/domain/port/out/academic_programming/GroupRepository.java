package org.unischeduler.backend.domain.port.out.academic_programming;

import org.unischeduler.backend.domain.model.academic_programming.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);
    List<Group> findAll();
    Group save(Group group);
    Group update(Group group);
    boolean deleteById(String id);
}
