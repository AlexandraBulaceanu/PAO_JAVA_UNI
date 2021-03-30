package model.courses;

import model.quizz.Quizz;
import model.users.Student;
import model.users.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Course {

        protected int id;
        protected String name;
        protected double price;
        protected LocalDate startDate;
        protected int durationWeeks;
        protected Teacher teacherName;
        protected List<Student> studentsEnrolled;
        protected String description;
        protected static int nbOfCourses;
        protected Quizz quiz;

    public Course() {
        this.id = ++nbOfCourses;
    }

    public Course(String name, double price, LocalDate startDate, int durationWeeks, Teacher teacherName, List<Student> studentsEnrolled, String description) {
        this.id = ++nbOfCourses;
        this.name = name;
        this.price = price;
        this.startDate = startDate;
        this.durationWeeks = durationWeeks;
        this.teacherName = teacherName;
        this.description = description;
        this.studentsEnrolled = new ArrayList<Student>();
        for(int i = 0; i < studentsEnrolled.size(); i++) {
            this.studentsEnrolled.add(studentsEnrolled.get(i));
        }
    }

    public Course(String name, double price, String description) {
        this.id = ++nbOfCourses;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDurationWeeks() {
        return durationWeeks;
    }

    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }

    public Teacher getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(Teacher teacherName) {
        this.teacherName = teacherName;
    }

    public List<Student> getStudentsEnrolled() { //TODO: change to Optional
        List<Student> copyStud = new ArrayList<>();
        for (Student stud : this.studentsEnrolled) {
            try {
                copyStud.add((Student) stud.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copyStud;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        for (Student stud : studentsEnrolled) {
            try {
                this.studentsEnrolled.add((Student) stud.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
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
        Course course = (Course) o;
        return id == course.id &&
                Double.compare(course.price, price) == 0 &&
                durationWeeks == course.durationWeeks &&
                name.equals(course.name) &&
                Objects.equals(startDate, course.startDate) &&
                teacherName.equals(course.teacherName) &&
                Objects.equals(studentsEnrolled, course.studentsEnrolled) &&
                Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, startDate, durationWeeks, teacherName, studentsEnrolled, description);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
