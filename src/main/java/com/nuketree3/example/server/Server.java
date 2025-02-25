package com.nuketree3.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Server extends JFrame {

    private static final Backup backup = new Backup("backup.txt");

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private boolean started = false;

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;

    private JTextArea statusArea;
    private JTextArea messageArea;

    private ArrayList<UserMessage> userMessages;

    public Server() {
        setTitle("Server");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        statusArea = new JTextArea();
        messageArea = new JTextArea();
        userMessages = new ArrayList<>();

        setResizable(false);

        JButton loginConfirm = new JButton("Start");
        loginConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                started = true;
                statusArea.setText("Starting server...");
                statusArea.setVisible(true);
                revalidate();
                repaint();
            }
        });
        loginConfirm.setVisible(true);

        JButton send = new JButton("Stop");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                started = false;
                statusArea.setText("Stopping server...");
                revalidate();
                repaint();
            }
        });
        send.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(loginConfirm);
        panel.add(send);
        panel.add(statusArea);
        add(messageArea, BorderLayout.NORTH);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void addUserMessage(UserMessage userMessage) {
        backup.backup(userMessage.toString());
        userMessages.add(userMessage);
        printMessages(userMessage.toString());
        revalidate();
        repaint();
    }

    public ArrayList<UserMessage> getUserMessages() {
        return userMessages;
    }

    public boolean isStarted() {
        return started;
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return PORT;
    }

    public void printMessages(String message) {
        messageArea.append(message);
    }
}
