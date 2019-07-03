package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Registration;
import fr.eni.sortircom.dal.exception.DALException;

import java.util.List;

public interface RegistrationDAO {

    List<Registration> selectById(Long id) throws DALException;

    void insert(Registration registration) throws DALException;
    void delete(Long id);

}
