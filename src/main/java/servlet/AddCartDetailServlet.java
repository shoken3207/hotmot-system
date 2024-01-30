package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CartDetailDao;
import models.AddCartDetailRequestBean;
import models.CartDetailBean;
import models.ResponseMessage;

/**
 * Servlet implementation class AddCartDetailServlet
 */
@WebServlet("/AddCartDetailServlet")
public class AddCartDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<AddCartDetailRequestBean> addCartDetailRequestList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
    	ObjectMapper objectMapper = new ObjectMapper();
    	List<Map<String, Object>> dataList = objectMapper.readValue(requestBody, List.class);
    	for(Map<String, Object> data: dataList) {
    		int cartId = (int) data.get("cartId");
    		int productId = (int) data.get("productId");
    		int riceId = (int) data.get("riceId");
    		int quantity = (int) data.get("quantity");
    		AddCartDetailRequestBean addCartDetailRequest = new AddCartDetailRequestBean(cartId, productId, riceId, quantity);
	        addCartDetailRequestList.add(addCartDetailRequest);
    	}
    	CartDetailDao cartDetailDao = new CartDetailDao(); 
    	try {
    		for (AddCartDetailRequestBean addCartDetailRequest : addCartDetailRequestList) {
    			int cartId = addCartDetailRequest.getCartId();
    			int productId = addCartDetailRequest.getProductId();
    			int riceId = addCartDetailRequest.getRiceId();
    			int quantity = addCartDetailRequest.getQuantity();
    			CartDetailBean cartDetail = cartDetailDao.getCartDetail(cartId, productId, riceId);
    			
    			if (cartDetail == null) {
    				cartDetailDao.createCartDetail(cartId, productId, riceId, quantity);
    		      } else {
    		    	  cartDetailDao.updateCartDetail(cartDetail.getId(), cartDetail.getQuantity() + quantity);
    		      }
			}
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ResponseMessage responseMessage = new ResponseMessage("商品をカートに追加しました。", false);
        String jsonResponse = objectMapper.writeValueAsString(responseMessage);
        response.getWriter().write(jsonResponse);
    	return;
	}

    
}