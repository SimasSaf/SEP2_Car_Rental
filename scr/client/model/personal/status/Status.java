package client.model.personal.status;

import client.model.personal.Personal;

public interface  Status {
      Stat getStatus();
      void logOut(Personal personal);
      void logIn(Personal personal);

}
