package servlet;

import java.io.BufferedReader;
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

import dao.CartDao;
import dao.CartDetailDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import models.CartBean;
import models.CartDetailBean;
import models.OrderRequestBean;
import models.ResponseMessage;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		String requestBody = sb.toString();
	  	ObjectMapper objectMapper = new ObjectMapper();
	  	OrderRequestBean orderRequest = objectMapper.readValue(requestBody, OrderRequestBean.class);
		String cartId = orderRequest.getCartId();
		if(cartId == "" ) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("パラメータに異常があります。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		}
		
		int parseCartId = Integer.parseInt(cartId);
		
		CartDao cartDao = new CartDao();
		CartDetailDao cartDetailDao = new CartDetailDao();
		OrderDao orderDao = new OrderDao();
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		
		CartBean cart = cartDao.findCartById(parseCartId);
		
		if(cart == null) {
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("カートが存在していません。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		}
		ArrayList<CartDetailBean> cartDetails = new ArrayList<>();
		try {
			cartDetails = cartDetailDao.getCartDetails(parseCartId);
			
			if(cartDetails.size() == 0) {
				response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            ResponseMessage responseMessage = new ResponseMessage("カートに商品が追加されていません。", true);
	            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
	            response.getWriter().write(jsonResponse);
				return;
			}
			
			orderDao.insertOrder(cart.getUserId(), cart.getShopId());
			
			for (CartDetailBean cartDetail : cartDetails) {
				orderDetailDao.insert(1, cartDetail.getProductId(), cartDetail.getRiceId(), cartDetail.getQuantity());
			}
			cartDetailDao.deleteCartDetailsByCartId(parseCartId);
			
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("カートの商品を注文しました。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return;
	}

}
