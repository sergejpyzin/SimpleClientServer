package ru.serjeypyzin.server;

import ru.serjeypyzin.client.Client;

public interface ServerManager {

    boolean connectUser (Client client);
    void disconnectUser ();
    void messageProcessing(String message);
    String getLogInfoFromFile();
}
