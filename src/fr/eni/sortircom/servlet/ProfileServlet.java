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

/**
 * @author Emeline Hourmand
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private Participant participant;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    /**
     * Récupère les informations de l'utilisateur connecter
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ParticipantManager pm = new ParticipantManager();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("lastUrl", "/profile");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        } else {
            participant = (Participant) session.getAttribute("user");
            request.setAttribute("participant", this.participant);
            request.getRequestDispatcher("/WEB-INF/jsp/showProfile.jsp").forward(request, response);
        }
    }
}
