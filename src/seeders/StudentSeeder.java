package seeders;

import module.academic_catalog.model.AcademicProgram;
import module.authentication.model.Password;
import module.authentication.model.User;
import module.enrollment.model.Student;
import repository.StudentRepository;
import shared.enums.Role;


public class StudentSeeder {

    public static void seed(
            StudentRepository studentRepository,
            AcademicProgram systemsEngineering
    ) {

        studentRepository.save(new Student(
                1L,
                "202311001",
                new User(
                        1L,
                        "Joan Sebastian",
                        "Vergara Valencia",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                2L,
                "202311002",
                new User(
                        2L,
                        "Maria Fernanda",
                        "Gomez",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                3L,
                "202311003",
                new User(
                        3L,
                        "Carlos Andres",
                        "Ruiz",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                4L,
                "202311004",
                new User(
                        4L,
                        "Laura Camila",
                        "Torres",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                5L,
                "202311005",
                new User(
                        5L,
                        "Daniel Esteban",
                        "Perez",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                6L,
                "202311006",
                new User(
                        6L,
                        "Sofia Alejandra",
                        "Martinez",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                7L,
                "202311007",
                new User(
                        7L,
                        "Miguel Angel",
                        "Hernandez",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                8L,
                "202311008",
                new User(
                        8L,
                        "Valentina",
                        "Rodriguez",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                9L,
                "202311009",
                new User(
                        9L,
                        "Andres Felipe",
                        "Castro",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        studentRepository.save(new Student(
                10L,
                "202311010",
                new User(
                        10L,
                        "Camila Andrea",
                        "Mendoza",
                        new Password("Password123*"),
                        Role.STUDENT
                ),
                systemsEngineering
        ));

        System.out.println("Students seeded successfully.");
    }
}