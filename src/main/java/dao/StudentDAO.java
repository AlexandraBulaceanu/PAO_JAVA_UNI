package dao;

import model.users.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentDAO {
    private Connection con;

    public StudentDAO(Connection con) {
        this.con = con;
    }

    public void insertStudent(Student student){
            String query = "INSERT INTO students VALUES(NULL,?,?,?,?)";
            try(PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1,student.getUsername());
                stmt.setString(2,student.getEmail());
                stmt.setString(3,student.getUniversity());
                stmt.setInt(4,student.getYearOfStudy());
                stmt.executeUpdate();

            }catch(SQLException e){
                throw new RuntimeException("Something went wrong while saving");
            }

    }

    public List<Student> getAllStudents(){
        String query = "SELECT * FROM students";
        List <Student> students;
        try(PreparedStatement stmt = con.prepareStatement(query)){
            students = new ArrayList<>();
            ResultSet resultset =  stmt.executeQuery();

            while (resultset.next()){

                String username = resultset.getString("username");
                String email = resultset.getString("email");
                String university = resultset.getString("university");
                int yearOfStudy = resultset.getInt("yearOfStudy");
                Student student = new Student(username,email,university,yearOfStudy);
               // student.setId(id);
                students.add(student);
            }
            if(students != null) {
                return students;
            }
            return Collections.emptyList();
        }catch(SQLException e){
            throw new RuntimeException("Something went wrong while loading");
        }

    }

    public void deleteStudents(String username){
        String query = "DELETE FROM students where username = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, username);
            stmt.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public void updateStudent(Student student){
        String query = "UPDATE students set yearOfStudy = ? where username = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setInt(1, student.getYearOfStudy());
            preparedStatement.setString(2, student.getUsername());

            preparedStatement.execute();

        }catch(SQLException e){
            throw new RuntimeException("Something went wrong while updating");
        }
    }

    public Optional<Student> findStudent(String username) {
        String query = "SELECT * FROM students WHERE username=?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1,username);
            ResultSet resultset = stmt.executeQuery();
            if(resultset.next()) {
                Student stud = new Student();
                stud.setUsername(resultset.getString("username"));
                stud.setEmail(resultset.getString("email"));
                stud.setUniversity(resultset.getString("university"));
                stud.setYearOfStudy(resultset.getInt("yearOfStudent"));
                return Optional.of(stud);
            }
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while trying to find the student");
        }
        return Optional.empty();
    }

}

