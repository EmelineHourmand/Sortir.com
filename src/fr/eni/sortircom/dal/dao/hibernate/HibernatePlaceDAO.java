package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Place;
import fr.eni.sortircom.dal.dao.PlaceDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

public class HibernatePlaceDAO implements PlaceDAO {


    @Override
    public void insert(Place place) throws DALException {

    }

    @Override
    public List<Place> selectAll() throws DALException {
        return null;
    }

    @Override
    public Place selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public void update(Place place) throws DALException {

    }

    @Override
    public void delete(Long id) throws DALException {

    }
}
