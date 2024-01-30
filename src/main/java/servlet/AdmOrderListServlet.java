package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrderDetailDao;
import models.OrderDetailBean;

@WebServlet("/AdmOrderListServlet")
public class AdmOrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDetailDao dao = new OrderDetailDao();
        List<OrderDetailBean> orderDetails = dao.getAllOrderDetails();

        // 取得したデータをリクエストにセット
        request.setAttribute("orderDetails", orderDetails);

        request.getRequestDispatcher("/admOrderList.jsp").forward(request, response);
    }
}