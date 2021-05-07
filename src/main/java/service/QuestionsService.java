package service;

import model.quizz.Question;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class QuestionsService {

    private QuestionsService()
    {

    }
    private static class SingletonHolder
    {
        private static final QuestionsService SINGLETON = new QuestionsService();
    }

    public static QuestionsService getInstance()
    {
        return QuestionsService.SingletonHolder.SINGLETON;
    }

    public static boolean checkAnswer(Question question, int[] studChoices) {
        int[] correctAnswers = question.getCorrectAnswers();
        if(studChoices.length != question.getCorrectAnswers().length)
            return false;
        Arrays.sort(studChoices);
        Arrays.sort(correctAnswers);
        for(int i = 0; i < correctAnswers.length; i++) {
            if(studChoices[i] != correctAnswers[i]) return false;
        } //Arrays.equals()
        return true;
    }

    public static List<Question> questions = new ArrayList<>();
    ReadingCSVService csvReader = ReadingCSVService.getInstance();
    WritingCSVService csvWriter = WritingCSVService.getInstance();
    Path path = Paths.get("../csvFiles/questions.csv");


    public void writeQuestion(String questionText, String[] choices, int[] correctAnswers)  {
        questions.add(new Question(questionText, choices, correctAnswers));

        List<String> record = new ArrayList<>();
        String answers = "";
        for (var choice : choices) {
            answers += choice + "; ";
        }
        record.add(answers);
        String correctAns = "";
        for (var correct : correctAnswers) {
            correctAns += correct + "; ";
        }
        record.add(correctAns);

        csvWriter.writeToCSV(path, record);

        System.out.println("\nThe question was written to the csv file\n");
    }

    public void readQuestion() {

        List<List<String>> records = csvReader.readFromCSV(path);
        for (List<String> record: records) {
            String questionText = record.get(0);
            String[] choices = record.get(1).split("; ");
            String[] answers = record.get(2).split("; ");
            int[] correctAns = new int[answers.length];
            for(int i = 0; i < answers.length; i++) {
                correctAns[i] = Integer.parseInt(answers[i]);
            }

            questions.add(new Question(questionText, choices, correctAns));

        }
    }


}
