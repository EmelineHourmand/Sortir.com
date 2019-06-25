package fr.eni.sortircom.dal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ehourman2019
 *
 */
public abstract class ConnectionProvider {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getConnection() {
        return sessionFactory.openSession();
    }
}
