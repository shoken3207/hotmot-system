package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDetailHistoryDao;
import dao.OrderHistoryDao;
import models.OrderBean;
import models.OrderDetailBean;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ユーザidはどこからもってくるのか聞く
//		String userId = request.getParameter("userId");
		int userId = 1;
		int orderId=0;
		
		OrderHistoryDao dao = new OrderHistoryDao();
		OrderDetailHistoryDao detailDao = new OrderDetailHistoryDao();
		try {
			ArrayList<OrderBean> orderHistory = dao.getOrderHistory(userId);
			//request.setAttribute("orderHistory", orderHistory);
			for (OrderBean order : orderHistory) {
				orderId = order.getId();
			}

			ArrayList<OrderDetailBean> orderDetailHistory = detailDao.getOrderDetailHistory(orderId);
			System.out.println("aaa");
			System.out.println(orderDetailHistory);
			request.setAttribute("orderId", orderId);
			request.setAttribute("orderDetailHistory", orderDetailHistory);
			@SuppressWarnings("unchecked")
			ArrayList<OrderDetailBean> order = (ArrayList<OrderDetailBean>)request.getAttribute("orderDetailHistory");
			System.out.println(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		String orderHistory = "assa";
//		request.setAttribute("orderHistory", orderHistory);
		
		request.getRequestDispatcher("/OrderHistory.jsp").forward(request, response);
	}
}