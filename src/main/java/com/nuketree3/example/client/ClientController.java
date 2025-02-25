package com.nuketree3.example.client;

import com.nuketree3.example.server.ServerController;
import com.nuketree3.example.server.UserMessage;

public class ClientController {

    private ServerController serverController;
    private boolean isLogin = false;
    private String username = "";



    public void setServer(ServerController serverController) {
        this.serverController = serverController;
    }

    public boolean checkConnection(String ip, int port) {
        return serverController.getIP().equals(ip) && serverController.getPort() == port;
    }

    public String treadToListener() {
        StringBuilder stringBuilder = new StringBuilder();
        if(serverController.isStarted()){
            for (UserMessage userMessage : serverController.getUserMessages()){
                stringBuilder.append(userMessage.toString());
            }
            return stringBuilder.toString();
        }
        else {
            return "Проблема подключения к серверу";
        }
    }

    public void setLoginStatus(boolean isLogin) {
        this.isLogin = isLogin;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public boolean isLogin() {
        return isLogin;
    }
    public String getUsername() {
        return username;
    }
    public boolean getServerStatus(){
        return serverController.isStarted();
    }

    public void sendMessage(String userMessage) {
        serverController.addUserMessage(new UserMessage(username, userMessage));;
    }
}
