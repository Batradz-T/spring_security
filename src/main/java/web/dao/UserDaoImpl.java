package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private final  Map<String, User> userMap = new HashMap<>();

//    public UserDaoImpl() {
//        userMap.put("admin",new User(1L, "admin", "admin", Collections.singleton(new Role(1L, "ROLE_ADMIN"))));
//        userMap.put("user",new User(2L, "user", "user", Collections.singleton(new Role(2L, "ROLE_USER"))));
//    }
//
//    @Override
//    public User getUserByName(String name) {
//        if (!userMap.containsKey(name)) {
//            return null;
//        }
//
//        return userMap.get(name);
//    }

    @Autowired
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("from User").getResultList();
        entityManager.getTransaction().commit();
        return users;
    }

    public User getUserById (Long id) {
        Query query = entityManager.createQuery("FROM User where id=:userId");
        query.setParameter("userId", id);
        return (User)query.getResultList().get(0);

    }

    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public void update(int id, User updateUser) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE User set name = :name WHERE id = :userId");
        query.setParameter("name", updateUser.getName()).setParameter("userId", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM User WHERE id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("FROM User where name=:userName");
        query.setParameter("userName", name);
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return (User)query.getResultList().get(0);
    }
}

