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

public class PlatformService {
    private List<Course> listCourses = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();

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
    public void addUser(User user) {
        listUsers.add(user);
    }

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

    public void showCoursesForStudent(int id) {
            if(findUser(id).isPresent()) {
                Student stud = (Student) findUser(id).get();
                stud.getCourses()
                    .stream()
                    .forEach(System.out::println);
            }
    }

    public void showStudentsForCourse(int id) {
        if(findCourse(id).isPresent()) {
            Course course = (Course) findCourse(id).get();
            course.getStudentsEnrolled()
                    .stream()
                    .forEach(System.out::println);
        }
    }

    public void rateTeacher(int id, double rate) {
        if (findUser(id).isPresent()) {
            Teacher teacher = (Teacher) findUser(id).get();
            teacher.setRating(rate);
        }
    }

    public void enrollInACourse(int idCourse, int idStudent) {
        if(findCourse(idCourse).isPresent()) {
            Course course = findCourse(idCourse).get();
            if(findUser(idStudent).isPresent()) {
                Student stud = (Student) findUser(idStudent).get();
                course.getStudentsEnrolled().add(stud);
                stud.getCourses().add(course);
            }
        }
    }

    public void dropOutOfACourse(int idCourse, int idStudent) {
        if(findCourse(idCourse).isPresent()) {
            Course course = findCourse(idCourse).get();
            if(findUser(idStudent).isPresent()) {
                Student stud = (Student) findUser(idStudent).get();
                course.getStudentsEnrolled().remove(stud);
                stud.getCourses().remove(course);
            }
        }
    }

    public Optional<Course> findCourse(int courseId) {
        for (Course course : listCourses) {
            if (course.getId() == courseId)
                return Optional.of(course);
        }
        return Optional.empty();
    }

    public Optional<User> findUser(int userId) {
        for (User user : listUsers) {
            if (user.getId() == userId)
                return Optional.of(user);
        }
        return Optional.empty();
    }

    public void deleteCourse(int id) {
        for (Course course : listCourses){
            listCourses.removeIf(it -> it.getId() == id);
        }
    }

    public void deleteUser(int id) {
        for (User user : listUsers){
            listUsers.removeIf(it -> it.getId() == id);
        }
    }

    public int nbOfCourses() {
        return listCourses.size();
    }

}
