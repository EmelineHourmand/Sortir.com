package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.ParticipantDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

import static fr.eni.sortircom.tools.RegExpPatterns.EMAIL_PATTERN;
import static fr.eni.sortircom.tools.RegExpPatterns.PHONE_PATTERN;

/**
 * @author hwasier2019
 * @author Emeline Hourmand
 */
public class ParticipantManager {

    private static ParticipantDAO participantDAO;

    /**
     * Constructor (Singleton)
     */
    public ParticipantManager() {
        participantDAO = DAOFactory.getParticipantDAO();
    }

    /**
     * Permet de créer un compte
     * @param participant : participant avec les informations remplies
     * @throws DALException
     *
     * Règles vérifiées :
     * - champs non nuls (erreurs 200xx)
     * - téléphone -> 10 charactères
     * - unicité de l'email
     */
    public void insertParticipant(Participant participant) throws BLLException {

        BLLException bllException = new BLLException();
        checkParticipant(participant, bllException);
        if(!bllException.hasErrors()) {
            try {
                participantDAO.insert(participant);
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
     * Vérifie le match entre email et password donc que le participant est bien enregistré.
     * Retourne le Participant si il existe bien en BDD
     * @param login
     * @param password
     * @return Participant | BLLException
     * @throws BLLException
     */
    public Participant login(String login, String password) throws BLLException {
        BLLException bllException = new BLLException();
        Participant participant;

        // LOGIN
        if(login == null || login.trim().length() == 0) {
            bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_USERNAME_EMPTY_ERROR);
            bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_EMAIL_EMPTY_ERROR);
        }
        // PASSWORD
        if(password == null || password.trim().length() == 0 ) {
            bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_PASSWORD_EMPTY_ERROR);
        }

        try {
            participant = participantDAO.checkByUserLogin(login, password);
            System.out.println(participant);
            if (participant == null) {
                bllException.addErreur(CodesErreursBLL.SIGNIN_WRONG_IDENTIFICATION_ERROR);
            }
        } catch (DALException e) {
            bllException.addErreur(CodesErreursBLL.PARTICIPANT_CANT_SIGNIN_ERROR);
            bllException.addSuppressed(e);
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
        return participant;
    }

    /**
     * Selection de tout les PARTICIPANT en BDD
     * @return La List de tout les participants
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
     * @return Participant correspondant à l'id
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
        BLLException bllException = new BLLException();
        checkParticipant(participant, bllException);
        if(!bllException.hasErrors()) {
            try {
                participantDAO.update(participant.getIdParticipant());
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
     * Suppression d'un PARTICIPANT
     * @param id
     * @throws BLLException
     */
    public void removeParticipant(Long id) throws BLLException {
        try {
            participantDAO.delete(id);
        } catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Vérifie si le PARTICIPANT créé remplis tout les critères avant insertion en BDD.
     * @param participant
     * @return
     */
    public static void checkParticipant(Participant participant, BLLException bllException) {

        if(participant == null) {
            bllException.addErreur(CodesErreursBLL.PARTICIPANT_NULL_ERROR);
        } else {

             checkMailUsername(participant.getMail(), participant.getUsername(), bllException);

            // NOM
            if(participant.getLastname() == null || participant.getLastname().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_LASTNAME_EMPTY_ERROR);
            }

            // PRENOM
            if(participant.getFirstname() == null || participant.getFirstname().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_FIRSTNAME_EMPTY_ERROR);
            }

            // PHONE
            if(participant.getPhone() ==  null || participant.getPhone().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_PHONE_EMPTY_ERROR);
            }
                // On verifie le pattern du numéro de téléphone
            if(!participant.getPhone().matches(PHONE_PATTERN)) {
                bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_PHONE_FORMAT_INVALID_ERROR);
            }

            // PASSWORD
            if(participant.getPassword() == null || participant.getPassword().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_PASSWORD_EMPTY_ERROR);
            }
        }
    }

    /**
     * Vérifie si l'EMAIL n'est pas déjà utilisé.
     * @param mail
     * @return
     */
    private static void checkMailUsername(String mail, String username, BLLException bllException) {

        // MAIL
        if(mail == null || mail.trim().length() == 0 ) {
            bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_EMAIL_EMPTY_ERROR);
        } else {
            /*try {
               /if(DAOFactory.getParticipantDAO().checkByEmail(mail) != 0) {
                    bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_EMAIL_UNIQUE_ERROR);
                } else {*/
                    // Si pattern ne correspond pas a la règle
                    if (!mail.matches(EMAIL_PATTERN)) {
                        bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_EMAIL_FORMAT_INVALID_ERROR);
                    }
                /*}
            } catch (DALException e) {
                e.printStackTrace();
                bllException.addSuppressed(e);
                bllException.addErreur(CodesErreursBLL.PARTICIPANT_CANT_CHECK_EMAIL_UNIQUE_ERROR);
            }*/
        }

        // PSEUDO
        if(username == null || username.trim().length() == 0 ) {
            bllException.addErreur(CodesErreursBLL.RULE_PARTICIPANT_USERNAME_EMPTY_ERROR);
        } else {
            /*try {
                if(DAOFactory.getParticipantDAO().checkByUsername(username) != 0) {
                    bllException.addErreur(CodesErreursBLL.PARTICIPANT_CANT_CHECK_USERNAME_UNIQUE_ERROR);
                }
            } catch (DALException e) {
                e.printStackTrace();
                bllException.addSuppressed(e);
                bllException.addErreur(CodesErreursBLL.PARTICIPANT_CANT_CHECK_USERNAME_UNIQUE_ERROR);
            }*/
        }
    }
}
