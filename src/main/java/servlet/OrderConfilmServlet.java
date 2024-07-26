package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDao;

/**
 * 注文を確認するためのサーブレットです。
 * ユーザーIDとカートIDに基づいて注文を確定します。
 * @author yamashita
 * @version 1.0.0
 */
@WebServlet("/OrderConfirm")
public class OrderConfilmServlet extends HttpServlet {
	
	/**
     * POSTメソッドを処理します。リクエストからユーザーIDとカートIDを取得し、注文を確定します。
     * @author yamashita
     * @param request  クライアントからのリクエストを含むHttpServletRequestオブジェクト
     * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
     * @throws ServletException 入出力エラーが発生した場合
     * @throws IOException リクエストの処理中にエラーが発生した場合
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userId = request.getParameter("userId");
        String cartId = request.getParameter("cartId");

        if (userId == null || cartId == null) {
            response.getWriter().println("<h1>パラメータに異常があります。</h1>");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        OrderDao orderDao = new OrderDao();
        try {
            boolean orderConfirmed = orderDao.confirmOrder(userId, cartId);

            if (orderConfirmed) {
                response.getWriter().println("<h1>注文が確定しました。</h1>");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.getWriter().println("<h1>注文確定中にエラーが発生しました。</h1>");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            response.getWriter().println("<h1>予期しないエラーが発生しました。</h1>");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
