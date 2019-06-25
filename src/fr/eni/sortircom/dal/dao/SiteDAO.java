package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Site;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

/**
 * @author hwasier2019
 */
public interface SiteDAO {
void insert(Site site) throws DALException;
List<Site> selectAll() throws DALException;
Site selectById(Long id) throws DALException;
void update(Site site) throws DALException;
void delete(Long id) throws DALException;

}
