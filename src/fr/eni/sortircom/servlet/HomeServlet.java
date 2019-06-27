package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bo.Participant;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;



@WebServlet(name = "HomeServlet", urlPatterns = {"/index"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    //private static final long serialVersionUID = 1L;
    Participant p = new Participant();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {




        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }
}
