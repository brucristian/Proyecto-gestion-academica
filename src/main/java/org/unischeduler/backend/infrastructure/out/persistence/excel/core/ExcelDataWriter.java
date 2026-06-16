package org.unischeduler.backend.infrastructure.out.persistence.excel.core;


import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
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
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ExcelDataWriter {

    private static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("hh:mm:ss a");

    // =====================================================
    // 🚀 ENTRY POINT
    // =====================================================
    public void save(String filePath, ExcelDataStore store) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(filePath));

            clearDataRows(doc.getTableByName("AcademicProgram"));
            clearDataRows(doc.getTableByName("AcademicHistory"));
            clearDataRows(doc.getTableByName("Course"));
            clearDataRows(doc.getTableByName("AcademicPeriod"));
            clearDataRows(doc.getTableByName("Prerequisite"));

            clearDataRows(doc.getTableByName("Group"));
            clearDataRows(doc.getTableByName("Teachers"));
            clearDataRows(doc.getTableByName("GroupSchedule"));
            clearDataRows(doc.getTableByName("SemesterTemplate"));
            clearDataRows(doc.getTableByName("SemesterTemplateDetails"));

            clearDataRows(doc.getTableByName("Students"));
            clearDataRows(doc.getTableByName("Enrollments"));
            clearDataRows(doc.getTableByName("EnrollmentDetails"));

            clearDataRows(doc.getTableByName("Users"));
            clearDataRows(doc.getTableByName("Roles"));
            clearDataRows(doc.getTableByName("UserRole"));

            writePrograms(doc, store.getPrograms());
            writeHistories(doc, store.getHistories());
            writeCourses(doc, store.getCourses());
            writePeriods(doc, store.getPeriods());
            writePrerequisites(doc, store.getPrerequisites());

            writeGroups(doc, store.getGroups());
            writeTeachers(doc, store.getTeachers());
            writeGroupSchedules(doc, store.getGroupSchedules());
            writeTemplates(doc, store.getTemplates());
            writeTemplateDetails(doc, store.getTemplateDetails());

            writeStudents(doc, store.getStudents());
            writeEnrollments(doc, store.getEnrollments());
            writeEnrollmentDetails(doc, store.getEnrollmentDetails());

            writeUsers(doc, store.getUsers());
            writeRoles(doc, store.getRoles());
            writeUserRoles(doc, store.getUserRoles());

            doc.save(filePath);

        } catch (Exception e) {
            throw new RuntimeException("Error saving Excel data", e);
        }
    }

    private void clearDataRows(Table table) {

        while (table.getRowCount() > 1) {
            table.removeRowsByIndex(1, 1);
        }
    }

    // =====================================================
    //  CATALOG
    // =====================================================
    private void writeHistories(SpreadsheetDocument doc,
                               Map<String, AcademicHistoryEntity> map) {

        Table table = doc.getTableByName("AcademicHistory");

        int row = 1;
        for (AcademicHistoryEntity e : map.values()) {

            ensureRow(table, row);
            for(int i = 0; i < e.getCompletedCoursesId().size(); i++) {
                table.getCellByPosition(0, row).setStringValue(e.getHistoryId());
                table.getCellByPosition(1, row).setStringValue(e.getStudentId());
                table.getCellByPosition(2, row).setStringValue(
                        e.getCompletedCoursesId().get(i)
                );
                table.getCellByPosition(3, row).setDoubleValue(e.getGrade());
                table.getCellByPosition(4, row).setStringValue(e.getStatus());
            }

            row++;
        }
    }

    private void writePrograms(SpreadsheetDocument doc,
                               Map<String, AcademicProgramEntity> map) {

        Table table = doc.getTableByName("AcademicProgram");

        int row = 1;
        for (AcademicProgramEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getAcademicProgramId());
            table.getCellByPosition(1, row).setStringValue(e.getName());
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getTotalSemesters()));

            row++;
        }
    }

    private void writeCourses(SpreadsheetDocument doc,
                              Map<String, CourseEntity> map) {

        Table table = doc.getTableByName("Course");

        int row = 1;
        for (CourseEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getCourseId());
            table.getCellByPosition(1, row).setStringValue(e.getName());
            table.getCellByPosition(2, row).setStringValue(e.getCode());
            table.getCellByPosition(3, row).setStringValue(String.valueOf(e.getCredits()));
            table.getCellByPosition(4, row).setStringValue(e.getDescription());

            row++;
        }
    }

    private void writePeriods(SpreadsheetDocument doc,
                              Map<String, AcademicPeriodEntity> map) {

        Table table = doc.getTableByName("AcademicPeriod");

        int row = 1;
        for (AcademicPeriodEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getAcademicPeriodId());
            table.getCellByPosition(1, row).setStringValue(e.getCode());
            table.getCellByPosition(2, row).setStringValue(e.getName());
            table.getCellByPosition(3, row).setStringValue(e.getStartDate());
            table.getCellByPosition(4, row).setStringValue(e.getEndDate());
            table.getCellByPosition(5, row).setStringValue(e.getStatus());

            row++;
        }
    }

    private void writePrerequisites(SpreadsheetDocument doc,
                                    Map<String, PrerequisiteEntity> map) {

        Table table = doc.getTableByName("Prerequisite");

        int row = 1;
        for (PrerequisiteEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getPrerequisiteId());
            table.getCellByPosition(1, row).setStringValue(e.getCourseId());
            table.getCellByPosition(2, row).setStringValue(e.getPrerequisiteCourseId());

            row++;
        }
    }

    // =====================================================
    // 🏫 PROGRAMMING
    // =====================================================

    private void writeGroups(SpreadsheetDocument doc,
                             Map<String, GroupEntity> map) {

        Table table = doc.getTableByName("Group");

        int row = 1;
        for (GroupEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getGroupId());
            table.getCellByPosition(1, row).setStringValue(e.getCourseId());
            table.getCellByPosition(2, row).setStringValue(e.getTeacherId());
            table.getCellByPosition(3, row).setStringValue(e.getGroupCode());
            table.getCellByPosition(4, row).setStringValue(String.valueOf(e.getCapacity()));

            row++;
        }
    }

    private void writeTeachers(SpreadsheetDocument doc,
                               Map<String, TeacherEntity> map) {

        Table table = doc.getTableByName("Teachers");

        int row = 1;
        for (TeacherEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getTeacherId());
            table.getCellByPosition(1, row).setStringValue(e.getName());

            row++;
        }
    }

    private void writeGroupSchedules(SpreadsheetDocument doc,
                                     Map<String, GroupScheduleEntity> map) {

        Table table = doc.getTableByName("GroupSchedule");

        int row = 1;
        for (GroupScheduleEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getGroupScheduleId());
            table.getCellByPosition(1, row).setStringValue(e.getGroupId());
            table.getCellByPosition(2, row).setStringValue(e.getDayOfWeek());

            table.getCellByPosition(3, row).setStringValue(e.getStartTime().format(TIME_FORMATTER));
            table.getCellByPosition(4, row).setStringValue(e.getEndTime().format(TIME_FORMATTER));

            table.getCellByPosition(5, row).setStringValue(e.getClassroom());

            row++;
        }
    }

    private void writeTemplates(SpreadsheetDocument doc,
                                Map<String, SemesterTemplateEntity> map) {

        Table table = doc.getTableByName("SemesterTemplate");

        int row = 1;
        for (SemesterTemplateEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getTemplateId());
            table.getCellByPosition(1, row).setStringValue(e.getProgramId());
            table.getCellByPosition(2, row).setStringValue(String.valueOf(e.getSemester()));

            row++;
        }
    }

    private void writeTemplateDetails(SpreadsheetDocument doc,
                                      Map<String, SemesterTemplateDetailEntity> map) {

        Table table = doc.getTableByName("SemesterTemplateDetails");

        int row = 1;
        for (SemesterTemplateDetailEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getTemplateDetailId());
            table.getCellByPosition(1, row).setStringValue(e.getTemplateId());
            table.getCellByPosition(2, row).setStringValue(e.getGroupId());

            row++;
        }
    }

    // =====================================================
    // 🧑‍🎓 ENROLLMENT
    // =====================================================

    private void writeStudents(SpreadsheetDocument doc,
                               Map<String, StudentEntity> map) {

        Table table = doc.getTableByName("Students");

        int row = 1;
        for (StudentEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getStudentId());
            table.getCellByPosition(1, row).setStringValue(e.getStudentCode());
            table.getCellByPosition(2, row).setStringValue(e.getUserId());

            row++;
        }
    }

    private void writeEnrollments(SpreadsheetDocument doc,
                                  Map<String, EnrollmentEntity> map) {

        Table table = doc.getTableByName("Enrollments");

        int row = 1;
        for (EnrollmentEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getEnrollmentId());
            table.getCellByPosition(1, row).setStringValue(e.getStudentId());
            table.getCellByPosition(2, row).setStringValue(e.getAcademicProgramId());
            table.getCellByPosition(3, row).setStringValue(e.getEnrollmentDate().toString());
            table.getCellByPosition(4, row).setStringValue(e.getAcademicPeriodId());

            row++;
        }
    }

    private void writeEnrollmentDetails(SpreadsheetDocument doc,
                                        Map<String, EnrollmentDetailEntity> map) {

        Table table = doc.getTableByName("EnrollmentDetails");

        int row = 1;
        for (EnrollmentDetailEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getEnrollmentDetailId());
            table.getCellByPosition(1, row).setStringValue(e.getEnrollmentId());
            table.getCellByPosition(2, row).setStringValue(e.getGroupId());
            table.getCellByPosition(3, row).setStringValue(e.getStatus());
            table.getCellByPosition(4, row).setStringValue(String.valueOf(e.getFinalGrade()));

            row++;
        }
    }

    // =====================================================
    // 🔐 AUTH
    // =====================================================

    private void writeUsers(SpreadsheetDocument doc,
                            Map<String, UserEntity> map) {

        Table table = doc.getTableByName("Users");

        int row = 1;
        for (UserEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getUserId());
            table.getCellByPosition(1, row).setStringValue(e.getFirstName());
            table.getCellByPosition(2, row).setStringValue(e.getLastName());
            table.getCellByPosition(3, row).setStringValue(e.getDocumentType());
            table.getCellByPosition(4, row).setStringValue(e.getDocumentNumber());
            table.getCellByPosition(5, row).setStringValue(String.valueOf(e.getBirthDate()));
            table.getCellByPosition(6, row).setStringValue(e.getGender());
            table.getCellByPosition(7, row).setStringValue(e.getPhoneNumber());
            table.getCellByPosition(8, row).setStringValue(e.getAddress());
            table.getCellByPosition(9, row).setStringValue(e.getEmail());
            table.getCellByPosition(10, row).setStringValue(e.getPassword());
            table.getCellByPosition(11, row).setStringValue(e.getStatus());
            table.getCellByPosition(12, row).setStringValue(e.getRole());
            table.getCellByPosition(13, row).setStringValue(String.valueOf(e.getCreatedAt()));

            row++;
        }
    }

    private void writeRoles(SpreadsheetDocument doc,
                            Map<String, RoleEntity> map) {

        Table table = doc.getTableByName("Roles");

        int row = 1;
        for (RoleEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getRoleId());
            table.getCellByPosition(1, row).setStringValue(e.getName());

            row++;
        }
    }

    private void writeUserRoles(SpreadsheetDocument doc,
                                Map<String, UserRoleEntity> map) {

        Table table = doc.getTableByName("UserRole");

        int row = 1;
        for (UserRoleEntity e : map.values()) {

            ensureRow(table, row);

            table.getCellByPosition(0, row).setStringValue(e.getUserRoleId());
            table.getCellByPosition(1, row).setStringValue(e.getUserId());
            table.getCellByPosition(2, row).setStringValue(e.getRoleId());

            row++;
        }
    }

    // =====================================================
    // 🧰 UTIL
    // =====================================================

    private void ensureRow(Table table, int rowIndex) {
        if (rowIndex >= table.getRowCount()) {
            table.appendRow();
        }
    }
}