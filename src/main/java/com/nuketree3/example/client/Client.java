package com.nuketree3.example.client;

import com.nuketree3.example.server.Server;
import com.nuketree3.example.server.UserMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame {

    private final Server server;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private boolean isLogin = false;

    private String username = "";

    private JTextField ipField;
    private JTextField portField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField messageField;

    private JButton loginConfirmButton;
    private JButton sendButton;

    private JPanel settingsPanel;
    private JPanel messagePanel;

    private JTextArea messageArea;

    public Client(Server server) {
        this.server = server;

        setTitle("Client");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setResizable(false);

        messagePanel = new JPanel(new GridLayout(1, 2));
        settingsPanel = new JPanel(new GridLayout(2, 2));

        ipField = new JTextField("127.0.0.1");
        portField = new JTextField("8080");
        usernameField = new JTextField("username");
        passwordField = new JTextField("password");
        messageField = new JTextField();

        messageArea = new JTextArea();

        loginConfirmButton = new JButton("Login");
        sendButton = new JButton("Send");

        updateVisibility();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (isLogin) {
                    if(server.isStarted()){
                        StringBuilder stringBuilder = new StringBuilder();
                        for (UserMessage userMessage : server.getUserMessages()){
                            stringBuilder.append(userMessage.toString());
                        }
                        messageArea.setText(stringBuilder.toString());
                    }
                    else {
                        messageArea.setText("Проблема подключения к серверу");
                    }
                }
            }
        });


        loginConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(server.isStarted() && checkConnection(server, ipField.getText(), Integer.parseInt(portField.getText()))){
                        isLogin = true;
                        System.out.println("Login");
                        username = usernameField.getText();
                        messageArea.setText("");
                        t1.start();
                    }
                    else{
                        messageArea.setText("Ошибка подключения");
                    }
                    updateVisibility();
                }
                catch (NullPointerException ex){
                    messageArea.setText("Ошибка подключения");
                    updateVisibility();
                }

            }
        });
        loginConfirmButton.setVisible(!isLogin);


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(server.isStarted()){
                    server.addUserMessage(new UserMessage(username, messageField.getText()));
                    messageField.setText("");
                }
            }
        });
        sendButton.setVisible(isLogin);

        settingsPanel.add(ipField);
        settingsPanel.add(portField);
        settingsPanel.add(usernameField);
        settingsPanel.add(passwordField);
        settingsPanel.add(loginConfirmButton);

        messagePanel.add(messageField);
        messagePanel.add(sendButton);

        add(messageArea, BorderLayout.CENTER);
        add(BorderLayout.SOUTH, messagePanel);
        add(BorderLayout.NORTH, settingsPanel);

        setVisible(true);
    }

    public boolean checkConnection(Server server, String ip, int port) {
        return server.getIP().equals(ip) && server.getPort() == port;
    }

    public void updateVisibility(){
        ipField.setVisible(!isLogin);
        portField.setVisible(!isLogin);
        usernameField.setVisible(!isLogin);
        passwordField.setVisible(!isLogin);
        messageField.setVisible(isLogin);
        sendButton.setVisible(isLogin);
        loginConfirmButton.setVisible(!isLogin);
        revalidate();
        repaint();
    }
}
