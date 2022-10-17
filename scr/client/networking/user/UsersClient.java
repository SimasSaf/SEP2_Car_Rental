package client.networking.user;

import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;
import shared.util.Subject;

import java.util.List;

public interface UsersClient extends Subject {
    User login(Email email, Password password);
    void createUser(User user);
    void editUser(int userID, User user);
    void deleteUser(int userID);
    boolean existUser(Email email);
    List<User> getUsersList();
}
