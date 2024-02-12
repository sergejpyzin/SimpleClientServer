package ru.serjeypyzin.server;

import ru.serjeypyzin.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server implements ServerManager{

    private final List<Client> clientList = new ArrayList<>();

    private boolean isRunning;

    public boolean isWork (){
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }



    @Override
    public boolean connectUser(Client client) {
        if (!isRunning){
            return false;
        }
        clientList.add(client);
        return true;

    }

    @Override
    public void disconnectUser(Client client) {
        clientList.remove(client);
    }
}
