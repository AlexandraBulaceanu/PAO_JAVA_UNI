package model.courses;

import model.users.Student;
import model.users.Teacher;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ForeignLanguageCourse extends Course{
    private String foreignLanguage;
    private boolean forCertification;

    public ForeignLanguageCourse(String foreignLanguage, boolean forCertification) {
        this.foreignLanguage = foreignLanguage;
        this.forCertification = forCertification;
    }

    public ForeignLanguageCourse( String foreignLanguage, double price, LocalDate startDate, int durationWeeks, Teacher teacherName, List<Student> studentsEnrolled, String description, boolean forCertification) {
        super(price, startDate, durationWeeks, teacherName, studentsEnrolled);
        this.foreignLanguage = foreignLanguage;
        this.forCertification = forCertification;
    }

    public ForeignLanguageCourse(String name, double price, String description, String foreignLanguage, boolean forCertification) {
        super(name, price, description);
        this.foreignLanguage = foreignLanguage;
        this.forCertification = forCertification;
    }



    public ForeignLanguageCourse() {

    }

    public ForeignLanguageCourse(double price, LocalDate startDate, int durationWeeks, Teacher teacherName, List<Student> studentsEnrolled, String foreignLanguage, boolean forCertification) {
        super(price, startDate, durationWeeks, teacherName, studentsEnrolled);
        this.foreignLanguage = foreignLanguage;
        this.forCertification = forCertification;

    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public boolean isForCertification() {
        return forCertification;
    }

    public void setForCertification(boolean forCertification) {
        this.forCertification = forCertification;
    }

    @Override
    public String toString() {
        return "ForeignLanguageCourse{" +
                "foreignLanguage='" + foreignLanguage + '\'' +
                ", forCertification=" + forCertification +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ForeignLanguageCourse that = (ForeignLanguageCourse) o;
        return forCertification == that.forCertification &&
                foreignLanguage.equals(that.foreignLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), foreignLanguage, forCertification);
    }
}
