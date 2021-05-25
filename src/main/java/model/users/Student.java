package model.users;

import java.time.LocalDate;

public class Student extends User{
    private String university;
    private int yearOfStudy;
    private String degree;

    public Student() {

    }

    public Student(String username) {
        super(username);
    }

    public Student(String username, LocalDate birthday, String adress, String email, String phoneNumber) {
        super(username, birthday, adress, email, phoneNumber);
    }

    public Student(String university, int yearOfStudy, String degree) {
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.degree = degree;
    }

    public Student(String username, String university, int yearOfStudy, String degree) {
        super(username);
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.degree = degree;
    }

    public Student(String username, LocalDate birthday, String adress, String email, String phoneNumber, String university, int yearOfStudy, String degree) {
        super(username, birthday, adress, email, phoneNumber);
        this.university = university;
        this.yearOfStudy = yearOfStudy;
        this.degree = degree;
    }

    public Student(String username,String email,String university,int yearOfStudy) {
        super(username,email);
        this.university = university;
        this.yearOfStudy = yearOfStudy;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Student{" +
                "university='" + university + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                ", degree='" + degree + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", courses=" + courses +
                '}';
    }


}
