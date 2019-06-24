package fr.eni.sortircom.dal.dao;

public class DAOFactory {

    public static ParticipantDAO() {
        return new HibernateParticipantDAO();
    }

}
