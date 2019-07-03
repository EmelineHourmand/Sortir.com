package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.RegistrationManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.bo.Registration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/withdrawEvent")
public class WithdrawEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            RegistrationManager rm = new RegistrationManager();
            rm.deleteRegistration(Long.parseLong(request.getParameter("id")));
            response.sendRedirect(request.getContextPath() + "/index");
    }
}
