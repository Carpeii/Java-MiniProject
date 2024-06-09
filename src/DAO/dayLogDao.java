package DAO;

import DTO.DayLogDto;

import java.sql.*;

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

    public boolean insertIncomeDayLog(String userId, String date, String income){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO day_log (u_id, date, income) values(?,?,?);";
        boolean isSuccess = false;
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,date);
            pstmt.setString(3,income);

            int rowInserted = pstmt.executeUpdate();
            if(rowInserted>0){
                isSuccess = true;
            }
            else {
                isSuccess = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }


    public boolean insertExpenseDayLog(String userId, String date, String expense){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO day_log (u_id, date, expense) values(?,?,?);";
        boolean isSuccess = false;
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,date);
            pstmt.setString(3,expense);

            int rowInserted = pstmt.executeUpdate();
            if(rowInserted>0){
                isSuccess = true;
            }
            else {
                isSuccess = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }
}

