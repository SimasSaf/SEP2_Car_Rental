package server.DataAccess;

import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public interface UsersData {
  User login(Email email, Password password);
  void storeUser(User user);
  void updateUser(int userID, User newUser);
  void deleteUser(int userID);
  List<User> getUserList();
  public boolean isAvailable(Email email);
  User getUserById(int userID);
  User getUserByEmail(Email email);
}
