package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.ResponseMessage;

/**
 * このサーブレットは、HTTP POSTリクエストに対してJSON形式のレスポンスを返します。
 * 主にテスト目的で使用され、成功メッセージをJSON形式で返します。
 * @author hira
 * @version 1.0.0
 */
@WebServlet("/ResponseTestServlet")
public class ResponseTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * HTTP POSTリクエストを処理し、JSON形式のレスポンスを返します。
	 * @author hira
	 * @param request クライアントからのリクエストを含むHttpServletRequestオブジェクト
	 * @param response サーバーからクライアントへのレスポンスを含むHttpServletResponseオブジェクト
	 * @throws ServletException リクエスト処理中にエラーが発生した場合
	 * @throws IOException リクエストやレスポンスの処理中にエラーが発生した場合
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  	ObjectMapper objectMapper = new ObjectMapper();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
        ResponseMessage responseMessage = new ResponseMessage("お気に入りに登録済みです。", true);
        String jsonResponse = objectMapper.writeValueAsString(responseMessage);
        response.getWriter().write(jsonResponse);
		return;
	}

}
