package com.nuketree3.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerView {

    private ServerController controller;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JTextArea statusArea;
    private JTextArea messageArea;

    public ServerGUI() {
        setTitle("ServerGUI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        statusArea = new JTextArea();
        messageArea = new JTextArea();

        setResizable(false);

        JButton loginConfirm = new JButton("Start");
        loginConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setServerStatus(true);
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
                controller.setServerStatus(false);
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


    public void setServerController(ServerController serverController) {
        this.controller = serverController;
    }

    @Override
    public void addMessage(String message) {
        messageArea.append(message);
        revalidate();
        repaint();
    }
}
