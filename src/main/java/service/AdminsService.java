package service;

import model.users.Admin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminsService {
    public static List<Admin> admins = new ArrayList<>();
    ReadingCSVService csvReader = ReadingCSVService.getInstance();
    WritingCSVService csvWriter = WritingCSVService.getInstance();
    Path path = Paths.get("src/main/java/csvFiles/admins.csv");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private AdminsService()
    {

    }
    private static class SingletonHolder
    {
        private static final AdminsService SINGLETON = new AdminsService();
    }

    public static AdminsService getInstance()
    {
        return AdminsService.SingletonHolder.SINGLETON;
    }

    public void writeAdmin(String username, LocalDate birthday, String address, String email, String phoneNumber, int privilegeDegree)  {

        admins.add(new Admin(username, birthday, address, email, phoneNumber, privilegeDegree));

        List<String> record = Arrays.asList(String.valueOf(admins.get(admins.size() - 1).getId()),
                username, birthday.format(formatter),
                address, email, phoneNumber, String.valueOf(privilegeDegree));

        csvWriter.writeToCSV(path, record);

        System.out.println("\nThe admin was saved successfully to the csv file\n");
    }

    public void readAdmin() {

        List<List<String>> records = csvReader.readFromCSV(path);

        try {
            for (List<String> record : records) {
                int id = Integer.parseInt(record.get(0));
                String username = record.get(1);
                LocalDate birthday = LocalDate.parse(record.get(2),formatter);
                String address = record.get(3);
                String email = record.get(4);
                String phoneNumber = record.get(5);
                int privilegeDegree= Integer.parseInt(record.get(6));

                admins.add(new Admin(username, birthday, address, email, phoneNumber, privilegeDegree));

            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }
}
