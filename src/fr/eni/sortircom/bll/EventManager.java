package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.EventDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Emeline Hourmand
 */
public class EventManager {

    private static EventDAO eventDAO;

    /**
     * Constructor (Singleton)
     * @throws BLLException
     */
    public EventManager() {
        eventDAO = DAOFactory.getEventDAO();
    }

    public void insertEvent(Event event) throws BLLException {
        BLLException bllException = new BLLException();
        checkEvent(event, bllException);
        if(!bllException.hasErrors()) {
            try {
                eventDAO.insert(event);
            } catch (DALException e) {
                bllException.addSuppressed(e);
                System.out.println(bllException.errorsToString());
                e.printStackTrace();
                throw bllException;
            }
        } else {
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
    }

    public List<Event> selectAllEvent() throws BLLException {
        try {
            return eventDAO.selectAll();
        } catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

//    public List<Event> selectByRegistredUser(Participant participant, boolean isRegistred) throws BLLException {
//        try {
//            return eventDAO.selectByRegistredUser(participant, isRegistred);
//        } catch (DALException e) {
//            BLLException bllException = new BLLException();
//            bllException.addSuppressed(e);
//            throw bllException;
//        }
//    }

    public Event selectEvent(Long id) throws BLLException {
        try {
            return eventDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    public void updateEvent(Event event) throws  BLLException {
        BLLException bllException = new BLLException();
        checkEvent(event, bllException);
        if(!bllException.hasErrors()) {
            try {
                eventDAO.update(event);
            } catch (DALException e) {
                bllException.addSuppressed(e);
                System.out.println(bllException.errorsToString());
                throw bllException;
            }
        } else {
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
    }

    public void removeEvent(Long id) throws BLLException {
        try {
            eventDAO.delete(id);
        } catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Cermet de verifier la validité des informations de l'event
     *
     * @throws BLLException
     */
    public static void checkEvent(Event event, BLLException bllException) throws BLLException {
        if (event == null) {
            bllException.addErreur(CodesErreursBLL.EVENT_NULL_ERROR);
        } else {
            // NOM
            if (event.getName() == null || event.getName().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_EVENT_NAME_EMPTY_ERROR);
            } else if (event.getName().length() > 250) {
                bllException.addErreur(CodesErreursBLL.RULE_EVENT_NAME_FORMAT_INVALID_ERROR);
            }

            // DESCRIPTION
            if (event.getDescription().length() > 65535) { // 65535 est la longeur max d'un type TEXT en MySQL
                bllException.addErreur(CodesErreursBLL.RULE_EVENT_DESCRIPTION_FORMAT_INVALID_ERROR);
            }

            // DATE DE DEBUT
            if (event.getEventBeginning() == null || event.getEventBeginning().toString().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_EVENT_DATE_BEGINNING_EMPTY_ERROR);
            } else if(event.getEventBeginning().isAfter(LocalDateTime.now())) {
                // TODO test car lève une exception pas normale
                //bllException.addErreur(CodesErreursBLL.RULE_EVENT_DATE_BEGINNING_OLD_ERROR);
            }

            // DATE DE FIN
            if (event.getEventEnd() == null || event.getEventEnd().toString().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_EVTN_DATE_END_EMPTY_ERROR);
            } else if (event.getEventEnd().isBefore(LocalDateTime.now())) {
                bllException.addErreur(CodesErreursBLL.RULE_EVENT_DATE_END_OLD_ERROR);
            }

            // DATE DE CLOTURE DES INSCRIPTIONS
            if (event.getRegistrationLimit() != null) {
                if (event.getRegistrationLimit().isBefore(LocalDateTime.now())) {
                    bllException.addErreur(CodesErreursBLL.RULE_EVENT_DATE_REGISTRATION_OLD_ERROR);
                }
            }
        }
    }

//    public List<Event> fetchByAffichageEvent(AffichageEvent ae, long id_participant){
//        return eventDAO.readFromAffichageEvent(ae, id_participant);
//    }

}