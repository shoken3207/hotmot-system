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
import dao.UserDao;
import models.OrderDetailHistoryByAdminBean;
import models.OrderHistoryBean;
import models.ResponseMessage;
import models.UserBean;

/**
 * 管理者画面に関するリクエストを処理するためのサーブレットです。
 * 指定されたユーザーID、開始日、および終了日を使用して注文履歴を取得します。
 * @author ezaki
 * @version 1.0.0
 */
@WebServlet("/AdminScreenServlet")
public class AdminScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminScreenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * GETメソッドを処理します。ユーザーID、開始日、および終了日を使用して注文履歴を取得し、レスポンスとして返します。
	 * @author ezaki
	 * @param request  クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException 入出力エラーが発生した場合
	 * @throws IOException リクエストの処理中にエラーが発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	  	ObjectMapper objectMapper = new ObjectMapper();
		String userId = request.getParameter("userId");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		
		UserDao userDao = new UserDao();
		OrderDao orderDao = new OrderDao();
		OrderDetailHistoryDao detailDao = new OrderDetailHistoryDao();
		ArrayList<OrderHistoryBean> orderHistories = new ArrayList<OrderHistoryBean>();
		if(userId == "") {
			session.setAttribute("message", "パラメータに異常があります。");
			return;
		}
		int parseUserId = Integer.parseInt(userId);
		
		UserBean user = userDao.findUserById(parseUserId);
		
		if(user == null) {
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("ユーザーが存在しません。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		}
		
		if(!user.getIsAdmin()) {
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("管理者ユーザー以外は閲覧できません。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		}
		
		try {
			ArrayList<OrderDetailHistoryByAdminBean> histories = detailDao.getOrderDetailHistoryByAdmin(fromDate, toDate);
			
			
			response.setCharacterEncoding("UTF-8");
			String json = objectMapper.writeValueAsString(histories);
            response.getWriter().write(json);
            return;
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
//		RequestDispatcher dispatcher =  request.getRequestDispatcher("WEB-INF/adminScreen.jsp");
//		dispatcher.forward(request, response);
	}

}
