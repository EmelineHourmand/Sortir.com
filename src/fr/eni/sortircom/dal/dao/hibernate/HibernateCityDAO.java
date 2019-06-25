package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.dao.CityDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

public class HibernateCityDAO implements CityDAO {


    @Override
    public void insert(City city) throws DALException {

    }

    @Override
    public List<City> selectAll() throws DALException {
        return null;
    }

    @Override
    public City selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public void update(City city) throws DALException {

    }

    @Override
    public void delete(Long id) throws DALException {

    }
}
