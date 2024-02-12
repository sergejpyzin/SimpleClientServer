package ru.serjeypyzin.server;

import javax.swing.*;
import java.awt.*;

public class ServerWindow extends JFrame {
    private final static int WIDTH = 300;
    private final static int HEIGHT = 450;

    private final Server server = new Server();

    public ServerWindow(){
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
                System.out.println("Сервер в работе!");
            } else {
                server.setRunning(true);
                System.out.println("Запуск сервера");
            }
        });

        JButton stopButton = new JButton("STOP");
        stopButton.addActionListener(e -> {
            if (server.isWork()) {
                System.out.println("Остановка сервера...");
                server.disconnectAllUser();
                server.setRunning(false);
            } else {
                System.out.println("Сервер уже остановлен");
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        return buttonPanel;
    }

    private void createWindow (){
        JTextArea logArea = new JTextArea();
        add(logArea);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }
}
