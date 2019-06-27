package fr.eni.sortircom.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public abstract class ServletTools {

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
                if(cookie != null && name.equals(cookie.getName()){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


}
