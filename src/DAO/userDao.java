package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDao {
    private Connection con;

    String url = "jdbc:mysql://localhost:3306/user";
    String user = "root";
    String password = "1234";

    public UserDao(){
        try {
            this.con = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean signUp(){
        
        return true;
    }
    public boolean login(){
        return true;
    }
}
