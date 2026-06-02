package org.unisheduler.backend;

import org.unisheduler.backend.application.service.academic_catalog.in.RegisterCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.RegisterCourseService;
import org.unisheduler.backend.application.service.academic_catalog.in.UpdateCourseCommand;
import org.unisheduler.backend.application.service.academic_catalog.in.UpdateCourseService;
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

  public static RegisterCourseCommand registerCourseCommand() {
    List<String> prerequisites = new ArrayList<>();
    prerequisites.add("ALG302");
    return new RegisterCourseCommand(
            "GXT32",
            "MATEMATICAS DISCRETAS",
            3,
            "Breve descripcion",
            prerequisites
    );
  }

  public static UpdateCourseCommand updateCourseCommand() {
    List<String> prerequisites = new ArrayList<>();
    prerequisites.add("ALG302");
    return new UpdateCourseCommand(
            "25",
            "GXT32",
            "MATEMATICAS DISCRETAS",
            3,
            "Esta es una correpcion al caso de uso de actualizar asignatura",
            prerequisites
    );
  }

  public static RegisterStudentCommand createStudentCommand() {
    return new RegisterStudentCommand(
            "Ana",
            "Gomez",
            "CC",
            "123456786",
            LocalDate.of(2002, 5, 15),
            "MALE",

            "3001234567",
            "Calle 123 #45-67",
            "ana.gomez@email.com",

            "1",
            1,
            LocalDate.now(),
            "ACTIVE"
    );
  }

  public static void printCourses(ListAllCoursesResponse response) {

    System.out.println("========================================");
    System.out.println("Resultado: " + response.isSuccssesfully());
    System.out.println("Mensaje: " + response.getMessge());
    System.out.println("========================================");

    for (CourseInfo course : response.getCourses()) {

      System.out.println("ID: " + course.getCourseId());
      System.out.println("Nombre: " + course.getName());
      System.out.println("Código: " + course.getCode());
      System.out.println("Créditos: " + course.getCredits());

      System.out.println("Prerrequisitos:");

      if (course.getPrerequisites() == null || course.getPrerequisites().isEmpty()) {
        System.out.println("  Ninguno");
      } else {
        for (PrerequisiteInfo prerequisite : course.getPrerequisites()) {
          System.out.println(
                  "  - [" + prerequisite.getCode() + "] "
                          + prerequisite.getName()
                          + " (ID: " + prerequisite.getId() + ")"
          );
        }
      }

      System.out.println("----------------------------------------");
    }
  }

  public static void printStudentResponse(RegisterStudentResponse response) {
    System.out.println("message: " + response.getMessage());
    System.out.println("Codigo de estudiante: " + response.getStudent().getStudentCode());
    System.out.println("Contrasenia: " + response.getStudent().getStudentPassword());
  }

  public static void printLoginResponse(LoginUserResponse successLoginResponse) {
    System.out.println("-----------------------------------------------");
    System.out.println("Message: " + successLoginResponse.getMessage());

    if(!successLoginResponse.isSuccessfully()) {
      return;
    }

    System.out.println("UserId: " + successLoginResponse.getUser().getUserId());
    System.out.println("Email: " + successLoginResponse.getUser().getEmail());
    System.out.println("Full Name: " + successLoginResponse.getUser().getFullName());
    System.out.println("Role: " + successLoginResponse.getUser().getRole());
  }

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

    //================// Ejecutar caso de uso (ejemplo) //================//
    //RegisterStudentResponse response = registerStudentService.execute(createStudentCommand());
    //printStudentResponse(response);

    //LoginUserCommand loginCommand = new LoginUserCommand(
    //        "ana.gomez@email.com",
    //              "SuwJivk@"
    //        );

    //LoginUserResponse successLoginResponse = loginUserService.execute(loginCommand);
    //printLoginResponse(successLoginResponse);

    //ListAllCoursesResponse listAllCoursesResponse = listAllCoursesService.execute();
    //printCourses(listAllCoursesResponse);

    //RegisterCourseCommand registerCourseCommand = registerCourseCommand();
    //RegisterCourseResponse response = registerCourseService.execute(registerCourseCommand);

    UpdateCourseCommand updatedCourseCommand = updateCourseCommand();
    UpdateCourseResponse response = updateCourseService.execute(updatedCourseCommand);

    System.out.println("========================================");
    System.out.println("Resultado: " + response.isSuccessfully());
    System.out.println("Mensaje: " + response.getMessage());
    System.out.println("========================================");

    System.out.println("ID: " + response.getCourse().getCourseId());
    System.out.println("Nombre: " + response.getCourse().getName());
    System.out.println("Código: " + response.getCourse().getCode());
    System.out.println("Créditos: " + response.getCourse().getCredits());

    System.out.println("Prerrequisitos:");

    if (response.getCourse().getPrerequisites() == null || response.getCourse().getPrerequisites().isEmpty()) {
      System.out.println("  Ninguno");
    } else {
      for (PrerequisiteInfo prerequisite : response.getCourse().getPrerequisites()) {
        System.out.println(
                "  - [" + prerequisite.getCode() + "] "
                        + prerequisite.getName()
                        + " (ID: " + prerequisite.getId() + ")"
        );
      }
    }
  }

  public static void main(String[] args) {

    dependencyInjection();
  }
}
