package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.RegistrationManager;
import fr.eni.sortircom.bll.SiteManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.bo.Registration;
import fr.eni.sortircom.bo.Site;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {

            Date aujourdhui = new Date();
            DateFormat shortDateFormat = new SimpleDateFormat("dd/MM/yy");
            String today = shortDateFormat.format(aujourdhui);
            request.setAttribute("today", today);


            // Récupération date début
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String beginningHTML = request.getParameter("beginning") + "T00:00:00";
            LocalDateTime beginning = null;
            try {
                beginning = LocalDateTime.parse(beginningHTML, formatter);
            } catch (DateTimeException e) {
                e.printStackTrace();
            }
            System.out.println("début: " + beginning);

            // Récupération date fin
            String endHTML = request.getParameter("end") + "T00:00:00";
            LocalDateTime end = null;
            try {
                end = LocalDateTime.parse(endHTML, formatter);
            } catch (DateTimeException e) {
                e.printStackTrace();
            }

            System.out.println("fin: " + end);

//
            //Test dates renseignées
            if (beginning == null) {
                beginning = LocalDateTime.now();
            }

            if (end == null) {
                end = LocalDateTime.now();
            }

            //
            if (end.isBefore(beginning)) {
                end = beginning.plusDays(1);
            }


            //Création liste Sites
            SiteManager s = new SiteManager();
            List<Site> listeSite = null;
            try {
                listeSite = s.selectAllSite();
            } catch (BLLException e) {
                //e.printStackTrace();
            }

            //Création liste Events
            EventManager ev = new EventManager();
            List<Event> filtredListeEvent = null;//new ArrayList<Event>();
            List<Event> eventList = null;
            try {
                eventList = ev.selectAllEvent();
            } catch (BLLException e) {
                e.printStackTrace();
            }
            // Récupération champ de recherche avec nom de sortie
            String recherche = request.getParameter("q");
            System.out.println("recherche: " + recherche);
            request.setAttribute("q", recherche);

            // Récupération du site selectionné
            String siteRecherche = request.getParameter("site");
            System.out.println("site recherché: " + siteRecherche);
            request.setAttribute("site", siteRecherche);


            filtredListeEvent = eventList.stream().filter((el)->el.getSite().getName().contains(siteRecherche)).collect(Collectors.toList());
            filtredListeEvent = filtredListeEvent.stream().filter((el)->el.getName().toLowerCase().contains((recherche.toLowerCase()))).collect(Collectors.toList());

            LocalDateTime finalEnd = end;
            LocalDateTime finalBeginning = beginning;
            filtredListeEvent = filtredListeEvent.stream().filter((el)->el.getEventEnd().isBefore(finalEnd) && el.getEventBeginning().isAfter(finalBeginning)).collect(Collectors.toList());

            System.out.println("début: " + beginning);
            System.out.println("fin: " + end);
            System.out.println(filtredListeEvent);
            String isOrganisator = request.getParameter("isOrganisator");
            Participant user = (Participant)(request.getSession().getAttribute("user"));
            filtredListeEvent = filtredListeEvent.stream().filter((el)->el.getOrganizer().getIdParticipant() == user.getIdParticipant()).collect(Collectors.toList());

            System.out.println(isOrganisator);

            RegistrationManager rm = new RegistrationManager();
            List<Registration> registrationList = rm.selectParticipantEvent(user.getIdParticipant());
            String isInscrit = request.getParameter("isInscrit");

            System.out.println(isInscrit);
            String isNotInscrit = request.getParameter("isNotInscrit");
            System.out.println(isNotInscrit);
            String isSortiePassee = request.getParameter("isSortiePassee");
            System.out.println(isSortiePassee);


            request.setAttribute("beginning", beginning);
            request.setAttribute("end", end);
            request.setAttribute("listeEvent", filtredListeEvent);
            request.setAttribute("listeSite", listeSite);
            request.setAttribute("site", siteRecherche);


            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);


        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("navType", "index");

    }
}
