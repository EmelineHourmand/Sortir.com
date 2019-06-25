package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.exception.DALException;

/**
 *
 * @author hWasier
 *
 */
public interface ParticipantDAO {

    void insert(Participant participant) throws DALException;

    void selectAll() throws DALException;

    void selectById(Long id) throws DALException;

    void update(Participant participant) throws DALException;

    void delete(Long id) throws DALException;
}
