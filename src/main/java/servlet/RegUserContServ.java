package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.UserDao;

/**
 * ユーザーの登録を処理するサーブレットです。
 * このサーブレットは、ユーザーの登録フォームを表示し、ユーザーの登録処理を行います。
 * ユーザーが正常に登録された場合は、成功ページにリダイレクトし、
 * エラーが発生した場合はエラーページにリダイレクトします。
 * @author okuda
 * @version 1.0.0
 */
@WebServlet("/RegUserContServ")
public class RegUserContServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
     * HTTP POSTリクエストを処理し、ユーザーの登録を行います。
     * @author okuda
     * @param request クライアントからのリクエストを含むHttpServletRequestオブジェクト
     * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
     * @throws ServletException リクエスト処理中にエラーが発生した場合
     * @throws IOException リクエストやレスポンスの処理中にエラーが発生した場合
     */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		//String pass = request.getParameter("pass");
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		try {
            // UserDaoのinsertメソッドを呼び出す
            int updateCount = userDao.insert(email, name, false); 

            if (updateCount >= 0) {
                // 挿入成功時
            	int userId =userDao.select(email);
            	cartDao.insert(userId , 1);
                response.sendRedirect("RegDone.jsp"); // 成功ページにリダイレクト
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            // エラーページにリダイレクト
            response.sendRedirect("error.jsp");
        }
	}
}