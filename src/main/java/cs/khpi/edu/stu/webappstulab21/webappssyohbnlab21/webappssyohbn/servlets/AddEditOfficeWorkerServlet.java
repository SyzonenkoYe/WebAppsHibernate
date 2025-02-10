package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.servlets;

import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.daohbn.DAOOfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.entities.OfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.Messages;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.OfficeWorkerStatus;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/officeworker")
public class AddEditOfficeWorkerServlet extends HttpServlet {

    private static final String TEXT_TITLE_ADD = "Add Office Worker";
    private static final String TEXT_TITLE_EDIT = "Edit Office Worker";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idWorker = request.getParameter("id_worker");
        Long id = (idWorker != null && !idWorker.isEmpty()) ? Long.parseLong(idWorker) : null;

        OfficeWorker officeWorker;
        String titleOfficeWorker;

        if (id == null) {
            officeWorker = new OfficeWorker("New Office Worker");
            titleOfficeWorker = TEXT_TITLE_ADD;
        } else {
            officeWorker = DAOOfficeWorker.getOfficeWorkerById(id);
            titleOfficeWorker = TEXT_TITLE_EDIT;
        }

        request.setAttribute("officeworker", officeWorker);
        request.setAttribute("titleOfficeWorker", titleOfficeWorker);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/officeworker.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id_worker");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String pname = request.getParameter("pname");
        String startWorkStr = request.getParameter("startWork");
        String endWorkStr = request.getParameter("endWork");
        String workerCod = request.getParameter("workerCod");
        String statusStr = request.getParameter("officeWorkerStatus");
        Long id = (idStr != null && !idStr.isEmpty()) ? Long.parseLong(idStr) : null;

        LocalDate startWorkDate = LocalDate.parse(startWorkStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endWorkDate = (endWorkStr != null && !endWorkStr.isEmpty()) ? LocalDate.parse(endWorkStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;

        OfficeWorkerStatus status = OfficeWorkerStatus.getOfficeWorkerStatusByName(statusStr);

        OfficeWorker newOfficeWorker = new OfficeWorker(id, surname, name, pname, startWorkDate, endWorkDate, workerCod, status);
        String errorString = null;
        String titleOfficeWorker = ""; // Initialize here

        if (id == null) { // Новая запись
            String result = DAOOfficeWorker.insert(newOfficeWorker);
            if (!result.equals(Messages.OK_INS_MSG.getText())) {
                errorString = result;
                titleOfficeWorker = TEXT_TITLE_ADD;
            } else {
                titleOfficeWorker = TEXT_TITLE_ADD; // Also set for success
            }
        } else { // Редактирование
            String result = DAOOfficeWorker.update(newOfficeWorker, id);
            if (!result.equals(Messages.OK_UPD_MSG.getText())) {
                errorString = result;
                titleOfficeWorker = TEXT_TITLE_EDIT;
            } else {
                titleOfficeWorker = TEXT_TITLE_EDIT; // Also set for success
            }
        }

        // Если ошибка, возвращаем на форму
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("officeworker", newOfficeWorker);
            request.setAttribute("titleOfficeWorker", titleOfficeWorker);
            request.getServletContext().getRequestDispatcher("/officeworker.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/officeworkers");
        }
    }
}
