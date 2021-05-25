package service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class WritingCSVService {

    private WritingCSVService()
    {

    }
    private static class SingletonHolder
    {
        private static final WritingCSVService SINGLETON = new WritingCSVService();
    }

    public static WritingCSVService getInstance()
    {
        return SingletonHolder.SINGLETON;
    }

    public void writeToCSV(Path csvPath, List<String> record) {

        try(BufferedWriter writer = new BufferedWriter((Files.newBufferedWriter(csvPath,CREATE,APPEND)))){
            for(int i = 0; i < record.size(); i++) {
                if(i!=record.size() - 1) {
                    writer.append(record.get(i));//the delimiter is a ',' as it is a csv
                    writer.append(", ");
                }
                else writer.append(record.get(i) + "\n");
            }
            writer.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
