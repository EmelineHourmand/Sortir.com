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
     * @throws BLLException
     */
    public void insertCity(City city) throws BLLException {
        BLLException bllException = new BLLException();
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
    }



 /*   /**
     * Verification et insertion d'un CITY
     * @param name
     * @param postalCode
     * @return
     * @throws BLLException
     */
  /*  public City insertCity(String name, String postalCode) throws BLLException {
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
    }*/


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
        if(checkCity(city)){
            System.out.println(bllException.errorsToString());
            throw bllException;
        }
        try {
            cityDAO.update(city);
        }catch (DALException e) {
            bllException.addSuppressed(e);
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
    public static boolean checkCity(City city){
        return false;
    }
}

