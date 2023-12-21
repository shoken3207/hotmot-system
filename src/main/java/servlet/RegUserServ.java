package servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class RegUserServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Get data from the form
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // Create an instance of your DAO class
        UserDao userDao = new UserDao(); // Replace YourDAO with your actual DAO class

        try {
            // Call the method in your DAO to save data to the database
            boolean success = userDao.saveData(email, name, pass);

            if (success) {
                out.println("<h2>Data saved successfully!</h2>");
            } else {
                out.println("<h2>Failed to save data.</h2>");
            }
        } catch (Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}