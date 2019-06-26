package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.dao.CityDAO;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.exception.DALException;
import fr.eni.sortircom.tools.RegExpPatterns;

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

    public City insertCity(String name, String postalCode) throws BLLException {
        BLLException bllException = new BLLException();
        City city = new City(name, postalCode);
        if(checkCity(city)){
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
        try {
            cityDAO.insert(city);
        }catch (DALException e) {
            //e.printStackTrace();
            bllException.addSuppressed(e);
            throw bllException;
        }
        return city;
    }

    public List<City> selectAllEvent() throws BLLException {
        try {
            return cityDAO.selectAll();
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    public City selectCity(Long id) throws BLLException {
        try {
            return cityDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    public void updateCity(City city) throws BLLException {
        BLLException bllException = new BLLException();
        if(checkCity(city)){
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
        try {
            cityDAO.update(city);
        }catch (DALException e) {
            //e.printStackTrace();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    public void removeCity(Long id) throws BLLException {
        try {
            cityDAO.delete(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    public static boolean checkCity(City city){

        if (!city.getName().matches(RegExpPatterns.TYPE_SQL_STRING_50_PATTERN)){

        }
        if (!city.getPostalCode().matches(RegExpPatterns.POSTAL_CODE_PATTERN)){

        }







        return false;
    }
}

