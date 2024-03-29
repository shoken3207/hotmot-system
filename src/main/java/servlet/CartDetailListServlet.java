package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CartDetailDao;
import models.CartDetailBean;

/**
 * Servlet implementation class CartDetailListServlet
 */
@WebServlet("/CartDetailListServlet")
public class CartDetailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDetailListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartId = request.getParameter("cartId");
		CartDetailDao cartDetailDao = new CartDetailDao();
		try {
			ArrayList<CartDetailBean> cartDetails = cartDetailDao.getCartDetails(Integer.parseInt(cartId));
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(cartDetails);
			HttpSession session = request.getSession();
			session.setAttribute("cartDetails", json);
		} catch (NumberFormatException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/cartDetailList.jsp");
		dispatcher.forward(request, response);
	}

}
	