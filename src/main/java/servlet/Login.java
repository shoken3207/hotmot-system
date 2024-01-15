package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// 入力された情報を取得する
		String email1 = request.getParameter("email");
		String pass1 = request.getParameter("pass");
		// データベースからユーザー情報を取得する
		UserDao dao = new UserDao();
		boolean user = dao.authenticateUser(email1, pass1);

		// 入力された情報とデータベースのユーザー情報を照合する
		if (user == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("loginResult.jsp");
			dispatcher.forward(request, response);
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("loginfailure.jsp");
			dispatcher.forward(request, response);
		}
	}
}