package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SampleDao;

/**
 * Servlet implementation class SampleServlet
 */
@WebServlet("/SampleServlet")
public class SampleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // サーブレットのロジックを実装
    	String name = request.getParameter("name");
    	String world = request.getParameter("world");
    	System.out.println(name);
    	System.out.println("world: " + world);
    	SampleDao dao = new SampleDao();
        dao.insertData(name);
        // JSPにリダイレクト
        response.sendRedirect("success.jsp");
    }
}