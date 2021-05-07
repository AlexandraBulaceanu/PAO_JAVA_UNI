package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * I use an approach that will allow me to use this reading service no matter the model, without changing it, thus if I add a new model I won't have to change this one too
 * It makes my app more mentenable
 */

public class ReadingCSVService {

    private ReadingCSVService()
    {

    }
    private static class SingletonHolder
    {
        private static final ReadingCSVService SINGLETON = new ReadingCSVService();
    }

    public static ReadingCSVService getInstance()
    {
        return ReadingCSVService.SingletonHolder.SINGLETON;
    }

    public List<List<String>>  readFromCSV(Path csvPath) {

        List<List<String>> records = new ArrayList<>(); // I can visualize it as a matrix, so I am not dependent on the no of attributes a class has

        try( BufferedReader reader = new BufferedReader(Files.newBufferedReader(csvPath))) {
            String currentRecord = null;
            while ((currentRecord = reader.readLine()) != null) {
                List<String> csvRow = List.of(currentRecord.split(", "));
                records.add(csvRow);
            }
            return records;
        }catch (IOException e) {
           e.printStackTrace();
        }
        return Collections.emptyList(); //in order not to return null is the csv at that path is empty
    }

}