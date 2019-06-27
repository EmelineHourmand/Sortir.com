package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.ParticipantManager;
import fr.eni.sortircom.bll.SiteManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.bo.Site;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletTEST")
public class ServletTEST extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           /* Participant participant = new Participant("Toto", "Tata", "0123456789", "toto.tata@toto.fr", false, "134", true);
            System.out.println(participant);
            ParticipantManager pm = new ParticipantManager();
            pm.insertParticipant(participant);*/

            SiteManager sm = new SiteManager();
            List<Site> siteList = sm.selectAllSite();
            System.out.println(siteList);
        } catch (BLLException e) {
            e.printStackTrace();
        }

    }
}
