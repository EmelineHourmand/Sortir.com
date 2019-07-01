package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.City;
import fr.eni.sortircom.dal.dao.CityDAO;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.exception.DALException;
import fr.eni.sortircom.tools.RegExpPatterns;

import java.util.List;


/**
 * @author jbruneau2019
 * @author Emeline Hourmand
 */
public class CityManager {

    private static CityDAO cityDAO;

    /**
     * Constructor (Singleton)
     */
    public CityManager(){
        cityDAO = DAOFactory.getCityDAO();
    }

    /**
     * Verification et insertion d'un CITY
     * @param city
     * @return
     * @throws BLLException
     */
    public void insertCity(City city) throws BLLException {
        BLLException bllException = new BLLException();
        checkCity(city, bllException);
        if(!bllException.hasErrors()) {
            try {
                cityDAO.insert(city);
            } catch (DALException e) {
                bllException.addSuppressed(e);
                System.out.println(bllException.errorsToString());
                throw bllException;
            }
        } else {
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
    }

    /**
     * Selection de tout les CITY en BDD
     * @return
     * @throws BLLException
     */
    public List<City> selectAllCity() throws BLLException {
        try {
            return cityDAO.selectAll();
        } catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Selection d'un seul CITY
     * @param id
     * @return
     * @throws BLLException
     */
    public City selectCity(Long id) throws BLLException {
        try {
            return cityDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Modification d'un CITY
     * @param city
     * @throws BLLException
     */
    public void updateCity(City city) throws BLLException {
        BLLException bllException = new BLLException();
        checkCity(city, bllException);
        if(!bllException.hasErrors()) {
            try {
                cityDAO.update(city);
            } catch (DALException e) {
                bllException.addSuppressed(e);
                System.out.println(bllException.errorsToString());
                throw bllException;
            }
        } else {
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
    }

    /**
     * Suppression d'un CITY
     * @param id
     * @throws BLLException
     */
    public void removeCity(Long id) throws BLLException {
        try {
            cityDAO.delete(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Vérifie si le SITE créé remplis tout les critères avant insertion en BDD.
     * @param city
     * @return
     */
    public static void checkCity(City city, BLLException bllException){
        if(city == null) {
            bllException.addErreur(CodesErreursBLL.CITY_NULL_ERROR);
        } else {
            // NOM
            if(city.getName() == null || city.getName().trim().length() == 0 ) {
                bllException.addErreur(CodesErreursBLL.RULE_CITY_NAME_EMPTY_ERROR);
            } else if(city.getName().length() > 50) {
                bllException.addErreur(CodesErreursBLL.RULE_CITY_NAME_FORMAT_INVALID_ERROR);
            }

            // CODE POSTAL
            if (city.getPostalCode() == null || city.getPostalCode().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_CITY_POSTAL_CODE_EMPTY_ERROR);
            } else if (city.getPostalCode().length() > 5) {
                bllException.addErreur(CodesErreursBLL.RULE_CITY_POSTAL_CODE_INVALID_ERROR);
            }
        }
    }
}

