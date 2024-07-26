package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ユーザーの登録画面を表示するためのサーブレットです。
 * このサーブレットは、GETリクエストを受け取ると、登録画面のJSPページにフォワードします。
 * @author hayashi
 * @version 1.0.0
 */
@WebServlet("/RegisterScreenServlet")
public class RegisterScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterScreenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * HTTP GETリクエストを処理し、登録画面のJSPページにフォワードします。
	 * @author hayashi
	 * @param request クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException リクエスト処理中にエラーが発生した場合
	 * @throws IOException リクエストやレスポンスの処理中にエラーが発生した場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

}
