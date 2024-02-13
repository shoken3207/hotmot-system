package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.UserDao;
import models.UserBean;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		RequestDispatcher registerDispatcher = request.getRequestDispatcher("register.jsp");
		RequestDispatcher loginDispatcher = request.getRequestDispatcher("index.jsp");
		if(name == "" || email == "" || password == "" || confirmPassword == "") {
			request.setAttribute("message", "パラメータに異常があります。");
            request.setAttribute("name",name);
            request.setAttribute("email",email);
            request.setAttribute("password",password);
            request.setAttribute("confirmPassword",confirmPassword);
			registerDispatcher.forward(request, response);
    		return;
		}
		
		if(!password.equals(confirmPassword)) {
			request.setAttribute("message", "パスワードと確認用パスワードが異なります。");
            request.setAttribute("name",name);
            request.setAttribute("email",email);
            request.setAttribute("password",password);
            request.setAttribute("confirmPassword",confirmPassword);
			registerDispatcher.forward(request, response);
    		return;
		}
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		UserBean user = userDao.findUserByEmail(email);
		
		if(user != null) {
            request.setAttribute("message", "登録済みのメールアドレスです。");
            request.setAttribute("name",name);
            request.setAttribute("email",email);
            request.setAttribute("password",password);
            request.setAttribute("confirmPassword",confirmPassword);
    		registerDispatcher.forward(request, response);
    		return;
		}
		
		try {
			userDao.insertUser(name, email, password, false);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		UserBean newUser = userDao.findUserByEmail(email);
		try {
			cartDao.insert(newUser.getId(), 1);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		loginDispatcher.forward(request, response);
		return;
	}

}
