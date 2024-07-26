package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 商品の詳細情報を表示するためのサーブレットです。
 * このサーブレットは、リクエストから商品IDを取得し、セッションに設定してから、商品詳細のJSPページにフォワードします。
 * @author hira
 * @version 1.0.0
 */
@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * HTTP GETリクエストを処理し、商品IDをセッションに設定し、商品詳細のJSPページにフォワードします。
	 * @author hira
	 * @param request クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException リクエスト処理中にエラーが発生した場合
	 * @throws IOException リクエストやレスポンスの処理中にエラーが発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productId = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("productId", productId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productDetail.jsp");
		dispatcher.forward(request, response);
	}

}
