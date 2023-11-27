package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddBookmarkServlet")
public class AddBookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// JSONデータを生成
		Map<String, Object> json = new HashMap<>();
		json.put("id", 1);
		json.put("name", "梅おろし豚しゃぶ弁当");
		json.put("price", 500);
		json.put("listImage", "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_list.jpg");
		json.put("detailImage", "https://netorder.hottomotto.com/pc/images/products/13154/13154_pc_detail.jpg");
		json.put("desc", "あっさりと柔らかい豚しゃぶに、みずみずしい大根おろしと上品でやわらかな酸味の紀州南高梅のペーストを合わた、蒸し暑くなる季節にぴったりの一品。<br>付け合わせには、オクラと茄子を添えました。<br>５種（ゆず・すだち・かぼす・だいだい・レモン）の柑橘果汁が爽やかなぽん酢をかけてお召し上がりください。");
		json.put("riceGroupId", 1);
		json.put("productCategoryId", 1);

		// JSONデータをリクエスト属性に設定
		request.setAttribute("json", json);
		System.out.print(json);
		 // 値を宣言
	    String text = "Hello";
	    // 値をリクエスト属性に設定
	    request.setAttribute("text", text);
		//フォワード
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/product_list.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
