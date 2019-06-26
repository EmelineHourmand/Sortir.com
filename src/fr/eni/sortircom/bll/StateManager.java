package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.State;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.StateDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author Emeline Hourmand
 */
public abstract class StateManager {

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
    public State insertState() throws BLLException {
        return null;
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
        if(checkState(state)){
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
        try {
            stateDAO.update(state);
        }catch (DALException e) {
            bllException.addSuppressed(e);
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
    public static boolean checkState(State state){
        return false;
    }
}
