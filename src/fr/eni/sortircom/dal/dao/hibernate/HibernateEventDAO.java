package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.EventDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author ehourman2019
 *
 */
public class HibernateEventDAO implements EventDAO {


    @Override
    public void insert(Event event) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
    }

    @Override
    public List<Event> selectAll() throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Event");
        List<Event> eventList = q.getResultList();
        return eventList;
    }

    @Override
    public Event selectById(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Event WHERE id=:idEvent");
        q.setParameter("idEvent", id);
        List<Event> events = q.getResultList();
        Event event = events.get(0);
        return event;
    }

    @Override
    public void update(Event event) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.saveOrUpdate(event);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Event event = (Event) session.get(Event.class, id);
        session.beginTransaction();
        session.delete(event);
        session.getTransaction().commit();
    }
}
