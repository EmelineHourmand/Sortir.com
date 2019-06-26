package fr.eni.sortircom.bll;


import com.sun.xml.bind.v2.TODO;
import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.ParticipantDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

/**
 * @author hwasier2019
 */


public class ParticipantManager {

    private static ParticipantDAO participantDAO;

    private static final String ALPHANUMERIC_REGEX = "[a-zA-Z0-9]+";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PHONE_REGEX = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";

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
        if (!firstname.matches(ALPHANUMERIC_REGEX)) {
            //TODO
            return null;
        }
        if (!phone.matches(PHONE_REGEX)) {
            //TODO
            return null;
        }
        if (!mail.matches(EMAIL_REGEX)) {
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

    public void insertParticipant(Participant participant) throws BLLException, DALException {
        participantDAO.insert(participant);
    }

    public List<Participant> selectAllParticipant() throws DALException {
        return participantDAO.selectAll();
    }

    public Participant selectParticipant(Long id) throws DALException {
        return participantDAO.selectById(id);
    }

    public void updateParticpant(Participant participant) throws DALException {
        participantDAO.update(participant);
    }

    public void removeParticipant(Long id) throws DALException {
        participantDAO.delete(id);
    }
}
