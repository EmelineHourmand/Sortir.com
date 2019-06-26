package fr.eni.sortircom.bll;


import com.sun.xml.bind.v2.TODO;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.ParticipantDAO;
import fr.eni.sortircom.dal.exception.DALException;
import fr.eni.sortircom.tools.RegExpPatterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

/**
 * @author hwasier2019
 */


public class ParticipantManager {

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
        if (!lastname.matches(ALPHANUMERIC_REGEX)) {
            //TODO
            return null;
        }
        if (!firstname.matches(RegExpPatterns.)) {
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
        participantDAO.insert(participant);
    }

    public List<Participant> selectAllParticipant() throws BLLException {
        return participantDAO.selectAll();
    }

    public Participant selectParticipant(Long id) throws BLLException {
        return participantDAO.selectById(id);
    }

    public void updateParticpant(Participant participant) throws BLLException {
        participantDAO.update(participant);
    }

    public void removeParticipant(Long id) throws BLLException {
        participantDAO.delete(id);
    }
}
