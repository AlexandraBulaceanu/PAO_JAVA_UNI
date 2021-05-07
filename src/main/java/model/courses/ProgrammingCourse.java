package model.courses;

import model.users.Student;
import model.users.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProgrammingCourse extends Course{
    private String programmingLanguage;
    private double languageVersion;
    private String ide;
    private int nbOfPracticalLabs;

    //TO DO: CREATE FACTORY FOR MULTIPLE CONSTRUCTOR PARAMETERS

    public ProgrammingCourse(String programmingLanguage, double languageVersion, String ide, int nbOfPracticalLabs) {
        this.programmingLanguage = programmingLanguage;
        this.languageVersion = languageVersion;
        this.ide = ide;
        this.nbOfPracticalLabs = nbOfPracticalLabs;
    }

    public ProgrammingCourse(String name, double price, LocalDate startDate, int durationWeeks, Teacher teacherName, List<Student> studentsEnrolled, String description, String programmingLanguage, double languageVersion, String ide, int nbOfPracticalLabs) {
        super(name, price, startDate, durationWeeks, teacherName, studentsEnrolled, description);
        this.programmingLanguage = programmingLanguage;
        this.languageVersion = languageVersion;
        this.ide = ide;
        this.nbOfPracticalLabs = nbOfPracticalLabs;
    }

    public ProgrammingCourse(String programmingLanguage, double price, LocalDate startDate, int durationWeeks, Teacher teacherName, List<Student> studentsEnrolled, double languageVersion, String ide, int nbOfPracticalLabs) {
        super(price, startDate, durationWeeks, teacherName, studentsEnrolled);
        this.programmingLanguage = programmingLanguage;
        this.languageVersion = languageVersion;
        this.ide = ide;
        this.nbOfPracticalLabs = nbOfPracticalLabs;
    }

    public ProgrammingCourse(String name, double price, String description, String programmingLanguage, double languageVersion, String ide, int nbOfPracticalLabs) {
        super(name, price, description);
        this.programmingLanguage = programmingLanguage;
        this.languageVersion = languageVersion;
        this.ide = ide;
        this.nbOfPracticalLabs = nbOfPracticalLabs;
    }

    public ProgrammingCourse() {
    }

    @Override
    public String toString() {
        return "ProgrammingCourse{" +
                "programmingLanguage='" + programmingLanguage + '\'' +
                ", languageVersion=" + languageVersion +
                ", ide='" + ide + '\'' +
                ", nbOfPracticalLabs=" + nbOfPracticalLabs +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", startDate=" + startDate +
                ", durationWeeks=" + durationWeeks +
                ", teacherName=" + teacherName +
                ", studentsEnrolled=" + studentsEnrolled +
                ", description='" + description + '\'' +
                '}';
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public double getLanguageVersion() {
        return languageVersion;
    }

    public void setLanguageVersion(double languageVersion) {
        this.languageVersion = languageVersion;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public int getNbOfPracticalLabs() {
        return nbOfPracticalLabs;
    }

    public void setNbOfPracticalLabs(int nbOfPracticalLabs) {
        this.nbOfPracticalLabs = nbOfPracticalLabs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProgrammingCourse that = (ProgrammingCourse) o;
        return Double.compare(that.languageVersion, languageVersion) == 0 &&
                nbOfPracticalLabs == that.nbOfPracticalLabs &&
                programmingLanguage.equals(that.programmingLanguage) &&
                Objects.equals(ide, that.ide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), programmingLanguage, languageVersion, ide, nbOfPracticalLabs);
    }
}
