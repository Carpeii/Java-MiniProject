package DAO;

import DTO.CategoryDto;
import DTO.DayLogDto;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DayLogDao {
    private DayLogDto dayLogDto;

    public DayLogDao() {

    }
    public ArrayList<DayLogDto> getDayLogArrayList(String userId){
        ArrayList<DayLogDto> dayLogDtoArrayList = new ArrayList<>();
        //TODO WHERE user_id 추가
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM day_log WHERE u_id = ?;";

        try {
            pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
            pstmt.setString(1,userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DayLogDto to = new DayLogDto();
                to.setId(rs.getInt("id"));
                to.setUserId(rs.getString("u_id"));
                to.setDate(rs.getDate("date"));
                to.setType(rs.getInt("type"));
                to.setMoney(rs.getInt("money"));
                to.setDescription(rs.getString("description"));
                to.setCategoryId(rs.getInt("category_id"));

                dayLogDtoArrayList.add(to);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dayLogDtoArrayList;
    }

    public boolean insertIncomeDayLog(String userId, String date, String money, String description, CategoryDto category){
        PreparedStatement pstmt = null;
        String sql = null;
        boolean isSuccess = false;
        try {
            if(category == null){
                sql = "INSERT INTO day_log ( u_id, date, type, money, description) values(?, ?, ?, ?, ?);";
                pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
                pstmt.setString(1,userId);
                pstmt.setString(2,date);
                pstmt.setInt(3,1);
                pstmt.setInt(4,Integer.parseInt(money));
                pstmt.setString(5,description);
            }else{
                sql = "INSERT INTO day_log ( u_id, date, type, money, description, category_id) values(?, ?, ?, ?, ?, ?);";
                pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
                pstmt.setString(1,userId);
                pstmt.setString(2,date);
                pstmt.setInt(3,1);
                pstmt.setInt(4,Integer.parseInt(money));
                pstmt.setString(5,description);
                pstmt.setInt(6,category.getId());
            }


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


    public boolean insertExpenseDayLog(String userId, String date, String money, String description, CategoryDto category){
        PreparedStatement pstmt = null;
        String sql = null;

        boolean isSuccess = false;
        try {
            if(category==null){
                sql = "INSERT INTO day_log ( u_id, date, type, money, description) values(?, ?, ?, ?, ?);";
                pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
                pstmt.setString(1,userId);
                pstmt.setString(2,date);
                pstmt.setInt(3,0);
                pstmt.setInt(4,Integer.parseInt(money));
                pstmt.setString(5,description);
            }else{
                sql = "INSERT INTO day_log ( u_id, date, type, money, description, category_id) values(?, ?, ?, ?, ?, ?);";
                pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
                pstmt.setString(1,userId);
                pstmt.setString(2,date);
                pstmt.setInt(3,0);
                pstmt.setInt(4,Integer.parseInt(money));
                pstmt.setString(5,description);
                pstmt.setInt(6,category.getId());
            }
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
            pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
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

    public boolean changeCategoryNull(CategoryDto toChange){
        boolean isSuccess = false;
        String sql = "UPDATE day_log SET category_id = NULL WHERE category_id = ?;";
        PreparedStatement pstmt = null;

        try {
            pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
            pstmt.setInt(1,toChange.getId());
            int rowChanged = pstmt.executeUpdate();
            if(rowChanged>0){
                isSuccess = true;
            }else{
                isSuccess = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }

    //TODO Update 추가하기
    public boolean updateDayLog(DayLogDto toUpdate, Date date, int type, int money, String description, int categoryId){
        boolean isSuccess = false;

        String sql = "UPDATE day_log SET date = ?, type=?,money=?,description=?, category_id = ?  WHERE id = ?;";
        PreparedStatement pstmt = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            pstmt = DatabaseManager.getInstance().getConnection().prepareStatement(sql);
            pstmt.setDate(1, date);
            pstmt.setInt(2,type);
            pstmt.setInt(3,money);
            pstmt.setString(4,description);
            pstmt.setInt(5,categoryId);
            pstmt.setInt(6,toUpdate.getId());

            int rowChanged = pstmt.executeUpdate();
            if(rowChanged>0){
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

