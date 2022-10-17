package server.model.user;

import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public interface UsersManager {
    User login(Email email, Password password);
    boolean isAvailable(Email email);
    void createUser(User user);
    void editUser(int userID, User user);
    void deleteUser(int userID);
   List<User> getUsersList();
}
