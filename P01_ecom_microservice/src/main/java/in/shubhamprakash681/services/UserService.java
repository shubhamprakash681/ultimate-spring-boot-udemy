package in.shubhamprakash681.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.shubhamprakash681.models.User;

@Service
public class UserService {
    private ArrayList<User> userList = new ArrayList<>();

    public List<User> getUsers() {
        return userList;
    }

    public User getUserDetails(long id) {
        return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public List<User> addUser(User user) {
        user.setId(userList.size() + 1);
        userList.add(user);
        return userList;
    }

    public User updateUser(long id, User user) {
        User existingUser = getUserDetails(id);

        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());

            return existingUser;
        }

        return null;
    }
}
