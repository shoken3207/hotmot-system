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

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.OrderDao;
import dao.OrderDetailHistoryDao;
import models.OrderBean;
import models.OrderDetailBean;
import models.OrderHistoryBean;

/**
 * 注文履歴を取得して表示するためのサーブレットです。
 * ユーザーIDに基づいて、ユーザーの注文履歴と注文の詳細を取得し、JSON形式でセッションに保存します。
 * @author okuda
 * @version 1.0.0
 */
@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 /**
     * GETメソッドを処理します。リクエストからユーザーIDを取得し、そのユーザーの注文履歴を取得してJSON形式でセッションに保存します。
     * @author okuda
     * @param request  クライアントからのリクエストを含むHttpServletRequestオブジェクト
     * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
     * @throws ServletException 入出力エラーが発生した場合
     * @throws IOException リクエストの処理中にエラーが発生した場合
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//コードを変えた際にしたのをIntegerにする
		String userId = request.getParameter("userId");
		HttpSession session = request.getSession();
		ArrayList<OrderHistoryBean> orderHistories = new ArrayList<OrderHistoryBean>();
		OrderDetailHistoryDao detailDao = new OrderDetailHistoryDao();
		OrderDao orderDao = new OrderDao();
		int parseUserId = Integer.parseInt(userId);
		try {
			ArrayList<OrderBean> orders = orderDao.findOrdersByUserId(parseUserId);
			
			for (OrderBean order : orders) {
    			ArrayList<OrderDetailBean> orderDetails = detailDao.getOrderDetailHistory(order.getId());
    			OrderHistoryBean orderHistory = new OrderHistoryBean(order.getId(), order.getShopId(), order.getUserId(), order.getCreatedAt(), orderDetails);
    			orderHistories.add(orderHistory);
			}
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(orderHistories);
			session.setAttribute("orderHistories", json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/OrderHistory.jsp").forward(request, response);
	}
}