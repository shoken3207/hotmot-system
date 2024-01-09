package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class LoginDao {

    public static User getUser(String name, String pass) throws SQLException {
        Connection connection = Data();

        String sql = "SELECT * FROM users WHERE name = ? AND pass = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            resultSet.getString("name");
            resultSet.getString("pass");

            return new User();
        }

        return null;
    }

	private static Connection Data() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
