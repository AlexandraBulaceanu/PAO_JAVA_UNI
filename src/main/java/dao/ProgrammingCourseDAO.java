package dao;

import model.courses.ProgrammingCourse;
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

public class ProgrammingCourseDAO {
    private Connection con;
    private Connection conDB = DBService.getInstance().getCon();

    public ProgrammingCourseDAO(Connection con) {
        this.con = con;
    }

    public void insertProgrammingCourse(ProgrammingCourse programmingCourse){
        String query = "INSERT INTO programming_courses VALUES(NULL,?,?,?)";
        try(PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1,programmingCourse.getProgrammingLanguage());
            stmt.setString(2,listToString(programmingCourse.getStudentsEnrolled()));
            stmt.setDouble(3,programmingCourse.getPrice());
            stmt.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("Something went wrong while saving");
        }

    }

    public List<ProgrammingCourse> getAllProgrammingCourses(){
        String query = "SELECT * FROM programming_courses";
        List<ProgrammingCourse> programmingCourses;
        try(PreparedStatement stmt = con.prepareStatement(query)){
            programmingCourses = new ArrayList<>();
            ResultSet resultset =  stmt.executeQuery();

            while (resultset.next()){

                String programmingLanguage = resultset.getString("programmingLanguage");
                String studentNames = resultset.getString("studentsEnrolled");
                double price = resultset.getInt("price");
                List<Student> studentsEnrolled = stringToList(studentNames);
                ProgrammingCourse programmingCourse = new ProgrammingCourse(programmingLanguage,studentsEnrolled,price);

                programmingCourses.add(programmingCourse);
            }
            if(programmingCourses != null) {
                return programmingCourses;
            }
            return Collections.emptyList();
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while loading");
        }

    }

    public void deleteProgrammingCourses(String programmingLanguage){
        String query = "DELETE FROM programming_courses where programmingLanguage = ?";
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, programmingLanguage);
            stmt.executeUpdate();
        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public void updateProgrammingCourse(ProgrammingCourse programmingCourse){
        String query = "UPDATE programming_courses set price = ? where programmingLanguage = ?";
        try(PreparedStatement preparedStatement = con.prepareStatement(query)){
            preparedStatement.setDouble(1, programmingCourse.getPrice());
            preparedStatement.setString(2, programmingCourse.getProgrammingLanguage());

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
