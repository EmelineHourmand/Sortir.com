package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.RegistrationDAO;
import fr.eni.sortircom.dal.exception.DALException;

public class RegistrationManager {

    private static RegistrationDAO registrationDAO;

    /**
     * Constructor (Singleton)
     */
    public RegistrationManager() {
        registrationDAO = DAOFactory.getRegistationDAO();
    }

    /**
     * Selection des Participants inscrit a l'évent
     * @param id
     * @return Participants inscrit a l'évent
     * @throws BLLException
     */
    public int selectParticipantEvent(Long id) throws BLLException {
        try {
            return registrationDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }
}
