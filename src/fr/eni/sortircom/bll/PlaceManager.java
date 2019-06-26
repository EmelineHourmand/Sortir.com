package fr.eni.sortircom.bll;


import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bo.Place;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.PlaceDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author jbruneau2019
 */
public class PlaceManager {

    private static PlaceDAO placeDAO;

    /**
     * Contructor (Singleton)
     */
    public PlaceManager(){
        placeDAO = DAOFactory.getPlaceDAO();
    }

    public void insertPlace(int idPlace, String name, String street, float latitude, float longitude){
        try {
            Place place = new Place( idPlace,  name,  street,  latitude,  longitude);
            placeDAO.insert(place);
        }catch (DALException e) {
            e.printStackTrace();
        }
    }

    public List<Place> selectAllEvent() throws DALException {
        return placeDAO.selectAll();
    }

    public Place selectPlace(Long id) throws DALException {
        return placeDAO.selectById(id);
    }

    public void updatePlace(Place place) throws  DALException {
        placeDAO.update(place);
    }

    public void removePlace(Long id) throws DALException {
        placeDAO.delete(id);
    }
}
