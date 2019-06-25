package fr.eni.sortircom.dal.dao;


import fr.eni.sortircom.bo.Place;
import fr.eni.sortircom.dal.exception.DALException;
import java.util.List;

/**
 * @author jbruneau2019
 */
public interface PlaceDAO {

    void insert(Place place) throws DALException;

    List<Place> selectAll() throws DALException;

    Place selectById(Long id) throws DALException;

    void update(Place place) throws DALException;

    void delete(Long id) throws DALException;
}
