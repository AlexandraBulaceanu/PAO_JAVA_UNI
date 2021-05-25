package model.users;

import java.time.LocalDate;

public class Teacher extends User{
    private int yearsOfExperience;
    private double rating;

    public Teacher() {
        super();
    }

    public Teacher(String username) {
        super(username);
    }

    public Teacher(String username, LocalDate birthday, String adress, String email, String phoneNumber) {
        super(username, birthday, adress, email, phoneNumber);
    }

    public Teacher(int yearsOfExperience, double rating) {
        super();
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
    }

    public Teacher(String username, int yearsOfExperience, double rating) {
        super(username);
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
    }

    public Teacher(String username, LocalDate birthday, String adress, String email, String phoneNumber, int yearsOfExperience, double rating) {
        super(username, birthday, adress, email, phoneNumber);
        this.yearsOfExperience = yearsOfExperience;
        this.rating = rating;
    }

    public Teacher(String username,String email,double rating) {
        super(username,email);
        this.rating = rating;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "yearsOfExperience=" + yearsOfExperience +
                ", rating=" + rating +
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
