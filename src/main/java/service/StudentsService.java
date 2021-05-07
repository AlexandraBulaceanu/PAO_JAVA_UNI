package service;

import model.users.Student;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentsService {
    public static List<Student> students = new ArrayList<>();
    ReadingCSVService csvReader = ReadingCSVService.getInstance();
    WritingCSVService csvWriter = WritingCSVService.getInstance();
    Path path = Paths.get("../csvFiles/students.csv");

    private StudentsService()
    {

    }
    private static class SingletonHolder
    {
        private static final StudentsService SINGLETON = new StudentsService();
    }

    public static StudentsService getInstance()
    {
        return StudentsService.SingletonHolder.SINGLETON;
    }

    public void writeStudent(String username, LocalDate birthday, String address, String email, String phoneNumber, String university, int yearOfStudy, String degree)  {

        students.add(new Student(username, birthday, address, email, phoneNumber, university, yearOfStudy, degree));

        List<String> record = Arrays.asList(String.valueOf(students.get(students.size() - 1).getId()),
                username, new SimpleDateFormat("dd/MM/yyyy").format(birthday),
                address, email, phoneNumber, university, String.valueOf(yearOfStudy), degree);

        csvWriter.writeToCSV(path, record);

        System.out.println("\nThe student was saved successfully to the csv file\n");
    }

    public void readStudent() {

        List<List<String>> records = csvReader.readFromCSV(path);

        try {
            for (List<String> record : records) {
                int id = Integer.parseInt(record.get(0));
                String username = record.get(1);
                LocalDate birthday = LocalDate.parse(record.get(2));
                String address = record.get(3);
                String email = record.get(4);
                String phoneNumber = record.get(5);
                String university = record.get(6);
                int yearOfStudy = Integer.parseInt(record.get(7));
                String degree = record.get(8);

                students.add(new Student(username, birthday, address, email, phoneNumber, university, yearOfStudy, degree));

            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }
}
