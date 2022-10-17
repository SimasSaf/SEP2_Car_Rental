package server.model.user;



import server.DataAccess.DataBaseUserHandler;
import server.DataAccess.UsersData;
import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public class UsersManagerImp implements UsersManager {

    UsersData usersData = new DataBaseUserHandler(); // Change this. create in another place


    @Override
    public User login(Email email, Password password) {
        return usersData.login(email, password);
    }

    @Override
    public boolean isAvailable(Email email) {
        return usersData.isAvailable(email);
    }

    @Override
    public void createUser(User user) {

        usersData.storeUser(user);
    }

    @Override
    public void editUser(int userID, User user) {
        usersData.updateUser(userID, user);

    }

    @Override
    public void deleteUser(int userID) {
        usersData.deleteUser(userID);
    }


    public List<User> getUsersList() {
        return usersData.getUserList();
    }

}


