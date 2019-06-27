package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.EventDAO;
import fr.eni.sortircom.dal.exception.DALException;

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
       // checkEvent(event, bllException);
        if(!bllException.hasErrors()) {
            try {
                DAOFactory.getEventDAO().insert(event);
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

    /**
     * Cermet de verifier la validité des informations de l'event
     *
     * @throws BLLException
     */
    public static boolean checkEvent(Event event, BLLException bllException) throws BLLException {
        return false;
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
        try {
            eventDAO.update(event);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeEvent(Long id) throws BLLException {
        try {
            eventDAO.delete(id);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

}