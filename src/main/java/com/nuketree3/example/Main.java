package com.nuketree3.example;

import com.nuketree3.example.client.ClientController;
import com.nuketree3.example.client.ClientGUI;
import com.nuketree3.example.server.ServerController;
import com.nuketree3.example.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        ServerController serverController = new ServerController();
        serverController.setServerView(serverGUI);
        serverGUI.setServerController(serverController);

        ClientController clientController1 = new ClientController();
        ClientGUI clientGUI1 = new ClientGUI(clientController1);
        clientController1.setServer(serverController);

        ClientController clientController2 = new ClientController();
        ClientGUI clientGUI2 = new ClientGUI(clientController2);
        clientController2.setServer(serverController);
    }
}