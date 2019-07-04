package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.StateManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.State;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "CancelEventServlet",
        urlPatterns = {"/cancelEvent"})
public class CancelEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
//            --- Connecté ---
            if (request.getParameter("idEvent") != null
                    && request.getParameter("idEvent").length() != 0
                    && request.getParameter("delete") != null) {
                // --- Cancel Event OK ----

                Event event = null;
                try{
                    System.out.println("##########################");
                    long idEvent = Long.parseLong(request.getParameter("idEvent"));

                    // Récupérer l'event correspondant à l'id
                    EventManager em = new EventManager();
                    StateManager stm = new StateManager();
                    event = em.selectEvent(idEvent);

                    // Modification State
                    // 1 Créée 2 Ouverte 3 Clôturée 4 Activité en cours 5 Passée 6 Annulée // Pas beau
                    State state = stm.selectState((long)6);

                    // Mise à jour event
                    event.setState(state);
                    System.out.println(event.getState().getLabel());
                    em.updateEvent(event);

                    response.sendRedirect(request.getContextPath() + "/index");

                }catch(BLLException e){
                    if (event != null) {
                        response.sendRedirect(request.getContextPath() + "/cancelEvent?id=" + event.getIdEvent() + "&errEvent=1");
                    }else{
                        response.sendRedirect(request.getContextPath() + "/cancelEvent?errEvent=2");
                    }

                }catch(NumberFormatException e){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "La sortie demandée n'existe pas.");
                }


            } else {
                // ----- Pas bouton delete ou pas d'id -----
                response.sendRedirect(request.getContextPath() + "/cancelEvent?errEvent=1");
            }
        } else {
            // ----- Non connecté -----
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
//            --- Connecté ---
            if (request.getParameter("id") != null) {
                // --- Cancel Event OK ----

                try{
                    long idEvent = Long.parseLong(request.getParameter("id"));

                    // Récupérer l'event correspondant à l'id
                    EventManager em = new EventManager();
                    Event event = em.selectEvent(idEvent);
                    request.setAttribute("event", event);

                    request.getRequestDispatcher("/WEB-INF/jsp/CancelEvent.jsp").forward(request, response);


                }catch(NumberFormatException | BLLException e){
                    // --- Bad idEvent ----
                    if (request.getParameter("errEvent") != null){
                        request.getRequestDispatcher("/WEB-INF/jsp/CancelEvent.jsp").forward(request, response);
                    }else{
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "La sortie demandée n'existe pas.");
                    }
                }

            }else {
                // --- Bad idEvent ----
                if (request.getParameter("errEvent") != null){
                    request.getRequestDispatcher("/WEB-INF/jsp/CancelEvent.jsp").forward(request, response);
                }else{
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "La sortie demandée n'existe pas.");
                }
            }

        } else {
//            --- Non Connecté ---
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
