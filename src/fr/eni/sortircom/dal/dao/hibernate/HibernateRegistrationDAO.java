package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Registration;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.RegistrationDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateRegistrationDAO implements RegistrationDAO {

    @Override
    public void insert(Registration registration) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.save(registration);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Registration> selectById(Long id) {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Registration WHERE event.idEvent=:idEvent");
        q.setParameter("idEvent", id);
        return q.getResultList();
    }

    @Override
    public void delete(Long id) {
        Session session = ConnectionProvider.getConnection();
        Registration registration = (Registration) session.get(Registration.class, id);
        session.beginTransaction();
        session.delete(registration);
        session.getTransaction().commit();
    }
}
