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
		    String name = request.getParameter("name");
		    String pass = request.getParameter("pass");


		    boolean isLogin = false;
			if (isLogin) {
		        // ログイン成功時
		        HttpSession session = request.getSession();
		        Object user = null;
				session.setAttribute("loginUser", user);

		        // ここにinsertメソッドの呼び出しを追加
		        UserDao dao = new UserDao();
		        dao.insert(name, pass); // 引数は必要に応じて調整してください
		    }

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/hotmot/loginResult.jsp");
		    dispatcher.forward(request, response);
		}

}