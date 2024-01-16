package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		int userId = dao.authenticateUser(email1, pass1);
	
		int cartId = dao.getCartId(userId);
		System.out.println("cartId: " + cartId);
		
		// 入力された情報とデータベースのユーザー情報を照合する
		String forwardPath = "";
		if (userId != 0) {
			forwardPath = "loginResult.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
		}else{
			forwardPath = "loginfailure.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}