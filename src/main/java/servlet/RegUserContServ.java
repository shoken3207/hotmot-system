package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import dao.UserDao;

@WebServlet("/RegUserContServ")
public class RegUserContServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	RequestDispatcher dispatcher =  request.getRequestDispatcher("RegForm.jsp");
	        dispatcher.forward(request, response);
	}
    
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		System.out.println("calle");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		//String pass = request.getParameter("pass");
		
		UserDao userDao = new UserDao();
		CartDao cartDao = new CartDao();
		try {
            // UserDaoのinsertメソッドを呼び出す
            int updateCount = userDao.insert(email, name, false); 
            //System.out.println("updateCount: " + updateCount);

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