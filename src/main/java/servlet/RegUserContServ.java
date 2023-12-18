package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegUserContServ")
public class RegUserContServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
    	String forwardPath = null;
    	String action = request.getParameter("action");
    	if(action == null) {
    		forwardPath = "/WEB-INF/jsp/RegForm.jsp";
    	}
    	else if (action.equals("done")) {
    		HttpSession session = request.getSession();
    		UserServ regUserContServ = (UserServ) session.getAttribute("regUserContServ");
    		
    		RegUserServ logic = new RegUserServ();
    		logic.execute(regUserContServ);
    		
    		session.removeAttribute("regUserContServ");
    		
    		forwardPath = "/WEB-INF/jsp/RegDone.jsp";
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
    	dispatcher.forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		UserServ regUserContServ = new UserServ(id,name,pass);
		
		HttpSession session = request.getSession();
		session.setAttribute("regUserContServ", regUserContServ);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/RegConfirm.jsp");
		dispatcher.forward(request, response);
		
	}

}
//228P