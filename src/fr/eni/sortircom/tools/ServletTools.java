package fr.eni.sortircom.tools;

import fr.eni.sortircom.bll.*;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public abstract class ServletTools {


// ------------------- ServletTools universelle : -------------------


    /**
     * Recherche dans une requête un cookie par sont nom.
     * Retourne la valeur du cookie si il existe ou null si il n'existe pas.
     * @param request
     * @param name
     * @return String | null
     */
    public static String getCookieValue(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for(Cookie cookie : cookies){
                if(cookie != null && name.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Fonction à appeler dans une sevlet doGet pas encore implémenté.
     * @param request
     * @param response
     * @param link
     * @throws IOException
     */
    public static void notYetImplemented(HttpServletRequest request, HttpServletResponse response, String link) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<br>");
        out.println("Pas encore implémenté !<br>");
        out.println("<a href=" + request.getContextPath() +"/"+link + ">" +"Retour sur la page /" + link + "</a>");
        out.flush();
    }







// ------------------- ServletTools propre à l'appli : -------------------

    /**Fake user injecté dans la session pour test des page avant login fonctionnel*/
    public static void fakeSessionUser(HttpServletRequest request){
        Participant user = new Participant("Jojo", "Toto", "Tata", "0123456789", "toto.tata@toto.fr", false, "134", true);
        request.getSession().setAttribute("user", user);
    }














}
