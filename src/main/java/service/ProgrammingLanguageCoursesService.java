package service;

import model.courses.ProgrammingCourse;
import model.users.Student;
import model.users.Teacher;
import model.users.User;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProgrammingLanguageCoursesService {
    public static List<ProgrammingCourse> programmingCourses = new ArrayList<>();
    ReadingCSVService csvReader = ReadingCSVService.getInstance();
    WritingCSVService csvWriter = WritingCSVService.getInstance();
    PlatformService service = PlatformService.getInstance();
    Path path = Paths.get("src/main/java/csvFiles/programmingCourses.csv");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private ProgrammingLanguageCoursesService()
    {

    }
    private static class SingletonHolder
    {
        private static final ProgrammingLanguageCoursesService SINGLETON = new ProgrammingLanguageCoursesService();
    }

    public static ProgrammingLanguageCoursesService getInstance()
    {
        return ProgrammingLanguageCoursesService.SingletonHolder.SINGLETON;
    }

    public void writeProgrammingCourse(String programmingLanguage, double price, LocalDate startDate, int durationWeeks, Teacher teacher, List<Student> studentsEnrolled,
                                       double languageVersion, String ide, int nbOfPracticalLabs)  {

        programmingCourses.add(new ProgrammingCourse(programmingLanguage, price, startDate, durationWeeks, teacher, studentsEnrolled,
                languageVersion, ide, nbOfPracticalLabs));

        List<String> record = Arrays.asList(String.valueOf(programmingCourses.get(programmingCourses.size() - 1).getId()),
                programmingLanguage, String.valueOf(price), startDate.format(formatter),
                String.valueOf(durationWeeks), teacher.getUsername(), String.valueOf(languageVersion), ide, String.valueOf(nbOfPracticalLabs));

        for (var student : studentsEnrolled) {
            record.add(student.getUsername());
        }

        csvWriter.writeToCSV(path, record);

        System.out.println("\nThe student was saved successfully to the csv file\n");
    }

    public void readProgrammingCourse() {

        List<List<String>> records = csvReader.readFromCSV(path);

        try {
            for (List<String> record : records) {
                int id = Integer.parseInt(record.get(0));
                String programmingLanguage = record.get(1);
                double price = Double.parseDouble(record.get(2));
                LocalDate startDate = LocalDate.parse(record.get(3),formatter);
                int durationWeeks= Integer.parseInt(record.get(4));
                String teacherName = record.get(5);
                double languageVersion = Double.parseDouble(record.get(6));
                String ide = record.get(7);
                int nbOfPracticalLabs = Integer.parseInt(record.get(8));

                List<Student> studentsEnrolled = new ArrayList<>();

                service = PlatformService.getInstance();
                for(int i = 8; i < record.size(); i++) {
                    if(service.findUserByName(record.get(i)).isPresent()) {
                        Student stud = (Student) service.findUserByName(record.get(i)).get();
                        studentsEnrolled.add(stud);
                    }
                }

                Optional<User> user = service.findUserByName(teacherName);
                if(user.isPresent()) {
                    User user2 =  user.get();
                    Teacher teacher = (Teacher)user2;
                    programmingCourses.add(new ProgrammingCourse(programmingLanguage, price, startDate, durationWeeks, teacher, studentsEnrolled, languageVersion, ide, nbOfPracticalLabs));
                }
                else {
                    programmingCourses.add(new ProgrammingCourse(programmingLanguage,languageVersion,ide,nbOfPracticalLabs));
                }



            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }
}
