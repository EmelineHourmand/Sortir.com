package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.Place;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.PlaceDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author jbruneau2019
 * @author Emeline Hourmand
 */
public class PlaceManager {

    private static PlaceDAO placeDAO;

    /**
     * Contructor (Singleton)
     */
    public PlaceManager(){
        placeDAO = DAOFactory.getPlaceDAO();
    }

    /**
     * Verification et insertion d'un CITY
     * @param place
     * @return
     * @throws BLLException
     */
    public void insertPlace(Place place) throws BLLException {
        BLLException bllException = new BLLException();
        checkPlace(place, bllException);
        if(!bllException.hasErrors()) {
            try {
                placeDAO.insert(place);
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
     * Selection de tout les PLACE en BDD
     * @return
     * @throws BLLException
     */
    public List<Place> selectAllEvent() throws BLLException {
        try {
            return placeDAO.selectAll();
        } catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Selection d'un seul PLACE
     * @param id
     * @return
     * @throws BLLException
     */
    public Place selectPlace(Long id) throws BLLException {
        try {
            return placeDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Modification d'un PLACE
     * @param place
     * @throws BLLException
     */
    public void updatePlace(Place place) throws BLLException {
        BLLException bllException = new BLLException();
        checkPlace(place, bllException);
        if(!bllException.hasErrors()) {
            try {
                placeDAO.update(place);
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
    public void removePlace(Long id) throws BLLException {
        try {
            placeDAO.delete(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }


    public static void checkPlace(Place place, BLLException bllException){

        if(place == null) {
            bllException.addErreur(CodesErreursBLL.CITY_NULL_ERROR);
        } else {

            // NOM
            if (place.getName() == null || place.getName().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_PLACE_NAME_EMPTY_ERROR);
            } else if (place.getName().length() > 50) {
                bllException.addErreur(CodesErreursBLL.RULE_PLACE_NAME_FORMAT_INVALID_ERROR);
            }
        }
    }

}
