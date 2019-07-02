package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.*;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ServletInjectionData",
        urlPatterns = {"/ServletInjectionData"})
public class ServletInjectionData extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            StateManager stm = new StateManager();
            SiteManager sm = new SiteManager();
            PlaceManager plm = new PlaceManager();
            CityManager cm = new CityManager();

            Event event = new Event(
                    "Un event en anglais",
                    LocalDateTime.now().plusDays(3),                // Commence dans 3 jours
                    LocalDateTime.now().plusDays(3).plusHours(5),   // Fini dans 3 jour + 5h
                    LocalDateTime.now().plusDays(2),                // Fin d'inscription dans 2 jours
                    5,
                    "La description est celle-ci",
                    new Place(
                            "La bicoque",
                            "2 rue de la santé",
                            48.0964f,
                            -1.63467f,
                            cm.selectCity((long)1)),
                    stm.selectState((long)3),
                    sm.selectSite((long)1),
                    (Participant) request.getSession().getAttribute("user")
            );

            EventManager em = new EventManager();
            em.insertEvent(event);

            response.getWriter().append("Les données ont été ajouté à la base de donnée.").flush();
        }catch(BLLException e){
            e.printStackTrace();
            response.getWriter().append("Erreur " + e.errorsToString()).flush();
        }
    }
}
