package service;

import model.courses.ForeignLanguageCourse;
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

public class ForeignLanguageCoursesService {
    public static List<ForeignLanguageCourse> foreignLanguageCourses = new ArrayList<>();
    ReadingCSVService csvReader = ReadingCSVService.getInstance();
    WritingCSVService csvWriter = WritingCSVService.getInstance();
    PlatformService service = PlatformService.getInstance();
    Path path = Paths.get("src/main/java/csvFiles/ForeignLanguageCourses.csv");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ForeignLanguageCoursesService()
    {

    }
    private static class SingletonHolder
    {
        private static final ForeignLanguageCoursesService SINGLETON = new ForeignLanguageCoursesService();
    }

    public static ForeignLanguageCoursesService getInstance()
    {
        return ForeignLanguageCoursesService.SingletonHolder.SINGLETON;
    }

    public void writeForeignLanguageCourse(double price, LocalDate startDate, int durationWeeks, Teacher teacher,
                                           List<Student> studentsEnrolled, String foreignLanguage, boolean forCertification)  {

        foreignLanguageCourses.add(new ForeignLanguageCourse(price, startDate, durationWeeks, teacher,
                                    studentsEnrolled, foreignLanguage, forCertification));

        List<String> record = Arrays.asList(String.valueOf(foreignLanguageCourses.get(foreignLanguageCourses.size() - 1).getId()),
                                foreignLanguage, String.valueOf(price), startDate.format(formatter),
                                String.valueOf(durationWeeks), teacher.getUsername(), String.valueOf(forCertification));

        for (var student : studentsEnrolled) {
            record.add(student.getUsername());
        }

        csvWriter.writeToCSV(path, record);

        System.out.println("\nThe student was saved successfully to the csv file\n");
    }

    public void readForeignLanguageCourse() {

        List<List<String>> records = csvReader.readFromCSV(path);

        try {
            for (List<String> record : records) {
                int id = Integer.parseInt(record.get(0));
                String foreignLanguage = record.get(1);
                double price = Double.parseDouble(record.get(2));
                LocalDate startDate = LocalDate.parse(record.get(3),formatter);
                int durationWeeks= Integer.parseInt(record.get(4));
                String teacherName = record.get(5);
                boolean forCertification = Boolean.parseBoolean(record.get(6));

                List<Student> studentsEnrolled = new ArrayList<>();

                for(int i = 8; i < record.size(); i++) {
                    Student stud = (Student)service.findUserByName(record.get(i)).get();
                    studentsEnrolled.add(stud);
                }
                service = PlatformService.getInstance();
                Optional<User> user = service.findUserByName(teacherName);
                if(user.isPresent()){
                    User user2 =  user.get();
                    Teacher teacher = (Teacher)user2;
                    foreignLanguageCourses.add(new ForeignLanguageCourse(price, startDate, durationWeeks, teacher, studentsEnrolled, foreignLanguage, forCertification));
                }

                else {foreignLanguageCourses.add(new ForeignLanguageCourse(foreignLanguage,forCertification));}

            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }
}
