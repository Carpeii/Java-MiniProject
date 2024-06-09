package DAO;

import DTO.UserDto;

import java.sql.*;

public class UserDao {
    private Connection conn;
    private UserDto userDto;
    String url = "jdbc:mysql://localhost:3306/account_book";
    String user = "root";
    String password = "1234";

    public UserDao(){
        try {
            this.conn = DriverManager.getConnection(url,user,password);
            userDto = new UserDto();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean signUp(String id, String password, String name){
        boolean isSuccess = false;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO user (u_id, u_password, u_name) VALUES (?, ?, ?);";
        try {
            pstmt = this.conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, name);

            int rowInserted = pstmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("new user added");
                isSuccess = true;
            }
            else{
                isSuccess = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }
    public boolean login(String userId, String password) {
        boolean isSuccess = false;

        try {
            String sql = "SELECT * FROM user WHERE u_id = ? AND u_password = ?";
            PreparedStatement pstmt = this.conn.prepareStatement(sql);

            pstmt.setString(1, userId);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                isSuccess = true;
                System.out.println("로그인 성공!");
                userDto.setId(rs.getString("u_id"));
                userDto.setPassword(rs.getString("u_password"));
                userDto.setName(rs.getString("u_name"));
            } else {
                System.out.println("로그인 실패.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public UserDto getUser() {
        return userDto;
    }
}
