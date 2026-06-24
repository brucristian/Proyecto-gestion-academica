package org.unischeduler.backend;

import javafx.application.Application;
import org.unischeduler.backend.application.service.academc_history.GetAcademicHistoryService;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.DeleteAcademicPeriodService;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.RegisterAcademicPeriodService;
import org.unischeduler.backend.application.service.academic_catalog.in.academic_period.UpdateAcademicPeriodService;
import org.unischeduler.backend.application.service.academic_catalog.in.course.DeleteCourseService;
import org.unischeduler.backend.application.service.academic_catalog.in.course.RegisterCourseService;
import org.unischeduler.backend.application.service.academic_catalog.in.course.UpdateCourseService;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.DeletePrerequisiteService;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.FindAllPrerequisitesService;
import org.unischeduler.backend.application.service.academic_catalog.in.prerequisite.RegisterPrerequisiteService;
import org.unischeduler.backend.application.service.academic_catalog.out.academic_period.ListAllAcademicPeriodService;
import org.unischeduler.backend.application.service.academic_catalog.out.academic_programs.ListAllProgramsService;
import org.unischeduler.backend.application.service.academic_catalog.out.course.ListAllCoursesServices;
import org.unischeduler.backend.application.service.academic_programming.in.RegisterGroupService;
import org.unischeduler.backend.application.service.academic_programming.in.UpdateGroupService;
import org.unischeduler.backend.application.service.academic_programming.in.DeleteGroupService;
import org.unischeduler.backend.application.service.academic_programming.out.GetScheduleService;
import org.unischeduler.backend.application.service.academic_programming.out.ListAllGroupsServices;
import org.unischeduler.backend.application.service.auth.login.RegisterUserCommand;
import org.unischeduler.backend.application.service.auth.login.RegisterUserService;
import org.unischeduler.backend.application.service.auth.login.dtos.RegisterUserResponse;
import org.unischeduler.backend.application.service.enrollment.ValidateCreditLimitService;
import org.unischeduler.backend.application.service.enrollment.register.RegisterEnrollmentService;
import org.unischeduler.backend.application.service.enrollment.validate.ValidatePrerequisiteService;
import org.unischeduler.backend.application.service.enrollment.validate.ValidateScheduleConflictsService;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.DeleteAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.ListAllAcademicPeriodsUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.RegisterAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_period.UpdateAcademicPeriodUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.academic_program.ListAllProgramsUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.DeleteCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.RegisterCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.UpdateCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.DeletePrerequisiteUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.FindAllPrerequisitesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.prerequisite.RegisterPrerequisiteUseCase;
import org.unischeduler.backend.domain.port.in.academic_history.GetAcademicHistoryUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.RegisterGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.UpdateGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.DeleteGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.application.service.auth.login.LoginUserService;
import org.unischeduler.backend.application.service.enrollment.register.RegisterStudentService;

import org.unischeduler.backend.domain.port.in.academic_programming.schedule.GetScheduleUseCase;
import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.backend.domain.port.in.auth.RegisterUserUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.*;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;
import org.unischeduler.backend.domain.port.out.academic_history.AcademicHistoryRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.*;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.domain.port.out.security.PasswordEncoderPort;
import org.unischeduler.backend.domain.port.out.security.PasswordGeneratorPort;
import org.unischeduler.backend.domain.port.out.security.StudentCodeGeneratorPort;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataLoader;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelDataStore;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicPeriodRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicProgramRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelCourseRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelPrerequisiteRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_history.ExcelAcademicHistoryRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program.*;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.auth.ExcelUserRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentDetailRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelStudentRepository;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.AcademicPeriodRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.AcademicProgramRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.CourseRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.PrerequisiteRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_history.AcademicHistoryRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_programming.*;
import org.unischeduler.backend.infrastructure.out.repository.auth.UserRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.enrollment.EnrollmentDetailImpl;
import org.unischeduler.backend.infrastructure.out.repository.enrollment.EnrollmentRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.enrollment.StudentRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.security.PasswordEncoderAdapter;
import org.unischeduler.backend.infrastructure.out.security.PasswordGeneratorAdapter;
import org.unischeduler.backend.infrastructure.out.security.StudentCodeGeneratorAdapter;
import org.unischeduler.ui.app.AppContext;
import org.unischeduler.ui.app.MainApplication;

