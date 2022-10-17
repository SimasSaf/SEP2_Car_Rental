package client.model.personal.status;

import client.model.personal.Personal;

public class Offline implements Status {
    @Override
    public Stat getStatus() {
        return Stat.OFFLINE;
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
