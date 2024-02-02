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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if(email == "" || password == "") {
			request.setAttribute("message", "パラメータに異常があります。");
			response.sendRedirect("/hotmot/index.jsp");
    		return;
		}
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		UserBean user = userDao.findUser(email, password);
		
		if(user == null) {
            request.setAttribute("message", "emailかpasswordのいずれかが間違っています。");
    		response.sendRedirect("/hotmot/index.jsp");
    		return;
		}
		session.setAttribute("userId", String.valueOf(user.getId()));
		
		if(user.getIsAdmin()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
			dispatcher.forward(request, response);
		} else {
			
			CartBean cart = cartDao.findCartByUserId(user.getId());
			
			if(cart == null) {
				request.setAttribute("message", "カートが作成されていません。");
				response.sendRedirect("/hotmot/index.jsp");
	    		return;
			}
			session.setAttribute("cartId", String.valueOf(cart.getId()));
			response.sendRedirect("/hotmot/ProductListServlet?userId=" + user.getId());
		}
		
	}
}