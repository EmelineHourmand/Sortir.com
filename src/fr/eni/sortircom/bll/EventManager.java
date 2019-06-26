package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.EventDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author Emeline Hourmand
 */
public abstract class EventManager {

    private static EventDAO eventDAO;

    /**
     * Constructor
     * @throws BLLException
     */
    public EventManager() {
        eventDAO = DAOFactory.getEventDAO();
    }

    public void insertEvent(Event event) throws BLLException {
        BLLException bllException = new BLLException();
       // checkEvent(event, bllException);
        if(!bllException.hasErrors()) {
            try {
                DAOFactory.getEventDAO().insert(event);
            } catch (DALException e) {
                bllException.addSuppressed(e);
                System.out.println(bllException.errorsToString());
                throw bllException;
            }
        } else {
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
    }

    /**
     * Cermet de verifier la validité des informations de l'event
     *
     * @throws BLLException
     */
   /* public static void checkEvent(Event event, BLLException bllException) throws BLLException {

        boolean isOK = true;

        // Verifie si l'event est pas pas null
        if(event == null){
            bllException.addErreur(CodesErreursBLL.EVENT_NULL_ERROR);
        } else {

            // Verification du nom de l'event
            if(event.getName() == null || event.getName().trim().length()==0){
                sb.append("Le nom est obligatoire.\n");
                isOK = false;
            }
            if(event.getName().length() > 255) {
                sb.append("Le nom doit avoir moins de 256 caractères.\n");
                isOK = false;
            }

            // Verification de la description de l'event
            if(event.getDescription()== null || event.getDescription().trim().length()==0){
                sb.append("La description est obligatoire.\n");
                isOK = false;
            }


            if(user.getLastName()==null || user.getLastName().trim().length()==0){
                sb.append("Le nom de famille est obligatoire.\n");
                isOK = false;
            }
            if (user.getEmail() == null || user.getEmail().trim().length()== 0) {
                sb.append("L'email est obligatoire.");
                isOK = false;
            } else {
                boolean emailCheck = emailCheck(user.getEmail());
                if (emailCheck == false) {
                    sb.append("L'email n'est pas valide.");
                    isOK = false;
                }
            }
            if (user.getPhoneNumber() != null || user.getPhoneNumber().trim().length() > 0) {
                boolean numberCheck = numberCheck(user.getPhoneNumber());
                if (numberCheck == false) {
                    sb.append("Le numéro de téléphone n'est pas valide");
                    isOK = false;
                }
            }
            if (user.getStreet() == null || user.getStreet().trim().length() ==0) {
                sb.append("La rue n'est pas valide.");
                isOK = false;
            }
            if (user.getPostalCode() == null || user.getPostalCode().trim().length() == 0) {
                sb.append("Le code postal est obligatoire");
                isOK = false;
            } else {
                if (user.getPostalCode().length() > 5 && user.getPostalCode().length() < 5) {
                    sb.append("Le code postal est invalide");
                    isOK = false;
                }
            }
            if (user.getCity() == null || user.getCity().trim().length() ==0 ) {
                sb.append("La ville postal est obligatoire");
                isOK = false;
            }
            if (user.getPassword() == null || user.getPassword().trim().length() ==0 ) {
                sb.append("Le password est obligatoire");
                isOK = false;
            }

            if(!isOK) {
                throw new BLLException(sb.toString());
            }
        }
    }

    public List<Event> selectAllEvent() throws BLLException {
        return eventDAO.selectAll();
    }

    public Event selectEvent(Long id) throws BLLException {
        return eventDAO.selectById(id);
    }

    public void updateEvent(Event event) throws  BLLException {
        eventDAO.update(event);
    }

    public void removeEvent(Long id) throws BLLException {
        eventDAO.delete(id);
    }
*/
}




