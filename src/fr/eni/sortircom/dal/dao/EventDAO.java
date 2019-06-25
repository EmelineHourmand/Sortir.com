package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Event;
import fr.eni.sortircom.dal.exception.DALException;

/**
 *
 * @author ehourman2019
 *
 */
public interface EventDAO {

    void insert(Event event) throws DALException;

    void selectAll() throws DALException;

    void selectById(Long id) throws DALException;

    void update(Event event) throws DALException;

    void delete(Long id) throws DALException;
}
