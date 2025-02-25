package com.nuketree3.example;

import com.nuketree3.example.client.Client;
import com.nuketree3.example.server.Server;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        new Client(server);
        new Client(server);
    }
}