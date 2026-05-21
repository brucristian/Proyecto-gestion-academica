package seeders;

import module.academic_catalog.model.Course;
import module.academic_programming.model.Group;
import module.academic_programming.model.GroupSchedule;
import module.academic_programming.model.Teacher;
import repository.CourseRepository;
import repository.GroupRepository;
import shared.enums.WeekDays;

import java.time.LocalTime;
import java.util.ArrayList;

public class GroupSeeder {

    public static void seed(
            GroupRepository groupRepository,
            CourseRepository courseRepository
    ) {

        //================ TEACHERS ================//

        Teacher teacher1 = new Teacher(
                1L,
                "Carlos",
                "Ramirez"
        );

        Teacher teacher2 = new Teacher(
                2L,
                "Laura",
                "Martinez"
        );

        Teacher teacher3 = new Teacher(
                3L,
                "Andres",
                "Gomez"
        );

        //================ COURSES ================//

        Course calculoI =
                courseRepository.findByCode("MAT101");

        Course algoritmos =
                courseRepository.findByCode("SYS102");

        Course poo =
                courseRepository.findByCode("SYS301");

        Course fisicaI =
                courseRepository.findByCode("PHY201");

        //================ GROUP 1 ================//

        ArrayList<GroupSchedule> calculoSchedules =
                new ArrayList<>();

        calculoSchedules.add(new GroupSchedule(
                1L,
                1L,
                WeekDays.MONDAY,
                LocalTime.of(7, 0),
                LocalTime.of(9, 0),
                "A-201"
        ));

        calculoSchedules.add(new GroupSchedule(
                2L,
                1L,
                WeekDays.WEDNESDAY,
                LocalTime.of(7, 0),
                LocalTime.of(9, 0),
                "A-201"
        ));

        Group calculoGroup = new Group(
                1L,
                calculoI,
                "CAL-I-01",
                teacher1,
                35,
                35,
                calculoSchedules
        );

        //================ GROUP 2 ================//

        ArrayList<GroupSchedule> algoritmosSchedules =
                new ArrayList<>();

        algoritmosSchedules.add(new GroupSchedule(
                3L,
                2L,
                WeekDays.TUESDAY,
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                "B-101"
        ));

        algoritmosSchedules.add(new GroupSchedule(
                4L,
                2L,
                WeekDays.THURSDAY,
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                "B-101"
        ));

        Group algoritmosGroup = new Group(
                2L,
                algoritmos,
                "ALG-01",
                teacher2,
                30,
                28,
                algoritmosSchedules
        );

        //================ GROUP 3 ================//

        ArrayList<GroupSchedule> pooSchedules =
                new ArrayList<>();

        pooSchedules.add(new GroupSchedule(
                5L,
                3L,
                WeekDays.MONDAY,
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                "LAB-02"
        ));

        pooSchedules.add(new GroupSchedule(
                6L,
                3L,
                WeekDays.FRIDAY,
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                "LAB-02"
        ));

        Group pooGroup = new Group(
                3L,
                poo,
                "POO-01",
                teacher3,
                25,
                20,
                pooSchedules
        );

        //================ GROUP 4 ================//

        ArrayList<GroupSchedule> fisicaSchedules =
                new ArrayList<>();

        fisicaSchedules.add(new GroupSchedule(
                7L,
                4L,
                WeekDays.TUESDAY,
                LocalTime.of(7, 0),
                LocalTime.of(9, 0),
                "C-301"
        ));

        fisicaSchedules.add(new GroupSchedule(
                8L,
                4L,
                WeekDays.THURSDAY,
                LocalTime.of(7, 0),
                LocalTime.of(9, 0),
                "C-301"
        ));

        Group fisicaGroup = new Group(
                4L,
                fisicaI,
                "FIS-I-01",
                teacher1,
                40,
                37,
                fisicaSchedules
        );

        //================ SAVE GROUPS ================//

        groupRepository.save(calculoGroup);
        groupRepository.save(algoritmosGroup);
        groupRepository.save(pooGroup);
        groupRepository.save(fisicaGroup);

        System.out.println("Groups seeded successfully.");
    }
}