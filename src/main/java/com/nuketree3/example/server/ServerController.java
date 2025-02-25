package com.nuketree3.example.server;

import java.util.ArrayList;

public class ServerController {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;

    private final Backup backup = new Backup("backup.txt");

    private ArrayList<UserMessage> userMessages;

    private ServerView serverView;

    private boolean started = false;

    public ServerController() {
        userMessages = new ArrayList<>();
    }

    public void setServerStatus(boolean started) {
        this.started = started;
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

    public void addUserMessage(UserMessage userMessage) {
        backup.doBackup(userMessage.toString());
        userMessages.add(userMessage);
        serverView.addMessage(userMessage.toString());
    }

    public ArrayList<UserMessage> getUserMessages() {
        return userMessages;
    }

    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }
}
