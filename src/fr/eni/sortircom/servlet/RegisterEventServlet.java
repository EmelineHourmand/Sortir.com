package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.ParticipantManager;
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

@WebServlet("/registerEvent")
public class RegisterEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            EventManager em = new EventManager();
            RegistrationManager rm = new RegistrationManager();
            Participant participant = (Participant) session.getAttribute("user");
            Event event = em.selectEvent(Long.parseLong(request.getParameter("id")));

            Registration registration = new Registration(LocalDateTime.now(), participant, event);
            rm.insertRegistration(registration);

            response.sendRedirect(request.getContextPath() + "/showEvent?id=" + event.getIdEvent());
        } catch (BLLException e) {
            e.getStackTrace();
        }
    }
}
