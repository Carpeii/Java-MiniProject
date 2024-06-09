package DAO;

import DTO.DayLogDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DayLogDao {
    private Connection conn;
    private DayLogDto dayLogDto = new DayLogDto();

    String url = "jdbc:mysql://localhost:3306/account_book";
    String user = "root";
    String password = "1234";

    public DayLogDao() {
        try {
            this.conn = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

