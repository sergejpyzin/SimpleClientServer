package ru.serjeypyzin.server;

import ru.serjeypyzin.client.Client;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame implements ServerManager{
    private final static int WIDTH = 300;
    private final static int HEIGHT = 450;
    private JTextArea logArea;
    private final Server server;


    public ServerWindow(){
        server = new Server();
        serverWindowSetting();
    }

    public void serverWindowSetting(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Server");

        createWindow();

        setVisible(true);
    }

    private JPanel createButtonPanel () {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> {
            if (server.isWork()) {
                logArea.setText("Сервер в работе!" + "\n");
            } else {
                server.setRunning(true);
                logArea.setText("Запуск сервера" + "\n");
            }
        });

        JButton stopButton = new JButton("STOP");
        stopButton.addActionListener(e -> {
            if (server.isWork()) {
                logArea.setText("Остановка сервера..." + "\n");
                disconnectUser();
                server.setRunning(false);
            } else {
                logArea.setText("Сервер уже остановлен" + "\n");
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        return buttonPanel;
    }

    private void createWindow (){
        logArea = new JTextArea();
        add(logArea);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    public void addTextInAreaLog(String text){
        logArea.append(text + "\n");
    }

    public void messageProcessing(String message) {
        if (!server.getIsRunning()){
            return;
        }else {
            server.SaveLogToFile(message);
            server.sendAllClients(message);
        }
    }

    @Override
    public String getLogInfoFromFile() {
        return server.getLog();
    }

    @Override
    public boolean connectUser(Client client) {
        return server.connectedUser(client);
    }

    @Override
    public void disconnectUser() {
        server.disconnectAllUsers();
    }

}
