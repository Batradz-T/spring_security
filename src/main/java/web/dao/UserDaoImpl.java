package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    private final  Map<String, User> userMap = new HashMap<>();

    public UserDaoImpl() {
        userMap.put("admin",new User(1L, "admin", "admin", Collections.singleton(new Role(1L, "ROLE_ADMIN"))));
        userMap.put("user",new User(2L, "user", "user", Collections.singleton(new Role(2L, "ROLE_USER"))));
    }



    @Override
    public User getUserByName(String name) {
        if (!userMap.containsKey(name)) {
            return null;
        }

        return userMap.get(name);
    }
}

