package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.*;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/ServletTEST")
public class ServletTEST extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            SiteManager sma = new SiteManager();
            Site site = sma.selectSite((long) 1);

            Participant participant = new Participant("Jojoj", "Toto", "Tata", "0123456789", "toti.tata@toto.fr", false, "134", true, site);
            ParticipantManager participantManager = new ParticipantManager();
            participantManager.insertParticipant(participant);

            City city =  new City("Rennes", "35000");
            CityManager cm = new CityManager();
            cm.insertCity(city);

            Place place =  new Place("Place de Bretagne", "14 rue de Bretagne", -3,67, city);
            PlaceManager pm = new PlaceManager();
            pm.insertPlace(place);

            State state = new State("En cours");
            StateManager sm = new StateManager();
            sm.insertState(state);

            LocalDateTime dateDebut = LocalDateTime.of(2019, 07, 15, 15, 00);
            LocalDateTime dateFinInscription = LocalDateTime.of(2019, 07, 14, 00, 00);
            Event event = new Event("Sortie au bar", LocalDateTime.now(),dateDebut ,dateFinInscription , 5, "Sortie au bar de place de bretagne", place, state, site, participant);
            System.out.println(event);
            EventManager em = new EventManager();
            em.insertEvent(event);

        } catch (BLLException e) {
            e.printStackTrace();
        }

    }
}
