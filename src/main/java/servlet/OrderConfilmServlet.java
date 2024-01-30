package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlets.DefaultServlet.SortManager.Order;

import dao.CartDao;
import dao.OrderDao;
import models.CartBean;
import models.OrderBean;

@WebServlet("/OrderConfilm")
public class OrderConfilmServlet extends HttpServlet {
	private static final long serialVersionUID=1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<CartDetail> cartDetail = getCartDetail(request);
        
        Order order = processOrder(cartDetail);
        
        clearCart(request);
        
        showOrderConfilmationPage(request,response,order);
    	}
    
    private List(CartDeatil) getCartDetail(HttpServletR3cuest())
    	List<CartDetail>  
    		
    	return new (cartDetail,ce)
    }
    }
}