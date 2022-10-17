package client.views.extraObjectsView;

import client.core.ViewModelFactory;
import client.core.viewHandler.View;
import client.core.viewHandler.ViewHandler;

public interface ExtraController {
    void init(ViewHandler viewHandler);
    void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory);
}
