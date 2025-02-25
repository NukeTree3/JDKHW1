package com.nuketree3.example.client;

import com.nuketree3.example.server.UserMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ClientView{

    private ClientController clientController;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;


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

    public ClientGUI(ClientController clientController) {

        this.clientController = clientController;

        setTitle("ClientGUI");
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
                showMessage();
            }
        });


        loginConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    if(clientController.getServerStatus() && clientController.checkConnection(ipField.getText(), Integer.parseInt(portField.getText()))){
                        clientController.setLoginStatus(true);
                        clientController.setUsername(usernameField.getText());
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
        loginConfirmButton.setVisible(!clientController.isLogin());


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientController.getServerStatus()){
                    clientController.sendMessage(messageField.getText());
                    messageField.setText("");
                }
            }
        });
        sendButton.setVisible(clientController.isLogin());

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



    public void updateVisibility(){
        ipField.setVisible(!clientController.isLogin());
        portField.setVisible(!clientController.isLogin());
        usernameField.setVisible(!clientController.isLogin());
        passwordField.setVisible(!clientController.isLogin());
        messageField.setVisible(clientController.isLogin());
        sendButton.setVisible(clientController.isLogin());
        loginConfirmButton.setVisible(!clientController.isLogin());
        revalidate();
        repaint();
    }

    @Override
    public void showMessage() {
        while (clientController.isLogin()) {
            messageArea.setText(clientController.treadToListener());
        }
    }
}
