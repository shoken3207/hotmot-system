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
import models.BookMarkBean;
import models.DeleteBookMarkRequestBean;
import models.ResponseMessage;

/**
 * Servlet implementation class DeleteBookMarkServlet
 */
@WebServlet("/DeleteBookMarkServlet")
public class DeleteBookMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookMarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
		    sb.append(line);
		}
		String requestBody = sb.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		DeleteBookMarkRequestBean deleteBookMarkRequest = objectMapper.readValue(requestBody, DeleteBookMarkRequestBean.class);
		String userId = deleteBookMarkRequest.getUserId();
		String productId = deleteBookMarkRequest.getProductId();
		if(userId == "" || productId == "") {
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage("パラメータに異常があります。", true);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			return;
		}
		
		int parseUserId = Integer.parseInt(userId);
		int parseProductId = Integer.parseInt(productId);
		
		BookMarkDao bookMarkDao = new BookMarkDao();
		try {
			BookMarkBean bookMark = bookMarkDao.findBookMark(parseProductId, parseUserId);
			if(bookMark == null) {
				response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            ResponseMessage responseMessage = new ResponseMessage("お気に入りに登録されていません。", true);
	            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
	            response.getWriter().write(jsonResponse);
				return;
			}
			
			bookMarkDao.delete(bookMark.getId());
			response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
			ResponseMessage responseMessage = new ResponseMessage("お気に入りを削除しました。", false);
            String jsonResponse = objectMapper.writeValueAsString(responseMessage);
            response.getWriter().write(jsonResponse);
			ArrayList<BookMarkBean> bookMarks = bookMarkDao.findBookMarksByUserId(parseUserId);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(bookMarks);
			session.setAttribute("bookMarks", json);
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
