package DAO;

import DTO.DayLogDto;

import java.sql.*;
import java.util.ArrayList;

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
    public ArrayList<DayLogDto> getDayLogArrayList(){
        ArrayList<DayLogDto> dayLogDtoArrayList = new ArrayList<>();

        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM day_log";

        try {
            pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DayLogDto to = new DayLogDto();
                to.setId(rs.getInt("id"));
                to.setUserId(rs.getString("u_id"));
                to.setDate(rs.getDate("date"));
                to.setType(rs.getInt("type"));
                to.setMoney(rs.getInt("money"));
                to.setDescription(rs.getString("description"));

                dayLogDtoArrayList.add(to);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dayLogDtoArrayList;
    }

    public boolean insertIncomeDayLog(String userId, String date, String money, String description){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO day_log (u_id, date, money, description) values(?,?,?,?);";
        boolean isSuccess = false;
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,date);
            pstmt.setInt(3,Integer.parseInt(money));
            pstmt.setString(4,description);

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


    public boolean insertExpenseDayLog(String userId, String date, String money, String description){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO day_log ( u_id, date, type, money, description) values(?, ?, ?, ? , ?);";

        boolean isSuccess = false;
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,date);
            pstmt.setInt(3,0);
            pstmt.setInt(4,Integer.parseInt(money));
            pstmt.setString(5,description);
            System.out.println(pstmt.toString());
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

    public boolean deleteDayLog(DayLogDto toDelete) {
        boolean isSuccess = false;
        String sql = "DELETE FROM day_log WHERE id = ?;";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1,toDelete.getId());
            int rowDeleted = pstmt.executeUpdate();
            if(rowDeleted>0){
                isSuccess = true;
            }else{
                isSuccess = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }
}

