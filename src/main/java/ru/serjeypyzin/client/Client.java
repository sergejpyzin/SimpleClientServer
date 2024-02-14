package ru.serjeypyzin.client;

import ru.serjeypyzin.server.ServerWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private final ServerWindow serverWindow;
    private final ClientWindow clientWindow;
    private boolean isConnect;
    private final String clientName;

    public Client(ServerWindow serverWindow, ClientWindow clientWindow) {
        this.serverWindow = serverWindow;
        this.clientWindow = clientWindow;
        clientName = clientWindow.getClientName();
    }

    public void setIsConnect (boolean value){
        isConnect = value;
    }

    public boolean getIsConnect(){
        return isConnect;
    }

    public void addedInfo(String info) {
        clientWindow.setMessageArea(info);
    }

    public String createMessage(String text) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        return "[" + dateFormat.format(new Date()) + "] " + clientName + ": " + text;
    }

    public void sendMessageFromClient(String message) {
            String text = createMessage(message);
            serverWindow.messageProcessing(text);
            serverWindow.addTextInAreaLog(text);
    }

    public void connectToServer() {
        if (serverWindow.connectUser(this)) {
            addedInfo("Вы успешно подключились!" + "\n");
            setIsConnect(true);
            String logInfo = serverWindow.getLogInfoFromFile();
            if (logInfo != null && !logInfo.isEmpty()) {
                addedInfo(logInfo);
                addedInfo("\n");
            } else {
                addedInfo("");
            }
        } else {
            addedInfo("Подключение не удалось" + "\n");
        }
    }

    public void disconnectFromServer() {
        if (isConnect) {
            setIsConnect(false);
            addedInfo("Вы отключились от сервера");
        } else {
            addedInfo("Подключение к серверу отсутствует");
        }
    }
}

