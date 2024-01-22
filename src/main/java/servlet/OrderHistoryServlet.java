package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDetailHistoryDao;
import dao.OrderHistoryDao;
import models.OrderBean;
import models.OrderDetailBean;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ユーザidはどこからもってくるのか聞く
		String userId = request.getParameter("userId");
		int orderId = 0;
		
		OrderHistoryDao orderHistoryDao = new OrderHistoryDao();
		OrderDetailHistoryDao orderDetailHistoryDao = new OrderDetailHistoryDao();
		try {
			ArrayList<OrderBean> orderHistory = orderHistoryDao.getOrderHistory(Integer.parseInt(userId));
			ArrayList<OrderDetailBean> orderDetailHistory = orderDetailHistoryDao.getOrderDetailHistory(orderId);
			orderId = orderHistoryDao.getOrderId(Integer.parseInt(userId));
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("orderId", orderId);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
}