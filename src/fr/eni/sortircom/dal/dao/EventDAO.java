package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.bo.Participant;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 *
 * @author ehourman2019
 *
 */
public interface EventDAO {

    void insert(Event event) throws DALException;

    List<Event> selectAll() throws DALException;

    Event selectById(Long id) throws DALException;

    void update(Event event) throws DALException;

    void delete(Long id) throws DALException;

//    List<Event> selectByRegistredUser(Participant participant, boolean isRegistred) throws DALException;
}
