package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.UserBean;

public class UserDao extends CommonDao {
	
    private final Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public List<UserBean> getAll() {
        List<UserBean> data = new ArrayList<UserBean>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from User";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                boolean isAdmin = rs.getBoolean("isAdmin");
                UserBean prf = new UserBean(id, email, name, isAdmin);
                data.add(prf);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null) {
                    pstmt.close();
                }
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
