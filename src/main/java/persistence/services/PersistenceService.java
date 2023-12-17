package persistence.services;

import persistence.models.User;

import java.util.List;

public interface PersistenceService {

    List<User> getUserByCriteria(String column, String valueOfTheColumn);

}