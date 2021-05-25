package dao;

import model.courses.ForeignLanguageCourse;
import model.users.Student;
import service.DBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageCourseDAO {
    private Connection con;
    private Connection conDB = DBService.getInstance().getCon();

    public LanguageCourseDAO(Connection con) {
        this.con = con;
    }

    public void insertForeignLanguageCourse(ForeignLanguageCourse foreignLanguageCourse){
        String query = "INSERT INTO language_courses VALUES(NULL,?,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1,foreignLanguageCourse.getForeignLanguage());
            stmt.setString(2,listToString(foreignLanguageCourse.getStudentsEnrolled()));
            stmt.setDouble(3,foreignLanguageCourse.getPrice());
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Something went wrong while saving");
        }

    }

    public List<ForeignLanguageCourse> getAllForeignLanguageCourses(){
        String query = "SELECT * FROM language_courses";
        List<ForeignLanguageCourse> foreignLanguageCourses;
        try(PreparedStatement stmt = con.prepareStatement(query)){
            foreignLanguageCourses = new ArrayList<>();
            ResultSet resultset =  stmt.executeQuery();

            while (resultset.next()){

                String foreignLanguage = resultset.getString("foreignLanguage");
                String studentNames = resultset.getString("studentsEnrolled");
                double price = resultset.getInt("price");
                List<Student> studentsEnrolled = stringToList(studentNames);
                ForeignLanguageCourse foreignLanguageCourse = new ForeignLanguageCourse(foreignLanguage,studentsEnrolled,price);

                foreignLanguageCourses.add(foreignLanguageCourse);
            }
            if(foreignLanguageCourses != null) {
                return foreignLanguageCourses;
            }
            return Collections.emptyList();
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while loading");
        }

    }

    public void deleteForeignLanguageCourses(String foreignLanguage){
        String query = "DELETE FROM language_courses where foreignLanguage = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, foreignLanguage);
            stmt.executeUpdate();
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public void updateForeignLanguageCourse(ForeignLanguageCourse ForeignLanguageCourse){
        String query = "UPDATE language_courses set price = ? where foreignLanguage = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setDouble(1, ForeignLanguageCourse.getPrice());
            preparedStatement.setString(2, ForeignLanguageCourse.getForeignLanguage());

            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while updating");
        }
    }


    public String listToString(List<Student> students){
        String stud = students.stream()
                .map(el -> el.getUsername())
                .collect(Collectors.joining(", "));

        return stud;
    }

    public List<Student> stringToList(String studentsNames){
        List<Student> students = new ArrayList<>();
        StudentDAO dao = new StudentDAO(conDB);
        String[] stud = studentsNames.split(", ");
        for(int i = 0; i < stud.length; i++) {
            if(dao.findStudent(stud[i]).isPresent()) {
                Student st = dao.findStudent(stud[i]).get();
                students.add(st);
            }
        }
        return students;
    }

}
