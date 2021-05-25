package audit;

import service.WritingCSVService;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class AuditService {

    private BufferedWriter csvWriter;

    private AuditService() {

        Path p = Paths.get("src/main/java/csvFiles/audit.csv");
        System.out.println(p);
        try {
            csvWriter = new BufferedWriter(Files.newBufferedWriter(p, CREATE, APPEND));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class SingletonHolder
    {
        private static final AuditService SINGLETON = new AuditService();
    }

    public static AuditService getInstance()
    {
        return AuditService.SingletonHolder.SINGLETON;
    }

    public void logActions(String actionName) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        String actionTimestamp = formatter.format(timestamp);

        try {
            csvWriter.write(actionName + ", " + actionTimestamp + '\n');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeStream() {
        try {
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



