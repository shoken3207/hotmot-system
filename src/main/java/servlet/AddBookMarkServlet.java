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

import dao.BookMarkDao;
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
		String userId = request.getParameter("userId");
		String productId = request.getParameter("productId");
		String categoryId = request.getParameter("categoryId");
		
		if(userId == "" || productId == "" || categoryId == "") {
			session.setAttribute("message", "パラメータに異常があります。");
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
