package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.dal.exception.DALException;

public interface RegistrationDAO {

    int selectById(Long id) throws DALException;

}
