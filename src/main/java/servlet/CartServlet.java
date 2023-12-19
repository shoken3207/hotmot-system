package servlet;

import java.io.IOException;
<<<<<<< HEAD

=======
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
>>>>>>> main
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
import dao.CartDao;
import models.CartBean;



@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int Id = Integer.parseInt(request.getParameter("Id"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int shopId = Integer.parseInt(request.getParameter("shopId"));
		CartDao dao= new CartDao();
		ArrayList<CartBean> data = dao.findAll();
		
		CartBean bean = new CartBean(shopId, shopId, shopId, null);
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);

	}

}