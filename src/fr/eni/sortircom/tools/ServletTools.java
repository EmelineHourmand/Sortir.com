package fr.eni.sortircom.tools;

import fr.eni.sortircom.bo.Participant;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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









// ------------------- ServletTools propre à l'appli : -------------------

    public static void fakeSessionUser(HttpServletRequest request){
        Participant user = new Participant("Titi", "Toto", "Tata", "0123456789", "toto.tata@toto.fr", false, "134", true);
        request.getSession().setAttribute("user", user);
    }

}
