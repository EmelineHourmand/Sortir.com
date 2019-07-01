package fr.eni.sortircom.dal.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.ConnectionProvider;
import fr.eni.sortircom.dal.dao.ParticipantDAO;
import fr.eni.sortircom.dal.exception.DALException;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author hwasier2019
 */
public class HibernateParticipantDAO implements ParticipantDAO {
    @Override
    public void insert(Participant participant) throws DALException {
        Session session = ConnectionProvider.getConnection();
        session.beginTransaction();
        session.save(participant);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Participant> selectAll() throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Participant ");
        List<Participant> participantList = q.getResultList();
        return participantList;
    }

    @Override
    public Participant selectById(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Participant WHERE id=:idParticipant");
        q.setParameter("idParticipant", id);
        List<Participant> participants = q.getResultList();
        Participant participant = participants.get(0);
        return participant;
    }

    @Override
    public void update(Long id) {
        Session session = ConnectionProvider.getConnection();
        Participant participant = (Participant) session.get(Participant.class, id);
        session.beginTransaction();
        session.merge(participant);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Long id) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Participant participant = (Participant) session.get(Participant.class, id);
        session.beginTransaction();
        session.delete(participant);
        session.getTransaction().commit();
    }

    @Override
    public int checkByEmail(String mail) throws DALException {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Participant WHERE mail=:mail");
        q.setParameter("mail", mail);
        List<Participant> participants = q.getResultList();
        return participants.size();
    }

    @Override
    public int checkByUsername(String username) {
        Session session = ConnectionProvider.getConnection();
        Query q = session.createQuery("FROM Participant WHERE username=:username");
        q.setParameter("username", username);
        List<Participant> participants = q.getResultList();
        return participants.size();
    }

    @Override
    public Participant checkByUserLogin(String login, String password) throws DALException {
        Participant participant = null;
        String hql = "FROM Participant WHERE "
                + "password = \'" + password + "\' AND "
                + " (username = \'" + login + "\' OR mail = \'" + login + "\')" ;
        try(Session session = ConnectionProvider.getConnection()){
            Query query = session.createQuery(hql);
            List<Participant> results = query.getResultList();
            if(!results.isEmpty()) {
                participant = results.get(0);
            } else {
                throw new DALException("DAL - Information de connexion incorrecte");
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage(), e);
        }
        return participant;
    }
}
