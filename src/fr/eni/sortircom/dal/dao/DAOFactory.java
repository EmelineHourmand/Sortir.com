package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.dal.dao.hibernate.HibernateCityDAO;
import fr.eni.sortircom.dal.dao.hibernate.HibernateEventDAO;
import fr.eni.sortircom.dal.dao.hibernate.HibernateParticipantDAO;
import fr.eni.sortircom.dal.dao.hibernate.HibernatePlaceDAO;

/**
 *
 * @author ehourman2019
 *
 */
public class DAOFactory {

    public static ParticipantDAO getParticipantDAO() { return new HibernateParticipantDAO(); }

    public static EventDAO getEventDAO() { return new HibernateEventDAO(); }

    public static CityDAO getCityDAO() { return new HibernateCityDAO(); }

    public static PlaceDAO getPlaceDAO() { return new HibernatePlaceDAO(); }

}
