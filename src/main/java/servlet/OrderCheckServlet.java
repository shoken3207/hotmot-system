package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.json.ItemsBean;
import models.json.OrderCheckBean;

/**
 * 注文確認を処理するためのサーブレットです。
 * @author okuda
 * @version 1.0.0
 */
@WebServlet("/OrderCheckServlet")
public class OrderCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * GETメソッドを処理します。リクエストから注文情報を取得し、セッションに格納します。
	 * @author okuda
	 * @param request  クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException 入出力エラーが発生した場合
	 * @throws IOException リクエストの処理中にエラーが発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int img = Integer.parseInt(request.getParameter("img"));
		String productName = request.getParameter("productName");
		String option = request.getParameter("option");
		int cartDetailId = Integer.parseInt("cartDetailId");
		String userName = request.getParameter("userName");
		String status = request.getParameter("status");
		
		OrderCheckBean order = new OrderCheckBean();
		order.setImg(img);
        order.setProductName(productName);
        order.setOption(option);
        ItemsBean items = new ItemsBean();
        items.setcartDetailId(cartDetailId);
        items.setuserName(userName);
        items.setstatus(status);
		
        
        @SuppressWarnings("unchecked")
		ArrayList<OrderCheckBean> orderList = (ArrayList<OrderCheckBean>) request.getSession().getAttribute("orderList");
        if (orderList == null) {
            orderList = new ArrayList<>();
            List<ItemsBean> itemsList = new ArrayList<>();
            	for (int i=0;i< itemsList.size();i++) {
            		itemsList.get(i);
        }
        orderList.add(order);
        request.getSession().setAttribute("orderList", orderList);
        itemsList.add(items);
        request.getSession().setAttribute("itemsList", itemsList);

        // JSPにリダイレクト
        response.sendRedirect("OrderCheck.jsp");
        }
	}
}