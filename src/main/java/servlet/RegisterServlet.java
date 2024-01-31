package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dao.UserDao;
import models.CartBean;
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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if(name == "" || email == "" || password == "" || confirmPassword == "") {
			session.setAttribute("message", "パラメータに異常があります。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/hotmot/register.jsp");
    		dispatcher.forward(request, response);
		}
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		UserBean user = userDao.findUserByEmail(email);
		
		
		if(user == null) {
            session.setAttribute("message", "登録済みのメールアドレスです。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/hotmot/register.jsp");
    		dispatcher.forward(request, response);
		}
		
		try {
			userDao.insertUser(name, email, password, false);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/hotmot/login.jsp");
		dispatcher.forward(request, response);
		if(user.getIsAdmin()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
			dispatcher.forward(request, response);
		} else {
			
			CartBean cart = cartDao.findCartByUserId(user.getId());
			
			if(cart == null) {
				session.setAttribute("message", "カートが作成されていません。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/hotmot/login.jsp");
	    		dispatcher.forward(request, response);
			}
			session.setAttribute("cartId", cart.getId());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
			dispatcher.forward(request, response);
		}
	}

}
