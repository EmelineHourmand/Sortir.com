package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.dal.dao.hibernate.HibernateEventDAO;
import fr.eni.sortircom.dal.dao.hibernate.HibernateParticipantDAO;

/**
 *
 * @author ehourman2019
 *
 */
public class DAOFactory {

    public static ParticipantDAO getParticipantDAO() { return new HibernateParticipantDAO(); }

    public static EventDAO getEventDAO() { return new HibernateEventDAO(); }

}
