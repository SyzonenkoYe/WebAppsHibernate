package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Syzonenko Yelyzaveta Oleksandrivna
 * @group KN-222b
 */
@WebServlet("/login")
public class CheckLoginServlet extends HttpServlet {
    private static final String USERNAME = "superuser";
    private static final String PASSWORD = "D123";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (!username.isEmpty()) {
            //ok
            if(USERNAME.equals(username)&&PASSWORD.equals(password)) {
                response.sendRedirect("start.jsp");
            }
            else{
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("SYOLogin.jsp").forward(request, response);
            }

        }

    }
}

