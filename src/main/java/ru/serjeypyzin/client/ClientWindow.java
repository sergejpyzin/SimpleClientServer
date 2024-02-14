package ru.serjeypyzin.client;

import ru.serjeypyzin.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientWindow extends JFrame implements ClientManager {
    private final static int WIDTH = 350;
    private final static int HEIGHT = 400;

    private final Client client;
    private JTextArea messageArea;
    private JTextField loginTextField;
    private JTextField sendField;

    public ClientWindow(ServerWindow serverWindow) {
        windowSetting();
        client = new Client(serverWindow, this);
    }


    public void windowSetting() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Client");

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMessageArea());
        add(createSendPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JScrollPane createMessageArea() {
        messageArea = new JTextArea();
        messageArea.setEditable(false);

        JScrollPane jScrollPane = new JScrollPane(messageArea);
        add(jScrollPane, BorderLayout.CENTER);
        return jScrollPane;
    }


    private JPanel createWebSettingPanel() {
        JPanel webSettingPanel = new JPanel(new GridLayout(1, 2));

        JTextField ipTextField = new JTextField("192.168.0.1");
        JTextField portTextField = new JTextField("8080");

        webSettingPanel.add(ipTextField);
        webSettingPanel.add(portTextField);

        return webSettingPanel;
    }

    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(1, 3));

        loginTextField = new JTextField("Sergey");
        JPasswordField passwordField = new JPasswordField("123456");

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            setMessageArea("Подключение к серверу" + "\n");
            connectedToServer();
        });

        loginPanel.add(loginTextField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        return loginPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(2, 1));

        headerPanel.add(createWebSettingPanel());
        headerPanel.add(createLoginPanel());

        return headerPanel;
    }

    private JPanel createSendPanel() {
        JPanel sendPanel = new JPanel(new BorderLayout());
        sendField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(sendField.getText());
                    sendField.setText("");
                }
            }
        });

        sendButton.addActionListener(e -> {
            sendMessage(sendField.getText());
            sendField.setText("");
        });

        sendPanel.add(sendField);
        sendPanel.add(sendButton, BorderLayout.EAST);
        return sendPanel;
    }


    public String getClientName() {
        return loginTextField.getText();
    }

    public void setMessageArea(String info) {
        if (!isMessageExists(info)) {
            messageArea.append(info);
        }
    }

    private boolean isMessageExists(String message) {
        String[] lines = messageArea.getText().split("\\n");
        for (String line : lines) {
            if (line.equals(message)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void sendMessage(String message) {
        if (client.getIsConnect()) {
            setMessageArea(client.createMessage(message) + "\n");
            client.sendMessageFromClient(message);
        } else {
            setMessageArea("Отправка сообщений невозможно! Сервер не подключен!");
        }
    }

    @Override
    public void connectedToServer() {
        client.connectToServer();
    }

    @Override
    public void disconnectedFromServer() {
        client.disconnectFromServer();
    }
}
