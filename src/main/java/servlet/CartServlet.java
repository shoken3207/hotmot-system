package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDao;
import models.CartBean;


/**
 * カートに関するリクエストを処理するためのサーブレットです。
 * @author okuda
 * @version 1.0.0
 */
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
	 * GETメソッドを処理します。指定されたパラメータに基づいてカート情報を取得し、レスポンスとして返します。
	 * @author okuda
	 * @param request  クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException 入出力エラーが発生した場合
	 * @throws IOException リクエストの処理中にエラーが発生した場合
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