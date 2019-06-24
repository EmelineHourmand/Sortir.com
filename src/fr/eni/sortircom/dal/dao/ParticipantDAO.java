package fr.eni.sortircom.dal.dao;

import fr.eni.sortircom.bo.Participant;

public interface ParticipantDAO {
    void insert(Participant participant);

    void selectAll();

    void selectById(Long id);

    void update(Participant participant);

    void delete(Long id);
}
