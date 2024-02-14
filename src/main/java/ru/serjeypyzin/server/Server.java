package ru.serjeypyzin.server;

import ru.serjeypyzin.client.Client;
import ru.serjeypyzin.repository.MessageFileManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server{

    MessageFileManager fileManager;
    private final List<Client> clientList;
    private boolean isRunning;

    public Server() {
        this.fileManager = new MessageFileManager();
        clientList = new ArrayList<>();
    }

    public boolean getIsRunning(){
        return isRunning;
    }

    public boolean isWork (){
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }


    public boolean connectedUser(Client client) {
        if (!isRunning){
            return false;
        }
        clientList.add(client);
        return true;
    }

    public void disconnectAllUsers() {
        Iterator<Client> clientIterator = clientList.iterator();
        while (clientIterator.hasNext()){
            Client client = clientIterator.next();
            clientIterator.remove();
            client.disconnectFromServer();
        }
    }

    public String getLog() {
        return fileManager.ReadLogFromFile();
    }

    public void SaveLogToFile(String message) {
        fileManager.SaveLogToFile(message);
    }

    public void sendAllClients(String message) {
        clientList.forEach(client -> client.addedInfo(message));
    }
}
