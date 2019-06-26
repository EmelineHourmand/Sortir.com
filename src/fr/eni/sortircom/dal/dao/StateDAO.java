package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.bo.State;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author Emeline Hourmand
 */
public interface StateDAO {

    void insert(State state) throws DALException;
    List<State> selectAll() throws DALException;
    State selectById(Long id) throws DALException;
    void update(State state) throws DALException;
    void delete(Long id) throws DALException;
}
