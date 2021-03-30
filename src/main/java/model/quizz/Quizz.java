package model.quizz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quizz {
    private List<Question> questionsList;
    private String name;
    private double timeToComplete;
    private int passValue;
    private boolean passed;
    private int score;

    public Quizz(List<Question> questionsList, String name, double timeToComplete, int passValue) {
        this.questionsList = questionsList;
        this.name = name;
        this.timeToComplete = timeToComplete;
        this.passValue = passValue;
    }

    public Quizz() {
    }

    public boolean isPassed() {
        return true; //TODO
    }

    public int calculateScore() {

        return 1; //TODO
    }

    public List<Question> getQuestionsList() { //TODO: change to Optional
        List<Question> copy= new ArrayList<>();
        for (Question ques : this.questionsList) {
            try {
                copy.add((Question) ques.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copy;
    }

    public void setQuestionsList(List<Question> questionsList) {
        for (Question ques : questionsList) {
            try {
                this.questionsList.add((Question) ques.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(double timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public int getPassValue() {
        return passValue;
    }

    public void setPassValue(int passValue) {
        this.passValue = passValue;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quizz quizz = (Quizz) o;
        return Double.compare(quizz.timeToComplete, timeToComplete) == 0 &&
                passValue == quizz.passValue &&
                passed == quizz.passed &&
                score == quizz.score &&
                Objects.equals(questionsList, quizz.questionsList) &&
                Objects.equals(name, quizz.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionsList, name, timeToComplete, passValue, passed, score);
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "questionsList=" + questionsList +
                ", name='" + name + '\'' +
                ", timeToComplete=" + timeToComplete +
                ", passValue=" + passValue +
                ", passed=" + passed +
                ", score=" + score +
                '}';
    }

}
