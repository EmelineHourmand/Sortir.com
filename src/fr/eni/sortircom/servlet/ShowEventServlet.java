package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.RegistrationManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
            int nbRegister = rm.selectParticipantEvent(Long.parseLong(request.getParameter("id")));
            request.setAttribute("event", event);
            request.setAttribute("nbRegister", nbRegister);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/showEvent.jsp");
            rd.forward(request, response);
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }

}
