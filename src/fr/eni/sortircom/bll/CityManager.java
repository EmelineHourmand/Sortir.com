package fr.eni.sortircom.bll;

import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.dao.CityDAO;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;


/**
 * @author jbruneau2019
 */
public class CityManager {

    private static CityDAO cityDAO;

    /**
     * Contructor (Singleton)
     */
    public CityManager(){
        cityDAO = DAOFactory.getCityDAO();
    }

    public void insertCity(String name, String postalCode){
        try {
            City city = new City(name, postalCode);
            cityDAO.insert(city);
        }catch (DALException e) {
            e.printStackTrace();
        }
    }

    public List<City> selectAllEvent() throws DALException {
        return cityDAO.selectAll();
    }

    public City selectCity(Long id) throws DALException {
        return cityDAO.selectById(id);
    }

    public void updateCity(City city) throws  DALException {
        cityDAO.update(city);
    }

    public void removeCity(Long id) throws DALException {
        cityDAO.delete(id);
    }
}

