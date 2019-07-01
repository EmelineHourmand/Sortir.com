package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.ParticipantManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteProfile")
public class DeleteProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ParticipantManager pm = new ParticipantManager();
            HttpSession session = request.getSession();
            Participant participant = (Participant) session.getAttribute("user");
            pm.removeParticipant(participant.getIdParticipant());
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/index");
        } catch (BLLException e) {
            e.getStackTrace();
        }
    }
}
