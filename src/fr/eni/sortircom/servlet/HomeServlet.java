package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.StateManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.bll.SiteManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.text.DateFormat;
import java.util.Locale;


@WebServlet(name = "HomeServlet", urlPatterns = {"/index"})
public class HomeServlet extends javax.servlet.http.HttpServlet {

    //private static final long serialVersionUID = 1L;


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
//          ... code ....
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {



            //Création liste Sites
            SiteManager s = new SiteManager();
            List<Site> listeSite = null;
            try {
                listeSite = s.selectAllSite();
            } catch (BLLException e) {
                e.printStackTrace();
            }
            //System.out.println(listeSite);
            for (Site site : listeSite){
                System.out.println(site.getName());
            }

            request.setAttribute("listeSite", listeSite);


            //Création liste Events

            EventManager ev = new EventManager();
            List<Event> listeEvent = null;
            try {
                listeEvent = ev.selectAllEvent();
            } catch (BLLException e) {
                e.printStackTrace();
            }
            System.out.println(listeEvent);
            request.setAttribute("listeEvent", listeEvent);

            Date aujourdhui = new Date();
            DateFormat shortDateFormat = new SimpleDateFormat("dd/MM/yy");
            String today = shortDateFormat.format(aujourdhui);
            request.setAttribute("today", today);

            request.setAttribute("navType", "index");

            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
