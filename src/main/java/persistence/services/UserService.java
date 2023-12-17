package persistence.services;

import org.hibernate.Session;
import org.hibernate.query.Query;
import persistence.models.User;

import java.util.List;

import static persistence.configuration.Persistence.getSession;

public class UserService implements PersistenceService {

    @Override
    public List<User> getUserByCriteria(String column, String valueOfTheColumn) {
        try (Session session = getSession().openSession()) {
            String sql = "SELECT * FROM \"AspNetUsers\" WHERE \"" + column + "\" = :valueOfTheColumn";
            Query<User> query = session.createNativeQuery(sql, User.class);
            query.setParameter("valueOfTheColumn", valueOfTheColumn);

            return query.list();
        }
    }
}