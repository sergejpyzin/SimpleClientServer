package ru.serjeypyzin.server;

import ru.serjeypyzin.client.Client;

public interface ServerManager {

    boolean connectUser (Client client);
    void disconnectUser (Client client);



}
