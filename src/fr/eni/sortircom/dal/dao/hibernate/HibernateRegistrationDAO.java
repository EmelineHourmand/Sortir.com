package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Registration;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.RegistrationDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateRegistrationDAO implements RegistrationDAO {

    @Override
    public int selectById(Long id) {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Registration WHERE event.idEvent=:idEvent");
        q.setParameter("idEvent", id);
        List<Registration> registrations = q.getResultList();
        return registrations.size();
    }
}
