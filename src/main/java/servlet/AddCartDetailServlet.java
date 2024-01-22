package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("called");
//		ArrayList<AddCartDetailRequestBean> addCartDetailRequestList = new ArrayList<>();
//		StringBuilder sb = new StringBuilder();
//        BufferedReader reader = request.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            sb.append(line);1
//        }
//        String requestBody = sb.toString();
//        System.out.println("request: " + requestBody);
//    	ObjectMapper objectMapper = new ObjectMapper();
//    	List<Map<String, Object>> dataList = objectMapper.readValue(requestBody, List.class);
//    	System.out.println("aaa");
//    	for(Map<String, Object> data: dataList) {
//    		int cartId = (int) data.get("cartId");
//    		int productId = (int) data.get("productId");
//    		int riceId = (int) data.get("riceId");
//    		int quantity = (int) data.get("quantity");
//    		System.out.println("call");
//    		System.out.println(cartId);
//    		System.out.println(productId);
//    		System.out.println(riceId);
//    		System.out.println(quantity);
//    		AddCartDetailRequestBean addCartDetailRequest = new AddCartDetailRequestBean(cartId, productId, riceId, quantity);
//	        addCartDetailRequestList.add(addCartDetailRequest);
//    	}
//    	CartDetailDao cartDetailDao = new CartDetailDao(); 
//    	try {
//			cartDetailDao.insert(addCartDetailRequestList);
//			
//		} catch (SQLException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
    	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
		dispatcher.forward(request, response);
	}

}
