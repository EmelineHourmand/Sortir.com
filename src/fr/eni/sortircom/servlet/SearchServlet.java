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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {

//            EventManager em = new EventManager();
//            try {
//                List<Event> listeEvent = em.selectAllEvent();
//            } catch (BLLException e) {
//                e.printStackTrace();
//            }

            // Récupération du site selectionné
            String siteRecherche = request.getParameter("site");
            System.out.println("site recherché: " + siteRecherche);

            // Liste filtrée par checkboxes
//            List<Event> listeEventAffichage = em.

            // Récupération champ de recherche avec nom de sortie
            String recherche = request.getParameter("q");
            System.out.println(recherche);

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
