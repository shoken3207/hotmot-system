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
import models.OrderDetailBean;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//コードを変えた際にしたのをIntegerにする
//		String userId = request.getParameter("userId");
		int userId=2;
		int orderId=1;
		OrderHistoryDao dao = new OrderHistoryDao();
		OrderDetailHistoryDao detailDao = new OrderDetailHistoryDao();
		try {
			orderId=dao.getOrderId(userId);
			//userIdから履歴の取得
			ArrayList<OrderDetailBean> orderDetailHistory = detailDao.getOrderDetailHistory(orderId);
			System.out.println(orderDetailHistory);
			request.setAttribute("orderDetailHistory", orderDetailHistory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/OrderHistory.jsp").forward(request, response);
	}
}