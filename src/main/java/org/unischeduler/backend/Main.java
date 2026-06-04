package org.unischeduler.backend;

import org.unischeduler.backend.application.service.academic_catalog.in.course.DeleteCourseService;
import org.unischeduler.backend.application.service.academic_catalog.in.course.RegisterCourseService;
import org.unischeduler.backend.application.service.academic_catalog.in.course.UpdateCourseService;
import org.unischeduler.backend.application.service.academic_catalog.out.course.ListAllCoursesServices;
import org.unischeduler.backend.application.service.academic_programming.in.RegisterGroupService;
import org.unischeduler.backend.application.service.academic_programming.in.UpdateGroupService;
import org.unischeduler.backend.application.service.academic_programming.in.DeleteGroupService;
import org.unischeduler.backend.application.service.academic_programming.out.ListAllGroupsServices;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.DeleteCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.ListAllCoursesUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.RegisterCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_catalog.course.UpdateCourseUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.RegisterGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.UpdateGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.DeleteGroupUseCase;
import org.unischeduler.backend.domain.port.in.academic_programming.ListAllGroupsUseCase;
import org.unischeduler.backend.application.service.auth.login.LoginUserService;
import org.unischeduler.backend.application.service.enrollment.register.RegisterStudentService;

import org.unischeduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unischeduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;
import org.unischeduler.backend.domain.port.out.academic_programming.*;
import org.unischeduler.backend.domain.port.out.auth.UserRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.domain.port.out.security.PasswordEncoderPort;
import org.unischeduler.backend.domain.port.out.security.PasswordGeneratorPort;
import org.unischeduler.backend.domain.port.out.security.StudentCodeGeneratorPort;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicProgramRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelCourseRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelPrerequisiteRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program.*;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.auth.ExcelUserRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentDetailRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentRepository;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelStudentRepository;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.AcademicProgramRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.CourseRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_catalog.PrerequisiteRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.academic_programming.*;
import org.unischeduler.backend.infrastructure.out.repository.auth.UserRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.enrollment.EnrollmentDetailImpl;
import org.unischeduler.backend.infrastructure.out.repository.enrollment.EnrollmentRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.repository.enrollment.StudentRepositoryImpl;
import org.unischeduler.backend.infrastructure.out.security.PasswordEncoderAdapter;
import org.unischeduler.backend.infrastructure.out.security.PasswordGeneratorAdapter;
import org.unischeduler.backend.infrastructure.out.security.StudentCodeGeneratorAdapter;

public class Main {

  public static void dependencyInjection() {
    //================// Repositories (Infraestructura) //================//
    UserRepository userRepository = new UserRepositoryImpl(new ExcelUserRepository());

    StudentRepository studentRepository = new StudentRepositoryImpl(new ExcelStudentRepository(), userRepository);

    AcademicProgramRepository academicProgramRepository = new AcademicProgramRepositoryImpl(new ExcelAcademicProgramRepository());

    CourseRepository courseRepository = new CourseRepositoryImpl(new ExcelCourseRepository());

    TeacherRepository teacherRepository = new TeacherRepositoryImpl(new ExcelTeacherRepository());

    GroupScheduleRepository groupScheduleRepository = new GroupScheduleRepositoryImpl(new ExcelGroupScheduleRepository());

    GroupRepository groupRepository = new GroupRepositoryImpl(new ExcelGroupRepository(), courseRepository, teacherRepository, groupScheduleRepository);

    EnrollmentDetailRepository enrollmentDetailRepository = new EnrollmentDetailImpl(new ExcelEnrollmentDetailRepository(), groupRepository);

    EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl(new ExcelEnrollmentRepository(), academicProgramRepository, studentRepository, enrollmentDetailRepository);

    SemesterTemplateDetailRepository semesterTemplateDetailRepository = new SemesterTemplateDetailRepositoryImpl(new ExcelSemesterTemplateDetailRepository(), groupRepository);

    SemesterTemplateRepository semesterTemplateRepository = new SemesterTemplateRepositoryImpl(new ExcelSemesterTemplateRepository(), semesterTemplateDetailRepository);

    PrerequisiteRepository prerequisiteRepository = new PrerequisiteRepositoryImpl(new ExcelPrerequisiteRepository(), courseRepository);

    //================// Ports (Servicios auxiliares) //================//
    PasswordGeneratorPort passwordGenerator = new PasswordGeneratorAdapter();
    PasswordEncoderPort passwordEncoderPort = new PasswordEncoderAdapter();
    StudentCodeGeneratorPort studentCodeGenerator = new StudentCodeGeneratorAdapter();

    //================// Use Case (Aplicación) //================//
    RegisterStudentUseCase registerStudentService =
            new RegisterStudentService(
                    userRepository,
                    studentRepository,
                    enrollmentRepository,
                    academicProgramRepository,
                    passwordGenerator,
                    passwordEncoderPort,
                    studentCodeGenerator,
                    semesterTemplateRepository,
                    enrollmentDetailRepository
            );

    LoginUserUseCase loginUserService = new LoginUserService(
            userRepository,
            passwordEncoderPort
    );

    ListAllCoursesUseCase listAllCoursesService = new ListAllCoursesServices(
            courseRepository,
            prerequisiteRepository
    );

    RegisterCourseUseCase registerCourseService = new RegisterCourseService(
            courseRepository,
            prerequisiteRepository
    );

    UpdateCourseUseCase updateCourseService = new UpdateCourseService(
            courseRepository,
            prerequisiteRepository
    );

    DeleteCourseUseCase deleteCourseService = new DeleteCourseService(courseRepository, prerequisiteRepository);

    ListAllGroupsUseCase listAllGroupsService = new ListAllGroupsServices(
            groupRepository
    );

    RegisterGroupUseCase registerGroupService = new RegisterGroupService(
            groupRepository,
            courseRepository,
            teacherRepository
    );

    UpdateGroupUseCase updateGroupService = new UpdateGroupService(
            groupRepository,
            courseRepository,
            teacherRepository
    );

    DeleteGroupUseCase deleteGroupService = new DeleteGroupService(groupRepository);

  }

  public static void main(String[] args) {

    dependencyInjection();
  }
}
