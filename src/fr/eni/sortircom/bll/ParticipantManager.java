package fr.eni.sortircom.bll;


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

    /**
     * Constructor
     *
     * @throws BLLException
     */
    public ParticipantManager() {
        participantDAO = DAOFactory.getParticipantDAO();
    }

    public void insertParticipant(String lastname, String firstname, int phone, String mail, boolean administrator, boolean actif) throws BLLException {
        try {
            Participant participant = new Participant(lastname, firstname, phone, mail, administrator, actif);
            int switchvariable = -1;
            if (!lastname.matches(ALPHANUMERIC_REGEX)) {
                switchvariable = 1;
            }
            if (!firstname.matches(ALPHANUMERIC_REGEX)) {
                switchvariable = 2;
            }
            if (!mail.matches(EMAIL_REGEX)) {
                switchvariable = 4;
            }
            participantDAO.insert(participant);
        } catch (DALException e) {
            e.printStackTrace();
        }
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
