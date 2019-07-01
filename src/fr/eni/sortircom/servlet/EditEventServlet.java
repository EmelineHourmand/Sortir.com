package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.tools.ServletTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Servlet for Create new Event and modify an Event
 */
@WebServlet(name = "EditEventServlet",
        urlPatterns = {"/event"})
public class EditEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {



        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
//            --- Connecté ---
            if (request.getParameter("id") != null) {
                // --- Edit Event ----
                long idEvent = 0;
                try{
                    idEvent = Long.parseLong(request.getParameter("id"));

                    // Récupérer l'event correspondant à l'id
                    EventManager em = new EventManager();
                    Event event = em.selectEvent(idEvent);
                    request.setAttribute("event", event);

                    request.getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp").forward(request, response);
                }catch(NumberFormatException | BLLException e){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "La sortie demandée n'existe pas.");
                }

            }else {
                // --- New Event ----
                request.getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp").forward(request, response);
            }
            //this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp")

        } else {
//            --- Non Connecté ---
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
