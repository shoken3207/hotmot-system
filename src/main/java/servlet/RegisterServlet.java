package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession();
//		session.setAttribute("message", "");
		session.removeAttribute("message");
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.removeAttribute("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		System.out.println(password);
		System.out.println(confirmPassword);
		if(name == "" || email == "" || password == "" || confirmPassword == "") {
			session.setAttribute("message", "パラメータに異常があります。");
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			response.sendRedirect("/hotmot/register.jsp");
    		return;
		}
		
		if(!password.equals(confirmPassword)) {
			session.setAttribute("message", "パスワードと確認用パスワードが異なります。");
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
            response.sendRedirect("/hotmot/register.jsp");
    		return;
		}
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		UserBean user = userDao.findUserByEmail(email);
		System.out.println(user);
		
		if(user != null) {
            session.setAttribute("message", "登録済みのメールアドレスです。");
            session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
            response.sendRedirect("/hotmot/register.jsp");
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
		
		response.sendRedirect("/hotmot/index.jsp");
		return;
	}

}
