package service;

import model.users.Teacher;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeachersService {
    public static List<Teacher> teachers = new ArrayList<>();
    ReadingCSVService csvReader = ReadingCSVService.getInstance();
    WritingCSVService csvWriter = WritingCSVService.getInstance();
    Path path = Paths.get("src/main/java/csvFiles/teachers.csv");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private TeachersService()
    {

    }
    private static class SingletonHolder
    {
        private static final TeachersService SINGLETON = new TeachersService();
    }

    public static TeachersService getInstance()
    {
        return TeachersService.SingletonHolder.SINGLETON;
    }

    public void writeTeacher(String username, LocalDate birthday, String address, String email, String phoneNumber, int yearsOfExperience, double rating)  {

        teachers.add(new Teacher(username, birthday, address, email, phoneNumber, yearsOfExperience, rating));

        List<String> record = Arrays.asList(String.valueOf(teachers.get(teachers.size() - 1).getId()),
                                            username, birthday.format(formatter),
                                            address, email, phoneNumber, String.valueOf(yearsOfExperience), String.valueOf(rating));

        csvWriter.writeToCSV(path, record);

        System.out.println("\nThe student was saved successfully to the csv file\n");
    }

    public void readTeacher() {

        List<List<String>> records = csvReader.readFromCSV(path);

        try {
            for (List<String> record : records) {
                int id = Integer.parseInt(record.get(0));
                String username = record.get(1);
                LocalDate birthday = LocalDate.parse(record.get(2),formatter);
                String address = record.get(3);
                String email = record.get(4);
                String phoneNumber = record.get(5);
                int yearsOfExperience= Integer.parseInt(record.get(6));
                double rating = Double.parseDouble(record.get(7));

                teachers.add(new Teacher(username, birthday, address, email, phoneNumber, yearsOfExperience, rating));

            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }
}
