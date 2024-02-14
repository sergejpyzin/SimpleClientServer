package ru.serjeypyzin;

import ru.serjeypyzin.client.ClientWindow;
import ru.serjeypyzin.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}