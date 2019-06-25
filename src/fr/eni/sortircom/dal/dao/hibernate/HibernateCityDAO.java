package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.CityDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class HibernateCityDAO implements CityDAO {


    @Override
    public void insert(City city) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
    }

    @Override
    public List<City> selectAll() throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM City");
        return (List<City>) q.getResultList();
    }

    @Override
    public City selectById(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        return session.get(City.class, id);
    }

    @Override
    public void update(City city) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.saveOrUpdate(city);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        City city = (City) session.get(City.class, id);
        session.beginTransaction();
        session.delete(city);
        session.getTransaction().commit();
    }
}
