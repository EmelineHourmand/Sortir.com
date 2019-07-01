package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.State;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.StateDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author Emeline Hourmand
 */
public class StateManager {

    private static StateDAO stateDAO;

    /**
     * Constructor (Singleton)
     */
    public StateManager() { stateDAO = DAOFactory.getStateDAO(); }

    /**
     * Verification et insertion d'un STATE
     * @return
     * @throws BLLException
     */
    public void insertState(State state) throws BLLException {
        BLLException bllException = new BLLException();
        checkState(state, bllException);
        if(!bllException.hasErrors()) {
            try {
                stateDAO.insert(state);
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
     * Selection de tout les STATE en BDD
     * @return
     * @throws BLLException
     */
    public List<State> selectAllState() throws BLLException {
        try {
            return stateDAO.selectAll();
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Selection d'un seul STATE
     * @param id
     * @return
     * @throws BLLException
     */
    public State selectState(Long id) throws BLLException {
        try {
            return stateDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Modification d'un SITE
     * @param state
     * @throws BLLException
     */
    public void updateState(State state) throws BLLException {
        BLLException bllException = new BLLException();
        checkState(state, bllException);
        if(!bllException.hasErrors()) {
            try {
                stateDAO.update(state);
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
     * Suppression d'un SITE
     * @param id
     * @throws BLLException
     */
    public void removeState(Long id) throws BLLException {
        try {
            stateDAO.delete(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Vérifie si le SITE créé remplis tout les critères avant insertion en BDD.
     * @param state
     * @return
     */
    public static void checkState(State state, BLLException bllException){
        if(state == null) {
            bllException.addErreur(CodesErreursBLL.CITY_NULL_ERROR);
        } else {
            // NOM
            if(state.getLabel() == null || state.getLabel().trim().length() == 0 ) {
                bllException.addErreur(CodesErreursBLL.RULE_STATE_LABEL_EMPTY_ERROR);
            } else if(state.getLabel().length() > 50) {
                bllException.addErreur(CodesErreursBLL.RULE_STATE_LABEL_FORMAT_INVALID_ERROR);
            }
        }
    }
}
