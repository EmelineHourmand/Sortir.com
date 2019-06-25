package fr.eni.sortircom.dal.dao.hibernate;

import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.dal.dao.SiteDAO;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

public class HibernateSiteDAO implements SiteDAO {
    @Override
    public void insert(Site site) throws DALException {

    }

    @Override
    public List<Site> selectAll() throws DALException {
        return null;
    }

    @Override
    public Site selectById(Long id) throws DALException {
        return null;
    }

    @Override
    public void update(Site site) throws DALException {

    }

    @Override
    public void delete(Long id) throws DALException {

    }
}
