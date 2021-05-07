package model.users;

import model.courses.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class User {
    protected UUID id;
    protected String username;
    protected LocalDate birthday;
    protected String adress;
    protected String email;
    protected String phoneNumber;
    protected List<Course> courses;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String username) {
        this.id = UUID.randomUUID();
        this.username = username;
    }

    public User(String username, LocalDate birthday, String adress, String email, String phoneNumber) {
        this.username = username;
        this.birthday = birthday;
        this.adress = adress;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public UUID getId() {
        return id;
    }

   /* public void setId(int id) {
        this.id = id;
    }
    */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /*public static  getNbOfUsers() {
        return nbOfUsers;
    }

    public static void setNbOfUsers(int nbOfUsers) {
        User.nbOfUsers = nbOfUsers;
    }*/


    public List<Course> getCourses() { //TODO: change to Optional
        List<Course> copy = new ArrayList<>();
        for (Course course : this.courses) {
            try {
                copy.add((Course) course.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copy;
    }

    public void setCourses(List<Course> courses) {
        for (Course course : courses) {
            try {
                this.courses.add((Course) course.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(adress, user.adress) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(courses, user.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, birthday, adress, email, phoneNumber, courses);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
