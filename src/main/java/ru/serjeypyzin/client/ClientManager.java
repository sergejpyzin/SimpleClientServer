package ru.serjeypyzin.client;

public interface ClientManager {

    void sendMessage(String message);
    void connectedToServer();
    void disconnectedFromServer();
}
