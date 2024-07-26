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

import dao.BookMarkDao;
import models.AddBookMarkRequestBean;
import models.BookMarkBean;
import models.ResponseMessage;

/**
 * 指定されたユーザーID、商品ID、およびカテゴリIDを使用してお気に入りを追加するためのサーブレットです。
 * もし既に同じ商品が登録されている場合は、その旨をクライアントに通知します。
 * @author okuda
 * @version 1.0.0
 */
@WebServlet("/AddBookMarkServlet")
public class AddBookMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookMarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * HTTP POSTメソッドを処理します。
	 * @author okuda
	 * @param request  クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException 入出力エラーが発生した場合
	 * @throws IOException リクエストの処理中にエラーが発生した場合
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
	  	AddBookMarkRequestBean addBookMarkRequest = objectMapper.readValue(requestBody, AddBookMarkRequestBean.class);
		String userId = addBookMarkRequest.getUserId();
		String productId = addBookMarkRequest.getProductId();
		String categoryId = addBookMarkRequest.getCategoryId();
		if(userId == "" || productId == "" || categoryId == "") {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("パラメータに異常があります。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		}
		
		int parseUserId = Integer.parseInt(userId);
		int parseProductId = Integer.parseInt(productId);
		int parseCategoryId = Integer.parseInt(categoryId);
		
		BookMarkDao bookMarkDao = new BookMarkDao();
		try {
			BookMarkBean bookMark = bookMarkDao.findBookMark(parseProductId, parseUserId);
			if(bookMark != null) {
				session.setAttribute("message", "お気に入りに登録済みです");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
	            ResponseMessage responseMessage = new ResponseMessage("お気に入りに登録済みです。", true);
	            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
	            response.getWriter().write(jsonResponse);
				return;
			}
			
			bookMarkDao.insert(parseUserId, parseProductId, parseCategoryId);
			ArrayList<BookMarkBean> bookMarks = bookMarkDao.findBookMarksByUserId(parseUserId);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(bookMarks);
			session.setAttribute("bookMarks", json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("お気に入りに登録しました。", false);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return;
	}

}
