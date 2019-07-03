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

@WebServlet("/profile")
public class ShowOtherProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("lastUrl", "/profile");
            RequestDispatcher rd = request.getRequestDispatcher("/login");
            rd.forward(request, response);
        } else {
            try {
                ParticipantManager pm = new ParticipantManager();
                Participant participant = pm.selectParticipant(Long.parseLong(request.getParameter("id")));
                request.setAttribute("userLog", session.getAttribute("user"));
                request.setAttribute("participant", participant);
                request.getRequestDispatcher("/WEB-INF/jsp/showOtherProfile.jsp").forward(request, response);
            } catch (BLLException e) {
                e.printStackTrace();
            }

        }
    }
}
