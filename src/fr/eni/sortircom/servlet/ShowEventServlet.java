package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.RegistrationManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.bo.Registration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/showEvent")
public class ShowEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EventManager em = new EventManager();
            RegistrationManager rm = new RegistrationManager();
            Event event = em.selectEvent(Long.parseLong(request.getParameter("id")));
            List<Registration> registrations = rm.selectParticipantEvent(Long.parseLong(request.getParameter("id")));
            int nbRegister = registrations.size();
            HttpSession session = request.getSession();
            Participant userLog = (Participant) session.getAttribute("user");
            boolean userIsRegister = false;
            for (Registration registration : registrations) {
                if (registration.getParticipant().getIdParticipant() == userLog.getIdParticipant()) {
                    userIsRegister = true;
                    request.setAttribute("nbRegister", registration.getIdRegistration() );
                }
            }
            request.setAttribute("event", event);
            request.setAttribute("userLog", userLog);
            request.setAttribute("nbRegister", nbRegister);
            request.setAttribute("userIsRegister", userIsRegister );
            request.setAttribute("registrations", registrations);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/showEvent.jsp");
            rd.forward(request, response);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }
}
