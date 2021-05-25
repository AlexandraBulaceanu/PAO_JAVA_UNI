package main;

import audit.AuditService;
import model.courses.Course;
import model.courses.ForeignLanguageCourse;
import model.courses.ProgrammingCourse;
import model.users.Admin;
import model.users.Student;
import model.users.Teacher;
import service.PlatformService;
import service.StudentsService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        PlatformService service = PlatformService.getInstance();
        AuditService auditService = AuditService.getInstance();
        StudentsService studentsService = StudentsService.getInstance();

        //on load - > read data from csvs
        service.ReadDataFromCSVsOnLoad();

        System.out.println("Welcome to the E-learning platform! Choose the action most suitable for you!");

        while(true){
            System.out.println("Enter a command:");
            String commamd = scanner.nextLine();
            String [] cuv = commamd.split("\\s+");
            switch(cuv[0]){
                case "exit": System.out.println("Thank you! See you next time!");
                                auditService.logActions("exit");
                                System.exit(0);

                case "addCourse":   if(cuv[1].equals("foreignLanguage")) {
                                        ForeignLanguageCourse c = new ForeignLanguageCourse();
                                        c.setName(cuv[2]);
                                        c.setForeignLanguage(cuv[3]);
                                        c.setStudentsEnrolled(new ArrayList<>());
                                        c.setPrice(Double.parseDouble(cuv[4]));
                                        PlatformService.getInstance().addCourse(c);
                                        System.out.println("Course Added!");
                                    } else {
                                        ProgrammingCourse c = new ProgrammingCourse();
                                        c.setName(cuv[2]);
                                        c.setProgrammingLanguage(cuv[3]);
                                        c.setStudentsEnrolled(new ArrayList<>());
                                        c.setPrice(Double.parseDouble(cuv[4]));
                                        PlatformService.getInstance().addCourse(c);
                                        System.out.println("Course Added!");
                                    }
                                    auditService.logActions("addCourse");
                                    break;
                case "addUser":    if(cuv[1].equals("Student")) {
                                    Student st = new Student();
                                    st.setUsername(cuv[2]);
                                    st.setUniversity(cuv[3]);
                                    st.setEmail(cuv[4]);
                                    st.setYearOfStudy(Integer.parseInt(cuv[5]));
                                    PlatformService.getInstance().addUser(st);
                                    studentsService.writeStudent(st.getUsername(),service.generateBirthday(),st.getAdress(),st.getEmail(),st.getPhoneNumber(),st.getUniversity(),st.getYearOfStudy(),st.getDegree());
                                    System.out.println("User Added!");
                                    } else if(cuv[1].equals("Teacher")){
                                        Teacher t = new Teacher();
                                        t.setUsername(cuv[2]);
                                        t.setEmail(cuv[3]);
                                        t.setYearsOfExperience(Integer.parseInt(cuv[4]));
                                        t.setRating(Double.parseDouble(cuv[5]));
                                        PlatformService.getInstance().addUser(t);
                                        System.out.println("User Added!");
                                    } else {
                                        Admin a = new Admin();
                                        a.setUsername(cuv[2]);
                                        PlatformService.getInstance().addUser(a);
                                        System.out.println("User Added!");
                                     }
                                     auditService.logActions("addUser");
                                    break;

                case "showCourses": PlatformService.getInstance().showAvailableCourses();
                                    auditService.logActions("showCourses");
                                    break;

                case "showUsers": PlatformService.getInstance().showAllUsers();
                                        auditService.logActions("showUsers");
                                    break;
                case "enrollCourse": PlatformService.getInstance().enrollInACourse(UUID.fromString(cuv[1]),UUID.fromString(cuv[1]));
                                        auditService.logActions("enrollCourse");
                                     break;
                case "dropCourse": PlatformService.getInstance().dropOutOfACourse(UUID.fromString(cuv[1]),UUID.fromString(cuv[1]));
                                        auditService.logActions("dropCourse");
                                    break;
                case "deleteCourse":   PlatformService.getInstance().deleteCourse(UUID.fromString(cuv[1]));
                                    auditService.logActions("deleteCourse");
                                    break;
                case "deleteUser": PlatformService.getInstance().deleteUser(UUID.fromString(cuv[1]));
                                        auditService.logActions("deleteUser");
                                    break;
                case "nbOfCourses": PlatformService.getInstance().nbOfCourses();
                                        auditService.logActions("nbOfCourses");
                                     break;
                case "showCoursesForStudent":  PlatformService.getInstance().showCoursesForStudent(UUID.fromString(cuv[1]));
                                                auditService.logActions("showCoursesForStudent");
                                               break;
                case "showStudentsForCourse":  PlatformService.getInstance().showStudentsForCourse(UUID.fromString(cuv[1]));
                                                auditService.logActions("showStudentsForCourse");
                                             break;
                case "showProgrammingCourses":  PlatformService.getInstance().showProgrammingCourses();
                                                auditService.logActions("showProgrammingCourses");
                                                break;
                case "showForeignLanguagesCourses": PlatformService.getInstance().showForeignLanguageCourses();
                                                    auditService.logActions("showForeignLanguagesCourses");
                                                break;
                case "showTeachers": PlatformService.getInstance().showTeachers();
                                         auditService.logActions("showTeachers");
                                        break;
                case "showStudents": PlatformService.getInstance().showStudents();
                                        auditService.logActions("showStudents");
                                    break;
                case "rateTeacher": PlatformService.getInstance().rateTeacher(UUID.fromString(cuv[1]),Double.parseDouble(cuv[2]));
                                    auditService.logActions("rateTeacher");
                default: System.out.println("The command you entered is not valid! Try again!");
            }

        }
    }
}
