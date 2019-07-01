package fr.eni.sortircom.bll;

import fr.eni.sortircom.bll.exception.BLLException;
import fr.eni.sortircom.bll.exception.CodesErreursBLL;
import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.dal.dao.DAOFactory;
import fr.eni.sortircom.dal.dao.SiteDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;
import java.util.concurrent.BlockingDeque;

/**
 * @author Emeline Hourmand
 */
public class SiteManager {

    private static SiteDAO siteDAO;

    /**
     * Constructor (Singleton)
     */
    public SiteManager() { siteDAO = DAOFactory.getSiteDAO(); }

    /**
     * Verification et insertion d'un SITE
     * @return
     * @throws BLLException
     */
    public void insertSite(Site site) throws BLLException {
        BLLException bllException = new BLLException();
        checkSite(site, bllException);
        if(!bllException.hasErrors()) {
            try {
                siteDAO.insert(site);
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
     * Selection de tout les SITE en BDD
     * @return
     * @throws BLLException
     */
    public List<Site> selectAllSite() throws BLLException {
        try {
            return siteDAO.selectAll();
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Selection d'un seul SITE
     * @param id
     * @return
     * @throws BLLException
     */
    public Site selectSite(Long id) throws BLLException {
        try {
            return siteDAO.selectById(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Modification d'un SITE
     * @param site
     * @throws BLLException
     */
    public void updateSite(Site site) throws BLLException {
        BLLException bllException = new BLLException();
        checkSite(site, bllException);
        if(!bllException.hasErrors()) {
            try {
                siteDAO.update(site);
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
     * Suppression d'un SITE
     * @param id
     * @throws BLLException
     */
    public void removeSite(Long id) throws BLLException {
        try {
            siteDAO.delete(id);
        }catch (DALException e) {
            BLLException bllException = new BLLException();
            bllException.addSuppressed(e);
            throw bllException;
        }
    }

    /**
     * Vérifie si le SITE créé remplis tout les critères avant insertion en BDD.
     * @param site
     * @return
     */
    public static void checkSite(Site site, BLLException bllException){
        if(site == null) {
            bllException.addErreur(CodesErreursBLL.CITY_NULL_ERROR);
        } else {

            // NOM
            if (site.getName() == null || site.getName().trim().length() == 0) {
                bllException.addErreur(CodesErreursBLL.RULE_SITE_NAME_EMPTY_ERROR);
            } else if (site.getName().length() > 50) {
                bllException.addErreur(CodesErreursBLL.RULE_SITE_NAME_FORMAT_INVALID_ERROR);
            }
        }
    }
}
