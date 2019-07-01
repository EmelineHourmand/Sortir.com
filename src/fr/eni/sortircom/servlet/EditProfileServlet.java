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

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {

    private Participant participant;

    /**
     * Vue de l'édition du profile de l'utilisateur connecter
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParticipantManager pm = new ParticipantManager();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("lastUrl", "/editProfile");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        } else {
            participant = (Participant) session.getAttribute("user");
            request.setAttribute("participant", this.participant);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/editProfile.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Recupère les informations du profile pour les mettre a jours en BDD
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ParticipantManager pm = new ParticipantManager();
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                session.setAttribute("lastUrl", "/editProfile");
                RequestDispatcher rd = request.getRequestDispatcher("/login");
                rd.forward(request, response);
            } else {

                String password = participant.getPassword();

                participant.setUsername(request.getParameter("userName"));
                participant.setUsername(request.getParameter("userName"));
                participant.setFirstname(request.getParameter("firstName"));
                participant.setLastname(request.getParameter("lastName"));
                participant.setMail(request.getParameter("email"));
                participant.setPhone(request.getParameter("phoneNumber"));

                String newPassword = request.getParameter("password");
                if (newPassword == "") {
                    participant.setPassword(password);
                } else {
                    participant.setPassword(newPassword);
                }
                pm.updateParticpant(participant);
                request.setAttribute("participant", participant);
                response.sendRedirect(request.getContextPath() + "/profile");
            }
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }
}