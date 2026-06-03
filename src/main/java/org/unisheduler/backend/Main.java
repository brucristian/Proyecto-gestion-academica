package org.unisheduler.backend;

import org.unisheduler.backend.application.service.academic_catalog.in.*;
import org.unisheduler.backend.application.service.academic_catalog.in.dtos.DeleteCourseResponse;
import org.unisheduler.backend.application.service.academic_catalog.in.dtos.RegisterCourseResponse;
import org.unisheduler.backend.application.service.academic_catalog.in.dtos.UpdateCourseResponse;
import org.unisheduler.backend.application.service.academic_catalog.out.ListAllCoursesServices;
import org.unisheduler.backend.application.service.academic_catalog.out.dtos.CourseInfo;
import org.unisheduler.backend.application.service.academic_catalog.out.dtos.ListAllCoursesResponse;
import org.unisheduler.backend.application.service.academic_catalog.out.dtos.PrerequisiteInfo;
import org.unisheduler.backend.application.service.auth.login.LoginUserCommand;
import org.unisheduler.backend.application.service.auth.login.LoginUserService;
import org.unisheduler.backend.application.service.auth.login.dtos.LoginUserResponse;
import org.unisheduler.backend.application.service.enrollment.register.RegisterStudentCommand;
import org.unisheduler.backend.application.service.enrollment.register.RegisterStudentService;
import org.unisheduler.backend.application.service.enrollment.register.dtos.RegisterStudentResponse;
import org.unisheduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unisheduler.backend.domain.model.enrollment.entity.Student;
import org.unisheduler.backend.domain.port.in.academic_catalog.DeleteCourseUseCase;
import org.unisheduler.backend.domain.port.in.academic_catalog.ListAllCoursesUseCase;
import org.unisheduler.backend.domain.port.in.academic_catalog.RegisterCourseUseCase;
import org.unisheduler.backend.domain.port.in.academic_catalog.UpdateCourseUseCase;
import org.unisheduler.backend.domain.port.in.auth.LoginUserUseCase;
import org.unisheduler.backend.domain.port.in.enrollment.RegisterStudentUseCase;
import org.unisheduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unisheduler.backend.domain.port.out.academic_catalog.CourseRepository;
import org.unisheduler.backend.domain.port.out.academic_catalog.PrerequisiteRepository;
import org.unisheduler.backend.domain.port.out.academic_programming.*;
import org.unisheduler.backend.domain.port.out.auth.UserRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unisheduler.backend.domain.port.out.security.PasswordEncoderPort;
import org.unisheduler.backend.domain.port.out.security.PasswordGeneratorPort;
import org.unisheduler.backend.domain.port.out.security.StudentCodeGeneratorPort;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicProgramRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelCourseRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelPrerequisiteRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program.*;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.auth.ExcelUserRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentDetailRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentRepository;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelStudentRepository;
import org.unisheduler.backend.infrastructure.out.repository.academic_catalog.AcademicProgramRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.academic_catalog.CourseRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.academic_catalog.PrerequisiteRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.academic_programming.*;
import org.unisheduler.backend.infrastructure.out.repository.auth.UserRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.enrollment.EnrollmentDetailImpl;
import org.unisheduler.backend.infrastructure.out.repository.enrollment.EnrollmentRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.repository.enrollment.StudentRepositoryImpl;
import org.unisheduler.backend.infrastructure.out.security.PasswordEncoderAdapter;
import org.unisheduler.backend.infrastructure.out.security.PasswordGeneratorAdapter;
import org.unisheduler.backend.infrastructure.out.security.StudentCodeGeneratorAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

  }

  public static void main(String[] args) {

    dependencyInjection();
  }
}
