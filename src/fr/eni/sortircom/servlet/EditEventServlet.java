package fr.eni.sortircom.servlet;


import fr.eni.sortircom.bll.EventManager;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
            if( request.getParameter("save")!= null ||
                request.getParameter("publish")!= null) {
                // ----- click sur un bouton enregistré ou publier -----

                // Si Event existe modifier sinon nouveau !

                Event ev = null;
                try {

                    EventManager em = new EventManager();

                    Place place = null;
                    City city = null;
                    State state = null;
                    Site site = null;

                    StateManager stm = new StateManager();
                    SiteManager sm = new SiteManager();
//                    List<State> states = stm.selectAllState();
//                    List<Site> sites = sm.selectAllSite();

                    if (request.getParameter("idEvent") != null && request.getParameter("idEvent").length() != 0) {
                        // ----- id event OK = mode édition = Récupération de l'event origine -----
                        ev = em.selectEvent(Long.parseLong(request.getParameter("idEvent")));
                        // Extraction des sous objet de l'évent pour modification
                        place = ev.getPlace();
                        city = place.getCity();
                        state = ev.getState();
                        site = ev.getSite();
                    }

                    // Modification City
                    if (city == null) {
                        city = new City();
                    }
                    city.setName(request.getParameter("cityName"));
                    city.setPostalCode(request.getParameter("postalCode"));

                    // Modification Place
                    if (place == null) {
                        place = new Place();
                    }
                    place.setCity(city);
                    place.setName(request.getParameter("placeName"));
                    place.setStreet(request.getParameter("street"));
//                    place.setLatitude(request.getParameter("latitude"));
//                    place.setLongitude(request.getParameter("longitude"));

                    // Modification Site
                    site = sm.selectSite(Long.parseLong(request.getParameter("site")));

                    // Modification State
                    // 1 Créée 2 Ouverte 3 Clôturée 4 Activité en cours 5 Passée 6 Annulée // Pas beau
                    state = stm.selectState((long)1);
                    if(request.getParameter("publish")!= null){
                        state = stm.selectState((long)2);
                    }

                    // Modification Event
                    if (ev == null) {
                        ev = new Event();
                        ev.setOrganizer((Participant) request.getSession().getAttribute("user"));
                    }
                    ev.setName(request.getParameter("eventName"));
                    LocalDateTime eventBeginning = LocalDateTime.parse(request.getParameter("dateTimeEvent"));
                    ev.setEventBeginning(eventBeginning);
                    ev.setEventEnd(eventBeginning.plusMinutes(Integer.parseInt(request.getParameter("durationMinute"))));
                    ev.setRegistrationLimit(LocalDateTime.parse(request.getParameter("dateLimit")+ "T00:00"));
                    ev.setMaxRegistration(Integer.parseInt(request.getParameter("nbParticipant")));
                    ev.setDescription(request.getParameter("description"));
                    ev.setPlace(place);
                    ev.setSite(site);
                    ev.setState(state);


                    if (request.getParameter("idEvent") != null && request.getParameter("idEvent").length() != 0) {
                        em.updateEvent(ev);
                    }else{
                        em.insertEvent(ev);
                    }

                    response.sendRedirect(request.getContextPath() + "/showEvent?id=" + ev.getIdEvent());

                } catch (DateTimeParseException | NumberFormatException | BLLException e) {
                    // ----- Event non retrouvé -----
                    // ----- mauvais format idEvent -----
                    // ----- mauvais format input LocalDateTime -----
                    // ----- mauvais format input Integer -----

                    e.printStackTrace();
                    if (ev != null && ev.getIdEvent() != null) {
                        response.sendRedirect(request.getContextPath() + "/editEvent?id=" + ev.getIdEvent() + "&errEvent=KO");
                    }else{
                        response.sendRedirect(request.getContextPath() + "/editEvent?errEvent=KO");
                    }

                }


//                response.sendRedirect(request.getContextPath() + "/showEvent?id=" + ev.getIdEvent());

            }else if(   request.getParameter("delete")!= null
                     && request.getParameter("idEvent") != null && request.getParameter("idEvent").length() != 0){
                // ----- click sur un bouton delete -----
                try {
                    EventManager em = new EventManager();
                    Event ev = em.selectEvent(Long.parseLong(request.getParameter("idEvent")));
                    // TODO suppression sortie
                    // En faite non ne passera jamais là car lien vers page cancelEvent

                } catch (NumberFormatException | BLLException e) {
                    e.printStackTrace();
                }

            }else{
                // ----- click sur un bouton KO -----
                response.sendRedirect(request.getContextPath() + "/editEvent?id=" + request.getParameter("idEvent") + "&errEvent=KO");

            }

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

                    // Récupérer liste des sites
                    SiteManager sm = new SiteManager();
                    List<Site> sites =  sm.selectAllSite();
                    request.setAttribute("sites", sites);

                    request.getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp").forward(request, response);
                }catch(NumberFormatException | BLLException e){
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "La sortie demandée n'existe pas.");
                }

            }else {
                // --- New Event ----
                // Récupérer liste des sites


                try {
                    SiteManager sm = new SiteManager();
                    List<Site> sites = sm.selectAllSite();
                    request.setAttribute("sites", sites);
                } catch (BLLException e) {
                    e.printStackTrace();
                }


                request.getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp").forward(request, response);
            }
            //this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp")

        } else {
//            --- Non Connecté ---
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
