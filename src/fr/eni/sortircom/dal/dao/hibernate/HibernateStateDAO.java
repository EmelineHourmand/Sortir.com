package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.bo.State;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.StateDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Emeline Hourmand
 */
public class HibernateStateDAO implements StateDAO {

    @Override
    public void insert(State state) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.save(state);
        session.getTransaction().commit();
    }

    @Override
    public List<State> selectAll() throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Event");
        List<State> stateList = q.getResultList();
        return stateList;
    }

    @Override
    public State selectById(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM State WHERE id=:idState");
        q.setParameter("idState", id);
        List<State> states = q.getResultList();
        State state = states.get(0);
        return state;
    }

    @Override
    public void update(State state) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.saveOrUpdate(state);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        State state = (State) session.get(State.class, id);
        session.beginTransaction();
        session.delete(state);
        session.getTransaction().commit();
    }
}
