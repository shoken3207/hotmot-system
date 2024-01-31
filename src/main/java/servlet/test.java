package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.AddCartDetailRequestBean;

/**
 * Servlet implementation class test
 */
@WebServlet("/test")
public class test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("test");
		 String jsonData = "{\"message\": \"Hello from Servlet!\"}";

        // JSON形式のデータを返す
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AddCartDetailRequestBean> addCartDetailRequestList = new ArrayList<>();
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
    	System.out.println(addCartDetailRequestList);
	}

}
