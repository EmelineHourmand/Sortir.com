package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.dal.dao.hibernate.*;

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

    public static SiteDAO getSiteDAO() { return new HibernateSiteDAO(); }

    public static StateDAO getStateDAO() { return new HibernateStateDAO(); }

}
