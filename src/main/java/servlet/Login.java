package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CartDao;
import dao.UserDao;
import models.CartBean;
import models.LoginRequestBean;
import models.UserBean;

@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		String requestBody = sb.toString();
	  	ObjectMapper objectMapper = new ObjectMapper();
	  	LoginRequestBean loginRequest = objectMapper.readValue(requestBody, LoginRequestBean.class);
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		if(email == "" || password == "") {
			session.setAttribute("message", "パラメータに異常があります。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request, response);
		}
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		UserBean user = userDao.findUserById(email, password);
		
		
		if(user == null) {
            session.setAttribute("message", "emailかpasswordのいずれかが間違っています。");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
    		dispatcher.forward(request, response);
		}
		session.setAttribute("userId", user.getId());
		
		if(user.getIsAdmin()) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
			dispatcher.forward(request, response);
		} else {
			
			CartBean cart = cartDao.findCartByUserId(user.getId());
			
			if(cart == null) {
				session.setAttribute("message", "カートが作成されていません。");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	    		dispatcher.forward(request, response);
			}
			session.setAttribute("cartId", cart.getId());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}