package cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.servlets;

import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.daohbn.DAOOfficeWorker;
import cs.khpi.edu.stu.webappstulab21.webappssyohbnlab21.webappssyohbn.enums.Messages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/officeworkers/delete")
public class DeleteOfficeWorkerServlet extends HttpServlet {
        //DDE 20240412
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("DeleteOfficeWorkerServlet#doGet");
            Long idDel = Long.parseLong(request.getParameter("id_worker"));
            String deleteRes = DAOOfficeWorker.delete(idDel);
            if (!deleteRes.equals(Messages.OK_DEL_MSG.getText())) {
                Messages errorMSG = Messages.getMessageByText(deleteRes);
                String path = request.getContextPath() + "/officeworkers?errorString="+errorMSG.name();
//            System.out.println(path);
                response.sendRedirect(path);
            } else {
                //back to listOfficeWorkers
                String path = request.getContextPath() + "/officeworkers";
                response.sendRedirect(path);
            }
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("DeleteOfficeWorkerServlet#doPost");
            doGet(request, response);
        }


    }
