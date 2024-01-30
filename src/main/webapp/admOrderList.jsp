<%@ page import="java.util.List" %>
<%@ page import="models.OrderDetailBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注文一覧</title>
</head>
<body>
    <h2>注文一覧</h2>
    <table border="1">
        <thead>
            <tr>
                <td style="width:4em;">注文ID</td>
                <!--<td style="width:4em;">状況</td>-->
                <td style="width:8em;">注文時間</td>
                <td style="width:18em;">商品名</td>
                <td style="width:2em;">ご飯</td>
                <td style="width:2em;">個数</td>
            </tr>
        </thead>
        <tbody>
            <% List<OrderDetailBean> orderDetails = (List<OrderDetailBean>) request.getAttribute("orderDetails"); %>
            <% for (OrderDetailBean orderDetail : orderDetails) { %>
                <tr>
                    <td><%= orderDetail.getOrderId() %></td>
                    <!--<td><%= orderDetail.getStatus() %></td>-->
                    <td><%= orderDetail.getCreatedAt() %></td>
                    <td><%= orderDetail.getProductId() %></td>
                    <td><%= orderDetail.getRiceId() %></td>
                    <td><%= orderDetail.getQuantity() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
<!-- https://hackmd.io/@ka-777/S1zlAmVj_ -->