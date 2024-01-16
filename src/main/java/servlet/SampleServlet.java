package servlet;

import java.io.IOException;
<<<<<<< HEAD

=======
import java.util.List;

import javax.servlet.RequestDispatcher;
>>>>>>> origin/test
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import dao.UserDao;
=======
import dao.SampleDao;
import dao.SelectDao;
>>>>>>> origin/test

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/SampleServlet")
public class SampleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // サーブレットのロジックを実装
<<<<<<< HEAD
    	request.setCharacterEncoding("UTF-8");
=======
>>>>>>> origin/test
    	String name = request.getParameter("name");
    	String world = request.getParameter("world");
    	System.out.println(name);
    	System.out.println("world: " + world);
<<<<<<< HEAD
    	UserDao dao = new UserDao();
        dao.insertData(name);
        // JSPにリダイレクト
        response.sendRedirect("RegDone.jsp");
    }
}
=======
    	SampleDao dao = new SampleDao();
        dao.insertData(name);
        // JSPにリダイレクト
        response.sendRedirect("success.jsp");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	SelectDao dao = new SelectDao();
    	List<String> names = dao.selectAllNames();

        // リクエスト属性にセット
        request.setAttribute("names", names);

        // JSPにフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayNames.jsp");
        dispatcher.forward(request, response);
    }
}
>>>>>>> origin/test
