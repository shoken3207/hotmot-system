package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SampleDao extends CommonDao {
    public void insertData(String name) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO users (name) VALUES (?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
            	System.out.println(name);
                ps.setString(1, name);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを追加
        }
    }
}
