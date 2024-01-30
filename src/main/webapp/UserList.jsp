<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,models.UserBean"%>
 
<%
ArrayList<UserBean> users = (ArrayList<UserBean>)request.getAttribute("users");
String error = (String)request.getAttribute("error");
%>
<html>
    <head>
        <title>全てのデータを表示する</title>
    </head>
    <body>
        <div style="text-align:center">
            <h2 style="text-align:center">全てのデータを表示するプログラム</h2>
            <hr style="height:3; background-color:#0000FF" />
            <br>
            <%= error %>
            <table style="margin:0 auto">
                <tr>
                    <th style="background-color:#6666FF; width:100">ID</th>
                    <th style="background-color:#6666FF; width:250">アドレス</th>
                    <th style="background-color:#6666FF; width:100">名前</th>
                </tr>
                <%
                if(users != null){
                    for(int i=0;i<users.size();i++){
                %>
                <tr>
                    <td style="text-align:center; width:100"><%= users.get(i).getId() %></td>
                    <td style="text-align:center; width:250"><%= users.get(i).getEmail() %></td>
                    <td style="text-align:center; width:100"><%= users.get(i).getName() %></td>
                </tr>
                <%
                    }
                }
                %>
            </table>
            <br>
        </div>
    </body>
 </html>
