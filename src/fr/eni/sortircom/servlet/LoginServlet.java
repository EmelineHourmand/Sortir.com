package fr.eni.sortircom.servlet;

import fr.eni.sortircom.bll.ParticipantManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.tools.ServletTools;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet",
            urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remembreMe = request.getParameter("remembreMe");

        ParticipantManager pm = new ParticipantManager();
        Participant user = null;


        try {
            user = pm.login(email, password);
            // Login mdp +++ OK +++
            // Ajout du userEmail en cookies si remembreMe est coché
            if (remembreMe != null){
                // Ajout cookie
                Cookie userCookie = new Cookie("userEmail", ""+user.getMail());
                userCookie.setMaxAge(1054456406);
                response.addCookie(userCookie);

            }else{
                // Invalide la cookie
                Cookie userCookie = new Cookie("userEmail", "");
                userCookie.setMaxAge(0);
                response.addCookie(userCookie);
            }

            // Ajout du user connecté dans la session
//            request.getSession().setAttribute("userId", user.getIdParticipant());
            request.getSession().setAttribute("user", user);

        } catch (BLLException e) {
            // Login mdp --- KO ---
            System.err.println(e.getMessage());
            // Retourne à la page de connection
            request.setAttribute("errLogin", "");
            request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
        }


//        Redirection tempo pour test
        response.sendRedirect(request.getContextPath() + "/index");
//        response.getWriter().append("Post - Served at: ").append(request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        if(request.getSession().getAttribute("user") != null){
            // Utilisateur déjà connecté (user dans session)
            response.sendRedirect(request.getContextPath() + "/index");
        }else{
            // Utilisateur non connecté
            String userEmail = ServletTools.getCookieValue(request,"userEmail");
            if(userEmail != null){
                // Cookies userEmail existe chargé les données email & remembreMe
                request.setAttribute("email", userEmail);
                request.setAttribute("remembreMe", "on");
            }
            request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
        }

        //response.getWriter().append("Served at: ").append(request.getContextPath());

    }
}
