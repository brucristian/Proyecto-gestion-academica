package org.unisheduler.backend.application.service.academic_catalog.out.dtos;

public class PrerequisiteInfo {
    private final String id;
    private final String code;
    private final String name;

    public PrerequisiteInfo(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
