package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.EventDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

/**
 *
 * @author ehourman2019
 *
 */
public class HibernateEventDAO implements EventDAO {


    @Override
    public void insert(Event event) throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            session.beginTransaction();
            session.save(event);
            session.getTransaction().commit();
        }catch(HibernateException e){
            e.printStackTrace();
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public List<Event> selectAll() {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Event");
        List<Event> eventList = q.getResultList();
        return eventList;
    }

    @Override
    public Event selectById(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
//        Query q = session.createQuery("FROM Event WHERE idEvent=:id");
//        q.setParameter("id", id);
//        List<Event> events = q.getResultList();
//        Event event = events.get(0);
        Event event = session.get(Event.class, id);
        if (event == null){throw new DALException("Couche DAL - Aucun Evenement correspondant.");}
        return event;
    }

    @Override
    public void update(Event event) {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.update(event);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        Session session = ConnectionProvider.getConnection();
        Event event = session.get(Event.class, id);
        session.beginTransaction();
        session.delete(event);
        session.getTransaction().commit();
    }
}
