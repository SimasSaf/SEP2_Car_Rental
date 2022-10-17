package shared.networking;

import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface RMIUserServer extends RMIServer{
    User login(Email email, Password password) throws RemoteException;
    boolean isAvailable(Email email) throws RemoteException;
    void createUser(User user) throws RemoteException;
    void editUser(int userID, User user) throws RemoteException;
    void deleteUser(int userID) throws RemoteException;
    List<User> getUsersList() throws RemoteException;
}
