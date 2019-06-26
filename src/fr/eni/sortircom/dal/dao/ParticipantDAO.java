package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 *
 * @author hwasier2019
 *
 */
public interface ParticipantDAO {

    void insert(Participant participant) throws DALException;

    List<Participant> selectAll() throws DALException;

    Participant selectById(Long id) throws DALException;

    void update(Participant participant) throws DALException;

    void delete(Long id) throws DALException;
}
