package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.CityDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author jbruneau2019
 */
public class HibernateCityDAO implements CityDAO {

    @Override
    public void insert(City city) throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            session.beginTransaction();
            session.save(city);
            session.getTransaction().commit();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public List<City> selectAll() throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            Query q = session.createQuery("FROM City");
            return (List<City>) q.getResultList();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public City selectById(Long id) throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            return session.get(City.class, id);
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public void update(City city) throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            session.beginTransaction();
            session.saveOrUpdate(city);
            session.getTransaction().commit();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) throws DALException {
        try(Session session = ConnectionProvider.getConnection()) {
            City city = session.get(City.class, id);
            session.beginTransaction();
            session.delete(city);
            session.getTransaction().commit();
        }catch(HibernateException e){
            throw new DALException(e.getMessage(), e);
        }
    }
}
