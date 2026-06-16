package org.unischeduler.ui.app;


import org.unischeduler.backend.application.service.auth.login.dtos.UserInfo;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.ListAllAcademicPeriodsUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.RegisterAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.UpdateAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_program.ListAllProgramsUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.DeleteCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.RegisterCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.UpdateCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.DeleteGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.RegisterGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.UpdateGroupUseCase;
import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateCreditLimitUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidatePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.ValidateScheduleConflictsUseCase;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;

public class AppContext {

    public static ExcelDataStore DATA_STORE;
    public static String FILE_PATH;

    private static UserInfo currentUser;

    private static LoginUserUseCase loginUserService;
    private static ListAllCoursesUseCase listAllCoursesService;
    private static RegisterCourseUseCase registerCourseService;
    private static UpdateCourseUseCase updateCourseService;
    private static DeleteCourseUseCase deleteCourseService;
    private static ListAllGroupsUseCase listAllGroupsService;
    private static RegisterGroupUseCase registerGroupService;
    private static UpdateGroupUseCase updateGroupService;
    private static DeleteGroupUseCase deleteGroupService;
    private static RegisterStudentUseCase registerStudentService;
    private static ListAllProgramsUseCase listAllProgramsService;
    private static ListAllAcademicPeriodsUseCase listAllAcademicPeriodsService;
    private static RegisterAcademicPeriodUseCase registerAcademicPeriodService;
    private static UpdateAcademicPeriodUseCase updateAcademicPeriodService;
    private static DeleteAcademicPeriodUseCase deleteAcademicPeriodService;
    private static ValidateScheduleConflictsUseCase validateScheduleConflictsService;
    private static ValidatePrerequisiteUseCase validatePrerequisiteService;
    private static ValidateCreditLimitUseCase validateCreditLimitService;

    private AppContext() {}

    // --------------------------- Authentication ------------------------------------
    public static LoginUserUseCase getLoginUserService() {
        return loginUserService;
    }

    public static void setLoginUserService(LoginUserUseCase service) {
        loginUserService = service;
    }

    // --------------------------- Session ------------------------------------

    public static UserInfo getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserInfo currentUser) {
        AppContext.currentUser = currentUser;
    }

    public static void clearSession() {
        AppContext.currentUser = null;
    }

    // --------------------------- Courses ------------------------------------
    public static ListAllCoursesUseCase getListAllCoursesService() {
        return listAllCoursesService;
    }

    public static void setListAllCoursesService(ListAllCoursesUseCase listAllCoursesService) {
        AppContext.listAllCoursesService = listAllCoursesService;
    }

    public static RegisterCourseUseCase getRegisterCourseService() {
        return registerCourseService;
    }

    public static void setRegisterCourseService(RegisterCourseUseCase registerCourseService) {
        AppContext.registerCourseService = registerCourseService;
    }

    public static UpdateCourseUseCase getUpdateCourseService() {
        return updateCourseService;
    }

    public static void setUpdateCourseService(UpdateCourseUseCase updateCourseService) {
        AppContext.updateCourseService = updateCourseService;
    }

    public static DeleteCourseUseCase getDeleteCourseService() {
        return deleteCourseService;
    }

    public static void setDeleteCourseService(DeleteCourseUseCase deleteCourseService) {
        AppContext.deleteCourseService = deleteCourseService;
    }

    // --------------------------- Groups ------------------------------------

    public static ListAllGroupsUseCase getListAllGroupsService() {
        return listAllGroupsService;
    }

    public static void setListAllGroupsService(ListAllGroupsUseCase listAllGroupsService) {
        AppContext.listAllGroupsService = listAllGroupsService;
    }

    public static RegisterGroupUseCase getRegisterGroupService() {
        return registerGroupService;
    }

    public static void setRegisterGroupService(RegisterGroupUseCase registerGroupService) {
        AppContext.registerGroupService = registerGroupService;
    }

    public static UpdateGroupUseCase getUpdateGroupService() {
        return updateGroupService;
    }

    public static void setUpdateGroupService(UpdateGroupUseCase updateGroupService) {
        AppContext.updateGroupService = updateGroupService;
    }

    public static DeleteGroupUseCase getDeleteGroupService() {
        return deleteGroupService;
    }

    public static void setDeleteGroupService(DeleteGroupUseCase deleteGroupService) {
        AppContext.deleteGroupService = deleteGroupService;
    }

    // --------------------------- Student ------------------------------------

    public static RegisterStudentUseCase getRegisterStudentService() {
        return registerStudentService;
    }

    public static void setRegisterStudentService(RegisterStudentUseCase registerStudentService) {
        AppContext.registerStudentService = registerStudentService;
    }

    // --------------------------- Academic Programs ------------------------------------

    public static ListAllProgramsUseCase getListAllProgramsService() {
        return listAllProgramsService;
    }

    public static void setListAllProgramsService(ListAllProgramsUseCase listAllProgramsService) {
        AppContext.listAllProgramsService = listAllProgramsService;
    }

    // --------------------------- Academic Periods ------------------------------------

    public static ListAllAcademicPeriodsUseCase getListAllAcademicPeriodsService() {
        return listAllAcademicPeriodsService;
    }

    public static void setListAllAcademicPeriodsService(ListAllAcademicPeriodsUseCase listAllAcademicPeriodsService) {
        AppContext.listAllAcademicPeriodsService = listAllAcademicPeriodsService;
    }

    public static RegisterAcademicPeriodUseCase getRegisterAcademicPeriodService() {
        return registerAcademicPeriodService;
    }

    public static void setRegisterAcademicPeriodService(RegisterAcademicPeriodUseCase registerAcademicPeriodService) {
        AppContext.registerAcademicPeriodService = registerAcademicPeriodService;
    }

    public static UpdateAcademicPeriodUseCase getUpdateAcademicPeriodService() {
        return updateAcademicPeriodService;
    }

    public static void setUpdateAcademicPeriodService(UpdateAcademicPeriodUseCase updateAcademicPeriodService) {
        AppContext.updateAcademicPeriodService = updateAcademicPeriodService;
    }

    public static DeleteAcademicPeriodUseCase getDeleteAcademicPeriodService() {
        return deleteAcademicPeriodService;
    }

    public static void setDeleteAcademicPeriodService(DeleteAcademicPeriodUseCase deleteAcademicPeriodService) {
        AppContext.deleteAcademicPeriodService = deleteAcademicPeriodService;
    }

    public static ValidateScheduleConflictsUseCase getValidateScheduleConflictsService() {
        return validateScheduleConflictsService;
    }

    public static void setValidateScheduleConflictsService(ValidateScheduleConflictsUseCase validateScheduleConflictsService) {
        AppContext.validateScheduleConflictsService = validateScheduleConflictsService;
    }

    public static ValidatePrerequisiteUseCase getValidatePrerequisiteService() {
        return validatePrerequisiteService;
    }

    public static void setValidatePrerequisiteService(ValidatePrerequisiteUseCase validatePrerequisiteService) {
        AppContext.validatePrerequisiteService = validatePrerequisiteService;
    }

    public static ValidateCreditLimitUseCase getValidateCreditLimitService() {
        return validateCreditLimitService;
    }

    public static void setValidateCreditLimitService(ValidateCreditLimitUseCase validateCreditLimitService) {
        AppContext.validateCreditLimitService = validateCreditLimitService;
    }
}
