package DAO;

import DTO.CategoryDto;
import DTO.DayLogDto;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDao {
    private Connection conn;
    private CategoryDto categoryDto;

    String url = "jdbc:mysql://localhost:3306/account_book";
    String user = "root";
    String password = "1234";

    public CategoryDao() {
        try {
            this.conn = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CategoryDto> getCategories(String userId) {
        ArrayList<CategoryDto> categories = new ArrayList<>();

        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM categories Where u_id=?;";

        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CategoryDto to = new CategoryDto();
                to.setId(rs.getInt("id"));
                to.setUserId(rs.getString("u_id"));
                to.setName(rs.getString("name"));

                categories.add(to);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public boolean insertCategories(String userId, String name){
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO categories ( u_id, name) values(?, ?);";
        boolean isSuccess = false;
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,name);

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

    public boolean deleteCategory(CategoryDto toDelete) {
        boolean isSuccess = false;
        String sql = "DELETE FROM categories WHERE id = ?;";
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

    public CategoryDto getCategory(String userId, String name) {
        CategoryDto to = new CategoryDto();

        boolean isSuccess = false;
        String sql = "SELECT * FROM categories WHERE u_id = ? AND name = ?;";
        PreparedStatement pstmt = null;

        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            pstmt.setString(2,name);

            ResultSet rs = pstmt.executeQuery();

            to.setId(rs.getInt("id"));
            to.setName(rs.getString("name"));
            to.setUserId(rs.getString("u_id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return to;
    }
}
