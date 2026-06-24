package org.unischeduler.backend.infrastructure.out.persistence.excel.core;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.domain.model.academic_history.entity.AcademicHistory;
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

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDataLoader {

    public ExcelDataStore load(String filePath) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(filePath));

            ExcelDataStore store = new ExcelDataStore();

            store.setPrograms(readPrograms(doc));
            store.setCourses(readCourses(doc));
            store.setPeriods(readPeriods(doc));
            store.setPrerequisites(readPrerequisites(doc));

            store.setHistories(readHistories(doc));

            store.setGroups(readGroups(doc));
            store.setTeachers(readTeachers(doc));
            store.setGroupSchedules(readGroupSchedules(doc));
            store.setTemplates(readTemplates(doc));
            store.setTemplateDetails(readTemplateDetails(doc));

            store.setStudents(readStudents(doc));
            store.setEnrollments(readEnrollments(doc));
            store.setEnrollmentDetails(readEnrollmentDetails(doc));

            store.setUsers(readUsers(doc));
            store.setRoles(readRoles(doc));
            store.setUserRoles(readUserRoles(doc));

            store.setLoaded(true);

            return store;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private Map<String, AcademicHistoryEntity> readHistories(SpreadsheetDocument doc) {
        Map<String, AcademicHistoryEntity> map = new HashMap<>();
        Table table = doc.getTableByName("AcademicHistory");

        for (int i = 1; i < table.getRowCount(); i++) {

            AcademicHistoryEntity e = new AcademicHistoryEntity();

            e.setHistoryId(table.getCellByPosition(0, i).getStringValue());
            e.setStudentId(table.getCellByPosition(1, i).getStringValue());

            List<String> completedCourses = new ArrayList<>();
            for(int j = 1; j < table.getRowCount(); j++) {

                String studentId = table.getCellByPosition(1, j).getStringValue();
                if(studentId.equals(e.getStudentId())) {
                    completedCourses.add(
                        table.getCellByPosition(2, j).getStringValue()
                    );
                }
            }

            e.setCompletedCourses(completedCourses);
            e.setGrade(Double.parseDouble(table.getCellByPosition(3, i).getStringValue()));
            e.setStatus(table.getCellByPosition(4, i).getStringValue());

            map.put(e.getHistoryId(), e);
        }

        return map;
    }

    // =====================================================
    // 📚 CATALOG
    // =====================================================

    private Map<String, AcademicProgramEntity> readPrograms(SpreadsheetDocument doc) {

        Map<String, AcademicProgramEntity> map = new HashMap<>();
        Table table = doc.getTableByName("AcademicProgram");

        for (int i = 1; i < table.getRowCount(); i++) {

            AcademicProgramEntity e = new AcademicProgramEntity();

            e.setAcademicProgramId(table.getCellByPosition(0, i).getStringValue());
            e.setName(table.getCellByPosition(1, i).getStringValue());
            e.setTotalSemesters(Integer.parseInt(
                    table.getCellByPosition(2, i).getStringValue()
            ));


            map.put(e.getAcademicProgramId(), e);
        }

        return map;
    }

    private Map<String, CourseEntity> readCourses(SpreadsheetDocument doc) {

        Map<String, CourseEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Course");

        for (int i = 1; i < table.getRowCount(); i++) {

            CourseEntity e = new CourseEntity();

            e.setCourseId(table.getCellByPosition(0, i).getStringValue());
            e.setName(table.getCellByPosition(1, i).getStringValue());
            e.setCode(table.getCellByPosition(2, i).getStringValue());
            e.setCredits(Integer.parseInt(
                    table.getCellByPosition(3, i).getStringValue()
            ));

            map.put(e.getCourseId(), e);
        }

        return map;
    }

    private Map<String, AcademicPeriodEntity> readPeriods(SpreadsheetDocument doc) {

        Map<String, AcademicPeriodEntity> map = new HashMap<>();
        Table table = doc.getTableByName("AcademicPeriod");

        for (int i = 1; i < table.getRowCount(); i++) {

            AcademicPeriodEntity e = new AcademicPeriodEntity();

            e.setAcademicPeriodId(table.getCellByPosition(0, i).getStringValue());
            e.setCode(table.getCellByPosition(1, i).getStringValue());
            e.setName(table.getCellByPosition(2, i).getStringValue());
            e.setStartDate(table.getCellByPosition(3, i).getStringValue());
            e.setEndDate(table.getCellByPosition(4, i).getStringValue());
            e.setStatus(table.getCellByPosition(5, i).getStringValue());

            map.put(e.getAcademicPeriodId(), e);
        }

        return map;
    }

    private Map<String, PrerequisiteEntity> readPrerequisites(SpreadsheetDocument doc) {

        Map<String, PrerequisiteEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Prerequisite");

        for (int i = 1; i < table.getRowCount(); i++) {

            PrerequisiteEntity e = new PrerequisiteEntity();

            e.setPrerequisiteId(table.getCellByPosition(0, i).getStringValue());
            e.setCourseId(table.getCellByPosition(1, i).getStringValue());
            e.setPrerequisiteCourseId(table.getCellByPosition(2, i).getStringValue());

            map.put(e.getPrerequisiteId(), e);
        }

        return map;
    }

    // =====================================================
    // 🏫 PROGRAMMING
    // =====================================================

    private Map<String, GroupEntity> readGroups(SpreadsheetDocument doc) {

        Map<String, GroupEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Group");

        for (int i = 1; i < table.getRowCount(); i++) {

            GroupEntity g = new GroupEntity();

            g.setGroupId(table.getCellByPosition(0, i).getStringValue());
            g.setCourseId(table.getCellByPosition(1, i).getStringValue());
            g.setTeacherId(table.getCellByPosition(2, i).getStringValue());
            g.setGroupCode(table.getCellByPosition(3, i).getStringValue());
            g.setCapacity(Integer.parseInt(
                    table.getCellByPosition(4, i).getStringValue()
            ));

            map.put(g.getGroupId(), g);
        }

        System.out.println("TOTAL GROUPS LOADED: " + map.size());

        return map;
    }

    private Map<String, TeacherEntity> readTeachers(SpreadsheetDocument doc) {

        Map<String, TeacherEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Teachers");

        for (int i = 1; i < table.getRowCount(); i++) {

            TeacherEntity t = new TeacherEntity();

            t.setTeacherId(table.getCellByPosition(0, i).getStringValue());
            t.setName(table.getCellByPosition(1, i).getStringValue());

            map.put(t.getTeacherId(), t);
        }

        return map;
    }

    private Map<String, GroupScheduleEntity> readGroupSchedules(SpreadsheetDocument doc) {

        Map<String, GroupScheduleEntity> map = new HashMap<>();
        Table table = doc.getTableByName("GroupSchedule");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        for (int i = 1; i < table.getRowCount(); i++) {

            GroupScheduleEntity e = new GroupScheduleEntity();

            e.setGroupScheduleId(table.getCellByPosition(0, i).getStringValue());
            e.setGroupId(table.getCellByPosition(1, i).getStringValue());
            e.setDayOfWeek(table.getCellByPosition(2, i).getStringValue());

            String startStr = table.getCellByPosition(3, i).getStringValue();
            String endStr = table.getCellByPosition(4, i).getStringValue();


            e.setStartTime(LocalTime.parse(startStr, timeFormatter));
            e.setEndTime(LocalTime.parse(endStr, timeFormatter));
            e.setClassroom(table.getCellByPosition(5, i).getStringValue());

            map.put(e.getGroupScheduleId(), e);
        }

        return map;
    }

    private Map<String, SemesterTemplateEntity> readTemplates(SpreadsheetDocument doc) {

        Map<String, SemesterTemplateEntity> map = new HashMap<>();
        Table table = doc.getTableByName("SemesterTemplate");

        for (int i = 1; i < table.getRowCount(); i++) {

            SemesterTemplateEntity e = new SemesterTemplateEntity();

            e.setTemplateId(table.getCellByPosition(0, i).getStringValue());
            e.setProgramId(table.getCellByPosition(1, i).getStringValue());
            e.setSemester(Integer.parseInt(
                    table.getCellByPosition(2, i).getStringValue()
            ));

            map.put(e.getTemplateId(), e);
        }

        return map;
    }

    private Map<String, SemesterTemplateDetailEntity> readTemplateDetails(SpreadsheetDocument doc) {

        Map<String, SemesterTemplateDetailEntity> map = new HashMap<>();
        Table table = doc.getTableByName("SemesterTemplateDetails");

        for (int i = 1; i < table.getRowCount(); i++) {

            SemesterTemplateDetailEntity e = new SemesterTemplateDetailEntity();

            e.setTemplateDetailId(table.getCellByPosition(0, i).getStringValue());
            e.setTemplateId(table.getCellByPosition(1, i).getStringValue());
            e.setGroupId(table.getCellByPosition(2, i).getStringValue());

            map.put(e.getTemplateDetailId(), e);
        }

        return map;
    }

    // =====================================================
    // 🧑‍🎓 ENROLLMENT
    // =====================================================

    private Map<String, StudentEntity> readStudents(SpreadsheetDocument doc) {

        Map<String, StudentEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Students");

        for (int i = 1; i < table.getRowCount(); i++) {

            StudentEntity s = new StudentEntity();

            s.setStudentId(table.getCellByPosition(0, i).getStringValue());
            s.setStudentCode(table.getCellByPosition(1, i).getStringValue());
            s.setUserId(table.getCellByPosition(2, i).getStringValue());
            s.setAcademicProgramId(table.getCellByPosition(3, i).getStringValue());

            map.put(s.getStudentId(), s);
        }

        return map;
    }

    private Map<String, EnrollmentEntity> readEnrollments(SpreadsheetDocument doc) {

        Map<String, EnrollmentEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Enrollments");

        for (int i = 1; i < table.getRowCount(); i++) {

            EnrollmentEntity e = new EnrollmentEntity();

            e.setEnrollmentId(table.getCellByPosition(0, i).getStringValue());
            e.setStudentId(table.getCellByPosition(1, i).getStringValue());
            e.setAcademicProgramId(table.getCellByPosition(2, i).getStringValue());
            e.setEnrollmentDate(LocalDate.parse(table.getCellByPosition(3, i).getStringValue()));
            e.setAcademicPeriodId(table.getCellByPosition(4, i).getStringValue());

            map.put(e.getEnrollmentId(), e);
        }

        return map;
    }

    private Map<String, EnrollmentDetailEntity> readEnrollmentDetails(SpreadsheetDocument doc) {

        Map<String, EnrollmentDetailEntity> map = new HashMap<>();
        Table table = doc.getTableByName("EnrollmentDetails");

        for (int i = 1; i < table.getRowCount(); i++) {

            EnrollmentDetailEntity e = new EnrollmentDetailEntity();

            e.setEnrollmentDetailId(table.getCellByPosition(0, i).getStringValue());
            e.setEnrollmentId(table.getCellByPosition(1, i).getStringValue());
            e.setGroupId(table.getCellByPosition(2, i).getStringValue());
            e.setStatus(table.getCellByPosition(3, i).getStringValue());
            e.setFinalGrade(Double.parseDouble(
                    table.getCellByPosition(4, i).getStringValue()
            ));

            map.put(e.getEnrollmentDetailId(), e);
        }

        return map;
    }

    // =====================================================
    // 🔐 AUTH
    // =====================================================

    private Map<String, UserEntity> readUsers(SpreadsheetDocument doc) {

        Map<String, UserEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Users");

        for (int i = 1; i < table.getRowCount(); i++) {

            UserEntity u = new UserEntity();

            u.setUserId(table.getCellByPosition(0, i).getStringValue());
            u.setFirstName(table.getCellByPosition(1, i).getStringValue());
            u.setLastName(table.getCellByPosition(2, i).getStringValue());
            u.setDocumentType(table.getCellByPosition(3, i).getStringValue());
            u.setDocumentNumber(table.getCellByPosition(4, i).getStringValue());
            u.setBirthDate(LocalDate.parse(table.getCellByPosition(5, i).getStringValue()));
            u.setGender(table.getCellByPosition(6, i).getStringValue());
            u.setPhoneNumber(table.getCellByPosition(7, i).getStringValue());
            u.setAddress(table.getCellByPosition(8, i).getStringValue());
            u.setEmail(table.getCellByPosition(9, i).getStringValue());
            u.setPassword(table.getCellByPosition(10, i).getStringValue());
            u.setStatus(table.getCellByPosition(11, i).getStringValue());
            u.setRole(table.getCellByPosition(12, i).getStringValue());
            u.setCreatedAt(LocalDateTime.parse(table.getCellByPosition(13, i).getStringValue()));

            map.put(u.getUserId(), u);
        }

        return map;
    }

    private Map<String, RoleEntity> readRoles(SpreadsheetDocument doc) {

        Map<String, RoleEntity> map = new HashMap<>();
        Table table = doc.getTableByName("Roles");

        for (int i = 1; i < table.getRowCount(); i++) {

            RoleEntity r = new RoleEntity();

            r.setRoleId(table.getCellByPosition(0, i).getStringValue());
            r.setName(table.getCellByPosition(1, i).getStringValue());

            map.put(r.getRoleId(), r);
        }

        return map;
    }

    private Map<String, UserRoleEntity> readUserRoles(SpreadsheetDocument doc) {

        Map<String, UserRoleEntity> map = new HashMap<>();
        Table table = doc.getTableByName("UserRole");

        for (int i = 1; i < table.getRowCount(); i++) {

            UserRoleEntity ur = new UserRoleEntity();

            ur.setUserRoleId(table.getCellByPosition(0, i).getStringValue());
            ur.setUserId(table.getCellByPosition(1, i).getStringValue());
            ur.setRoleId(table.getCellByPosition(2, i).getStringValue());

            map.put(ur.getUserRoleId(), ur);
        }

        return map;
    }
}