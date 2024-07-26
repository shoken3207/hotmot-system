package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BookMarkDao;
import models.BookMarkBean;

/**
 * ユーザーのブックマークした商品のリストを表示するためのサーブレットです。
 * このサーブレットは、GETリクエストでユーザーIDを受け取り、そのユーザーのブックマークをデータベースから取得し、
 * JSON形式でセッションに設定し、リクエストをJSPページにフォワードします。
 * POSTリクエストはGETリクエストと同じ処理を実行します。
 * @author okuda
 * @version 1.0.0
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * HTTP GETリクエストを処理し、ユーザーIDに基づいてブックマークを取得し、セッションに設定してから
	 * JSPページにフォワードします。
	 * @author okuda
	 * @param request クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException リクエスト処理中にエラーが発生した場合
	 * @throws IOException リクエストやレスポンスの処理中にエラーが発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		if(userId == "") {
			session.setAttribute("message", "パラメータに異常があります。");
			return;
		}
		int parseUserId = Integer.parseInt(userId);
		BookMarkDao bookMarkDao = new BookMarkDao();
		try {
			ArrayList<BookMarkBean> bookMarks = bookMarkDao.findBookMarksByUserId(parseUserId);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(bookMarks);
			session.setAttribute("bookMarks", json);
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productList.jsp");
		dispatcher.forward(request, response);
	}

}
