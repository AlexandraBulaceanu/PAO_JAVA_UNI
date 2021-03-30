package model.quizz;

import java.util.Arrays;
import java.util.Objects;

public class Question {
    private String questionText;
    private String[] choices;
    private int[] correctAnswers;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public boolean checkAnswer(int[] studChoices) {
        if(studChoices.length != correctAnswers.length)
            return false;
        Arrays.sort(studChoices);
        Arrays.sort(correctAnswers);
        for(int i = 0; i < this.correctAnswers.length; i++) {
            if(studChoices[i] != correctAnswers[i]) return false;
        } //Arrays.equals()
        return true;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getChoices() {
        String[] copy = new String[this.choices.length];
        System.arraycopy(this.choices, 0, copy, 0, copy.length);
        return copy;
    }

    public void setChoices(String[] choices) {
        this.choices = new String[choices.length];
        System.arraycopy(choices, 0, this.choices, 0, choices.length);
    }

    public int[] getCorrectAnswers() {
        int[] copy = new int[this.correctAnswers.length];
        System.arraycopy(this.correctAnswers, 0, copy, 0, copy.length);
        return copy;
    }

    public void setCorrectAnswers(int[] correctAnswers) {
        this.correctAnswers = new int[correctAnswers.length];
        System.arraycopy(correctAnswers, 0, this.correctAnswers, 0, correctAnswers.length);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", choices=" + Arrays.toString(choices) +
                ", correctAnswers=" + Arrays.toString(correctAnswers) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionText.equals(question.questionText) &&
                Arrays.equals(choices, question.choices) &&
                Arrays.equals(correctAnswers, question.correctAnswers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(questionText);
        result = 31 * result + Arrays.hashCode(choices);
        result = 31 * result + Arrays.hashCode(correctAnswers);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
