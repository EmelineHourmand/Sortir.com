package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.bo.State;
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
     * Constructor
     * @throws BLLException
     */
    public EventManager() throws BLLException {
        eventDAO = DAOFactory.getEventDAO();

    }

    public void insertEvent(String name, LocalDateTime eventBeginning, LocalDateTime duration, LocalDateTime registrationLimit, Integer maxRegistration, String description, State state, Site site) throws BLLException {
        try {
            Event event = new Event(name,eventBeginning,duration,registrationLimit,maxRegistration,description,state,site);
            eventDAO.insert(event);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public List<Event> selectAllEvent() throws DALException {
        return eventDAO.selectAll();
    }

    public Event selectEvent(Long id) throws DALException {
        return eventDAO.selectById(id);
    }

    public void updateEvent(Event event) throws  DALException {
        eventDAO.update(event);
    }

    public void removeEvent(Long id) throws DALException {
        eventDAO.delete(id);
    }
}




