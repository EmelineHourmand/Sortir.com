package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Place;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.PlaceDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class HibernatePlaceDAO implements PlaceDAO {


    @Override
    public void insert(Place place) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.save(place);
        session.getTransaction().commit();
    }

    @Override
    public List<Place> selectAll() throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Place");
        return (List<Place>) q.getResultList();
    }

    @Override
    public Place selectById(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        return session.get(Place.class, id);
    }

    @Override
    public void update(Place place) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.saveOrUpdate(place);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Place place = (Place) session.get(Place.class, id);
        session.beginTransaction();
        session.delete(place);
        session.getTransaction().commit();
    }
}
