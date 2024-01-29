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
 * Servlet implementation class AddBookMarkServlet
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
