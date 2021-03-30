package model.users;

import java.time.LocalDate;

public class Admin extends User{
    private int privilegeDegree;

    public Admin() {
    }

    public Admin(String username) {
        super(username);
    }

    public Admin(String username, LocalDate birthday, String adress, String email, String phoneNumber) {
        super(username, birthday, adress, email, phoneNumber);
    }

    public Admin(int privilegeDegree) {
        this.privilegeDegree = privilegeDegree;
    }

    public Admin(String username, int privilegeDegree) {
        super(username);
        this.privilegeDegree = privilegeDegree;
    }

    public Admin(String username, LocalDate birthday, String adress, String email, String phoneNumber, int privilegeDegree) {
        super(username, birthday, adress, email, phoneNumber);
        this.privilegeDegree = privilegeDegree;
    }

    public int getPrivilegeDegree() {
        return privilegeDegree;
    }

    public void setPrivilegeDegree(int privilegeDegree) {
        this.privilegeDegree = privilegeDegree;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "privilegeDegree=" + privilegeDegree +
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
