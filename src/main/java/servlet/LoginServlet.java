package servlet;

import java.io.IOException;

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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		session.setAttribute("message", "");
		session.removeAttribute("message");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email == "" || password == "") {
			session.setAttribute("message", "パラメータに異常があります。");
			response.sendRedirect("/hotmot/login.jsp");
    		return;
		}
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		UserBean user = userDao.findUser(email, password);
		
		System.out.println(user);
		System.out.println(user == null);
		if(user == null) {
            session.setAttribute("message", "emailかpasswordのいずれかが間違っています。");
    		response.sendRedirect("/hotmot/login.jsp");
    		return;
		}
		session.setAttribute("userId", user.getId());
		
		if(user.getIsAdmin()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
			dispatcher.forward(request, response);
		} else {
			
			CartBean cart = cartDao.findCartByUserId(user.getId());
			
			if(cart == null) {
				session.setAttribute("message", "カートが作成されていません。");
				response.sendRedirect("/hotmot/login.jsp");
	    		return;
			}
			session.setAttribute("cartId", cart.getId());
			response.sendRedirect("/hotmot/ProductListServlet");
		}
		
	}
}