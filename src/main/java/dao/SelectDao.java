package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectDao extends CommonDao {
    // ... 他のメソッド

    public List<String> selectAllNames() {
        List<String> names = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String sql = "SELECT name FROM users";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        names.add(rs.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // エラーハンドリングを追加
        }
        return names;
    }
}
