package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Place;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.PlaceDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author jbruneau2019
 */
public class HibernatePlaceDAO implements PlaceDAO {

    @Override
    public void insert(Place place) throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            session.beginTransaction();
            session.save(place);
            session.getTransaction().commit();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public List<Place> selectAll() throws DALException {
        try(Session session = ConnectionProvider.getConnection()){
            Query q = session.createQuery("FROM Place");
            return (List<Place>) q.getResultList();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public Place selectById(Long id) throws DALException {
        try(Session session = ConnectionProvider.getConnection()){
            return session.get(Place.class, id);
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Place place) throws DALException {
        try(Session session = ConnectionProvider.getConnection()){
            session.beginTransaction();
            session.saveOrUpdate(place);
            session.getTransaction().commit();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws DALException {
        try(Session session = ConnectionProvider.getConnection()){
            Place place = session.get(Place.class, id);
            session.beginTransaction();
            session.delete(place);
            session.getTransaction().commit();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }
}
