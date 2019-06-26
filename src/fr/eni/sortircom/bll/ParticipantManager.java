package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.ParticipantDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author hwasier2019
 * @author Emeline Hourmand
 */
public abstract class ParticipantManager {

    private static ParticipantDAO participantDAO;

    /**
     * Constructor (Singleton)
     */
    public ParticipantManager() {
        participantDAO = DAOFactory.getParticipantDAO();
    }

    /**
     * Verification et insertion d'un PARTICIPANT
     * @return
     * @throws BLLException
     */
    public void insertParticipant(Participant participant) throws BLLException {
        try {
            participantDAO.insert(participant);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    /**
     * Selection de tout les PARTICIPANT en BDD
     * @return
     * @throws BLLException
     */
    public List<Participant> selectAllParticipant() throws BLLException {
        try {
            return participantDAO.selectAll();
        } catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Selection d'un seul PARTICIPANT
     * @param id
     * @return
     * @throws BLLException
     */
    public Participant selectParticipant(Long id) throws BLLException {
        try {
            return participantDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Modification d'un PARTICIPANT
     * @param participant
     * @throws BLLException
     */
    public void updateParticpant(Participant participant) throws BLLException {
        try {
            participantDAO.update(participant);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    /**
     * Suppression d'un PARTICIPANT
     * @param id
     * @throws BLLException
     */
    public void removeParticipant(Long id) throws BLLException {
        try {
            participantDAO.delete(id);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    /**
     * Vérifie si le PARTICIPANT créé remplis tout les critères avant insertion en BDD.
     * @param participant
     * @return
     */
    public static boolean checkParticipant(Participant participant) {
        return false;
    }

    /**
     * Vérifie si l'EMAIL n'est pas déjà utilisé.
     * @param mail
     * @return
     */
    public static boolean checkMail(String mail) throws DALException {
       return false;
    }
}
