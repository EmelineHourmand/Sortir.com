package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author hwasier2019
 * @author Emeline Hourmand
 */
public interface ParticipantDAO {

    void insert(Participant participant) throws DALException;

    List<Participant> selectAll() throws DALException;

    Participant selectById(Long id) throws DALException;

    void update(Participant participant) throws DALException;

    void delete(Long id) throws DALException;

    int checkByEmail(String mail) throws DALException;

    int checkByUsername(String username) throws DALException;

    Participant checkByUserLogin(String login, String password) throws DALException;
}
