package org.unischeduler.backend.infrastructure.out.persistence.excel.core;

import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicPeriodEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicProgramEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.PrerequisiteEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_history.AcademicHistoryEntity;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.*;
import org.unischeduler.backend.infrastructure.out.entity.auth.RoleEntity;
import org.unischeduler.backend.infrastructure.out.entity.auth.UserEntity;
import org.unischeduler.backend.infrastructure.out.entity.auth.UserRoleEntity;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentDetailEntity;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentEntity;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.StudentEntity;

import java.util.Map;

public class ExcelDataStore {

    private Map<String, AcademicProgramEntity> programs;
    private Map<String, CourseEntity> courses;
    private Map<String, AcademicPeriodEntity> periods;
    private Map<String, PrerequisiteEntity> prerequisites;

    private Map<String, AcademicHistoryEntity> histories;

    private Map<String, GroupEntity> groups;
    private Map<String, TeacherEntity> teachers;
    private Map<String, GroupScheduleEntity> groupSchedules;
    private Map<String, SemesterTemplateEntity> templates;
    private Map<String, SemesterTemplateDetailEntity> templateDetails;

    private Map<String, StudentEntity> students;
    private Map<String, EnrollmentEntity> enrollments;
    private Map<String, EnrollmentDetailEntity> enrollmentDetails;

    private Map<String, UserEntity> users;
    private Map<String, RoleEntity> roles;
    private Map<String, UserRoleEntity> userRoles;

    private boolean loaded = false;

    public void load(ExcelDataLoader loader, String filePath) {
        if (loaded) return;

        ExcelDataStore loadedStore = loader.load(filePath);

        this.programs = loadedStore.programs;
        this.courses = loadedStore.courses;
        this.periods = loadedStore.periods;
        this.prerequisites = loadedStore.prerequisites;

        this.histories = loadedStore.histories;

        this.groups = loadedStore.groups;
        this.teachers = loadedStore.teachers;
        this.groupSchedules = loadedStore.groupSchedules;
        this.templates = loadedStore.templates;
        this.templateDetails = loadedStore.templateDetails;

        this.students = loadedStore.students;
        this.enrollments = loadedStore.enrollments;
        this.enrollmentDetails = loadedStore.enrollmentDetails;

        this.users = loadedStore.users;
        this.roles = loadedStore.roles;
        this.userRoles = loadedStore.userRoles;

        loaded = true;
    }

    public Map<String, AcademicProgramEntity> getPrograms() {
        return programs;
    }

    public void setPrograms(Map<String, AcademicProgramEntity> programs) {
        this.programs = programs;
    }

    public Map<String, CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, CourseEntity> courses) {
        this.courses = courses;
    }

    public Map<String, AcademicPeriodEntity> getPeriods() {
        return periods;
    }

    public void setPeriods(Map<String, AcademicPeriodEntity> periods) {
        this.periods = periods;
    }

    public Map<String, PrerequisiteEntity> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Map<String, PrerequisiteEntity> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Map<String, GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Map<String, GroupEntity> groups) {
        this.groups = groups;
    }

    public Map<String, TeacherEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(Map<String, TeacherEntity> teachers) {
        this.teachers = teachers;
    }

    public Map<String, GroupScheduleEntity> getGroupSchedules() {
        return groupSchedules;
    }

    public void setGroupSchedules(Map<String, GroupScheduleEntity> groupSchedules) {
        this.groupSchedules = groupSchedules;
    }

    public Map<String, SemesterTemplateEntity> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, SemesterTemplateEntity> templates) {
        this.templates = templates;
    }

    public Map<String, SemesterTemplateDetailEntity> getTemplateDetails() {
        return templateDetails;
    }

    public void setTemplateDetails(Map<String, SemesterTemplateDetailEntity> templateDetails) {
        this.templateDetails = templateDetails;
    }

    public Map<String, StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Map<String, StudentEntity> students) {
        this.students = students;
    }

    public Map<String, EnrollmentEntity> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Map<String, EnrollmentEntity> enrollments) {
        this.enrollments = enrollments;
    }

    public Map<String, EnrollmentDetailEntity> getEnrollmentDetails() {
        return enrollmentDetails;
    }

    public void setEnrollmentDetails(Map<String, EnrollmentDetailEntity> enrollmentDetails) {
        this.enrollmentDetails = enrollmentDetails;
    }

    public Map<String, UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Map<String, UserEntity> users) {
        this.users = users;
    }

    public Map<String, RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, RoleEntity> roles) {
        this.roles = roles;
    }

    public Map<String, UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Map<String, UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public Map<String, AcademicHistoryEntity> getHistories() {
        return histories;
    }

    public void setHistories(Map<String, AcademicHistoryEntity> histories) {
        this.histories = histories;
    }
}