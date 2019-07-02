package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.SiteManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Site;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {


            // Récupération du site selectionné
            String siteRecherche = request.getParameter("site");
            System.out.println("site recherché: " + siteRecherche);



            // Récupération champ de recherche avec nom de sortie
            String recherche = request.getParameter("q");
            System.out.println("recherche: " + recherche);

            // Récupération date début
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String beginningHTML = request.getParameter("beginning");
            LocalDate beginning = LocalDate.parse(beginningHTML, formatter);
            System.out.println("début: " + beginning);

            // Récupération date fin
            String endHTML = request.getParameter("end");
            LocalDate end = LocalDate.parse(endHTML, formatter);
            System.out.println("fin: " + end);

            LocalDate endValue = end;
            if (end.isBefore(beginning)){
                endValue = beginning;
                request.setAttribute("endValue", endValue);

                request.getRequestDispatcher("/index");
            }


            //System.out.println(end.getClass().getName());

            // Liste filtrée par checkboxes
            //List<Event> listeEventAffichage = em.

//            List<Event> listeEventAffichageEtRecherche = new ArrayList<>();
//            if ((recherche != null) && (!recherche.isEmpty())) {
//                for (Event event : listeEventAffichage) {
//                    if (event.getName().toLowerCase().contains(recherche.toLowerCase())){
//                        listeEventAffichageEtRecherche.add(event);
//                    }
//                }
//            } else {
//                // si champ de recherche vide
//                listeEventAffichageEtRecherche = listeEventAffichage;
//            }



            request.getRequestDispatcher("/index").forward(request, response);

        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("navType", "index");

    }
}
