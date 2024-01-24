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
import models.AddBookMarkRequest;
import models.BookMarkBean;

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
      System.out.println("request: " + requestBody);
  	ObjectMapper objectMapper = new ObjectMapper();
  	System.out.println("bbb");
  	AddBookMarkRequest addBookMarkRequest = objectMapper.readValue(requestBody, AddBookMarkRequest.class);
  	System.out.println("aaa");
  	System.out.println(addBookMarkRequest);
  	System.out.println(addBookMarkRequest.getCategoryId());
  	System.out.println(addBookMarkRequest.getProductId());
  	System.out.println(addBookMarkRequest.getUserId());
		String userId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		String categoryId = request.getParameter("categoryId");
		System.out.println(userId);
		System.out.println(productId);		
		System.out.println(categoryId);				
		if(userId == "" || productId == "" || categoryId == "") {
			session.setAttribute("message", "パラメータに異常があります。");
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("パラメータに異常があります。");
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
				return;
			}
			
			bookMarkDao.insert(parseUserId, parseProductId, parseCategoryId);
			session.setAttribute("message", "お気に入りに登録しました。");
			
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
