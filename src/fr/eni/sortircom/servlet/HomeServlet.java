package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.bll.SiteManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "HomeServlet", urlPatterns = {"/index"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    //private static final long serialVersionUID = 1L;


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        SiteManager s = new SiteManager();
        List<Site> listeSite = null;
        try {
            listeSite = s.selectAllSite();
        } catch (BLLException e) {
            e.printStackTrace();
        }
        System.out.println(listeSite);
        request.setAttribute("listeSite", listeSite);


        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
