package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.ParticipantDAO;
import fr.eni.sortircom.dal.exception.DALException;
import fr.eni.sortircom.tools.RegExpPatterns;

/**
 * @author hwasier2019
 * @author Emeline Hourmand
 */
public abstract class ParticipantManager {

    private static ParticipantDAO participantDAO;

    /**
     * Constructor
     *
     * @throws BLLException
     */
    public ParticipantManager() {
        participantDAO = DAOFactory.getParticipantDAO();
    }

    public static Participant checkParticipant(String lastname, String firstname, String phone, String mail, boolean administrator, boolean actif) {
        if (!lastname.matches(RegExpPatterns.ALPHANUMERIC_PATTERN)) {
            //TODO
            return null;
        }
        if (!firstname.matches(RegExpPatterns.ALPHANUMERIC_PATTERN)) {
            //TODO
            return null;
        }
        if (!phone.matches(RegExpPatterns.PHONE_PATTERN)) {
            //TODO
            return null;
        }
        if (!mail.matches(RegExpPatterns.EMAIL_PATTERN)) {
            //TODO
            return null;
        } else {
            return new Participant(lastname, firstname, phone, mail, administrator, actif);
        }
    }

    public static void checkMail(String mail) throws DALException {
        if (participantDAO.checkByEmail(mail)!=0){
            //TODO
        }
    }

    public void insertParticipant(Participant participant) throws BLLException {
        try {
            participantDAO.insert(participant);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    /*public void selectAllParticipant() throws BLLException {
        try {
            //return participantDAO.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void selectParticipant(Long id) throws BLLException {
        try {
          //  return participantDAO.selectById(id);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }*/

    public void updateParticpant(Participant participant) throws BLLException {
        try {
            participantDAO.update(participant);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeParticipant(Long id) throws BLLException {
        try {
            participantDAO.delete(id);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
}
