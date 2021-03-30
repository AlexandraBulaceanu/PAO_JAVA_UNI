package main;

import model.courses.Course;
import model.courses.ForeignLanguageCourse;
import model.courses.ProgrammingCourse;
import model.users.Admin;
import model.users.Student;
import model.users.Teacher;
import service.PlatformService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Welcome to the E-learning platform! Choose the action most suitable for you!");

        while(true){
            System.out.println("Enter a command:");
            String commamd = scanner.nextLine();
            String [] cuv = commamd.split("\\s+");
            switch(cuv[0]){
                case "exit": System.out.println("Thank you! See you next time!");
                    System.exit(0);

                case "addCourse":   if(cuv[1].equals("foreignLanguage")) {
                                        Course c = new ForeignLanguageCourse();
                                        c.setName(cuv[2]);
                                        c.setPrice(Double.parseDouble(cuv[3]));
                                        PlatformService.getInstance().addCourse(c);
                                        System.out.println("Course Added!");
                                    } else {
                                        Course c = new ProgrammingCourse();
                                        c.setName(cuv[2]);
                                        c.setPrice(Double.parseDouble(cuv[3]));
                                        PlatformService.getInstance().addCourse(c);
                                        System.out.println("Course Added!");
                                    }
                                    break;
                case "addUser":    if(cuv[1].equals("Student")) {
                                    Student st = new Student();
                                    st.setUsername(cuv[2]);
                                    st.setUniversity(cuv[3]);
                                    PlatformService.getInstance().addUser(st);
                                    System.out.println("User Added!");
                                    } else if(cuv[1].equals("Teacher")){
                                        Teacher t = new Teacher();
                                        t.setUsername(cuv[2]);
                                        t.setYearsOfExperience(Integer.parseInt(cuv[3]));
                                        PlatformService.getInstance().addUser(t);
                                        System.out.println("User Added!");
                                    } else {
                                        Admin a = new Admin();
                                        a.setUsername(cuv[2]);
                                        PlatformService.getInstance().addUser(a);
                                        System.out.println("User Added!");
                                     }
                                    break;

                case"showCourses": PlatformService.getInstance().showAvailableCourses();
                                    break;

                case "showUsers": PlatformService.getInstance().showAllUsers();
                                    break;
                case "enrollCourse": PlatformService.getInstance().enrollInACourse(Integer.parseInt(cuv[1]),Integer.parseInt(cuv[2]));
                                     break;
                case "dropCourse": PlatformService.getInstance().dropOutOfACourse(Integer.parseInt(cuv[1]),Integer.parseInt(cuv[2]));
                                    break;
                case "deleteCourse":   PlatformService.getInstance().deleteCourse(Integer.parseInt(cuv[1]));
                                    break;
                case "deleteUser": PlatformService.getInstance().deleteUser(Integer.parseInt(cuv[1]));
                                    break;
                case "nbOfCourses": PlatformService.getInstance().nbOfCourses();
                                     break;
                case "showCoursesForStudent":  PlatformService.getInstance().showCoursesForStudent(Integer.parseInt(cuv[1]));
                                               break;
                case "showStudentsForCourse":  PlatformService.getInstance().showStudentsForCourse(Integer.parseInt(cuv[1]));
                                             break;
                case "showProgrammingCourses":  PlatformService.getInstance().showProgrammingCourses();
                                                break;
                case "showForeignLanguagesCourses": PlatformService.getInstance().showForeignLanguageCourses();
                                                break;
                case "showTeachers": PlatformService.getInstance().showTeachers();
                                        break;
                case "showStudents": PlatformService.getInstance().showStudents();
                                    break;
                case "rateTeacher": PlatformService.getInstance().rateTeacher(Integer.parseInt(cuv[1]),Double.parseDouble(cuv[2]));
                default: System.out.println("The command you entered is not valid! Try again!");
            }

        }
    }
}
