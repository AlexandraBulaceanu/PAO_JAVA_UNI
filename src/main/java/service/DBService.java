package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBService {
    private String url = "jdbc:mysql://localhost/proiect_PAO";
    private String user = "root";
    private String pass = "";
    private Connection con;
    private DBService(){
        try{
            con = DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private  static final class SingletonHolder{
        private static final DBService INSTANCE = new DBService();
    }
    public static DBService getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public Connection getCon() {
        return con;
    }
}
