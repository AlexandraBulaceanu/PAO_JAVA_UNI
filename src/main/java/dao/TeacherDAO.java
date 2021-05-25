package dao;

import model.users.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeacherDAO {
    private Connection con;

    public TeacherDAO(Connection con) {
        this.con = con;
    }

    public void insertTeacher(Teacher teacher){
        String query = "INSERT INTO teachers VALUES(NULL,?,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1,teacher.getUsername());
            stmt.setString(2,teacher.getEmail());
            stmt.setDouble(3,teacher.getRating());
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Something went wrong while saving");
        }

    }

    public List<Teacher> getAllTeachers(){
        String query = "SELECT * FROM teachers";
        List <Teacher> teachers;
        try(PreparedStatement stmt = con.prepareStatement(query)){
            teachers = new ArrayList<>();
            ResultSet resultset =  stmt.executeQuery();

            while (resultset.next()){

                String username = resultset.getString("username");
                String email = resultset.getString("email");
                double rating = resultset.getDouble("rating");
                Teacher teacher = new Teacher(username,email,rating);

                teachers.add(teacher);
            }
            if(teachers != null) {
                return teachers;
            }
            return Collections.emptyList();
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while loading");
        }

    }

    public void deleteTeachers(String username){
        String query = "DELETE FROM teachers where username = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, username);
            stmt.executeUpdate();
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public void updateTeacher(Teacher Teacher){
        String query = "UPDATE teachers set rating = ? where username = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setDouble(1, Teacher.getRating());
            preparedStatement.setString(2, Teacher.getUsername());

            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while updating");
        }
    }
}
