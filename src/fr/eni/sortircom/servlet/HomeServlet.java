package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bo.Participant;

import javax.servlet.RequestDispatcher;
import java.io.IOException;



@javax.servlet.annotation.WebServlet(name = "/home")
public class HomeServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    Participant p = new Participant();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/home");
        rd.forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
