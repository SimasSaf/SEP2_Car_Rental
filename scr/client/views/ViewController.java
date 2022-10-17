package client.views;

import client.core.viewHandler.ViewHandler;
import client.core.ViewModelFactory;

public interface ViewController {
  void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);

}
