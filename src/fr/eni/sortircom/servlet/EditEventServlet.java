package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.ParticipantManager;
import fr.eni.sortircom.bll.SiteManager;
import fr.eni.sortircom.bll.StateManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


/**
 * Servlet for Create new Event and modify an Event
 */
@WebServlet(name = "EditEventServlet",
        urlPatterns = {"/editEvent"})
public class EditEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if (request.getSession().getAttribute("user") != null) {
            // ----- Connecté -----
            if(request.getParameter("idEvent") != null){
                // ----- id event OK -----
                EventManager em = new EventManager();
                StateManager stm = new StateManager();
                SiteManager sm = new SiteManager();

                try {
                    List<State> states = stm.selectAllState();
                    List<Site> sites = sm.selectAllSite();
                    Event ev = em.selectEvent(Long.parseLong(request.getParameter("idEvent")));


                    // Pour la place C'est pas top, devrait créer des nouvelle place pour les garders en mémoire (lien oneToMany)
                    Place place = ev.getPlace();
                    City city = place.getCity();
                    State state = ev.getState();
                    Site site = ev.getSite();
/*
                    city.setName();
                    city.setPostalCode();

                    place.setCity(city);
                    place.setName();
                    place.setStreet();
                    place.setLatitude();
                    place.setLongitude();



                    ev.setName();
                    ev.setEventBeginning();
                    ev.setEventEnd();
                    ev.setRegistrationLimit();
                    ev.setMaxRegistration();
                    ev.setDescription();


                    ev.setPlace();

*/


                } catch (NumberFormatException | BLLException e) {
                    // ----- Event non retrouvé ou mauvais format -----

//                    e.printStackTrace();



                }


            }else{
                // ----- id event KO -----

            }


//"eventName"
//"dateTimeEvent"
//"dateLimit"
//"nbParticipant"
//"durationMinute"
//"description"
//"site"
//"placeName"
//"street"
//"postalCode"
//"latitude"
//"longitude"
//            dateLimit
//            2019-07-02
//            dateTimeEvent
//            2019-07-03T13:20:41
//            description
//            Sortie pub !
//                    durationMinute
//            300
//            eventName
//            Pour un pub !
//                    latitude
//            48.0964
//            longitude
//                    -1.63467
//            nbParticipant
//            5
//            placeName
//                    AVEC
//            postalCode
//            35000
//            save
//                    site
//            CHARTRES DE BRETAGNE
//                    street
//            1 Rue du Breil




        } else {
            // ----- Non connecté -----
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*   /event         -> créer un nouvel event
         *   /event?id=x    -> modifier l'event x
         */

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

                    Duration duration = Duration.between(event.getEventBeginning(),event.getEventEnd());
                    long durationMinute = duration.toMinutes();
                    request.setAttribute("durationMinute", durationMinute);

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
