package fr.eni.sortircom.tools;

import fr.eni.sortircom.bll.EventManager;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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









// ------------------- ServletTools propre à l'appli : -------------------

    /**Fake user injecté dans la session pour test des page avant login fonctionnel*/
    public static void fakeSessionUser(HttpServletRequest request){
        Participant user = new Participant("Jojo", "Toto", "Tata", "0123456789", "toto.tata@toto.fr", false, "134", true);
        request.getSession().setAttribute("user", user);
    }

    /**Insertion d'un Event si il n'existe pas déjà*/
    public static long insertEvent(){

        Event e = new Event("name",
                            LocalDateTime.now().plusDays(5), // Date debut evenement
                            LocalDateTime.now().plusDays(6), // date fin evenement
                            LocalDateTime.now().plusDays(2), // date fin inscription
                            3,                               // nb participant max
                            "C'est vraiment une sortie de ouf !",
                            // TODO le manager doit l'inséré en base si il n'existe pas
                            new Place("AVEC", "rue de la soif", 48.096439f, -1.634671f),
                            new State("En cours"),
                            new Site("ENI-Rennes"),
                            // TODO recup user depuis session
                            new Participant("Jojo", "Toto", "Tata", "0123456789", "toto.tata@toto.fr", false, "134", true));

        EventManager em = new EventManager();
        try {
            em.insertEvent(e);
        } catch (BLLException ex) {
            ex.printStackTrace();
        }
        return e.getIdEvent();

    }












}
