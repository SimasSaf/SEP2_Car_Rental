package client.networking.user;

import client.networking.Connect;
import shared.networking.ClientCallback;
import shared.networking.RMIUserServer;
import shared.transferObjects.user.Email;
import shared.transferObjects.user.Password;
import shared.transferObjects.user.User;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class RMIUsersClient implements UsersClient, ClientCallback {

    private RMIUserServer userServer;
    private PropertyChangeSupport support;
    private Connect connect;

    public RMIUsersClient(Connect connect) {
        this.connect = connect;
        userServer = connect.getUserServer();
    }


    @Override
    public User login(Email email, Password password) {
        try {
            return userServer.login(email, password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        try {
            userServer.createUser(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(int userID, User user) {
        try {
            userServer.editUser(userID, user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(int userID)  {
        try {
            userServer.deleteUser(userID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existUser(Email email) {
        try {
            return (!userServer.isAvailable(email));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getUsersList() {
        try {
            return userServer.getUsersList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


    //Start ClientCallback

    @Override
    public void updateChanges() throws RemoteException {
        support.firePropertyChange("Test", null, null);
    }

    @Override
    public void addListener(String evt, PropertyChangeListener listener) {
        support.addPropertyChangeListener(evt, listener);
    }

    @Override
    public void removeListener(String evt, PropertyChangeListener listener) {
        support.removePropertyChangeListener(evt, listener);
    }


}
