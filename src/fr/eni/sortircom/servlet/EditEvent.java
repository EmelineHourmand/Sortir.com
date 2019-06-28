package fr.eni.sortircom.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet for Create new Event and modify an Event
 */
@WebServlet(name = "EditEvent")
public class EditEvent extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {



        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
//            --- Connecté ---
            if (request.getParameter("event") != null) {
                


            }
            request.getRequestDispatcher("/WEB-INF/jsp/EditEvent.jsp").forward(request, response);
        } else {
//            --- Non Connecté ---
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
