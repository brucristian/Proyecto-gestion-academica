package org.unischeduler.backend.application.service.academic_programming.out.dtos;

import java.util.List;

public class ListAllGroupsResponse {
    private final boolean successfully;
    private final String message;
    private final List<GroupInfo> groups;

    public ListAllGroupsResponse(boolean successfully, String message, List<GroupInfo> groups) {
        this.successfully = successfully;
        this.message = message;
        this.groups = groups;
    }

    public boolean isSuccessfully() {
        return successfully;
    }

    public String getMessage() {
        return message;
    }

    public List<GroupInfo> getGroups() {
        return groups;
    }
}
