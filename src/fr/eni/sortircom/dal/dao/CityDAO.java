package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author jbruneau2019
 */
public interface CityDAO {

    void insert(City city) throws DALException;

    List<City> selectAll() throws DALException;

    City selectById(Long id) throws DALException;

    void update(City city) throws DALException;

    void delete(Long id) throws DALException;

}