import java.time.LocalDate;

public class Main {

  public static void dependencyInjection() {
    //================// Repositories (Infraestructura) //================//
    ExcelDataStore store = AppContext.DATA_STORE;


    UserRepository userRepository = new UserRepositoryImpl(new ExcelUserRepository(store));

    AcademicProgramRepository academicProgramRepository = new AcademicProgramRepositoryImpl(new ExcelAcademicProgramRepository(store));

    StudentRepository studentRepository = new StudentRepositoryImpl(new ExcelStudentRepository(store), userRepository, academicProgramRepository);

    CourseRepository courseRepository = new CourseRepositoryImpl(new ExcelCourseRepository(store));

    TeacherRepository teacherRepository = new TeacherRepositoryImpl(new ExcelTeacherRepository(store));

    GroupScheduleRepository groupScheduleRepository = new GroupScheduleRepositoryImpl(new ExcelGroupScheduleRepository(store));

    GroupRepository groupRepository = new GroupRepositoryImpl(new ExcelGroupRepository(store), courseRepository, teacherRepository, groupScheduleRepository);

    EnrollmentDetailRepository enrollmentDetailRepository = new EnrollmentDetailImpl(new ExcelEnrollmentDetailRepository(store), groupRepository);

    AcademicPeriodRepository academicPeriodRepository = new AcademicPeriodRepositoryImpl(new ExcelAcademicPeriodRepository(store));

    EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl(new ExcelEnrollmentRepository(store), academicProgramRepository, studentRepository, enrollmentDetailRepository, academicPeriodRepository);

    SemesterTemplateDetailRepository semesterTemplateDetailRepository = new SemesterTemplateDetailRepositoryImpl(new ExcelSemesterTemplateDetailRepository(store), groupRepository);

    SemesterTemplateRepository semesterTemplateRepository = new SemesterTemplateRepositoryImpl(new ExcelSemesterTemplateRepository(store), semesterTemplateDetailRepository);

    PrerequisiteRepository prerequisiteRepository = new PrerequisiteRepositoryImpl(new ExcelPrerequisiteRepository(store), courseRepository);

    AcademicHistoryRepository academicHistoryRepository = new AcademicHistoryRepositoryImpl(
            new ExcelAcademicHistoryRepository(store),
            studentRepository,
            courseRepository
    );

    //================// Ports (Servicios auxiliares) //================//
    PasswordGeneratorPort passwordGenerator = new PasswordGeneratorAdapter();
    PasswordEncoderPort passwordEncoderPort = new PasswordEncoderAdapter();
    StudentCodeGeneratorPort studentCodeGenerator = new StudentCodeGeneratorAdapter();

    //================// Use Case (Aplicación) //================//

    RegisterUserUseCase registerUserService = new RegisterUserService(userRepository, passwordEncoderPort);

    LoginUserUseCase loginUserService = new LoginUserService(
            userRepository,
            studentRepository,
            passwordEncoderPort
    );
    AppContext.setLoginUserService(loginUserService);

    FindAllPrerequisitesUseCase findAllPrerequisitesService = new FindAllPrerequisitesService(prerequisiteRepository, courseRepository);

    DeletePrerequisiteUseCase deletePrerequisiteService = new DeletePrerequisiteService(prerequisiteRepository, courseRepository);

    RegisterPrerequisiteUseCase registerPrerequisiteService = new RegisterPrerequisiteService(prerequisiteRepository,courseRepository, deletePrerequisiteService);
    AppContext.setRegisterPrerequisiteService(registerPrerequisiteService);

    ListAllCoursesUseCase listAllCoursesService = new ListAllCoursesServices(
            courseRepository,
            findAllPrerequisitesService
    );
    AppContext.setListAllCoursesService(listAllCoursesService);

    RegisterCourseUseCase registerCourseService = new RegisterCourseService(
            courseRepository,
            prerequisiteRepository
    );
    AppContext.setRegisterCourseService(registerCourseService);

    UpdateCourseUseCase updateCourseService = new UpdateCourseService(
            courseRepository,
            registerPrerequisiteService
    );
    AppContext.setUpdateCourseService(updateCourseService);

    DeleteCourseUseCase deleteCourseService = new DeleteCourseService(courseRepository, prerequisiteRepository);
    AppContext.setDeleteCourseService(deleteCourseService);

    ListAllGroupsUseCase listAllGroupsService = new ListAllGroupsServices(
            groupRepository
    );
    AppContext.setListAllGroupsService(listAllGroupsService);

    RegisterGroupUseCase registerGroupService = new RegisterGroupService(
            groupRepository,
            courseRepository,
            teacherRepository
    );
    AppContext.setRegisterGroupService(registerGroupService);

    UpdateGroupUseCase updateGroupService = new UpdateGroupService(
            groupRepository,
            courseRepository,
            teacherRepository
    );
    AppContext.setUpdateGroupService(updateGroupService);

    DeleteGroupUseCase deleteGroupService = new DeleteGroupService(groupRepository);
    AppContext.setDeleteGroupService(deleteGroupService);

    ListAllProgramsUseCase listAllProgramsService = new ListAllProgramsService(academicProgramRepository);
    AppContext.setListAllProgramsService(listAllProgramsService);

    ListAllAcademicPeriodsUseCase listAllAcademicPeriodsService = new ListAllAcademicPeriodService(academicPeriodRepository);
    AppContext.setListAllAcademicPeriodsService(listAllAcademicPeriodsService);

    RegisterAcademicPeriodUseCase registerAcademicPeriodService = new RegisterAcademicPeriodService(academicPeriodRepository);
    AppContext.setRegisterAcademicPeriodService(registerAcademicPeriodService);

    UpdateAcademicPeriodUseCase updateAcademicPeriodService = new UpdateAcademicPeriodService(academicPeriodRepository);
    AppContext.setUpdateAcademicPeriodService(updateAcademicPeriodService);

    DeleteAcademicPeriodUseCase deleteAcademicPeriodService = new DeleteAcademicPeriodService(academicPeriodRepository);
    AppContext.setDeleteAcademicPeriodService(deleteAcademicPeriodService);

    ValidateScheduleConflictsUseCase validateScheduleConflictsService = new ValidateScheduleConflictsService(enrollmentRepository);
    AppContext.setValidateScheduleConflictsService(validateScheduleConflictsService);

    ValidatePrerequisiteUseCase validatePrerequisiteService = new ValidatePrerequisiteService(
            prerequisiteRepository,
            enrollmentRepository,
            enrollmentDetailRepository,
            courseRepository
    );
    AppContext.setValidatePrerequisiteService(validatePrerequisiteService);

    ValidateCreditLimitUseCase validateCreditLimitService = new ValidateCreditLimitService(enrollmentRepository, courseRepository);
    AppContext.setValidateCreditLimitService(validateCreditLimitService);

    RegisterEnrollmentUseCase registerEnrollmentService = new RegisterEnrollmentService(
              enrollmentRepository, enrollmentDetailRepository, studentRepository,
              groupRepository, academicProgramRepository, academicPeriodRepository,
              validateCreditLimitService, validatePrerequisiteService
            );
    AppContext.setRegisterEnrollmentService(registerEnrollmentService);

    RegisterStudentUseCase registerStudentService =
            new RegisterStudentService(
                    registerUserService, registerEnrollmentService, userRepository,
                    studentRepository, academicProgramRepository, passwordGenerator,
                    studentCodeGenerator, semesterTemplateRepository
            );
    AppContext.setRegisterStudentService(registerStudentService);


    GetAcademicHistoryUseCase getAcademicHistoryService = new GetAcademicHistoryService(enrollmentRepository);
    AppContext.setGetAcademicHistoryService(getAcademicHistoryService);

    GetScheduleUseCase getScheduleService = new GetScheduleService(enrollmentRepository, groupScheduleRepository);
    AppContext.setGetScheduleService(getScheduleService);

  }

  public static void main(String[] args) {
    AppContext.FILE_PATH = "database/unishedulerdatabase.ods";
    ExcelDataLoader loader = new ExcelDataLoader();

    AppContext.DATA_STORE = loader.load(AppContext.FILE_PATH);

    dependencyInjection();
    Application.launch(MainApplication.class, args);
  }
}
