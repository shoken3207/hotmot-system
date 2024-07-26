package test;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.CartDetailDao;
import models.CartDetailBean;
import servlet.AddCartDetailServlet;

public class AddCartDetailServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock0
    +-.032*1/7
    private HttpServletResponse response;

    @Mock
    private CartDetailDao cartDetailDao;

    @InjectMocks
    private AddCartDetailServlet addCartDetailServlet;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost_Success() throws IOException, SQLException {
        // リクエストデータの準備
        String jsonRequest = "[{\"cartId\": 1, \"productId\": 1, \"riceId\": 1, \"quantity\": 2}]";
        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));

        when(request.getReader()).thenReturn(reader);
        when(cartDetailDao.getCartDetail(1, 1, 1)).thenReturn(null);
        doNothing().when(cartDetailDao).createCartDetail(1, 1, 1, 2);

        // レスポンスデータの準備
        PrintWriter writer = new PrintWriter(System.out);
        when(response.getWriter()).thenReturn(writer);

        // サーブレットの実行
        addCartDetailServlet.doPost(request, response);

        // 検証
        verify(cartDetailDao, times(1)).createCartDetail(1, 1, 1, 2);
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
    }

    @Test
    public void testDoPost_UpdateCartDetail() throws IOException, SQLException {
        // リクエストデータの準備
        String jsonRequest = "[{\"cartId\": 1, \"productId\": 1, \"riceId\": 1, \"quantity\": 2}]";
        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));

        when(request.getReader()).thenReturn(reader);
        CartDetailBean existingCartDetail = new CartDetailBean(1, 1, 1, 1, 3);
        when(cartDetailDao.getCartDetail(1, 1, 1)).thenReturn(existingCartDetail);
        doNothing().when(cartDetailDao).updateCartDetail(1, 5);

        // レスポンスデータの準備
        PrintWriter writer = new PrintWriter(System.out);
        when(response.getWriter()).thenReturn(writer);

        // サーブレットの実行
        addCartDetailServlet.doPost(request, response);

        // 検証
        verify(cartDetailDao, times(1)).updateCartDetail(1, 5);
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
    }

    @Test
    public void testDoPost_InvalidRequest() throws IOException {
        // 無効なリクエストデータの準備
        String jsonRequest = "invalid json";
        BufferedReader reader = new BufferedReader(new StringReader(jsonRequest));

        when(request.getReader()).thenReturn(reader);

        // レスポンスデータの準備
        PrintWriter writer = new PrintWriter(System.out);
        when(response.getWriter()).thenReturn(writer);

        // サーブレットの実行
        addCartDetailServlet.doPost(request, response);

        // 検証
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
    }
}
