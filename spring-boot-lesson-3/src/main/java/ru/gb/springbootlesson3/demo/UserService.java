package ru.gb.springbootlesson3.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Long, User> userMap = new HashMap<>();
    private Long nextId = 1L;

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public User getUserById(Long id) {
        return userMap.get(id);
    }

    public User createUser(User user) {
        Long id = nextId++;
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    public User updateUser(Long id, User user) {
        if (userMap.containsKey(id)) {
            user.setId(id);
            userMap.put(id, user);
            return user;
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userMap.remove(id);
    }
}
