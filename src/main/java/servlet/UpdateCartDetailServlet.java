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
import models.UpdateCartDetailRequestBean;

/**
 * Servlet implementation class EditCartDetailServlet
 */
@WebServlet("/UpdateCartDetailServlet")
public class UpdateCartDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCartDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		ArrayList<UpdateCartDetailRequestBean> updateCartDetailRequestList = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        System.out.println("request: " + requestBody);
    	ObjectMapper objectMapper = new ObjectMapper();
    	@SuppressWarnings("unchecked")
		List<Map<String, Object>> dataList = objectMapper.readValue(requestBody, List.class);
    	for(Map<String, Object> data: dataList) {
    		int cartDetailId = (int) data.get("cartDetailId");
    		int quantity = (int) data.get("quantity");
    		UpdateCartDetailRequestBean updateCartDetailRequest = new UpdateCartDetailRequestBean(cartDetailId, quantity);
	        updateCartDetailRequestList.add(updateCartDetailRequest);
    	}
    	CartDetailDao cartDetailDao = new CartDetailDao(); 
    	try {
			cartDetailDao.update(updateCartDetailRequestList);
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/aa.jsp");
		dispatcher.forward(request, response);
	}

}
