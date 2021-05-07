package service;

import model.courses.Course;
import model.courses.ForeignLanguageCourse;
import model.courses.ProgrammingCourse;
import model.users.Student;
import model.users.Teacher;
import model.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlatformService {
    private List<Course> listCourses = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();

    ProgrammingLanguageCoursesService programmingLanguageCoursesService = ProgrammingLanguageCoursesService.getInstance();
    ForeignLanguageCoursesService foreignLanguageCoursesService = ForeignLanguageCoursesService.getInstance();
    AdminsService adminsService = AdminsService.getInstance();
    StudentsService studentsService = StudentsService.getInstance();
    TeachersService teachersService = TeachersService.getInstance();
    QuestionsService questionsService = QuestionsService.getInstance();

    private PlatformService(){}

    private static class SingletonHolder
    {
        private static final PlatformService INSTANCE = new PlatformService();
    }

    public static PlatformService getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public void addCourse(Course course) {
        listCourses.add(course);
    }
    public void addUser(User user) { }

    public void showAvailableCourses() {
        listCourses.forEach(System.out::println);
    }

    public void showAllUsers() {
        listUsers.forEach(System.out::println);
    }

    public void showProgrammingCourses() {
        listCourses.stream()
                 .filter(c -> c instanceof ProgrammingCourse)
                 .forEach(System.out::println);
    }
    public void showForeignLanguageCourses() {
        listCourses.stream()
                .filter(c -> c instanceof ForeignLanguageCourse)
                .forEach(System.out::println);
    }

    public void showTeachers() {
        listUsers.stream()
                .filter(t -> t instanceof Teacher)
                .forEach(System.out::println);
    }
    public void showStudents() {
        listUsers.stream()
                .filter(s -> s instanceof Student)
                .forEach(System.out::println);
    }

    public void showCoursesForStudent(UUID id) {
            if(findUserById(id).isPresent()) {
                Student stud = (Student) findUserById(id).get();
                stud.getCourses()
                    .stream()
                    .forEach(System.out::println);
            }
    }

    public void showStudentsForCourse(UUID id) {
        if(findCourse(id).isPresent()) {
            Course course = (Course) findCourse(id).get();
            course.getStudentsEnrolled()
                    .stream()
                    .forEach(System.out::println);
        }
    }

    public void rateTeacher(UUID id, double rate) {
        if (findUserById(id).isPresent()) {
            Teacher teacher = (Teacher) findUserById(id).get();
            teacher.setRating(rate);
        }
    }

    public void enrollInACourse(UUID idCourse, UUID idStudent) {
        if(findCourse(idCourse).isPresent()) {
            Course course = findCourse(idCourse).get();
            if(findUserById(idStudent).isPresent()) {
                Student stud = (Student) findUserById(idStudent).get();
                course.getStudentsEnrolled().add(stud);
                stud.getCourses().add(course);
            }
        }
    }

    public void dropOutOfACourse(UUID idCourse, UUID idStudent) {
        if(findCourse(idCourse).isPresent()) {
            Course course = findCourse(idCourse).get();
            if(findUserById(idStudent).isPresent()) {
                Student stud = (Student) findUserById(idStudent).get();
                course.getStudentsEnrolled().remove(stud);
                stud.getCourses().remove(course);
            }
        }
    }

    public Optional<Course> findCourse(UUID courseId) {
        for (Course course : listCourses) {
            if (course.getId() == courseId)
                return Optional.of(course);
        }
        return Optional.empty();
    }

    public Optional<User> findUserById(UUID userId) {
        for (User user : listUsers) {
            if (user.getId() == userId)
                return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> findUserByName(String name) {
        for (User user : listUsers) {
            if (user.getUsername() == name)
                return Optional.of(user);
        }
        return Optional.empty();
    }

    public void deleteCourse(UUID id) {
        for (Course course : listCourses){
            listCourses.removeIf(it -> it.getId() == id);
        }
    }

    public void deleteUser(UUID id) {
        for (User user : listUsers){
            listUsers.removeIf(it -> it.getId() == id);
        }
    }

    public int nbOfCourses() {
        return listCourses.size();
    }

    public void ReadDataFromCSVsOnLoad() {
            foreignLanguageCoursesService.readForeignLanguageCourse();
            adminsService.readAdmin();
            programmingLanguageCoursesService.readProgrammingCourse();
            questionsService.readQuestion();
            studentsService.readStudent();
            teachersService.readTeacher();
    }


    //NU AM AVUT TIMP SA RAFINEZ CODUL SI SA FAC TOT CE MI-AM PROPUS:(
    //TO DO: REFARCTOR CODE
    //TO DO: GENERATOR METHODS

}
