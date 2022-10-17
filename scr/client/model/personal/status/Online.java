package client.model.personal.status;

import client.model.personal.Personal;

public class Online implements Status{

    @Override
    public Stat getStatus() {
        return Stat.ONLINE;
    }

    @Override
    public void logOut(Personal personal) {
        personal.setOffline();
    }

    @Override
    public void logIn(Personal personal) {
        personal.setOnline();

    }

}
