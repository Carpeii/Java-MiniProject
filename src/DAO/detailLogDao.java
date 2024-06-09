package DAO;


import DTO.DetailLogDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DetailLogDao {
    private Connection conn;
    private DetailLogDto detailLogDto = new DetailLogDto();
    String url = "jdbc:mysql://localhost:3306/account_book";
    String user = "root";
    String password = "1234";

    public DetailLogDao() {
        try {
            this.conn = DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}