package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.servlets;

import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.daohbn.DAOOfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorkerList;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/officeworkers")
public class ShowOfficeWorkersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ShowOfficeWorkersServlet#get");  // Лог для отладки

        List<OfficeWorker> listOfficeWorkers = DAOOfficeWorker.getAllList();  // Получаем список работников

        // Проверка, если список пуст
        if (listOfficeWorkers == null || listOfficeWorkers.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("List size: " + listOfficeWorkers.size());  // Лог для отладки
        }

        request.setAttribute("officeworkers", listOfficeWorkers);  // Передача данных на JSP

        String path = "/officeworkers.jsp";  // Путь к JSP
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);  // Перенаправление на JSP

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.servlets.ShowOfficeWorkersServlet#doPost");
    }
}





